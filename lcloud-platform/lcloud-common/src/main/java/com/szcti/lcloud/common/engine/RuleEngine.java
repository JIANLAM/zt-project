package com.szcti.lcloud.common.engine;

import java.util.Map;

public class RuleEngine {
    private   RuleBaseService ruleBaseService;
    public void setRuleService(RuleBaseService b){ruleBaseService=b;}
    public void run(){
        ruleBaseService.ruleAction();
    }

    public Boolean getResultBoolean() {
        return ruleBaseService.getResultBoolean();
    }


    public String getResultMsg() {
        return ruleBaseService.getResultMsg();
    }


    public Map getResultMap() {
        return ruleBaseService.getResultMap();
    }
}
