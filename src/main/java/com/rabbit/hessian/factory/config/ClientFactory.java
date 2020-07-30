package com.rabbit.hessian.factory.config;


import com.caucho.hessian.client.HessianProxyFactory;
import com.rabbit.hessian.factory.service.ClientTestService;
import com.rabbit.hessian.factory.service.ClientUiTestNewService;
import com.rabbit.hessian.factory.service.ClientUiTestService;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {
    public ClientUiTestService clientUiTestService(String ip,int port) throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        ClientUiTestService facade = (ClientUiTestService) factory.create(ClientUiTestService.class, "http://" + ip + ":" + port + "/hessian/clientUiTestService");
        return facade;
    }

    public ClientTestService clientTestService(String ip, int port) throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        ClientTestService facade = (ClientTestService) factory.create(ClientTestService.class, "http://" + ip + ":" + port + "/hessian/clientTestService");
        return facade;
    }

    public ClientUiTestNewService clientUiTestNewService(String ip, int port) throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        ClientUiTestNewService facade = (ClientUiTestNewService) factory.create(ClientUiTestNewService.class, "http://" + ip + ":" + port + "/hessian/clientUiTestNewService");
        return facade;
    }
}
