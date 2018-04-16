<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Model.book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>快看漫画网</title>
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
	
	<%
		List<String> categoryList = new ArrayList();
		categoryList.add("少年热血");
		categoryList.add("少女爱情");
		categoryList.add("爆笑喜剧");
		categoryList.add("科幻灵异");
		categoryList.add("耽美恋爱");
		categoryList.add("武侠格斗");
		
		/* for( int i = 0; i < categoryList.size(); i++ ){
			System.out.println("-------category is " + categoryList.get(i));
		} */
		
		session.setAttribute("categoryList", categoryList);
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
	
	<div class="main">
	
		<div id = "container">
			<% for( int i = bookList.size()-1; i > bookList.size()-5; i -- ){ %>
				<div class = "image"><a href="#"><img src="<%= bookList.get(i).getFace() %>"></a></div>
			<% } %>
		</div>
	
		<div  class = "border1">
			<ul class = "ul">
				<li><strong>
				热门排行榜  </strong>
				</li>
				
				<% for( int i = bookList.size()-1; i >= 0; i -- ){
					
					if( i-7 >= 0 ){
				%>
					<li ><span class = "hot"><%= 10-i %></span>
						<a href="viewBookInfoServlet?bookName=<%= bookList.get(i).getBookname() %>"><%= bookList.get(i).getBookname() %></a>
					</li>
				<% 
					}
					else{
				%>
						<li ><span class = "hot_"><%= 10-i %></span>
						<a href="viewBookInfoServlet?bookName=<%= bookList.get(i).getBookname() %>"><%= bookList.get(i).getBookname() %></a>
						</li>
				<%
					}
				}
				%>
				
			</ul>
		</div>
	</div>

	
	
	
	
	<div class="bottom"><br>版权所有，翻版必究</div>
	
	
	
	<script type="text/javascript">
		var lb_num = 4;
		var index = 0;
		var images = document.getElementsByClassName('image');
		var circles = document.getElementsByClassName('circle');
		window.onload = function (){
			lb();
			onMose();
		}
		function lb(){
			setInterval(
				function(){
				 	show(index % lb_num);
					index++;
				},
				3000
				);
		}
		
		function show(num){
			images[num].style.opacity = "1";
			if( num == 0)
			{
			for(var i = 1;i < images.length;++i){
				 		images[i].style.opacity = "0";
				 }
			}
				 
		}
		
		
		function onMose(){
		
			circles[1].addEventListener("mouseover",show(1),false);
			circles[2].addEventListener("mouseover",show(2),false);
			circles[3].addEventListener("mouseover",show(3),false);
		
		}
	
	</script>
</body>
</html>