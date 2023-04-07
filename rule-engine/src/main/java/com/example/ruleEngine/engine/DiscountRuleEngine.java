package com.example.ruleEngine.engine;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.domain.actor.ActorFactory;
import com.example.ruleEngine.domain.actor.rules.RuleActor;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.rules.RuleData;
import org.sdk.RunningState;
import org.sdk.template.NodeTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountRuleEngine implements RuleEngine<RuleData>{

    private static final Logger logger = LoggerFactory.getLogger(DiscountRuleEngine.class);
    private String name;

    private RuleEngineContext ctx;

    private RuleEngineConfiguration config;

    private RunningState runningState = RunningState.UNKNOWN;
    private HashMap<String, RuleActor<?,?>> actors = new HashMap<>();

    private List<NodeTemplate> getLoadedRules() {
        return actors.values().stream()
                .map(RuleActor::getRuleTemplate)
                .collect(Collectors.toList());
    }

    public DiscountRuleEngine(String name, ApplicationContext ctx, RuleEngineConfiguration config) {
        this.name = name;
        this.ctx = new RuleEngineContext(name,ctx,config);
        this.config = config;
    }

    @Override
    public RunningState status() {
        return null;
    }

    @Override
    public void start() {
        if (getLoadedRules().isEmpty()){
            logger.warn(ctx.getName() + "引擎未启动，加载规则为空");
//            throw new Exception("DiscountRuleEngine 未包含任何有效规则，无法启动！");
        }

        actors.forEach((key, actor) -> {
            actor.start();
        });
        this.runningState = RunningState.RUNNING;
    }

    @Override
    public void stop() {
        if(RunningState.RUNNING != this.runningState) {
            logger.warn(ctx.getName() + "引擎未运行！");
            return;
        }
        actors.forEach( (key, actor) -> {
//            actor
        });
        this.runningState = RunningState.STOPPED;
    }

    @Override
    public void loadRule(List<RuleData> rules) {
        rules.forEach(this::loadRule);
    }

    @Override
    public void loadRule(RuleData rule) {
        System.out.println("success load rule: " + rule);
        loadActor(rule);
    }


    private void loadActor(RuleData rule){
        RuleActor<?,?> actor = ActorFactory.getRuleActor(ctx, rule);
        actors.put(rule.getRuleId(), actor);
    }

    @Override
    public List<RuleData> getLoadedRule() {
        /**
         * TODO
         */
        return null;
    }

    @Override
    public void unloadRule(List<RuleData> rules) {
//        rules.forEach((item) -> {
//            ReactorDiagramRuleActor targetRule = actors.get(item.getId());
//            if (targetRule!=null)
//                unloadRule(targetRule.getRuleModel());
//        });
    }

    @Override
    public void unloadRule(RuleData rule) {
//        ReactorDiagramRuleActor removedRule = this.actors.remove(rule.getId());
//        if (removedRule != null) {
//            stop();
//            logger.debug("规则'" + removedRule.getId() + "'已卸载");
//        }
    }

    @Override
    public void unloadRule(String ruleId) {

    }

    @Override
    public void reStart() {

    }

    @Override
    public void reload(DiagramRuleModel model) {

    }

    @Override
    public void reload(String diagramId) {

    }

    @Override
    public RuleEngineContext getContext() {
        return ctx;
    }

    public RunningState getRunningState() {
        return runningState;
    }

    public void setRunningState(RunningState runningState) {
        this.runningState = runningState;
    }

    public RuleActor<?, ?> getActor(String id) {
        return actors.get(id);
    }
}
