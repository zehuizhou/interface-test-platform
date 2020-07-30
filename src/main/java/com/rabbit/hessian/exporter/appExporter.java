package com.rabbit.hessian.exporter;

import com.rabbit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class appExporter {
    @Autowired
    private DeviceService deviceService;

    @Bean(name = "/hessian/deviceService")
    public HessianServiceExporter deviceService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(deviceService);
        exporter.setServiceInterface(DeviceService.class);
        return exporter;
    }

}
