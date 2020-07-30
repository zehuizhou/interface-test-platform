package com.rabbit.typehandler;

import com.rabbit.model.po.Extract;

public class ExtractListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Extract.class;
    }
}
