package com.example.ruleEngine.engine;

import com.example.ruleEngine.config.RuleEngineConfiguration;
import com.example.ruleEngine.domain.NodeTemplate;
import com.example.ruleEngine.domain.actor.ActorFactory;
import com.example.ruleEngine.domain.actor.RunningState;
import com.example.ruleEngine.domain.actor.diagramRuleActor.DiagramRuleActor;
import com.example.ruleEngine.domain.actor.rules.RuleActor;
import com.example.ruleEngine.domain.io.OutputSlot;
import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.domain.rules.RuleData;
import com.example.ruleEngine.msg.DataMsg;
import com.example.ruleEngine.service.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiagramRuleEngine implements RuleEngine<DiagramRuleModel>{

    private static final Logger logger = LoggerFactory.getLogger(DiagramRuleEngine.class);
    private String name;

    private RuleEngineContext ctx;

    private RuleEngineConfiguration config;
    private RunningState runningState = RunningState.UNKNOWN;

    private HashMap<String, DiagramRuleActor> actors = new HashMap<>();
    private OutputSlot<DataMsg> match = new OutputSlot<>();

    private List<DiagramRuleModel> getLoadedRules() {
        return actors.values().stream()
                .map(DiagramRuleActor::getRuleModel)
                .collect(Collectors.toList());
    }

    public DiagramRuleEngine(String name, ApplicationContext ctx, RuleEngineConfiguration config) {
        this.name = name;
        this.ctx = new RuleEngineContext(name,ctx,config);
        this.config = config;
    }

    @Override
    public RunningState status() {
        return this.runningState;
    }

    @Override
    public void start() {
        if (getLoadedRules().isEmpty()){
            logger.warn(ctx.getName() + "引擎未启动，加载规则为空");
//            throw new Exception("DiscountRuleEngine 未包含任何有效规则，无法启动！");
        }

        actors.forEach((key, actor) -> {
            actor.start();
            logger.info("diagram " + key + " started!");
        });
        this.actors.values().forEach(item -> {
            match.connect(item.getBegin().getInputs());
        });
        this.runningState = RunningState.RUNNING;
    }

    @Override
    public void stop() {
        if(RunningState.RUNNING != this.runningState) {
            logger.warn(ctx.getName() + "引擎未运行！");
            return;
        }
        actors.forEach( (key, actor) -> actor.stop());
        this.runningState = RunningState.STOPPED;
    }



    @Override
    public void loadRule(List<DiagramRuleModel> rules) {
        rules.forEach(this::loadRule);
    }

    @Override
    public void loadRule(DiagramRuleModel rule) {
        if (actors.containsKey(rule.getId())) {
            unloadRule(rule);
        }
        if (!rule.getDisabled()) {
            loadActor(rule);
        }else {
            logger.warn("规则 " + rule.getId() + " 已被禁用！");
        }
    }


    private void loadActor(DiagramRuleModel rule){
        DiagramRuleActor actor = ActorFactory.getDiagramRuleActor(ctx, rule);
        actors.put(rule.getId(), actor);
    }

    private Map<String, Object> getActors() {
        return actors.values()
                .stream()
                .collect(Collectors.toMap(DiagramRuleActor::getId, Function.identity()));
    }

    @Override
    public List<DiagramRuleModel> getLoadedRule() {
        /**
         * TODO
         */
        return null;
    }

    @Override
    public void unloadRule(List<DiagramRuleModel> rules) {
//        rules.forEach((item) -> {
//            ReactorDiagramRuleActor targetRule = actors.get(item.getId());
//            if (targetRule!=null)
//                unloadRule(targetRule.getRuleModel());
//        });
    }

    @Override
    public void unloadRule(DiagramRuleModel rule) {
        DiagramRuleActor removedRule = this.actors.remove(rule.getId());
        if (removedRule != null) {
            stop();
            logger.debug("规则'" + removedRule.getId() + "'已卸载");
        }
    }

    @Override
    public void unloadRule(String ruleId) {
        DiagramRuleActor removedRule = this.actors.remove(ruleId);
        if (removedRule != null) {
            stop();
            logger.debug("规则'" + removedRule.getId() + "'已卸载");
        }
    }

    @Override
    public void reStart() {

    }

    @Override
    public void reload(DiagramRuleModel model) {
        unloadRule(model);
        loadRule(model);
        if (getLoadedRules().isEmpty()){
            logger.warn(ctx.getName() + "引擎未启动，加载规则为空");
//            throw new Exception("DiscountRuleEngine 未包含任何有效规则，无法启动！");
        }
        actors.get(model.getId()).start();
        match.clear();
        this.actors.values().forEach(item -> {
            match.connect(item.getBegin().getInputs());
        });
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

    public DiagramRuleActor getActor(String id) {
        return actors.get(id);
    }

    public void execute(Object data) {
        match.send(new DataMsg(data));
    }
}
