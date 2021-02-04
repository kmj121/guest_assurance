package com.vsc.guest_assurance;

import com.vsc.guest_assurance.service.StoresService;
import com.vsc.guest_assurance.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Configuration       //1.主要用于标记配置类，兼备Component的效果
@EnableScheduling    // 2.开启定时任务
@Transactional(isolation = Isolation.READ_COMMITTED)
public class StaticScheduleTask {
    private static Logger logger = LoggerFactory.getLogger(StaticScheduleTask.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private StoresService storesService;


    /**
     * 定时任务--自动更新用户数据
     * 时间：每天0时0分10秒执行
     */
    //@Scheduled(cron = "10 0 0 * * ?")
    //public void configureTasks() {
    //    logger.info("===============自动更新用户数据定时任务开始================");
    //    usersService.updateUsers();
    //    logger.info("===============自动更新用户数据定时任务结束================");
    //}

    ///**
    // * 定时任务--自动清除临时文件
    // * 时间：每天15分15秒执行
    // */
    //@Scheduled(cron = "15 15 * * * ?")
    //public void txClearTmpFiles() {
    //    logger.info("===============自动清除临时文件定时任务开始================");
    //    attachmentsService.clearTmpFiles();
    //    logger.info("===============自动清除临时文件定时任务开始================");
    //}

    /**
     * 定时任务--自动更新用户数据
     * 时间：每天0时0分10秒执行
     */
    @Scheduled(cron = "0 45 * * * ?")
    public void updateStores() throws Exception {
        logger.info("===============自动更新门店数据定时任务开始================");
        storesService.updateStores();
        logger.info("===============自动更新门店数据定时任务结束================");
    }
}
