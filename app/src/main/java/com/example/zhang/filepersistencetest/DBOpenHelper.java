package com.example.zhang.filepersistencetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOpenHelper extends AppCompatActivity {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    //    private static final String DBURL = "jdbc:mysql://192.168.137.1:3306/wifi?useSSL=FALSE";
    private static final String DBURL = "jdbc:mysql://192.168.2.22:3306/wifi";
    //    private static final String DBURL = "jdbc:mysql://172.20.10.3:3306/wifi";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "123456";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    private TextView textView;

    public static void getConn() {

        try {
            Class.forName(DBDRIVER).newInstance();
            conn = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录--用户名查询
     */
    public static String login(String account, String password) {

        getConn();
        try {
            String sql = "select * from user where people_name= '" + account + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = null;
        String ps = null;
        try {
            rs.next();
            name = rs.getString("people_name");
            ps = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
            closeAll();
            System.out.println("fail");
            return "fail";
        }
        closeAll();
        if (name.equals(account) && password.equals(ps)) {

            System.out.println("success");
            return "success";
        } else {

            System.out.println("fail");
            return "fail";
        }

    }

    /**
     * 注册
     */
    public static int updateNumber(String name, String MAC, String password) {
        int a = 0;
        getConn();
        try {

            String sql = "insert into user(people_name,MAC,password)values('" + name + "','" + MAC + "','" + password + "')";
//            stmt = conn.prepareStatement(sql);
//            a = stmt.executeUpdate();
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        System.out.println(a);
        return a;
    }

    /**
     * 修改密码
     */
    public static String updatePassword(String MAC, String password) {
        int a = 0;
        getConn();
        try {
            String sql = "update user set password='" + password + "' where MAC='" + MAC + "'";
//            stmt = conn.prepareStatement(sql);
//            a = stmt.executeUpdate();
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        closeAll();
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }


    }

    /**
     * 查询会议
     *
     * @return
     */
    public static ArrayList<ArrayList<String>> selectMeeting(String people_name) {
        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("会议名称");
        row1.add("X");
        row1.add("Y");
        lists.add(row1);
        getConn();
        try {
            String sql = "SELECT meeting_name from meeting_number where people_name_name= '" + people_name + "'";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> a = new ArrayList<>();
                a.add(rs.getString("meeting_name"));
                a.add(rs.getString("x"));
                a.add(rs.getString("y"));
                lists.add(a);
//                Log.i("a", "meetingname:" + meetingname + ",meetingadd:" + meetingadd + ",meetingdata:" + meetingdata + ",meetinghoster:" + meetinghoster);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        System.out.println(lists.toString());
        return lists;
    }

    /**
     * 查询参会人员信息
     */

    public static ArrayList<ArrayList<String>> selectNumber(String name) {
        ArrayList<ArrayList<String>> lists1 = new ArrayList<ArrayList<String>>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("姓名");
        row1.add("MAC地址");
        row1.add("X");
        row1.add("Y");
        lists1.add(row1);
        getConn();
        try {


            String sql = "SELECT people_name,mac,x,y from meeting_number where meeting_name= '" + name + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> a = new ArrayList<>();
                a.add(rs.getString("people_name"));
                a.add(rs.getString("MAC"));
                a.add(rs.getString("x"));
                a.add(rs.getString("y"));
                lists1.add(a);
//                Log.i("a", "meetingname:" + meetingname + ",meetingadd:" + meetingadd + ",meetingdata:" + meetingdata + ",meetinghoster:" + meetinghoster);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        System.out.println(lists1.toString());
        return lists1;
    }

    /**
     * 删除会议
     */
    public static String delectMeeting(String name) {
        int a = 0;
        getConn();
        try {
            String sql = "delete from meeting where meeting_name= '" + name + "'";
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        System.out.println(a);
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 修改会议
     */
    public static String updateMeeting(String name, String time, String location, String people, String count) {
        int a = 0;
        getConn();
        try {

            String sql = "update meeting set meeting_data='" + time + "',meeting_add='" + location + "',hoster='" + people + "',number='" + count + "' where meeting_name='" + name + "'";
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    /**
     * 增加会议
     *
     * @param name
     * @param time
     * @param location
     * @param people
     * @param count
     * @return
     */
    public static String insertMeeting(String name, String time, String location, String people, String count) {
        int a = 0;
        getConn();
        try {
            String sql = "insert into meeting(meeting_name,meeting_data,meeting_add,hoster,number)values('" + name + "','" + time + "','" + location + "','" + people + "','" + count + "')";
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }
    }


    /*
     *添加参会人员信息
     */
    public static String insertNumber(String meeting_name, String people_name, String MAC) {
        int a = 0;
        getConn();
        try {

            String sql = "insert into meeting_number(meeting_name,people_name,MAC)values('" + meeting_name + "','" + people_name + "','" + MAC + "')";
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        closeAll();
        System.out.println(a);
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    public static String updateUserInfo(String mac, String name, String password) {
        int a = 0;
        getConn();

        String sql = "update user set people_name='" + name + "',password='" + password + "' where MAC='" + mac + "'";
        try {
            stmt = conn.createStatement();
            a = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        System.out.println(a);
        if (a == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    public static ArrayList<ArrayList<String>> selectMeetingByPeopleName(String name) {
        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("会议名称");
        row1.add("MAC");
        row1.add("X");
        row1.add("Y");
        lists.add(row1);
        getConn();
        try {
            String sql = "select * from meeting_number where people_name='" + name + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> a = new ArrayList<>();
                a.add(rs.getString("meeting_name"));
                a.add(rs.getString("MAC"));
                a.add(rs.getString("x"));
                a.add(rs.getString("y"));
                lists.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeAll();
        System.out.println(lists.toString());
        return lists;
    }


    /**
     * 关闭数据库
     */
    public static void closeAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

