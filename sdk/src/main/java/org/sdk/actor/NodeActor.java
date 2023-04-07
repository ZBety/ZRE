package org.sdk.actor;

import org.sdk.DataModel;
import org.sdk.RuleModel;
import org.sdk.RunningState;
import org.sdk.io.InputSlot;
import org.sdk.io.OutputSlot;
import org.sdk.msg.DataMsg;

public abstract class NodeActor<M extends DataModel, T extends RuleModel> implements Actor {

//    RuleEngineContext ctx;

//    DiagramRuleModel diagramRuleModel;

    T nodeRuleModel;

    //    private List<InputSlot<DataMsg>> inputs = new ArrayList<>();
    private InputSlot<DataMsg> inputs = new InputSlot<>();

    private OutputSlot<DataMsg> outputs = new OutputSlot<>();

    private Boolean begin = false;

    private Boolean end = false;

    public NodeActor( T nodeRuleModel) {
//        this.ctx = ctx;
//        this.diagramRuleModel = diagramRuleModel;
        this.nodeRuleModel = nodeRuleModel;
        begin = nodeRuleModel.getType().equals("BEGIN_RULE");
        end = nodeRuleModel.getType().equals("End_RULE");
    }

    protected RunningState runningState = RunningState.PENDING;


    protected void initLayout() {
//        nodeRuleModel
    }

    @Override
    public void init() {
        initLayout();
    }

    @Override
    public void start() {
        /**
         * 异步执行onHandle，一直监听，直到input
         */
        inputs.connect(this::onHandle);
    }

    @Override
    public void stop() {

    }

//    public void initIo() {
//        diagramRuleModel.getEdges().forEach(this::addIO);
//    }

//    void addIO(Edge it) {
//        if (Objects.equals(it.getSource(), nodeRuleModel.getId())) {
//            OutputSlot<DataMsg> outputSlot = new OutputSlot<>();
//        }
//        if (Objects.equals(it.getTarget(), nodeRuleModel.getId())) {
//            InputSlot<DataMsg> inputSlot = new InputSlot<>();
//            this.inputs.add(inputSlot);
//        }
//    }

    @Override
    public RunningState status() {
        return null;
    }

    @Override
    public String getId() {
        return nodeRuleModel.getId();
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public boolean isDiagram() {
        return false;
    }


    protected void onHandle(DataMsg dataMsg) {
    }

//    public InputSlot<DataMsg> addInputs(InputSlot<DataMsg> input) {
//        this.inputs.add(input);
//        return input;
//    }

    public InputSlot<DataMsg> getInputs() {
        return inputs;
    }

    public OutputSlot<DataMsg> getOutputs() {
        return outputs;
    }

//    public OutputSlot<DataMsg> addOutputs(InputSlot<DataMsg> inputSlot) {
//        this.outputs.add(inputSlot);
//        return output;
//    }


    public Boolean isBegin() {
        return begin;
    }

    public Boolean isEnd() {
        return end;
    }

//    public Action getAction() {
//        return this.ctx.getBean(Action.class);
//    }
}
