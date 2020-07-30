package com.rabbit.dao;


import com.rabbit.dto.SysDictDto;

import java.util.List;

public interface SysDictDtoMapper {
    List<SysDictDto> findUiActions();

    List<SysDictDto> findApiActions();
}
