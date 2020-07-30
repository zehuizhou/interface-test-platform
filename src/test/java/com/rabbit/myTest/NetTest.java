package com.rabbit.myTest;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class NetTest {

    @Test
    public void test1() throws Exception {
        String json = "{\"code\":{\"code\":\"0\",\"msg\":\"操作成功\",\"ok\":true,\"fallback\":false},\"result\":{\"id\":\"f7e2d11ead724e05a77f81d911870e71\",\"project_code\":\"KDSX\",\"project_name\":\"KDSX经销售生意系统\",\"name\":\"性能测试22966\",\"code\":\"KDSX20200303006144\",\"memo\":null,\"creator\":\"1\",\"create_at\":\"2020-03-03 13:43\",\"mender\":\"系统管理员\",\"update_at\":\"2020-03-03 13:43\",\"status\":\"1\",\"content\":\"\",\"update_startat\":null,\"update_endat\":null,\"case_type\":\"0\",\"verify_status\":\"0\",\"module\":\"性能测试\",\"labels\":null,\"parent_id\":\"\",\"expected_results\":\"\",\"testCaseSdResultBeanList\":null,\"testCaseParamList\":[],\"stepList\":null,\"apiBean\":null,\"apiId\":null,\"case_biz_memo\":\"step1111\",\"flag\":\"1\",\"startDate\":null,\"endDate\":null,\"parent_name\":\"\",\"selfCode\":null,\"reversed_steps_memo\":null}}";
        Object author =  JsonPath.parse(json).read( "$.code");
        System.out.println(author.toString());
    }

    @Test
    public void test2() throws Exception {
        String json = "{\"properties\": 1111}";
        DocumentContext context = JsonPath.parse(json);

        context.put("$", "image", 11111);   // add a new path "$.properties.image"
//        context.put("$.image", "source","image_url"); // continue to add a new path "$.properties.image.source"
        String newJson = context.jsonString();

        System.out.println(newJson);
    }

    @Test
    public void test3() throws Exception {
        String json = "[{\"c\":1,\"e\":\"evule\"},{\"c\":1,\"e\":\"evule\"}]";
        DocumentContext ext = JsonPath.parse(json);
        JsonPath p = JsonPath.compile("$[0].f");
        ext.set(p, 1111);
        String author = ext.jsonString();
        System.out.println(author);
    }

    @Test
    public void test4() throws Exception {

        String json = "[\n" +
                "    {\n" +
                "        \"ac_type\": \"A320\"" +

                "    }\n" +
                "]";
        Object read = JsonPath.read(json, "[0].ac_type");
        System.out.println(read.toString());
    }

}
