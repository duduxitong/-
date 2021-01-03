package com.servlet;

import com.Dao.UserDao;
import com.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String t_email = request.getParameter("t_email");
        String t_pwd = request.getParameter("t_pwd");
        User user = new User(null,t_email, t_pwd, new Date(),new Date());
        UserDao userDao = new UserDao();

        Integer count = UserDao.selectUserEmailCount(t_email);
        if(count>0){
            //数据库中已经有相同email的用户
            //通过response对象给客户端一个错误提示
            //返回注册界面
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write(" alert('申请注册的email已经被占用！');");
            writer.write("window.location.href='Register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }else{
            //flag是否注册成功
            boolean flag = userDao.saveUser(user);
            if(flag){
                //注册成功就跳转到登录界面  重定向
                response.sendRedirect("/Login.html");
            }else{
                //注册失败就返回注册界面   请求转发
                RequestDispatcher dispatcher = request.getRequestDispatcher("Register.html");
                dispatcher.forward(request,response);
            }
        }




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
