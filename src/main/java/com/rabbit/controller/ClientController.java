package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.Client;
import com.rabbit.service.ClientService;
import com.rabbit.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户端相关接口
 */
@Slf4j
@RestController
@RequestMapping("/clients")
@Api(tags = "客户端相关接口")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/listPage")
    @ApiOperation(value = "获取分页带参列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        Client client = JSONObject.toJavaObject(jsonObject, Client.class);
        PageInfo pageInfo = clientService.findByAllwithPage(pageNum, pageSize, client);
        return new ResponseInfo(true, pageInfo);
    }



    @GetMapping("/listByProjectId/{id}")
    @ApiOperation(value = "获取列表")
    public ResponseInfo listByProjectId(@PathVariable long id) {
        return new ResponseInfo(true,  clientService.findByProjectId(id));
    }


//    @PostMapping("/add")
//    @ApiOperation(value = "新增")
//    public ResponseInfo savaClient(@RequestBody Client client) {
//        List<Client> clients = clientService.findByNameAndProjectId(client.getName(),client.getProjectId());
//        if (clients.size() > 0) {
//            return new ResponseInfo(false, new ErrorInfo(520, "客户端" + client.getName() + "已存在"));
//        }
//        client.setUpdateBy(UserUtil.getLoginUser().getUsername());
//        client.setCreateBy(UserUtil.getLoginUser().getUsername());
//        clientService.insertSelective(client);
//        return new ResponseInfo(true, "保存客户端成功");
//    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    public ResponseInfo editClient(@RequestBody Client client) {
        List<Client> clients = clientService.findByRemarkAndIdNot(client.getRemark(),client.getId());
        if (clients.size() > 0) {
            return new ResponseInfo(false, new ErrorInfo(520, "备注[" + client.getRemark() + "]已存在"));
        }
        client.setUpdateBy(UserUtil.getLoginUser().getUsername());
        clientService.updateByPrimaryKeySelective(client);
        return new ResponseInfo(true, "修改客户端成功");
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public ResponseInfo delClient(@RequestBody Client client) {
        clientService.deleteByPrimaryKey(client.getId());
        return new ResponseInfo(true, "删除客户端成功");
    }
}
