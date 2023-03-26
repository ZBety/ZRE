package com.example.ruleEngine.aspect;

import com.example.ruleEngine.domain.Audit;
import com.example.ruleEngine.domain.AuditAOP;
import com.example.ruleEngine.domain.ResponseModel;
import com.example.ruleEngine.repositories.AuditRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
public class AuditAspect {

    @Autowired
    private AuditRepository auditRepo;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.example.ruleEngine.domain.AuditAOP)")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
        String params = Arrays.toString(joinPoint.getArgs());
        String remoteAddress = request.getRemoteAddr();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuditAOP auditAnnotation = method.getAnnotation(AuditAOP.class);
        String action = auditAnnotation.action();

        ResponseEntity<ResponseModel> result = (ResponseEntity<ResponseModel>) joinPoint.proceed();

        Audit audit = new Audit(username,action,remoteAddress,params);
        if (Objects.requireNonNull(result.getBody()).getCode() == 200) {
            audit.setResult("success");
        } else {
            audit.setResult("failed");
        }
        auditRepo.save(audit);
        return result;
    }
}
