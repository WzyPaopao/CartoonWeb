<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Model.book" %>
<%@ page import="Common.SqlHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/comic.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="top center">
		<a href="topBookServlet">
			<img class="logo" src="images/logo.png">
		</a>
		<img src="images/tlogo.png">
	<div class="login" >
		<% if( (Boolean)session.getAttribute("loginStatus") == false ){ %>
			<a href="LoginPage.jsp">登录</a> | <a href="LoginPage.jsp">注册</a>
		<% }else{ %>
			<a href="theCenterServlet"><%= (String)session.getAttribute("userName") %> 的个人中心</a>
		<% } %>
		</div>
	</div>

	<%
		book thebook = (book)session.getAttribute("thebook");
		System.out.println("----------imageUrl = " + thebook.getFace());
		/* String[] strArray = thebook.getFace().split("\\.");
		String imageUrl = "upload/" + strArray[ strArray.length-2 ] + "\\." + strArray[ strArray.length-1 ]; */
	%>

	<div class = "main1">
		<div class = "comic">
			<%-- <img class = "img" src="upload\\<%= thebook.getFace() %>"> --%>
			<img class = "img" src="<%= thebook.getFace() %>">
			<div class = "intro t_r">
				<div class = "name"><%= thebook.getBookname() %></div>
				<div class = "author" id = "color"><a href=""><%= thebook.getAuthor() %></a></div>
				<div class = "C_intro">漫画简介</div>
				<p class = "con">
					<%= thebook.getIntroduction() %>
				</p>

				<!--是否关注的值来自数据库-->
				<!--游客不能关注漫画-->
				<%
					if((Boolean)session.getAttribute("loginStatus") == true){
						Boolean isFocus = (Boolean)session.getAttribute("isFocus");
				
						if( isFocus == true ){
				%>
						<input type = "submit" name="focus" class = "btn" value ="已关注" style="color:white; background:#FED61A" onclick="on(this)">
				<%
						}else{
				%>
						<input type = "button" name="focus" class = "btn" value ="关注" style="color:#FED61A; background:white" onclick="on(this)">
				<%
						} 
					}else{
				%>
						<input type = "button" name="focus" class = "btn" value ="请登录后关注" style="color:#FED61A; background:white" onclick="jump()">
				<%
					}
				%>
			</div>	

			<div class = "margin"></div>
			
		</div>
		<div class = "chapter">
		
			<% for( int i = 0; i < thebook.getDic().size(); i++ ){ %>
			<!--每一集-->
			<div class = "item">
				<span class = "theChapter">第<%= i + 1 %>章</span>
				<a href="./viewBookServlet?chapterNum=<%= i %>" class = "theName"><%= thebook.getDic().get(i).getChaptername() %></a>
			</div> 
			<% } %>

		</div>

	</div>
	<a href="#"><img id="totop"src="images/top.png"></a>
	<div class="bottom"><br>版权所有，翻版必究</div>
	

<script type="text/javascript">
function on(node){
	/* if(node.value != "已关注"){
		node.value = "已关注";
		node.style.color = "white";
		node.style.background = "#FED61A";
		
	}
	else{
		node.value = "关注";
		node.style.color = "#FED61A";
		node.style.background = "white";
	} */
	
	var url = "changeFocusServlet?value=" + node.value;
	
	window.location.href = url;
}

function jump(){
	window.location.href = "LoginPage.jsp";
}
</script>
</body>
</html>