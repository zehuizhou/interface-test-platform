package com.rabbit.typehandler;

import com.rabbit.model.po.Param;

public class ParamListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Param.class;
    }
}
