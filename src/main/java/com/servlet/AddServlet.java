package com.servlet;

import com.Dao.BillDao;
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
import java.text.SimpleDateFormat;
import java.util.*;

import static com.utils.JDBCUtil.getData;
import static com.utils.test.dateToWeek;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 从表单中获取信息，并将信息添加至数据库
         * */

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("name");
        String money = request.getParameter("money");
        String dateString = request.getParameter("date");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        int flag1 = date.compareTo(date2);
        if(flag1==1){
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write(" alert('日期不能超过当前日期！');");
            writer.write("window.location.href='Interface.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
        double money_1 = Double.valueOf(money.toString());
        String detailed = request.getParameter("detailed");
        String flag = null;
        if(money_1>0){
            flag = "收入";
            Bill bill = null;
            try {
                bill = new Bill(null,email,money_1,detailed,flag,date,dateToWeek(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            BillDao billDao = new BillDao();
            boolean addFlag = billDao.saveBill(bill);
        }else if(money_1<0){
            flag = "支出";
            Bill bill = null;
            try {
                bill = new Bill(null,email,money_1,detailed,flag,new Date(),dateToWeek(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            BillDao billDao = new BillDao();
            boolean addFlag = billDao.saveBill(bill);
        }
        List<Bill> billList = new ArrayList<>();
        List<Bill> weekList = new ArrayList<>();
        try {
            weekList = getData(email);
            billList = getData(email);
            Collections.sort(billList,new test.sortByTime());
            Collections.sort(weekList,new test.sortByTime());

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        session.setAttribute("billList",billList);
        session.setAttribute("weekList",weekList);
        response.sendRedirect("/Interface.jsp");
    }
}
