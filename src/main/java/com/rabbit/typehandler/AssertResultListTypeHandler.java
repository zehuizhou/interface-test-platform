package com.rabbit.typehandler;

import com.rabbit.model.po.AssertResult;

public class AssertResultListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return AssertResult.class;
    }
}
