package com.rabbit.hessian.exporter;

import com.rabbit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class serviceNewExporter {
    @Autowired
    private TTestsuiteUiService tTestsuiteUiService;

    @Autowired
    private TTestcaseUiNewService tTestcaseUiNewService;

    @Autowired
    private TStepUiNewService stepUiNewService;

    @Autowired
    private TTestPlanUiNewLogService testPlanUiNewLogService;

    @Autowired
    private TTestSuiteUiLogService testSuiteUiLogService;

    @Autowired
    private TTestCaseUiNewLogService testCaseUiNewLogService;

    @Autowired
    private TTestStepUiNewLogService testStepUiNewLogService;

    @Bean(name = "/hessian/testStepUiNewLogService")
    public HessianServiceExporter testStepUiNewLogService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(testStepUiNewLogService);
        exporter.setServiceInterface(TTestStepUiNewLogService.class);
        return exporter;
    }
    @Bean(name = "/hessian/testCaseUiNewLogService")
    public HessianServiceExporter testCaseUiNewLogService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(testCaseUiNewLogService);
        exporter.setServiceInterface(TTestCaseUiNewLogService.class);
        return exporter;
    }
    @Bean(name = "/hessian/testSuiteUiLogService")
    public HessianServiceExporter testSuiteUiLogService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(testSuiteUiLogService);
        exporter.setServiceInterface(TTestSuiteUiLogService.class);
        return exporter;
    }
    @Bean(name = "/hessian/testPlanUiNewLogService")
    public HessianServiceExporter testPlanUiNewLogService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(testPlanUiNewLogService);
        exporter.setServiceInterface(TTestPlanUiNewLogService.class);
        return exporter;
    }

    @Bean(name = "/hessian/tTestsuiteUiService")
    public HessianServiceExporter tTestsuiteUiService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(tTestsuiteUiService);
        exporter.setServiceInterface(TTestsuiteUiService.class);
        return exporter;
    }

    @Bean(name = "/hessian/tTestcaseUiNewService")
    public HessianServiceExporter tTestcaseUiNewService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(tTestcaseUiNewService);
        exporter.setServiceInterface(TTestcaseUiNewService.class);
        return exporter;
    }

    @Bean(name = "/hessian/stepUiNewService")
    public HessianServiceExporter stepUiNewService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(stepUiNewService);
        exporter.setServiceInterface(TStepUiNewService.class);
        return exporter;
    }

}
