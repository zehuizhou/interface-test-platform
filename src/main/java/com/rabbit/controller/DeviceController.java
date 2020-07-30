package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.Device;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.DeviceService;
import com.rabbit.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/device")
@Api(tags = "手机设备相关接口")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 给Agent调用的接口
     * 保存Device信息
     *
     * @param device
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public ResponseInfo savaClient(@RequestBody Device device) {
        // 数据库是否有该手机
        Device dbDevice = deviceService.selectByPrimaryKey(device.getId());
        int saveRow;
        if (dbDevice == null) {
            // 首次接入的device
            saveRow = deviceService.insertSelective(device);
        } else {
            // 更新Device
            saveRow = deviceService.updateByPrimaryKeySelective(device);
        }
        return new ResponseInfo(true, "保存客户端成功");
    }

    @PostMapping("/listById")
    @ApiOperation(value = "通过id查询设备列表，仅供客户端查询")
    public ResponseInfo list(Device device) {
        List<Device> byAll = deviceService.findByAll(device);
        if (byAll.size() > 0) {
            return new ResponseInfo(true, JSONObject.toJSONString(byAll.get(0)));
        } else {
            return new ResponseInfo(true, null);
        }
    }


    @GetMapping("/listPage")
    @ApiOperation(value = "查询设备分页带参数列表")
    public ResponseInfo getPageList(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchData") String serchData) {
        JSONObject jsonObject = JSONObject.parseObject(serchData);
        Device device = JSONObject.toJavaObject(jsonObject, Device.class);
        PageInfo pageInfo = deviceService.findByAllwithPage(pageNum, pageSize, device);
        return new ResponseInfo(true, pageInfo);
    }

    /**
     * 开始控制手机
     *
     * @param deviceId
     * @return
     */
    @GetMapping("/{deviceId}/start")
    public ResponseInfo start(@PathVariable String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            return new ResponseInfo(true, new ErrorInfo(0, "设备id不能为空"));
        }

        Device device = deviceService.selectByPrimaryKey(deviceId);
        if (device == null) {
            return new ResponseInfo(true, new ErrorInfo(0, "手机不存在"));
        }
        if (device.getStatus() != Device.IDLE_STATUS) {
            return new ResponseInfo(true, new ErrorInfo(0, "手机未闲置"));
        }

        return new ResponseInfo(true, "手机为闲置");
    }

    /**
     * 获取在线的设备
     *
     * @param platform
     * @return
     */
    @GetMapping("/online/platform/{platform}")
    public ResponseInfo getOnlineDevices(@PathVariable Integer platform) {
        List<Device> onlineDevices = deviceService.getOnlineDevices(platform);
        return new ResponseInfo(true, onlineDevices);
    }

    @PostMapping("/uploadFile")
    public ResponseInfo uploadFile(MultipartFile file, HttpServletRequest request) {
        return new ResponseInfo(true,  fileUploadService.uploadFile(file, request,""));
    }

}
