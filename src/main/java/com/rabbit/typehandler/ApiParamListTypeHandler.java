package com.rabbit.typehandler;

import com.rabbit.model.po.ApiParam;

public class ApiParamListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return ApiParam.class;
    }
}
