package com.rabbit.typehandler;

import com.rabbit.model.po.BodyData;

public class BodyDataListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return BodyData.class;
    }
}
