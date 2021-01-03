package com.utils;

import com.bean.Bill;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JDBCUtil {
    private static String mysqlDriver = "com.mysql.jdbc.Driver";
    private static String url ="jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=GMT%2B8";
    private static String user = "root";
    private static String password = "root";

    public static Connection getMysqlConn() {
        try {
            Class.forName(mysqlDriver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //保存对象方法
    public static boolean save(String sql, Object...args){
        Connection conn = null;
        PreparedStatement ps =null;
        Integer count = null;
        conn = JDBCUtil.getMysqlConn();
        try{
            conn = JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement(sql);

            if(args!=null&&args.length>0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);

                }
            }
            //返回更新的记录数
            count= ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps,null);
        }
        return  count!=null&&count>0?true:false;
    }

    public static boolean delete(String name, Object...args) throws SQLException {
        Connection conn = JDBCUtil.getMysqlConn();
        String sql = null;
        Integer count = 0;
        String flag1 = null;
        flag1 = (String) args[0];
        String flag2 = null;
        flag2 = (String) args[1];
        if("deleteTime".equals(flag1)){
            sql = "delete from t_bill where bill_time = '"+flag2+"' and bill_email = '"+name+"' ";
        }else if("deleteFlag".equals(flag1)){
            sql = "delete from t_bill where bill_flag = '"+flag2+"' and bill_email = '" +name+ "'";
        }else if("deleteDetailed".equals(flag1)){
            sql = "delete from t_bill where bill_detailed = '"+flag2+"' and bill_email = '" +name+ "' ";
        }
        PreparedStatement ps = null;
        if(flag1!=null && flag2!=null){
            ps = conn.prepareStatement(sql);
            count = ps.executeUpdate();
        }

        close(conn, ps, null);
        return count>0?true:false;
    }

    public static boolean loginCheck(String sql, String email, String pwd) throws SQLException {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        conn = JDBCUtil.getMysqlConn();
        ps = conn.prepareStatement(sql);
        String t_email = null;
        String t_pwd = null;
        rs = ps.executeQuery();
        int flag = 0;
        while(rs.next()){
            t_email = rs.getString("t_email");
            t_pwd = rs.getString("t_pwd");
            if(email.equals(t_email)&&pwd.equals(t_pwd)) {
                flag = 1;
                break;
            }else {
                flag = 0;
            }
        }
        if(flag == 1){
            return true;
        }else{
            return false;
        }
    }

    public static List<Bill> getData(String name, Object...args) throws SQLException, ParseException {
        List<Bill> billList = new ArrayList<>();
        Connection conn = JDBCUtil.getMysqlConn();
        String sql = "select bill_email,bill_time,bill_flag,bill_money,bill_detailed,bill_week from t_bill";
        ResultSet rs = null;
        PreparedStatement statement = conn.prepareStatement(sql);
        rs = statement.executeQuery();
        if(args!=null&&args.length>0) {
            String flag = (String) args[0];
            String flag_1 = (String) args[1];
            while(rs.next()) {
                String email = rs.getString("bill_email");
                String condition = null;
                if ("queryTime".equals(flag)) {
                    condition = rs.getString("bill_time");
                } else if ("queryFlag".equals(flag)) {
                    condition = rs.getString("bill_flag");
                } else if ("queryDetailed".equals(flag)) {
                    condition = rs.getString("bill_detailed");
                } else if(flag==null){
                    condition = null;
                }
                if ((email.equals(name) && (condition==null || flag_1==null))  ||  (email.equals(name) && condition.equals(flag_1) )) {
                    Bill bill = new Bill();
                    bill.setTime(rs.getDate("bill_time"));
                    bill.setFlag(rs.getString("bill_flag"));
                    bill.setMoney(rs.getDouble("bill_money"));
                    bill.setDetailed(rs.getString("bill_detailed"));
                    bill.setWeek(rs.getString("bill_week"));
                    billList.add(bill);
                } else {
                    continue;
                }
            }
        }else{
            while(rs.next()) {
                String email = rs.getString("bill_email");
                if (email.equals(name)) {
                    Bill bill = new Bill();
                    bill.setTime(rs.getDate("bill_time"));
                    bill.setFlag(rs.getString("bill_flag"));
                    bill.setMoney(rs.getDouble("bill_money"));
                    bill.setDetailed(rs.getString("bill_detailed"));
                    bill.setWeek(rs.getString("bill_week"));
                    billList.add(bill);
                } else {
                    continue;
                }
            }
        }

        return billList;
    }

    //查询记录数量
    public static Integer getCount(String sql, Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Integer count=null;

        try{
            conn= JDBCUtil.getMysqlConn();
            ps = conn.prepareStatement(sql);

            if(args!=null&&args.length>0) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);

                }
            }
            rs = ps.executeQuery();

            while (rs.next()){

                count = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn, ps, rs);
        }
        return count;
    }



}

