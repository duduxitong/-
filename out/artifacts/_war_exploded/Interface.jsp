<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bean.Bill" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: d0620
  Date: 2020/12/29
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="static/jquery/jquery-3.5.1.js"></script>
    <script src="static/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script src="Interface.js"></script>
    <link rel="stylesheet" href="static/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css">
    <script type="text/javascript">
        function judgeText() {
            var money = document.getElementById("money").value;
            var detailed = document.getElementById("detailed").value;
            var date = document.getElementById("date").value;

            if(money=="" && detailed=="" && date==""){
                alert("未填写信息！");
                return false;
            }else if(money=="" && detailed.length!=0 && date.length!=0){
                alert("未填写金额！");
                return false;
            }else if(detailed=="" && money.length!=0 && date.length!=0){
                alert("未填写明细！");
                return false;
            }else if(date=="" && detailed.length!=0 && money.length!=0){
                alert("未填写时间！");
                return false;
            }else if(date=="" && detailed=="" && money.length!=0){
                alert("未填写时间和明细！");
                return false;
            }else if(date=="" && detailed.length!=0 && money==""){
                alert("未填写时间和金额！");
                return false;
            }else if(detailed=="" && money=="" && date.length!=0){
                alert("未填写金额和明细！");
                return false;
            }else if(money=="0"){
                alert("金额不能为0！");
                return false;
            }
            return true;
        }

    </script>
    <style type="text/css">
        .form1{
            position: absolute;
            left: 400px;
            top: 50px;
        }
        .form2{
            position: absolute;
            left: 830px;
            top: 40px;
        }
        .form3{
            position: absolute;
            left: 1260px;
            top: 40px;
        }
        .form4{
            position: absolute;
            top: 180px;
            left: 550px;
        }
        body {
            background-image: url("img/3.jpeg"); /* 首先增加背景图 */
            /*background-size:  100% auto;*/ /* 设置背景的大小 */
            background-repeat: no-repeat; /* 将背景设置为不重复显示 */
        }
    </style>
</head>
<body>
<%
    String name = (String) session.getAttribute("name");
%>
    <div ><b>用户：${name}</b></div>
    <form action="AddServlet" method="get" onsubmit="return judgeText()" class="form1">
        <div  align="center" class="form1_1">
            <span><input type="text" id="money" name="money" placeholder="请输入金额"></span>
            <br>
            <span><input type="text" id="detailed" name="detailed" placeholder="请填写明细"></span>
            <br>
            <span><input type="text" id="date" name="date" placeholder="请根据日历选择时间"></span>
            <br>
            <script>
                $(function(){
                    $('#date').datepicker({
                        dateFormat: 'yy-mm-dd',
                    });
                });
            </script>
            <span><button type="submit" id="form_button">添加</button></span>
        </div>
    </form>
<script>
    $("#form_button").button();
</script>
    <%--<form action="QueryServlet" method="get" class="form2">
        <div align="center">
            时间:<input type="radio" name="choose" value="queryTime">&nbsp;&nbsp;
            收支:<input type="radio" name="choose" value="queryFlag">&nbsp;&nbsp;
            明细:<input type="radio" name="choose" value="queryDetailed">&nbsp;&nbsp;
        </div>
        <div align="center">
            <input type="text" name="search" placeholder="请先选择查询条件" id="queryText">
            <button type="submit">查询</button>
        </div>

    </form>--%>
<form action="QueryServlet" method="get"  class="form2">
    <fieldset style="width: fit-content" >
        <legend>查询区</legend>
        <div id="radio1" align="center">
            <input type="radio" id="choosetime" name="choose" value="queryTime" >
            <label for="choosetime">时间</label>

            <input type="radio" id="chooseflag" name="choose"  value="queryFlag" >
            <label for="chooseflag">收支</label>

            <input type="radio" id="choosedetailed" name="choose" value="queryDetailed" >
            <label for="choosedetailed">明细</label>
        </div>
        <div align="center">
            <input type="text" name="search" placeholder="请先选择查询条件" id="queryText">
            <button type="submit" id="query_btn">查询</button>
        </div>
    </fieldset>
</form>
<script>
    $( "#radio1" ).buttonset();
    $("#query_btn").button();
