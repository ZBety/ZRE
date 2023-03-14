//package com.example.ruleEngine.engine;
//
//import com.example.ruleEngine.config.RuleEngineConfiguration;
//import com.example.ruleEngine.domain.actor.ActorFactory;
//import com.example.ruleEngine.domain.actor.RunningState;
//import com.example.ruleEngine.domain.actor.diagramRuleActor.ReactorDiagramRuleActor;
//import com.example.ruleEngine.domain.layout.DiagramRuleModel;
//import com.example.ruleEngine.domain.rules.RuleData;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class DiagramRuleEngine implements RuleEngine<RuleData>{
//
//    private static final Logger logger = LoggerFactory.getLogger(DiagramRuleEngine.class);
//    String name;
//
//    RuleEngineContext ctx;
//
//    RuleEngineConfiguration config;
//
//    private RunningState runningState = RunningState.UNKNOWN;
//    private HashMap<String, ReactorDiagramRuleActor> actors = new HashMap<>();
//
//    private List<DiagramRuleModel> getLoadedRules() {
//        return (List<DiagramRuleModel>) actors.values().stream().map((it) -> it.getRuleModel() );
//    }
//
//    public DiagramRuleEngine(String name, ApplicationContext ctx, RuleEngineConfiguration config) {
//        this.name = name;
//        this.ctx = new RuleEngineContext(name,ctx,config);
//        this.config = config;
//    }
//
//    @Override
//    public RunningState status() {
//        return null;
//    }
//
//    @Override
//    public void start() {
//        if (getLoadedRules().isEmpty()){
//            logger.warn(ctx.getName() + "引擎未启动，加载规则为空");
////            throw new Exception("DiscountRuleEngine 未包含任何有效规则，无法启动！");
//        }
//
//        actors.forEach((key, actor) -> {
//            actor.start();
//        });
//        this.runningState = RunningState.RUNNING;
//    }
//
//    @Override
//    public void stop() {
//        if(RunningState.RUNNING != this.runningState) {
//            logger.warn(ctx.getName() + "引擎未运行！");
//            return;
//        }
//        actors.forEach( (key, actor) -> {
////            actor
//        });
//        this.runningState = RunningState.STOPPED;
//    }
//
//    @Override
//    public void loadRule(List<DiagramRuleModel> rules) {
//        rules.forEach(this::loadRule);
//    }
//
//    @Override
//    public void loadRule(List<RuleData> rules) {
//        rules.forEach(this::loadRule);
//    }
//
//    @Override
//    public void loadRule(RuleData rule) {
//        System.out.println("success load rule: " + rule);
////        loadActor(rule);
//    }
//
//    @Override
//    public void loadRule(DiagramRuleModel rule) {
//        System.out.println("success load rule: " + rule);
//        loadActor(rule);
//    }
//
//    private void loadActor(DiagramRuleModel rule){
//        ReactorDiagramRuleActor actor = ActorFactory.getDiagramRuleActor(ctx, rule);
//        actors.put(rule.getId(), actor);
//    }
//
//    @Override
//    public List<DiagramRuleModel> getLoadedRule() {
//        /**
//         * TODO
//         */
//        return null;
//    }
//
//    @Override
//    public void unloadRule(List<DiagramRuleModel> rules) {
//        rules.forEach((item) -> {
//            ReactorDiagramRuleActor targetRule = actors.get(item.getId());
//            if (targetRule!=null)
//                unloadRule(targetRule.getRuleModel());
//        });
//    }
//
//    @Override
//    public void unloadRule(DiagramRuleModel rule) {
//        ReactorDiagramRuleActor removedRule = this.actors.remove(rule.getId());
//        if (removedRule != null) {
//            stop();
//            logger.debug("规则'" + removedRule.getId() + "'已卸载");
//        }
//    }
//
//    @Override
//    public RuleEngineContext getContext() {
//        return ctx;
//    }
//
//    public RunningState getRunningState() {
//        return runningState;
//    }
//
//    public void setRunningState(RunningState runningState) {
//        this.runningState = runningState;
//    }
//
//    public HashMap<String, ReactorDiagramRuleActor> getActors() {
//        return actors;
//    }
//
//    public void setActors(HashMap<String, ReactorDiagramRuleActor> actors) {
//        this.actors = actors;
//    }
//}
