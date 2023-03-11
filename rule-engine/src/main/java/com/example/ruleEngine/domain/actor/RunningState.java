package com.example.ruleEngine.domain.actor;

public enum RunningState {

    UNKNOWN("初始化"),
    PENDING("挂起"),
    RUNNING("运行"),
    PAUSED("暂停"),
    STOPPED("停止");

    private String desc;

    RunningState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
