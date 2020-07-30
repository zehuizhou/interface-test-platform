package com.rabbit.task;

import com.rabbit.model.Job;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 */
@Component("ryTask")
public class RyTask {
    public void ryParams(Job job) throws InterruptedException {
//        int a = 5 / 0;
        System.out.println("执行有参方法：=============================" + job.getMethodParams());
        Thread.sleep(3000);
        System.out.println("执行有参方法结束：=============================" + job.getMethodParams());
    }

    public void ryNoParams(Job job) throws Exception {

        System.out.println("执行无参方法：=============================" + job);
        Thread.sleep(19000);

        System.out.println("执行无参方法=========结束"+job);
    }
}
