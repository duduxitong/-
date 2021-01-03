package com.servlet;

import com.bean.Bill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.utils.JDBCUtil.getData;

@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        /*
        * getData()参数加一个Object...args,传入多种参数(date/String)
        * 获取前端按钮value参数
        * 根据Object...args的值进行判断，if/else语句，
        * 在对读取判断进行修改，读取双重判断的信息
        * 在前端显示
        * */
        String radio = null;
        radio = request.getParameter("choose");
        String button = null;
        button = request.getParameter("search");
        List<Bill> billList = new ArrayList<>();
        try {
            billList = getData((String) session.getAttribute("name"),radio,button);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        session.setAttribute("billList",billList);
        response.sendRedirect("/Interface.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
