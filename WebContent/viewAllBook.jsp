<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Model.book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="style/cotent.css" rel="stylesheet" type="text/css" />
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="top center">
<a href="topBookServlet"><img class="logo" src="images/logo.png"></a><img src="images/tlogo.png">

	<div class="login" >
		<% if( (Boolean)session.getAttribute("loginStatus") == false ){ %>
			<a href="LoginPage.jsp">登录</a> | <a href="LoginPage.jsp">注册</a>
		<% }else{ %>
			<a href="theCenterServlet"><%= (String)session.getAttribute("userName") %> 的个人中心</a>
		<% } %>
		</div>
	</div>

<div class="login_user" >
<a href="login.html">个人中心</a> 
</div>

</div>

<%
	List<String> categoryList = new ArrayList();
	categoryList = (List<String>)session.getAttribute("categoryList");
%>

<div class="gps center">
	      <a href="./topBookServlet">漫画首页</a> |
	      <% for( int i = 0; i < categoryList.size(); i ++ ){ %>
		      <a href="./showBookServlet?category=<%= categoryList.get(i) %>"><%= categoryList.get(i) %></a> |
	      <% } %>
	      <a href="submit.html">读者投稿</a> 
</div>

<%
	List<book> bookList = (List<book>)session.getAttribute("bookList");
%>

<div class = "main1">

	<% for( int i = 0; i < bookList.size(); i++ ){ %>
	<!--一部漫画 class=section-->
	<div class = "section">
		<div class = "img">
			<a href="./viewBookInfoServlet?bookName=<%= bookList.get(i).getBookname() %>">
				<%-- <img src="upload\\<%= bookList.get(i).getFace() %>"> --%>
				<img src="<%= bookList.get(i).getFace() %>">
			</a>	
		</div>
		<!-- 介绍版块 -->
		<div class = "intro">
			<!-- 漫画名 -->
			<a href="#" class = "name"><%= bookList.get(i).getBookname() %></a>
			<!-- 作者 -->
			<div  class = "icon1 f_l">
				<a href="#" class = "author"><%= bookList.get(i).getAuthor() %></a>
			</div>
			
			<!-- 热度 -->
			<div  class = "icon2 f_r" >
				<span class ="num">关注度: <%= bookList.get(i).getLike() %></span>
			</div>
		</div>
	</div>
	<% } %>
	
</div>

<a href="#"><img id="totop"src="images/top.png"></a>
</body>
</body>
</html>