package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.rabbit.dao.TStepApiMapper;
import com.rabbit.dto.StepUiNewDto;
import com.rabbit.model.*;
import com.rabbit.model.po.Action;
import com.rabbit.model.po.ApiParam;
import com.rabbit.service.*;
import com.rabbit.utils.MyStringUtils;
import com.rabbit.utils.apitest.ApiKeywords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.TApiMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TApiServiceImpl implements TApiService {

    @Resource
    private TApiMapper tApiMapper;
    @Resource
    private TFileInfoService fileInfoService;
    @Autowired
    private TTestDatabeseService testDatabeseService;
    @Resource
    private RequestExecutorServer requestExecutorServer;

    @Resource
    private TStepApiMapper stepApiMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        List<TStepApi> byTypeAndSourceId = stepApiMapper.findByTypeAndSourceId(1, id);
        if (byTypeAndSourceId != null && byTypeAndSourceId.size() > 0) {
            throw new IllegalArgumentException("该接口已经被用例引用，请先删除用例");
        }
        fileInfoService.deleteBySourceTypeAndSourceId(2, id);
        return tApiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TApi record) {
        return tApiMapper.insert(record);
    }

    @Override
    public int insertSelective(TApi record) {
        return tApiMapper.insertSelective(record);
    }

    @Override
    public TApi selectByPrimaryKey(Long id) {
        return tApiMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TApi record) {
        return tApiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TApi record) {
        return tApiMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<TApi> findByAllwithPage(int page, int pageSize, TApi tApi) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(tApiMapper.findByAll(tApi));
    }

    @Override
    public List<TApi> findByProjectId(Long projectId) {
        return tApiMapper.findByProjectId(projectId);
    }

    @Override
    public List<TApi> findByNameAndProjectId(String name, Long projectId) {
        return tApiMapper.findByNameAndProjectId(name, projectId);
    }

    @Override
    public List<TApi> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId) {
        return tApiMapper.findByNameAndProjectIdAndIdNot(name, projectId, notId);
    }


    @Override
    public TApiResult excApi(TApi api, Map<String, Object> gVars, Map<String, Object> caseVars, List<ApiParam> params) {
        return requestExecutorServer.executeHttpRequest(api, gVars, caseVars, params);
    }

    @Override
    public Result runAction(Action action, Map<String, Object> gVars, Map<String, Object> caseVars) {
        action.setParams(MyStringUtils.replaceFromMap(action.getParams(), caseVars));
        StringBuffer errMsg = new StringBuffer();
        try {
            Class<?> keywordsClass = new ApiKeywords().getClass();
            ApiKeywords keywords = (ApiKeywords) keywordsClass.newInstance();
            try {
                Method method;
                Result result = new Result();
                if (action.getActionType().trim().equals("apiAction1")) {
                    //  数据库类型关键字修改参数
                    TTestDatabese testDatabese = testDatabeseService.selectByPrimaryKey(action.getMainParamId().intValue());
                    method = keywordsClass.getMethod(action.getAction(), TTestDatabese.class, String.class, Map.class);
                    result = (Result) method.invoke(keywords, testDatabese, action.getParams(), caseVars);
                } else if (action.getActionType().trim().equals("apiAction2")) {
                    //  文件操作
                    String absolutePath = fileInfoService.getAbsolutePath(1, 1L, "");
                    File clientFile = new File(absolutePath);
                    if (clientFile == null) {
                        result = Result.fail("没获取到需要上传的文件，请检查");
                    } else {
                        method = keywordsClass.getMethod(action.getAction(), String.class, Map.class, File.class);
                        result = (Result) method.invoke(keywords, action.getParams(), caseVars, clientFile);
                    }
                } else {
                    method = keywordsClass.getMethod(action.getAction(), String.class, Map.class, TTestDatabese.class);
                    result = (Result) method.invoke(keywords, action.getParams(), caseVars, null);
                }
                if (result.getFlag()) {
                    log.info(result.getMsg());
                    log.info("执行步骤【{}】结束", action.getName());
                    return Result.success(result.getMsg());
                } else {
                    String errorLog = "执行步骤【" + action.getName() + "】关键字【" + action.getAction() + "】失败：原因：" + result.getMsg();
//                    log.error("执行步骤【{}】关键字【{}】失败：原因：{}", stepUi.getName(), stepUi.getAction(), result.getMsg());
                    return Result.fail(result.getMsg(), errorLog);
                }
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();
                errMsg = errMsg.append("执行关键字【" + action.getAction() + "】异常:");
                if (targetException instanceof NullPointerException) {
                    errMsg.append("请检查参数，有必填项为null值");
                } else if (targetException instanceof NoSuchMethodException) {
                    errMsg.append("没有找到关键字方法");
                } else if (targetException instanceof MySQLSyntaxErrorException) {
                    errMsg.append("执行的Sql错误");
                } else {
                    errMsg.append("未知异常:" + targetException.getMessage());
                    targetException.printStackTrace();
                }
                String errorLog = "执行步骤【" + action.getName() + "】关键字【" + action.getAction() + "】失败：" + errMsg.toString();
//                log.error("执行步骤【{}】关键字【{}】失败：{}", stepUi.getName(), stepUi.getAction(), errMsg.toString());
                return Result.fail(errMsg.toString(), errorLog);
            }
        } catch (Exception e) {
            errMsg = errMsg.append("获取关键字【" + action.getAction() + "】异常:");
            if (e instanceof ClassNotFoundException) {
                errMsg.append("获取不到keywordsClass");
            } else if (e instanceof NoSuchMethodException) {
                errMsg.append("没有找到关键字方法");
            } else if (e instanceof InstantiationException) {
                errMsg.append("获取关键字实例异常 ");
            } else if (e instanceof IllegalAccessException) {
                errMsg.append("关键字方法没有访问权限");
            } else {
                errMsg.append("未知异常:" + e.getMessage());
                e.printStackTrace();
            }
//            log.error(errMsg.toString());
            return Result.fail(errMsg.toString(), errMsg.toString());
        }
    }
}












