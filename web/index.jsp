<%--
  Created by IntelliJ IDEA.
  User: d0620
  Date: 2020/12/26
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="static/jquery/jquery-3.5.1.js"></script>
    <script src="static/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script src="index.js"></script>
    <link rel="stylesheet" href="static/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css">
    <style type="text/css">
      #login-box {
        /*border: 1px solid grey;*/
        width: 30%;
        text-align: center;
        margin: 0 auto;
        margin-top: 15%;
        background: #00000080;
        padding: 20px 50px;
      }
      #login-box h1 {
        color: white;
      }
      #login-box button {
        border: 0; /* 取消按钮的边界 */
        width: 150px; /* 设置合适的按钮的长和宽 */
        height: 30px;
        margin-top: 18px; /* 设置合适的上部外框的宽度，增加与上面的password框的距离 */
        font-size: 18px; /* 修改按钮文字的大小 */
        color: white; /* 修改按钮上文字的颜色 */
        border-radius: 25px; /* 将按钮的左右边框设置为圆弧 */
        background-image: linear-gradient(to right, #00dbde 0%, #fc00ff 100%); /* 为按钮增加渐变颜色 */
      }
      body {
        background-image: url("//www.bing.com/th?id=OHR.LargestCave_ZH-CN2069899703_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=HpEdgeAn"); /* 首先增加背景图 */
        background-size:  100% auto; /* 设置背景的大小 */
        background-repeat: no-repeat; /* 将背景设置为不重复显示 */
      }
    </style>
  </head>
  <body class="index_body">
        <div id="login-box">
          <h1>个人收支管理系统</h1>

          <button><a href="/Register.html">Regist</a></button>
          <button><a href="/Login.html">Login</a></button>
        </div>
  </body>
</html>
