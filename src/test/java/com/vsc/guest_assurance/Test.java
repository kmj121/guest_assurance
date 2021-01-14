package com.vsc.guest_assurance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Description
 * @Author Roger
 * @Date 2020/11/5
 */
public class Test {
    public static void main(String[] args) {
        //加载驱动类
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/guest_assurance?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT","root","kmj123");
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/guest_assurance?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai","root","kmj123");
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
