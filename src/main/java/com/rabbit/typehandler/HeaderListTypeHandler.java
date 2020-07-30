package com.rabbit.typehandler;

import com.rabbit.model.po.Header;

public class HeaderListTypeHandler extends ListTypeHandler {
    @Override
    public Class getTypeClass() {
        return Header.class;
    }
}
