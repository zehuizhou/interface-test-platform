2020-07-29 09:46:46,092 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 09:46:46,263 [main] ==> Parameters: 1(Integer)
2020-07-29 09:46:46,300 [main] <==      Total: 0
2020-07-29 09:46:46,579 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 09:46:46,580 [main] ==> Parameters: 
2020-07-29 09:46:46,646 [main] <==      Total: 5
2020-07-29 09:46:54,770 [main] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 09:46:54,771 [main] ==> Parameters: test(String)
2020-07-29 09:46:54,786 [main] <==      Total: 0
2020-07-29 09:46:54,800 [main] ==>  Preparing: SELECT plan_log_id, SUM(case_total_count) AS case_total_count, SUM(case_succ_count) AS case_succ_count, SUM(case_fail_count) AS case_fail_count, SUM(case_skip_count) AS case_skip_count, MAX(end_time) AS end_time, MIN(create_time) AS create_time FROM t_test_suite_ui_log WHERE plan_log_id=? GROUP BY plan_log_id 
2020-07-29 09:46:54,803 [main] ==> Parameters: 72(Long)
2020-07-29 09:46:54,818 [main] <==      Total: 1
2020-07-29 09:46:54,819 [main] ==>  Preparing: select id, `name`, job_id, project_id, `status`, suite_total_count, suite_succ_count, suite_fail_count, end_time, remark, create_by, create_time from t_test_plan_ui_new_log where id = ? 
2020-07-29 09:46:54,820 [main] ==> Parameters: 72(Long)
2020-07-29 09:46:54,836 [main] <==      Total: 1
2020-07-29 09:46:54,837 [main] ==>  Preparing: select id, plan_log_id, suite_log_id, suite_id, suite_name, case_id, case_name, `status`, end_time, remark, create_by, create_time from t_test_case_ui_new_log where plan_log_id=? 
2020-07-29 09:46:54,838 [main] ==> Parameters: 72(Long)
2020-07-29 09:46:54,859 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 09:46:54,860 [main] ====> Parameters: 85(Long)
2020-07-29 09:46:54,877 [main] <====      Total: 3
2020-07-29 09:46:54,878 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 09:46:54,879 [main] ====> Parameters: 86(Long)
2020-07-29 09:46:54,894 [main] <====      Total: 3
2020-07-29 09:46:54,895 [main] <==      Total: 2
2020-07-29 09:53:30,825 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 09:53:30,980 [main] ==> Parameters: 1(Integer)
2020-07-29 09:53:31,011 [main] <==      Total: 0
2020-07-29 09:53:31,252 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 09:53:31,254 [main] ==> Parameters: 
2020-07-29 09:53:31,280 [main] <==      Total: 5
2020-07-29 09:53:38,705 [main] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 09:53:38,706 [main] ==> Parameters: test(String)
2020-07-29 09:53:38,719 [main] <==      Total: 0
2020-07-29 09:53:38,733 [main] ==>  Preparing: SELECT plan_log_id, SUM(case_total_count) AS case_total_count, SUM(case_succ_count) AS case_succ_count, SUM(case_fail_count) AS case_fail_count, SUM(case_skip_count) AS case_skip_count, MAX(end_time) AS end_time, MIN(create_time) AS create_time FROM t_test_suite_ui_log WHERE plan_log_id=? GROUP BY plan_log_id 
2020-07-29 09:53:38,737 [main] ==> Parameters: 72(Long)
2020-07-29 09:53:38,748 [main] <==      Total: 1
2020-07-29 09:53:38,750 [main] ==>  Preparing: select id, `name`, job_id, project_id, `status`, suite_total_count, suite_succ_count, suite_fail_count, end_time, remark, create_by, create_time from t_test_plan_ui_new_log where id = ? 
2020-07-29 09:53:38,751 [main] ==> Parameters: 72(Long)
2020-07-29 09:53:38,766 [main] <==      Total: 1
2020-07-29 09:53:38,768 [main] ==>  Preparing: select id, plan_log_id, suite_log_id, suite_id, suite_name, case_id, case_name, `status`, end_time, remark, create_by, create_time from t_test_case_ui_new_log where plan_log_id=? 
2020-07-29 09:53:38,769 [main] ==> Parameters: 72(Long)
2020-07-29 09:53:38,788 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 09:53:38,789 [main] ====> Parameters: 85(Long)
2020-07-29 09:53:38,807 [main] <====      Total: 3
2020-07-29 09:53:38,808 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 09:53:38,809 [main] ====> Parameters: 86(Long)
2020-07-29 09:53:38,822 [main] <====      Total: 3
2020-07-29 09:53:38,823 [main] <==      Total: 2
2020-07-29 09:55:49,886 [restartedMain] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 09:55:49,984 [restartedMain] ==> Parameters: 1(Integer)
2020-07-29 09:55:50,015 [restartedMain] <==      Total: 0
2020-07-29 09:55:50,209 [restartedMain] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 09:55:50,210 [restartedMain] ==> Parameters: 
2020-07-29 09:55:50,231 [restartedMain] <==      Total: 5
2020-07-29 09:56:28,539 [http-nio-8889-exec-3] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 09:56:28,540 [http-nio-8889-exec-3] ==> Parameters: admin(String)
2020-07-29 09:56:28,556 [http-nio-8889-exec-3] <==      Total: 1
2020-07-29 09:56:28,566 [http-nio-8889-exec-3] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 09:56:28,567 [http-nio-8889-exec-3] ==> Parameters: 1(Long)
2020-07-29 09:56:28,580 [http-nio-8889-exec-3] <==      Total: 1
2020-07-29 09:56:28,585 [http-nio-8889-exec-3] ==>  Preparing: select * from sys_permission t order by t.sort 
2020-07-29 09:56:28,586 [http-nio-8889-exec-3] ==> Parameters: 
2020-07-29 09:56:28,614 [http-nio-8889-exec-3] <==      Total: 91
2020-07-29 09:56:28,625 [http-nio-8889-exec-3] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 09:56:28,626 [http-nio-8889-exec-3] ==> Parameters: 
2020-07-29 09:56:28,638 [http-nio-8889-exec-3] <==      Total: 3
2020-07-29 09:56:28,835 [http-nio-8889-exec-3] ==>  Preparing: insert into sys_logs(userId, module, flag, remark, createTime) values(?, ?, ?, ?, now()) 
2020-07-29 09:56:28,837 [http-nio-8889-exec-3] ==> Parameters: 1(Long), 登陆(String), true(Boolean), null
2020-07-29 09:56:28,866 [http-nio-8889-exec-3] <==    Updates: 1
2020-07-29 09:56:33,279 [http-nio-8889-exec-10] ==>  Preparing: SELECT count(0) FROM t_project_page LEFT JOIN sys_project ON t_project_page.project_id = sys_project.id WHERE 1 = 1 AND project_id = ? 
2020-07-29 09:56:33,281 [http-nio-8889-exec-10] ==> Parameters: 1(Long)
2020-07-29 09:56:33,295 [http-nio-8889-exec-10] <==      Total: 1
2020-07-29 09:56:33,300 [http-nio-8889-exec-10] ==>  Preparing: select t_project_page.*,sys_project.project_name from t_project_page left join sys_project on t_project_page.project_id = sys_project.id where 1=1 and project_id=? order by create_time desc LIMIT ? 
2020-07-29 09:56:33,302 [http-nio-8889-exec-10] ==> Parameters: 1(Long), 10(Integer)
2020-07-29 09:56:33,320 [http-nio-8889-exec-10] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,321 [http-nio-8889-exec-10] ====> Parameters: 9(Long)
2020-07-29 09:56:33,336 [http-nio-8889-exec-10] <====      Total: 1
2020-07-29 09:56:33,337 [http-nio-8889-exec-10] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,338 [http-nio-8889-exec-10] ====> Parameters: 8(Long)
2020-07-29 09:56:33,352 [http-nio-8889-exec-10] <====      Total: 3
2020-07-29 09:56:33,353 [http-nio-8889-exec-10] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,353 [http-nio-8889-exec-10] ====> Parameters: 7(Long)
2020-07-29 09:56:33,370 [http-nio-8889-exec-10] <====      Total: 5
2020-07-29 09:56:33,371 [http-nio-8889-exec-10] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,371 [http-nio-8889-exec-10] ====> Parameters: 4(Long)
2020-07-29 09:56:33,384 [http-nio-8889-exec-10] <====      Total: 3
2020-07-29 09:56:33,385 [http-nio-8889-exec-10] <==      Total: 4
2020-07-29 09:56:33,437 [http-nio-8889-exec-2] ==>  Preparing: SELECT count(0) FROM t_project_page LEFT JOIN sys_project ON t_project_page.project_id = sys_project.id WHERE 1 = 1 AND project_id = ? 
2020-07-29 09:56:33,438 [http-nio-8889-exec-2] ==> Parameters: 1(Long)
2020-07-29 09:56:33,449 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:56:33,451 [http-nio-8889-exec-2] ==>  Preparing: select t_project_page.*,sys_project.project_name from t_project_page left join sys_project on t_project_page.project_id = sys_project.id where 1=1 and project_id=? order by create_time desc LIMIT ? 
2020-07-29 09:56:33,452 [http-nio-8889-exec-2] ==> Parameters: 1(Long), 10(Integer)
2020-07-29 09:56:33,469 [http-nio-8889-exec-2] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,470 [http-nio-8889-exec-2] ====> Parameters: 9(Long)
2020-07-29 09:56:33,488 [http-nio-8889-exec-2] <====      Total: 1
2020-07-29 09:56:33,489 [http-nio-8889-exec-2] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,490 [http-nio-8889-exec-2] ====> Parameters: 8(Long)
2020-07-29 09:56:33,508 [http-nio-8889-exec-2] <====      Total: 3
2020-07-29 09:56:33,510 [http-nio-8889-exec-2] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,511 [http-nio-8889-exec-2] ====> Parameters: 7(Long)
2020-07-29 09:56:33,524 [http-nio-8889-exec-2] <====      Total: 5
2020-07-29 09:56:33,526 [http-nio-8889-exec-2] ====>  Preparing: select id, page_id, element_name, by_type, by_value, is_enable, remark, create_by, create_time, update_by, update_time from t_page_element where page_id=? 
2020-07-29 09:56:33,526 [http-nio-8889-exec-2] ====> Parameters: 4(Long)
2020-07-29 09:56:33,540 [http-nio-8889-exec-2] <====      Total: 3
2020-07-29 09:56:33,541 [http-nio-8889-exec-2] <==      Total: 4
2020-07-29 09:56:36,568 [http-nio-8889-exec-1] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 09:56:36,569 [http-nio-8889-exec-1] ==> Parameters: 
2020-07-29 09:56:36,584 [http-nio-8889-exec-1] <==      Total: 3
2020-07-29 09:56:36,586 [http-nio-8889-exec-4] ==>  Preparing: SELECT count(0) FROM t_client WHERE 1 = 1 
2020-07-29 09:56:36,587 [http-nio-8889-exec-4] ==> Parameters: 
2020-07-29 09:56:36,598 [http-nio-8889-exec-4] <==      Total: 1
2020-07-29 09:56:36,600 [http-nio-8889-exec-4] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where 1=1 LIMIT ? 
2020-07-29 09:56:36,601 [http-nio-8889-exec-4] ==> Parameters: 10(Integer)
2020-07-29 09:56:36,615 [http-nio-8889-exec-4] <==      Total: 3
2020-07-29 09:56:37,796 [http-nio-8889-exec-8] ==>  Preparing: SELECT count(0) FROM device 
2020-07-29 09:56:37,797 [http-nio-8889-exec-8] ==> Parameters: 
2020-07-29 09:56:37,810 [http-nio-8889-exec-8] <==      Total: 1
2020-07-29 09:56:37,811 [http-nio-8889-exec-8] ==>  Preparing: select id, `name`, agent_ip, agent_port, system_version, cpu_info, mem_size, screen_width, screen_height, img_path, platform, `status`, last_online_time, last_offline_time, username, create_time from device LIMIT ? 
2020-07-29 09:56:37,812 [http-nio-8889-exec-8] ==> Parameters: 20(Integer)
2020-07-29 09:56:37,825 [http-nio-8889-exec-8] <==      Total: 2
2020-07-29 09:56:39,330 [http-nio-8889-exec-10] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 09:56:39,330 [http-nio-8889-exec-10] ==> Parameters: 
2020-07-29 09:56:39,342 [http-nio-8889-exec-2] ==>  Preparing: SELECT count(0) FROM t_client WHERE 1 = 1 
2020-07-29 09:56:39,343 [http-nio-8889-exec-2] ==> Parameters: 
2020-07-29 09:56:39,344 [http-nio-8889-exec-10] <==      Total: 3
2020-07-29 09:56:39,355 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:56:39,356 [http-nio-8889-exec-2] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where 1=1 LIMIT ? 
2020-07-29 09:56:39,358 [http-nio-8889-exec-2] ==> Parameters: 10(Integer)
2020-07-29 09:56:39,371 [http-nio-8889-exec-2] <==      Total: 3
2020-07-29 09:56:41,275 [http-nio-8889-exec-1] ==>  Preparing: SELECT count(0) FROM device 
2020-07-29 09:56:41,276 [http-nio-8889-exec-1] ==> Parameters: 
2020-07-29 09:56:41,286 [http-nio-8889-exec-1] <==      Total: 1
2020-07-29 09:56:41,288 [http-nio-8889-exec-1] ==>  Preparing: select id, `name`, agent_ip, agent_port, system_version, cpu_info, mem_size, screen_width, screen_height, img_path, platform, `status`, last_online_time, last_offline_time, username, create_time from device LIMIT ? 
2020-07-29 09:56:41,288 [http-nio-8889-exec-1] ==> Parameters: 20(Integer)
2020-07-29 09:56:41,304 [http-nio-8889-exec-1] <==      Total: 2
2020-07-29 09:56:51,253 [http-nio-8889-exec-7] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 09:56:51,254 [http-nio-8889-exec-7] ==> Parameters: 
2020-07-29 09:56:51,279 [http-nio-8889-exec-7] <==      Total: 3
2020-07-29 09:56:51,285 [http-nio-8889-exec-6] ==>  Preparing: SELECT count(0) FROM sys_user t LEFT JOIN sys_project sp ON t.project_id = sp.id 
2020-07-29 09:56:51,287 [http-nio-8889-exec-6] ==> Parameters: 
2020-07-29 09:56:51,298 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:56:51,300 [http-nio-8889-exec-6] ==>  Preparing: select t.*,sp.project_name from sys_user t left join sys_project sp on t.project_id = sp.id order by t.id LIMIT ? 
2020-07-29 09:56:51,301 [http-nio-8889-exec-6] ==> Parameters: 10(Integer)
2020-07-29 09:56:51,318 [http-nio-8889-exec-6] <==      Total: 2
2020-07-29 09:56:51,320 [http-nio-8889-exec-6] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 09:56:51,321 [http-nio-8889-exec-6] ==> Parameters: 1(Long)
2020-07-29 09:56:51,333 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:56:51,336 [http-nio-8889-exec-6] ==>  Preparing: select ru.roleId from sys_role_user ru where ru.userId = ? 
2020-07-29 09:56:51,338 [http-nio-8889-exec-6] ==> Parameters: 1(Long)
2020-07-29 09:56:51,348 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:56:51,350 [http-nio-8889-exec-6] ==>  Preparing: SELECT sp.* FROM sys_user_project up ,sys_project sp WHERE up.projectId = sp.id AND up.userId = ? 
2020-07-29 09:56:51,351 [http-nio-8889-exec-6] ==> Parameters: 1(Long)
2020-07-29 09:56:51,361 [http-nio-8889-exec-6] <==      Total: 0
2020-07-29 09:56:51,363 [http-nio-8889-exec-6] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 09:56:51,365 [http-nio-8889-exec-6] ==> Parameters: 2(Long)
2020-07-29 09:56:51,380 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:56:51,381 [http-nio-8889-exec-6] ==>  Preparing: select ru.roleId from sys_role_user ru where ru.userId = ? 
2020-07-29 09:56:51,382 [http-nio-8889-exec-6] ==> Parameters: 2(Long)
2020-07-29 09:56:51,392 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:56:51,395 [http-nio-8889-exec-6] ==>  Preparing: SELECT sp.* FROM sys_user_project up ,sys_project sp WHERE up.projectId = sp.id AND up.userId = ? 
2020-07-29 09:56:51,396 [http-nio-8889-exec-6] ==> Parameters: 2(Long)
2020-07-29 09:56:51,410 [http-nio-8889-exec-6] <==      Total: 1
2020-07-29 09:57:11,188 [http-nio-8889-exec-3] ==>  Preparing: select * from sys_role 
2020-07-29 09:57:11,188 [http-nio-8889-exec-3] ==> Parameters: 
2020-07-29 09:57:11,200 [http-nio-8889-exec-3] <==      Total: 2
2020-07-29 09:57:13,623 [http-nio-8889-exec-10] ==>  Preparing: update sys_user t SET username = ?, project_id = ? , nickname = ?, email = ?, sex = ?, status = ?, updateTime = ? where t.id = ? 
2020-07-29 09:57:13,624 [http-nio-8889-exec-10] ==> Parameters: test1(String), 1(Long), 测试(String), (String), 0(Integer), 1(Integer), 2020-07-29 09:57:13.589(Timestamp), 2(Long)
2020-07-29 09:57:13,650 [http-nio-8889-exec-10] <==    Updates: 1
2020-07-29 09:57:13,651 [http-nio-8889-exec-10] ==>  Preparing: delete from sys_role_user where userId = ? 
2020-07-29 09:57:13,652 [http-nio-8889-exec-10] ==> Parameters: 2(Long)
2020-07-29 09:57:13,672 [http-nio-8889-exec-10] <==    Updates: 1
2020-07-29 09:57:13,676 [http-nio-8889-exec-10] ==>  Preparing: insert into sys_role_user(roleId, userId) values (?, ?) 
2020-07-29 09:57:13,677 [http-nio-8889-exec-10] ==> Parameters: 4(Long), 2(Long)
2020-07-29 09:57:13,699 [http-nio-8889-exec-10] <==    Updates: 1
2020-07-29 09:57:13,700 [http-nio-8889-exec-10] ==>  Preparing: delete from sys_user_project where userId = ? 
2020-07-29 09:57:13,701 [http-nio-8889-exec-10] ==> Parameters: 2(Long)
2020-07-29 09:57:13,725 [http-nio-8889-exec-10] <==    Updates: 1
2020-07-29 09:57:13,727 [http-nio-8889-exec-10] ==>  Preparing: insert into sys_user_project(projectId, userId) values (?, ?) 
2020-07-29 09:57:13,728 [http-nio-8889-exec-10] ==> Parameters: 1(Long), 2(Long)
2020-07-29 09:57:13,750 [http-nio-8889-exec-10] <==    Updates: 1
2020-07-29 09:57:13,869 [http-nio-8889-exec-2] ==>  Preparing: SELECT count(0) FROM sys_user t LEFT JOIN sys_project sp ON t.project_id = sp.id 
2020-07-29 09:57:13,869 [http-nio-8889-exec-2] ==> Parameters: 
2020-07-29 09:57:13,881 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:13,882 [http-nio-8889-exec-2] ==>  Preparing: select t.*,sp.project_name from sys_user t left join sys_project sp on t.project_id = sp.id order by t.id LIMIT ? 
2020-07-29 09:57:13,882 [http-nio-8889-exec-2] ==> Parameters: 10(Integer)
2020-07-29 09:57:13,898 [http-nio-8889-exec-2] <==      Total: 2
2020-07-29 09:57:13,899 [http-nio-8889-exec-2] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 09:57:13,899 [http-nio-8889-exec-2] ==> Parameters: 1(Long)
2020-07-29 09:57:13,910 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:13,911 [http-nio-8889-exec-2] ==>  Preparing: select ru.roleId from sys_role_user ru where ru.userId = ? 
2020-07-29 09:57:13,911 [http-nio-8889-exec-2] ==> Parameters: 1(Long)
2020-07-29 09:57:13,923 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:13,925 [http-nio-8889-exec-2] ==>  Preparing: SELECT sp.* FROM sys_user_project up ,sys_project sp WHERE up.projectId = sp.id AND up.userId = ? 
2020-07-29 09:57:13,926 [http-nio-8889-exec-2] ==> Parameters: 1(Long)
2020-07-29 09:57:13,937 [http-nio-8889-exec-2] <==      Total: 0
2020-07-29 09:57:13,939 [http-nio-8889-exec-2] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 09:57:13,940 [http-nio-8889-exec-2] ==> Parameters: 2(Long)
2020-07-29 09:57:13,956 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:13,957 [http-nio-8889-exec-2] ==>  Preparing: select ru.roleId from sys_role_user ru where ru.userId = ? 
2020-07-29 09:57:13,958 [http-nio-8889-exec-2] ==> Parameters: 2(Long)
2020-07-29 09:57:13,979 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:13,980 [http-nio-8889-exec-2] ==>  Preparing: SELECT sp.* FROM sys_user_project up ,sys_project sp WHERE up.projectId = sp.id AND up.userId = ? 
2020-07-29 09:57:13,981 [http-nio-8889-exec-2] ==> Parameters: 2(Long)
2020-07-29 09:57:13,995 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 09:57:15,970 [http-nio-8889-exec-5] ==>  Preparing: select * from sys_role 
2020-07-29 09:57:15,971 [http-nio-8889-exec-5] ==> Parameters: 
2020-07-29 09:57:15,991 [http-nio-8889-exec-5] <==      Total: 2
2020-07-29 10:17:51,703 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:17:51,880 [main] ==> Parameters: 1(Integer)
2020-07-29 10:17:51,929 [main] <==      Total: 0
2020-07-29 10:17:52,211 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:17:52,213 [main] ==> Parameters: 
2020-07-29 10:17:52,236 [main] <==      Total: 5
2020-07-29 10:18:00,279 [main] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 10:18:00,280 [main] ==> Parameters: test(String)
2020-07-29 10:18:00,297 [main] <==      Total: 0
2020-07-29 10:18:00,307 [main] ==>  Preparing: SELECT plan_log_id, SUM(case_total_count) AS case_total_count, SUM(case_succ_count) AS case_succ_count, SUM(case_fail_count) AS case_fail_count, SUM(case_skip_count) AS case_skip_count, MAX(end_time) AS end_time, MIN(create_time) AS create_time FROM t_test_suite_ui_log WHERE plan_log_id=? GROUP BY plan_log_id 
2020-07-29 10:18:00,311 [main] ==> Parameters: 72(Long)
2020-07-29 10:18:00,327 [main] <==      Total: 1
2020-07-29 10:18:00,328 [main] ==>  Preparing: select id, `name`, job_id, project_id, `status`, suite_total_count, suite_succ_count, suite_fail_count, end_time, remark, create_by, create_time from t_test_plan_ui_new_log where id = ? 
2020-07-29 10:18:00,329 [main] ==> Parameters: 72(Long)
2020-07-29 10:18:00,345 [main] <==      Total: 1
2020-07-29 10:18:00,346 [main] ==>  Preparing: select id, plan_log_id, suite_log_id, suite_id, suite_name, case_id, case_name, `status`, end_time, remark, create_by, create_time from t_test_case_ui_new_log where plan_log_id=? 
2020-07-29 10:18:00,347 [main] ==> Parameters: 72(Long)
2020-07-29 10:18:00,366 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:18:00,367 [main] ====> Parameters: 85(Long)
2020-07-29 10:18:00,389 [main] <====      Total: 3
2020-07-29 10:18:00,390 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:18:00,391 [main] ====> Parameters: 86(Long)
2020-07-29 10:18:00,407 [main] <====      Total: 3
2020-07-29 10:18:00,408 [main] <==      Total: 2
2020-07-29 10:22:58,823 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:22:58,981 [main] ==> Parameters: 1(Integer)
2020-07-29 10:22:59,019 [main] <==      Total: 0
2020-07-29 10:22:59,280 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:22:59,281 [main] ==> Parameters: 
2020-07-29 10:22:59,306 [main] <==      Total: 5
2020-07-29 10:23:06,975 [main] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 10:23:06,976 [main] ==> Parameters: test(String)
2020-07-29 10:23:06,992 [main] <==      Total: 0
2020-07-29 10:23:07,007 [main] ==>  Preparing: SELECT plan_log_id, SUM(case_total_count) AS case_total_count, SUM(case_succ_count) AS case_succ_count, SUM(case_fail_count) AS case_fail_count, SUM(case_skip_count) AS case_skip_count, MAX(end_time) AS end_time, MIN(create_time) AS create_time FROM t_test_suite_ui_log WHERE plan_log_id=? GROUP BY plan_log_id 
2020-07-29 10:23:07,011 [main] ==> Parameters: 72(Long)
2020-07-29 10:23:07,026 [main] <==      Total: 1
2020-07-29 10:23:07,028 [main] ==>  Preparing: select id, `name`, job_id, project_id, `status`, suite_total_count, suite_succ_count, suite_fail_count, end_time, remark, create_by, create_time from t_test_plan_ui_new_log where id = ? 
2020-07-29 10:23:07,028 [main] ==> Parameters: 72(Long)
2020-07-29 10:23:07,046 [main] <==      Total: 1
2020-07-29 10:23:07,047 [main] ==>  Preparing: select id, plan_log_id, suite_log_id, suite_id, suite_name, case_id, case_name, `status`, end_time, remark, create_by, create_time from t_test_case_ui_new_log where plan_log_id=? 
2020-07-29 10:23:07,048 [main] ==> Parameters: 72(Long)
2020-07-29 10:23:07,067 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:23:07,068 [main] ====> Parameters: 85(Long)
2020-07-29 10:23:07,092 [main] <====      Total: 3
2020-07-29 10:23:07,095 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:23:07,095 [main] ====> Parameters: 86(Long)
2020-07-29 10:23:07,117 [main] <====      Total: 3
2020-07-29 10:23:07,118 [main] <==      Total: 2
2020-07-29 10:24:41,256 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:24:41,436 [main] ==> Parameters: 1(Integer)
2020-07-29 10:24:41,465 [main] <==      Total: 0
2020-07-29 10:24:41,753 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:24:41,755 [main] ==> Parameters: 
2020-07-29 10:24:41,782 [main] <==      Total: 5
2020-07-29 10:24:50,101 [main] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 10:24:50,103 [main] ==> Parameters: test(String)
2020-07-29 10:24:50,118 [main] <==      Total: 0
2020-07-29 10:24:50,138 [main] ==>  Preparing: SELECT plan_log_id, SUM(case_total_count) AS case_total_count, SUM(case_succ_count) AS case_succ_count, SUM(case_fail_count) AS case_fail_count, SUM(case_skip_count) AS case_skip_count, MAX(end_time) AS end_time, MIN(create_time) AS create_time FROM t_test_suite_ui_log WHERE plan_log_id=? GROUP BY plan_log_id 
2020-07-29 10:24:50,143 [main] ==> Parameters: 72(Long)
2020-07-29 10:24:50,158 [main] <==      Total: 1
2020-07-29 10:24:50,160 [main] ==>  Preparing: select id, `name`, job_id, project_id, `status`, suite_total_count, suite_succ_count, suite_fail_count, end_time, remark, create_by, create_time from t_test_plan_ui_new_log where id = ? 
2020-07-29 10:24:50,161 [main] ==> Parameters: 72(Long)
2020-07-29 10:24:50,176 [main] <==      Total: 1
2020-07-29 10:24:50,179 [main] ==>  Preparing: select id, plan_log_id, suite_log_id, suite_id, suite_name, case_id, case_name, `status`, end_time, remark, create_by, create_time from t_test_case_ui_new_log where plan_log_id=? 
2020-07-29 10:24:50,182 [main] ==> Parameters: 72(Long)
2020-07-29 10:24:50,199 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:24:50,201 [main] ====> Parameters: 85(Long)
2020-07-29 10:24:50,226 [main] <====      Total: 3
2020-07-29 10:24:50,229 [main] ====>  Preparing: select id, plan_job_id, case_log_id, step_id, step_name, case_id, case_name, log_detail, `status`, `type`, end_time, end_flag, imgname, create_by, create_time from t_test_step_ui_new_log where case_log_id=? 
2020-07-29 10:24:50,229 [main] ====> Parameters: 86(Long)
2020-07-29 10:24:50,242 [main] <====      Total: 3
2020-07-29 10:24:50,243 [main] <==      Total: 2
2020-07-29 10:38:42,595 [restartedMain] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:38:42,717 [restartedMain] ==> Parameters: 1(Integer)
2020-07-29 10:38:42,747 [restartedMain] <==      Total: 0
2020-07-29 10:38:42,957 [restartedMain] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:38:42,958 [restartedMain] ==> Parameters: 
2020-07-29 10:38:42,983 [restartedMain] <==      Total: 5
2020-07-29 10:46:19,046 [restartedMain] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:46:19,160 [restartedMain] ==> Parameters: 1(Integer)
2020-07-29 10:46:19,197 [restartedMain] <==      Total: 0
2020-07-29 10:46:19,431 [restartedMain] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:46:19,432 [restartedMain] ==> Parameters: 
2020-07-29 10:46:19,454 [restartedMain] <==      Total: 5
