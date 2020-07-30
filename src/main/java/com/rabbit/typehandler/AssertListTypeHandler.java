package com.rabbit.typehandler;

import com.rabbit.model.po.Assert;

public class AssertListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Assert.class;
    }
}
