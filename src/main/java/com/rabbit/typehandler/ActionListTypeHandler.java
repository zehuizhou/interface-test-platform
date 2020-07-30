package com.rabbit.typehandler;

import com.rabbit.model.po.Action;

public class ActionListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Action.class;
    }
}
