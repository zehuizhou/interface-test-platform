package com.rabbit.typehandler;

import com.rabbit.model.po.ExtractResult;

public class ExtractResultListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return ExtractResult.class;
    }
}
