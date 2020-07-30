package com.rabbit.typehandler;

import com.rabbit.model.po.Query;

public class QueryListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Query.class;
    }
}
