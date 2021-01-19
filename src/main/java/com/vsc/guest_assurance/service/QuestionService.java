package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.util.PageUtil;
import com.vsc.guest_assurance.vo.BackendQuestionVo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class QuestionService {

    public PageBean<BackendQuestionVo> list(Integer page, Integer size) throws IOException, DocumentException {

        //解析xml
        List<BackendQuestionVo> listVos = read();
        long total;
        List<BackendQuestionVo> items = new ArrayList<>();
        if (listVos == null || listVos.size() == 0) {
            total = 0;
        } else {
            total = listVos.size();
            items = PageUtil.getData(page, size, listVos);
        }

        return new PageBean<>(page, size, total, items);
    }

    public List<BackendQuestionVo> read() throws DocumentException, IOException {
        List<BackendQuestionVo> listVos = new ArrayList<>();
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        ClassPathResource classPathResource = new ClassPathResource("static/file/faqs.xml");
        InputStream inputStream = classPathResource.getInputStream();
        Document document = reader.read(inputStream);
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            BackendQuestionVo backendQuestionVo = new BackendQuestionVo();
            Element stu = (Element) iterator.next();
            Iterator iterator1 = stu.elementIterator();
            while (iterator1.hasNext()){
                Element stuChild = (Element) iterator1.next();
                if("question".equals(stuChild.getName())) {
                    backendQuestionVo.setQuestion(stuChild.getStringValue());
                }
                if("answer".equals(stuChild.getName())) {
                    backendQuestionVo.setAnswer(stuChild.getStringValue());
                }
            }
            listVos.add(backendQuestionVo);
        }
        return listVos;
    }
}
