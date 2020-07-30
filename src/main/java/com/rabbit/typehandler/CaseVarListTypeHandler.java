package com.rabbit.typehandler;

import com.rabbit.model.po.CaseVar;

public class CaseVarListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return CaseVar.class;
    }
}
