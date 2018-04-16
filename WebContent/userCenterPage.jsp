<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.users" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/user.css" rel="stylesheet" type="text/css" />
<script src="JavaScript/jquery-3.2.1.min.js"></script>
</head>
<body>

	<div class="top center">
		<a href="topBookServlet">
			<img class="logo" src="images/logo.png">
		</a>
		<img src="images/ttlogo.png">
	
		<div class="login" >
			<% if( (Boolean)session.getAttribute("loginStatus") == false ){ %>
				<a href="LoginPage.jsp">登录</a> | <a href="LoginPage.jsp">注册</a>
			<% }else{ %>
				<a href="theCenterServlet"><%= (String)session.getAttribute("userName") %> 的个人中心</a>
			<% } %>
			</div>
		</div>

	</div>

	<!--内容-->

	<%
		users theuser = (users)session.getAttribute("theuser");
		List<book> bookList = (List<book>)session.getAttribute("bookList");
	
	%>

	<div class = "nav">
		<div class = "theleft">
			<div class = "data" onclick = "on1(this)">个人资料</div>
			<!-- <span></span> -->
			<div class = "works" onclick = "on2(this)">我的漫画</div>
		</div>
		
	<!-- 	<div class = "theright"> -->
				<!--个人资料-->
				<div id = "data1">
					<div class = "thetop">
						<span class = "bold">个人信息</span>
						<span class = "edit f_r" onclick = "change_me()">编辑</span>
					</div>

					<div class = "thebottom">
						<div>
							<div class = "f_l bold">昵称：</div>
							<!--获取昵称-->
							<div class = "  name"><%= theuser.getUsername() %></div>
						</div>
							
						<div>
							<div class = "f_l bold" >性别：</div>
							<!--获取性别-->
							<div class = "sex "><%= theuser.getUsersex() %></div>
						</div>
							
						<div>
							<div class = "f_l bold">介绍：</div>
							<!--获取介绍-->
							<div class = " self"><%= theuser.getSelfIntroduction() %></div>
						</div>
					</div>	
				</div>

				<!--修改个人资料-->
				
				<div id = "change">
					<div>
						<div class = "f_l bold">昵称：</div>
						<!--获取昵称-->
						<input type = "text" id="theName" value="<%= theuser.getUsername() %>" class = "form" placeholder="输入昵称">
					</div>
							
					<div>
						<div class = "f_l bold" >性别：</div>
						<!--获取性别-->
						<input type = "text" id="theSex" value="<%= theuser.getUsersex() %>" placeholder="输入男或者女" class = "form">
					</div>
						
					<div>
						<div class = "f_l bold">介绍：</div>
						<!--获取介绍-->
						<input type = "text" id="theSelf" value="<%= theuser.getSelfIntroduction() %>" placeholder="输入个人介绍" class="form">
					</div>

					<div class = "edit1 f_l" onclick = "change_ag()">保存</div>
				</div>
				


				<!--个人作品-->
				<div id = "works1">
					<!--普通读者显示的页面-->
					<p class = "ordinary light">您还没有作者资格哦！若有投稿意愿，请联系我们！24242414@qq.com</p>

					<!--作者显示的页面-->
					<div>
						<div class = "thetop">
						<span class = "bold">我的漫画</span>
						</div>
						
						<% for( int i = 0; i < bookList.size(); i ++ ){ %>
							<div class = "item">
								<span class = "getmore"><%= bookList.get(i).getBookname() %></span>
								<div class = "more">
									<a href="updateBookPage.jsp">新增</a>
									
									<% for( int j = 0; j < bookList.get(i).getDic().size(); j ++ ){ %>
										<a href="">第<%= j + 1 %>话: <%= bookList.get(i).getDic().get(j).getChaptername() %></a>
									<% } %>
	
								</div>
							</div>
						<% } %>
						
						<a href="UploadBook.jsp" class = "bold">+新建漫画</a>
					</div>
					</div>
					
				</div>
	<!-- 	</div> -->
	</div>
	





	<img class = "q" src="images/user.png">

	

	<script type="text/javascript">
		var data = document.getElementsByClassName("data");
		var works = document.getElementsByClassName("works");
		var theData = data[0];
		var theWorks = works[0];
		var data1 = document.getElementById("data1");
		var works1 = document.getElementById("works1");
		var change = document.getElementById("change");
		
		var index = 1;

		function on1(node){
			if (index == 0) {
				
				node.color = "red";
				node.style.borderRight = "5px solid #FED61A";
				theWorks.style.borderRight = "5px solid white";
				data1.style.display = "block";
				works1.style.display = "none";
				index = 1;
			}
		}

		function on2(node){
			if (index == 1) {
				node.style.borderRight = "5px solid #FED61A";
				theData.style.borderRight = "5px solid white";
				change.style.display = "none";
				data1.style.display = "none";
				works1.style.display = "block";
				index = 0;
			}
		}

		function change_me(){
			data1.style.display = "none";
			change.style.display = "block";
		}
		function change_ag(){
			data1.style.display = "block";
			change.style.display = "none";
			
			var url = "changeUserInfoServlet?name=" + $(theName).val() + "&sex=" + $(theSex).val() + "&self=" + $(theSelf).val();
			//alert( url );
			window.location.href = url;
		}

		
	</script>

</body>
</html>