<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Model.directory" %>
<%@ page import="Model.book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/look.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>

<%
	/* String imageUrl = (String)session.getAttribute("imageUrl");
	String bookName = (String)session.getAttribute("bookName"); */
	book thebook = (book)session.getAttribute("thebook");
	int chapterNum = Integer.parseInt( session.getAttribute("chapterNum").toString() ) + 1;
%>

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

	<!--内容-->

	<% if( chapterNum < thebook.getDic().size() ){ %>
	<div class = "main1">
		<!--获取章节-->
	<span class = "getChapter">第<%= chapterNum + 1 %>章</span>
	<!--图片-->
	<img class = "comic" src="<%= thebook.getDic().get(chapterNum).getImage() %>">
	
	<% session.setAttribute("chapterNum", chapterNum); %>

	<a href="previousImagePage.jsp" class = "former">上一章</a>|
	<a href="nextImagePage.jsp" class = "later">下一章</a>|
	<a href="./viewBookInfoServlet?bookName=<%= thebook.getBookname() %>" class = "ca">返回目录</a>

	</div>
	<%
	}
	else{
		chapterNum = chapterNum - 1;
		session.setAttribute("chapterNum", chapterNum);
	%>
	<div class = "main1">
		获取章节
	<span class = "getChapter">第<%= chapterNum + 1 %>章</span>
	图片
	<img class = "comic" src="images/hehe.png">

	<a href="#" class = "former">上一章</a>|
	<a href="#" class = "later">下一章</a>|
	<a href="#" class = "ca">返回目录</a>

	</div>
	
	<%} %>



	<a href="#"><img id="totop"src="images/top.png"></a>
	<div class="bottom"><br>版权所有，翻版必究</div>
	
</body>

<script type="text/javascript">
	window.onload = function(){
		var node = document.getElementsByClassName("comic");
	
		var rate = 900 / (node[0].width);
		node[0].width = node[0].width * rate ;
		node[0].height = node[0].height * rate;
	}
</script>
</html>