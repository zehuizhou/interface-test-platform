package com.rabbit.service.Impl;

import com.rabbit.dao.SysCodeStoreMapper;
import com.rabbit.model.SysCodeStore;
import com.rabbit.service.IdWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IdWorkerServiceImpl implements IdWorkerService {
    @Autowired
    private SysCodeStoreMapper sysCodeStoreMapper;

    //预期生成的id格式：前缀+20190628+5位流水号（不够前面补0）
    @Override
    public synchronized String getOrderCode(String start) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // 时间字符串产生方式
        String newDataString = format.format(new Date());
        String newCode = "";
        List<SysCodeStore> byStart = sysCodeStoreMapper.findByCodeLike(start.toUpperCase());
        if (byStart.size() == 1) {
            SysCodeStore sysCodeStore = byStart.get(0);
            String code = sysCodeStore.getCode();
            String replace = code.substring(code.length() - 13);
            String oldDataString = replace.substring(0, 8);
            int liushui = Integer.parseInt(replace.substring(8));
            if (oldDataString.equals(newDataString)) {
                liushui = liushui + 1;
            } else {
                liushui = 1;
            }
            newCode = start.toUpperCase() + newDataString + String.format("%5d", liushui).replace(" ", "0");
            sysCodeStore.setCode(newCode);
            sysCodeStoreMapper.updateByPrimaryKey(sysCodeStore);
        } else if (byStart.size() == 0) {
            SysCodeStore sysCodeStore = new SysCodeStore();
            newCode = start.toUpperCase() + newDataString + String.format("%5d", 1).replace(" ", "0");
            sysCodeStore.setCode(newCode);
            sysCodeStoreMapper.insertSelective(sysCodeStore);
        } else {
        }
        return newCode;
    }

}
