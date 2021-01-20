package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.util.PageUtil;
import com.vsc.guest_assurance.vo.backend.BMediaVo;
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
public class MediaService {

    public PageBean<BMediaVo> list(Integer page, Integer size) throws IOException, DocumentException {

        //解析xml
        List<BMediaVo> listVos = read();
        long total;
        List<BMediaVo> items = new ArrayList<>();
        if (listVos == null || listVos.size() == 0) {
            total = 0;
        } else {
            total = listVos.size();
            items = PageUtil.getData(page, size, listVos);
        }

        return new PageBean<>(page, size, total, items);
    }

    public List<BMediaVo> read() throws DocumentException, IOException {
        List<BMediaVo> listVos = new ArrayList<>();
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        ClassPathResource classPathResource = new ClassPathResource("static/file/media.xml");
        InputStream inputStream = classPathResource.getInputStream();
        Document document = reader.read(inputStream);
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            BMediaVo bMediaVo = new BMediaVo();
            Element stu = (Element) iterator.next();
            Iterator iterator1 = stu.elementIterator();
            while (iterator1.hasNext()){
                Element stuChild = (Element) iterator1.next();
                if("title".equals(stuChild.getName())) {
                    bMediaVo.setTitle(stuChild.getStringValue());
                }
                if("summary".equals(stuChild.getName())) {
                    bMediaVo.setSummary(stuChild.getStringValue());
                }
                if("imageUrl".equals(stuChild.getName())) {
                    bMediaVo.setImageUrl(stuChild.getStringValue());
                }
            }
            listVos.add(bMediaVo);
        }
        return listVos;
    }
}
