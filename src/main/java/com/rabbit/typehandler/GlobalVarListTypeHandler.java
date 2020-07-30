package com.rabbit.typehandler;

import com.rabbit.model.po.GlobalVar;

public class GlobalVarListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return GlobalVar.class;
    }
}
