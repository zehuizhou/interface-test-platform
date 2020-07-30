package com.rabbit.service.Impl;

import com.rabbit.model.Client;
import com.rabbit.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.ClearType;
import org.mockserver.model.Cookie;
import org.mockserver.model.HttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;

import org.mockserver.log.MockServerEventLog;

@Slf4j
@Service
public class MockServiceImpl implements MockService {
    /**
     * 项目启动时，初始化初始化mockserver
     */
    @PostConstruct
    public void init() {
//        log.info("初始化mockserver");
//        ClientAndServer server = new ClientAndServer(1180);
//        server.clear(request().withPath("/**"), ClearType.LOG);
//        server.when(
//                request()
//                        .withMethod("GET")
//                        .withPath("/test")
//                        .withQueryStringParameters(
//                                param("p", "1")
//                        )
//        ).respond(
//                response().header
//                        .withCookie(new Cookie("cKey", "cValue"))
//                        .withBody("test1")
//        );
    }
}


















