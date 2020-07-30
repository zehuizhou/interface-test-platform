package com.rabbit.utils;

import com.rabbit.model.TJmeterExcDetail;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {
    public static Document readXml(String filePath) throws Exception {
        return readXml(new File(filePath));
    }


    public static Document readXml(File file) throws Exception {
        SAXReader reader = new SAXReader();
        return reader.read(file);
    }

    public static Document readXml(InputStream inputStream) throws Exception {
        SAXReader reader = new SAXReader();
        return reader.read(inputStream);
    }

    public static String getAttribute(Element element, String attrName) {
        return element.attributeValue(attrName);
    }

    public static String getText(Element element) {
        return element.getText();
    }

    public static List<Element> getElements(Element element, String tagName) {
        return element.elements(tagName);
    }

    /**
     * 根据节点名称获取子节点数据
     */
    public static String getElementText(String resource, String qName) throws Exception {
        Element root = readXml(resource).getRootElement();
        String text = root.elementText(qName);
        return text.trim();
    }

    /**
     * 根据节点名称获取子节点的子节点数据
     */
    public static String getElementText(String resource, String qName, String SubQName) throws Exception {
        Element root = readXml(resource).getRootElement();
        String text = root.element(qName).elementText(SubQName);
        return text.trim();
    }

    /**
     * 根据节点名称获取子节点的子节点的数据列表
     */
    public static List<String> getElementTextList(String resource, String qName) throws Exception {
        List<Element> elementList = readXml(resource).getRootElement().element(qName).elements();
        List<String> list = new ArrayList<>();
        for (Element e : elementList) {
            list.add(e.getTextTrim());
        }
        return list;
    }

    /**
     * 根据文件获取jmeter执行日志list对象
     */
    public static List<TJmeterExcDetail> getJmeterExcDetail(String resource) throws Exception {
        List<TJmeterExcDetail> jmeterExcDetailList = new ArrayList<>();
        List<Element> elementList = readXml(resource).getRootElement().elements();
        for (Element element : elementList) {
            TJmeterExcDetail jmeterExcDetail = new TJmeterExcDetail();
            jmeterExcDetail.setName(DateUtil.stampToDate(element.attributeValue("ts")));
            jmeterExcDetail.setMetaResponseTime(Long.valueOf(element.attributeValue("t")));
            jmeterExcDetail.setName(element.attributeValue("lb"));

            List<Element> elements = element.elements(element.getQName());
            if (elements.size() > 1) {
                element = elements.get(elements.size() - 1);
            }

            jmeterExcDetail.setMetaStatusCode(element.attributeValue("rc"));
            jmeterExcDetail.setMetaUrl(element.elementText("java.net.URL"));
            jmeterExcDetail.setMetaMethod(element.elementText("method"));
            jmeterExcDetail.setMetaRequestHeaders(element.elementText("requestHeader"));
            jmeterExcDetail.setMetaRequestBody(element.elementText("queryString"));
            jmeterExcDetail.setMetaResponseHeaders(element.elementText("responseHeader"));
            jmeterExcDetail.setMetaResponseBody(element.elementText("responseData"));
            jmeterExcDetail.setMetaCookies(element.elementText("cookies"));
            jmeterExcDetailList.add(jmeterExcDetail);
        }

        return jmeterExcDetailList;
    }

}
