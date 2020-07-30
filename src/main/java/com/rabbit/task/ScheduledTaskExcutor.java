package com.rabbit.task;

import com.rabbit.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTaskExcutor {


    @Autowired
    private ClientService clientService;
    /**
     * 每隔30秒定时检测在线设备是否离线
     */
//    @Scheduled(fixedRate = 3000)
    @Scheduled(cron = "0,30 * * * * ? ")
    public void checkOnlineClientTask() {
        // 未完成的测试任务
        clientService.asynOnlineStatus();
    }
}
