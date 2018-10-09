package com.szcti.lcloud.common.engine;

import java.util.List;
import java.util.Map;

public interface RuleBaseService {
     List ruleAction();
     Boolean getResultBoolean();
     String getResultMsg();
     Map getResultMap();
    void clear();
}
