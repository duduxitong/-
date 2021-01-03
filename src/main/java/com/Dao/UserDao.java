package com.Dao;

import com.bean.User;
import com.utils.JDBCUtil;

import java.sql.SQLException;

public class UserDao {

    public boolean saveUser(User user) {
        String sql = "insert into t_user(t_email,t_pwd,t_createdtime,t_modifiedtime)"+ "values(?,?,?,?)";
        return JDBCUtil.save(sql,user.getT_email(),user.getT_pwd(),user.getT_createdtime(),user.getT_modifiedtime());
    }

    public boolean getUserbyEmailAndPass(String t_email, String t_pwd) throws SQLException {
        String user = "t_user";
        String sql = "select t_email,t_pwd from "+user;
        return JDBCUtil.loginCheck(sql,t_email,t_pwd);
    }

    public static Integer selectUserEmailCount(String email) {

        String sql="SELECT count(*) from t_user a WHERE a.t_email= ?";
        Integer count = JDBCUtil.getCount(sql,email);
        return count;

    }
}
