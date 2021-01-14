//package com.vsc.guest_assurance.util;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @Description
// * @Author Roger
// * @Date 2021/1/13
// */
//public class XmlParseUtil {
//    /**
//     * 读取
//     */
//    public static void read() {
//        try {
//            File xmlFile = new File("C:/Users/curry/Desktop/company.xml");
//            Document document = new SAXReader().read(xmlFile);
//            String xpathExpression = "/company/employee";
//            List<Element> elements = document.selectNodes(xpathExpression);
//            for (Element element : elements) {
//                String name = element.attributeValue("name");
//                String value = element.attributeValue("value");
//                System.out.println(name + " = " + value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 写入
//     */
//    public static void write() {
//        FileOutputStream fileOutputStream = null;
//        XMLWriter xmlWriter = null;
//        try {
//            File xmlFile = new File("C:/Users/curry/Desktop/company.xml");
//            Document document = new SAXReader().read(xmlFile);
//            // 获取根节点
//            Element root = document.getRootElement();
//            // 动态添加 <employee name="xiaohong" value="小红"/>
//            Element employee = root.addElement("employee");
//            employee.addAttribute("name", "xiaohong");
//            employee.addAttribute("value", "小红");
//            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
//            outputFormat.setEncoding("UTF-8");
//            outputFormat.setIndent(true);
//            // tab格
//            outputFormat.setIndent("    ");
//            // 换行
//            outputFormat.setNewlines(true);
//            fileOutputStream = new FileOutputStream(xmlFile);
//            xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
//            xmlWriter.write(document);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (xmlWriter != null) {
//                try {
//                    xmlWriter.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        //read();
//        write();
//    }
//}
