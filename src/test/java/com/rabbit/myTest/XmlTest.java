package com.rabbit.myTest;

import com.rabbit.model.TJmeterExcDetail;
import com.rabbit.utils.XmlUtils;
import org.junit.Test;

import java.util.List;

public class XmlTest {

    @Test
    public void file() throws Exception {

        List<TJmeterExcDetail> jmeterExcDetail = XmlUtils.getJmeterExcDetail("G:\\桌面临时空间\\ecm_jmeter\\test\\94e0d6c6-e40d-4e95-9d65-b5e30f2a34e3\\result\\logs\\test.xml");
        for (TJmeterExcDetail tJmeterExcDetail : jmeterExcDetail) {
            System.out.println(tJmeterExcDetail.getName());
        }
    }


}
