package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.util.PageUtil;
import com.vsc.guest_assurance.vo.BackendQuestionListVo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
public class QuestionService {

    public PageBean<BackendQuestionListVo> list(Integer page, Integer size) throws IOException {

        //ClassPathResource classPathResource = new ClassPathResource("file/faqs.xml");
        //InputStream inputStream = classPathResource.getInputStream();
        //List<BackendQuestionListVo> listVos = new ArrayList<>();
        //try {
        //    Document document = new SAXReader().read(inputStream);
        //    String xpathExpression = "/faqs/list";
        //    List<Element> elements = document.selectNodes(xpathExpression);
        //    for (Element element : elements) {
        //        BackendQuestionListVo backendQuestionListVo = new BackendQuestionListVo();
        //        String question = element.attributeValue("question");
        //        String answer = element.attributeValue("answer");
        //        backendQuestionListVo.setQuestion(question);
        //        backendQuestionListVo.setAnswer(answer);
        //        listVos.add(backendQuestionListVo);
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //
        //long total;
        //List<BackendQuestionListVo> items = new ArrayList<>();
        //if (listVos == null || listVos.size() == 0) {
        //    total = 0;
        //} else {
        //    total = listVos.size();
        //    items = PageUtil.getData(page, size, listVos);
        //}
        //
        //return new PageBean<>(page, size, total, items);
        return null;
    }
}
