package com.servlet;

import com.Dao.UserDao;
import com.bean.Bill;
import com.utils.test;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.utils.JDBCUtil.getData;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取前台email和密码
        String t_email = request.getParameter("t_email");
        String t_pwd = request.getParameter("t_pwd");
        session.setAttribute("name",t_email);

        List<Bill> weekList = new ArrayList<>();
        List<Bill> billList = new ArrayList<>();
        try {
            weekList = getData((String) session.getAttribute("name"));
            billList = getData((String) session.getAttribute("name"));
            Collections.sort(billList,new test.sortByTime());
            Collections.sort(weekList,new test.sortByTime());
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        session.setAttribute("weekList",weekList);
        session.setAttribute("billList",billList);
        //根据email和密码查询申请人
        UserDao userDao = new UserDao();
        try {
            boolean user = userDao.getUserbyEmailAndPass(t_email,t_pwd);
            if(user){
                response.sendRedirect("/Interface.jsp");
            }else{
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write(" alert('账号或密码错误！');");
                writer.write("window.location.href='Login.html'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
