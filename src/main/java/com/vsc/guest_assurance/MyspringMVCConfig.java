package com.vsc.guest_assurance;

import com.vsc.guest_assurance.config.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootConfiguration
public class MyspringMVCConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenFilter tokenFilter;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    //集成swagger 加入拦截器，需要忽略掉 swagger-resources下面的请求 以及忽略掉 v2下面的请求即可
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenFilter)
                .addPathPatterns("/backend/**")
                .addPathPatterns("/commons/**")
                .excludePathPatterns("/commons/attachments/**")
                .excludePathPatterns("/common/users/login")
                //xml解析问答
                .excludePathPatterns("/backend/question/**")
                //xml解析媒体
                .excludePathPatterns("/backend/media/**")
                ///提交留言
                .excludePathPatterns("/backend/contactInformation/add")
                //点赞
                .excludePathPatterns("/backend/store/thumbsUpStores")
                .excludePathPatterns("/backend/store/thumbsUp")
                //.excludePathPatterns("/commons/icaptcha/**")
                //.excludePathPatterns("/commons/mobileCaptcha/**")
                //.excludePathPatterns("/backend/student/exportModel")
                //.excludePathPatterns("/wechat/customer/login")
                //.excludePathPatterns("/wechat/customer/register")
                //.excludePathPatterns("/wechat/customer/checkPhone")
                //.excludePathPatterns("/wechat/acceptMessage")
                //.excludePathPatterns("/wechat/information/**")
                //.excludePathPatterns("/data/**")
                //.excludePathPatterns("/dataStatistics/**")
                //.excludePathPatterns("/dataState/**")
                //.excludePathPatterns("/dict/**")
                //.excludePathPatterns("/test/**")
                //.excludePathPatterns("/testRecord/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