</script>
    <%--<form action="DeleteServlet" method="get" name ="form1" class="form3">
        <div align="center">
            时间:<input type="radio" name="delete" value="deleteTime">&nbsp;&nbsp;
            收支:<input type="radio" name="delete" value="deleteFlag">&nbsp;&nbsp;
            明细:<input type="radio" name="delete" value="deleteDetailed">&nbsp;&nbsp;
        </div>
        <div align="center">
            <input type="text" name="deleteText" placeholder="请先选择删除条件" id="deleteText">
            <button type="submit">删除</button>
        </div>

    </form>--%>
    <form action="QueryServlet" method="get"  class="form3">
        <fieldset style="width: fit-content" >
            <legend>删除区</legend>
            <div id="radio2" align="center">
                <input type="radio" id="deletetime" name="delete" value="deleteTime">
                <label for="deletetime">时间</label>

                <input type="radio" id="deleteflag" name="delete"  value="deleteFlag">
                <label for="deleteflag">收支</label>

                <input type="radio" id="deletedetailed" name="delete" value="deleteDetailed">
                <label for="deletedetailed">明细</label>
            </div>
            <div align="center">
                <input type="text" name="deleteText" placeholder="请先选择删除条件" id="deleteText">
                <button type="submit" id="delete_btn">删除</button>
            </div>
        </fieldset>
    </form>
    <script>
        $( "#radio2" ).buttonset();
        $("#delete_btn").button();
    </script>
    <script>
        $('input:radio').click(function(){
            var radio = $(this);
            if (radio.data('deletetime') == true){
                radio.prop('checked', false);
                radio.data('deletetime', false);
            } else {
                radio.prop('checked', true);
                radio.data('deletetime', true);
            }
        })
        $('input:radio').click(function(){
            var radio = $(this);
            if (radio.data('deleteflag') == true){
                radio.prop('checked', false);
                radio.data('deleteflag', false);
            } else {
                radio.prop('checked', true);
                radio.data('deleteflag', true);
            }
        })
        $('input:radio').click(function(){
            var radio = $(this);
            if (radio.data('deletedetailed') == true){
                radio.prop('checked', false);
                radio.data('deletedetailed', false);
            } else {
                radio.prop('checked', true);
                radio.data('deletedetailed', true);
            }
        })
    </script>

    <form action=""  method="get" class="form4">
        <table  border="1" cellspacing="0" align="center" >
            <caption >个人收支管理表</caption>
            <thead >
                <tr >
                    <th  height="50px" width="200px">时&nbsp间</th>
                    <th  width="200px">收/支</th>
                    <th  width="200px">金&nbsp额</th>
                    <th  width="200px">明&nbsp细</th>
                </tr>
            </thead>
            <tbody >
            <c:forEach items="${sessionScope.billList}" var="emp">
                <c:if test="${emp.money<0}">
                    <c:set var="sum1" value="${sum1+emp.money}"/>
                </c:if>
                <c:if test="${emp.money>0}">
                    <c:set var="sum2" value="${sum2+emp.money}"/>
                </c:if>
                <c:set var="sum" value="${sum1+sum2}"/>
                <c:set var="week" value="周五"/>
                <tr align="center">
                    <td height="50px">${emp.time}(${emp.week})</td>
                    <td>${emp.flag}</td>
                    <td>${emp.money}</td>
                    <td>${emp.detailed}</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot >
                <tr align="center">
                    <td height="50px" ><b>总收入：${sum2}</b></td>
                    <td height="50px" ><b>总支出：${sum1}</b></td>
                    <td height="50px" ><b>总计：</b></td>
                    <td height="50px" ><b>${sum}</b></td>
                </tr>
            </tfoot>
        </table>
        <br>
        <br>
        <br>
        <table border="1" cellspacing="0" align="center" >
            <caption>个人收支管理每周汇总表</caption>
            <%
                List<Bill> list = (List<Bill>) session.getAttribute("weekList");
                Date start = null;
                Date end = null;
                String start_week = null;
                String end_week = null;
                double money = 0;
                int flag= 0;
                int num = 0;
                for(int i=0;i<list.size();i++){
                    Bill bill = list.get(i);
                    if(flag == 0){
                        start = bill.getTime();
                        start_week = bill.getWeek();
                        end = bill.getTime();
                        end_week = bill.getWeek();
                        flag = 1;
                    }
                    money += bill.getMoney();
                    if(bill.getWeek().equals("周一")){
                        money -= bill.getMoney();
                        num++;
                        //打印这一周的数据（最初的日期、最后的日期、总收支），重新算
            %>
                <tr align="center">
                    <td height="50px" width="200px">第<%=num%>周</td>
                    <%--<td ><%=start%>(<%=start_week%>)</td>
                    <td><%=end%>(<%=end_week%>)</td>--%>
                    <td width="200px"><b>总收支:<%=money%></b></td>
                </tr>
            <%

                        money = 0;
                        start = bill.getTime();
                        start_week = bill.getWeek();
                    }else if(i==list.size()-1){
                        num++;
                        //打印这一周的数据（最初的日期、最后的日期、总收支），重新算
            %>
                <tr align="center">
                    <td height="50px" width="200px">第<%=num%>周</td>
                    <%--<td width="200px"><%=start%>(<%=start_week%>)</td>
                    <td width="200px"><%=end%>(<%=end_week%>)</td>--%>
                    <td width="200px"><b>总收支:<%=money%></b></td>
                </tr>
            <%
                    }
                    end = bill.getTime();
                    end_week = bill.getWeek();
                }
            %>
        </table>
    </form>


</body>
</html>
