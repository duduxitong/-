package com.servlet;



import com.bean.Bill;
import com.utils.JDBCUtil;


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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 获取前端按钮的value,和submit中的信息
        *
        * */
        String radio = request.getParameter("delete");
        String button = request.getParameter("deleteText");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        try {
            boolean deleteFlag = JDBCUtil.delete(name,radio,button);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Bill> billList = new ArrayList<>();
        List<Bill> weekList = new ArrayList<>();
        try {
            billList = getData((String) session.getAttribute("name"));
            weekList = getData((String) session.getAttribute("name"));
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        session.setAttribute("billList",billList);
        session.setAttribute("weekList",weekList);
        response.sendRedirect("/Interface.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
