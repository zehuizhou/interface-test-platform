2020-07-29 10:53:41,576 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 10:53:41,805 [main] ==> Parameters: 1(Integer)
2020-07-29 10:53:41,840 [main] <==      Total: 0
2020-07-29 10:53:42,164 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 10:53:42,165 [main] ==> Parameters: 
2020-07-29 10:53:42,187 [main] <==      Total: 5
2020-07-29 11:26:31,603 [main] ==>  Preparing: select id, `name`, project_ids, client_ip, client_port, is_enable, `status`, java_version, jmeter_version, appium_version, os_name, remark, create_by, create_time, update_by, update_time from t_client where `status`=? 
2020-07-29 11:26:31,782 [main] ==> Parameters: 1(Integer)
2020-07-29 11:26:31,826 [main] <==      Total: 0
2020-07-29 11:26:32,107 [main] ==>  Preparing: select job_id, job_name, job_group, job_type, project_id, method_name, method_params, cron_expression, misfire_policy, status, create_by, create_time, remark from sys_job 
2020-07-29 11:26:32,108 [main] ==> Parameters: 
2020-07-29 11:26:32,129 [main] <==      Total: 5
2020-07-29 11:27:22,678 [http-nio-8889-exec-8] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 11:27:22,679 [http-nio-8889-exec-8] ==> Parameters: admin(String)
2020-07-29 11:27:22,768 [http-nio-8889-exec-8] <==      Total: 1
2020-07-29 11:27:22,781 [http-nio-8889-exec-8] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 11:27:22,789 [http-nio-8889-exec-8] ==> Parameters: 1(Long)
2020-07-29 11:27:22,801 [http-nio-8889-exec-8] <==      Total: 1
2020-07-29 11:27:22,809 [http-nio-8889-exec-8] ==>  Preparing: select * from sys_permission t order by t.sort 
2020-07-29 11:27:22,810 [http-nio-8889-exec-8] ==> Parameters: 
2020-07-29 11:27:22,838 [http-nio-8889-exec-8] <==      Total: 91
2020-07-29 11:27:22,859 [http-nio-8889-exec-8] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 11:27:22,860 [http-nio-8889-exec-8] ==> Parameters: 
2020-07-29 11:27:22,883 [http-nio-8889-exec-8] <==      Total: 3
2020-07-29 11:27:23,329 [http-nio-8889-exec-8] ==>  Preparing: insert into sys_logs(userId, module, flag, remark, createTime) values(?, ?, ?, ?, now()) 
2020-07-29 11:27:23,332 [http-nio-8889-exec-8] ==> Parameters: 1(Long), 登陆(String), true(Boolean), null
2020-07-29 11:27:23,360 [http-nio-8889-exec-8] <==    Updates: 1
2020-07-29 11:27:27,843 [http-nio-8889-exec-4] ==>  Preparing: SELECT count(0) FROM t_testcase_ui_new WHERE 1 = 1 AND project_id = ? AND case_type = ? 
2020-07-29 11:27:27,846 [http-nio-8889-exec-4] ==> Parameters: 1(Long), 2(Long)
2020-07-29 11:27:28,017 [http-nio-8889-exec-4] <==      Total: 1
2020-07-29 11:27:28,021 [http-nio-8889-exec-4] ==>  Preparing: select id, `name`, env_id, project_id, timout_time, fail_continue, flags, params, case_vars, case_type, remark, create_by, update_by, create_time, update_time from t_testcase_ui_new where 1=1 and project_id=? and case_type=? order by create_time desc LIMIT ? 
2020-07-29 11:27:28,024 [http-nio-8889-exec-4] ==> Parameters: 1(Long), 2(Long), 10(Integer)
2020-07-29 11:27:28,042 [http-nio-8889-exec-4] <==      Total: 3
2020-07-29 14:29:32,896 [http-nio-8889-exec-7] ==>  Preparing: select * from sys_user t where t.username = ? 
2020-07-29 14:29:32,903 [http-nio-8889-exec-7] ==> Parameters: admin(String)
2020-07-29 14:29:32,917 [http-nio-8889-exec-7] <==      Total: 1
2020-07-29 14:29:32,919 [http-nio-8889-exec-7] ==>  Preparing: select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = ? 
2020-07-29 14:29:32,920 [http-nio-8889-exec-7] ==> Parameters: 1(Long)
2020-07-29 14:29:32,933 [http-nio-8889-exec-7] <==      Total: 1
2020-07-29 14:29:32,934 [http-nio-8889-exec-7] ==>  Preparing: select * from sys_permission t order by t.sort 
2020-07-29 14:29:32,935 [http-nio-8889-exec-7] ==> Parameters: 
2020-07-29 14:29:32,980 [http-nio-8889-exec-7] <==      Total: 91
2020-07-29 14:29:32,986 [http-nio-8889-exec-7] ==>  Preparing: select id, project_name, description, platform, create_time, update_time, create_by, update_by from sys_project 
2020-07-29 14:29:32,987 [http-nio-8889-exec-7] ==> Parameters: 
2020-07-29 14:29:33,000 [http-nio-8889-exec-7] <==      Total: 3
2020-07-29 14:29:33,123 [http-nio-8889-exec-7] ==>  Preparing: insert into sys_logs(userId, module, flag, remark, createTime) values(?, ?, ?, ?, now()) 
2020-07-29 14:29:33,124 [http-nio-8889-exec-7] ==> Parameters: 1(Long), 登陆(String), true(Boolean), null
2020-07-29 14:29:33,152 [http-nio-8889-exec-7] <==    Updates: 1
2020-07-29 14:29:33,689 [http-nio-8889-exec-6] ==>  Preparing: select id, `name`, `type`, remark, project_id, is_valid, create_by, create_time from t_flag where project_id=? and `type`=? 
2020-07-29 14:29:33,690 [http-nio-8889-exec-6] ==> Parameters: 1(Long), 2(Integer)
2020-07-29 14:29:33,705 [http-nio-8889-exec-6] <==      Total: 2
2020-07-29 14:29:33,709 [http-nio-8889-exec-5] ==>  Preparing: SELECT count(0) FROM t_testcase_ui_new WHERE 1 = 1 AND project_id = ? AND case_type = ? 
2020-07-29 14:29:33,710 [http-nio-8889-exec-5] ==> Parameters: 1(Long), 1(Long)
2020-07-29 14:29:33,731 [http-nio-8889-exec-5] <==      Total: 1
2020-07-29 14:29:36,753 [http-nio-8889-exec-8] ==>  Preparing: SELECT count(0) FROM t_testsuite_ui WHERE 1 = 1 AND project_id = ? 
2020-07-29 14:29:36,754 [http-nio-8889-exec-8] ==> Parameters: 1(Long)
2020-07-29 14:29:36,764 [http-nio-8889-exec-8] <==      Total: 1
2020-07-29 14:29:36,765 [http-nio-8889-exec-8] ==>  Preparing: select id, `name`, project_id, create_by, update_by, create_time, update_time from t_testsuite_ui where 1=1 and project_id=? order by create_time desc LIMIT ? 
2020-07-29 14:29:36,766 [http-nio-8889-exec-8] ==> Parameters: 1(Long), 10(Integer)
2020-07-29 14:29:36,777 [http-nio-8889-exec-8] <==      Total: 1
2020-07-29 14:29:38,984 [http-nio-8889-exec-10] ==>  Preparing: SELECT count(0) FROM t_testcase_ui_new WHERE 1 = 1 AND project_id = ? AND case_type = ? 
2020-07-29 14:29:38,985 [http-nio-8889-exec-10] ==> Parameters: 1(Long), 1(Long)
2020-07-29 14:29:38,996 [http-nio-8889-exec-10] <==      Total: 1
2020-07-29 14:29:38,997 [http-nio-8889-exec-9] ==>  Preparing: SELECT t_suite_case_ui.id as suite_case_id ,t_suite_case_ui.suite_id,t_testcase_ui_new.* FROM t_suite_case_ui, t_testcase_ui_new WHERE t_suite_case_ui.case_id = t_testcase_ui_new.id AND t_suite_case_ui.suite_id = ? order by t_suite_case_ui.sort 
2020-07-29 14:29:38,998 [http-nio-8889-exec-9] ==> Parameters: 8(Long)
2020-07-29 14:29:39,012 [http-nio-8889-exec-9] <==      Total: 0
2020-07-29 14:29:41,142 [http-nio-8889-exec-2] ==>  Preparing: SELECT count(0) FROM t_testsuite_ui WHERE 1 = 1 AND project_id = ? 
2020-07-29 14:29:41,143 [http-nio-8889-exec-2] ==> Parameters: 1(Long)
2020-07-29 14:29:41,156 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 14:29:41,157 [http-nio-8889-exec-2] ==>  Preparing: select id, `name`, project_id, create_by, update_by, create_time, update_time from t_testsuite_ui where 1=1 and project_id=? order by create_time desc LIMIT ? 
2020-07-29 14:29:41,158 [http-nio-8889-exec-2] ==> Parameters: 1(Long), 20(Integer)
2020-07-29 14:29:41,173 [http-nio-8889-exec-2] <==      Total: 1
2020-07-29 14:29:45,065 [http-nio-8889-exec-4] ==>  Preparing: select id, `name`, project_id, remark, create_by, create_time, update_by, update_time from t_api_suite where project_id=? 
2020-07-29 14:29:45,067 [http-nio-8889-exec-4] ==> Parameters: 1(Long)
2020-07-29 14:29:45,107 [http-nio-8889-exec-1] ==>  Preparing: SELECT count(0) FROM t_api WHERE project_id = ? 
2020-07-29 14:29:45,108 [http-nio-8889-exec-4] <==      Total: 2
2020-07-29 14:29:45,108 [http-nio-8889-exec-1] ==> Parameters: 1(Long)
2020-07-29 14:29:45,122 [http-nio-8889-exec-1] <==      Total: 1
2020-07-29 14:29:45,124 [http-nio-8889-exec-1] ==>  Preparing: select id, `name`, labels, project_id, api_suite_id, `method`, env_id, `domain`, `path`, `type`, remark, `status`, before_excs, req_header, req_query, req_body_data, req_body_json, req_body_type, req_extract, req_assert, debug_rsp, create_by, create_time, update_by, update_time from t_api WHERE project_id=? order by create_time desc LIMIT ? 
2020-07-29 14:29:45,126 [http-nio-8889-exec-1] ==> Parameters: 1(Long), 10(Integer)
2020-07-29 14:29:45,168 [http-nio-8889-exec-1] <==      Total: 5
