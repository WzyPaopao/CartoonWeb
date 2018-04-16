<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/submit.css" rel="stylesheet" type="text/css" />
<style type="text/css" src="JavaScript/jquery-3.2.1.min.js"></style>
<title>新增漫画</title>
</head>
<body>
	<div class="top center">
		<a href="topBookServlet"><img class="logo" src="images/logo.png"></a><img src="images/tlogo.png">
	</div>

	<div id="main">
	
		<div class="set">
			<p>请选择您的作品上传（注：作品须为作者原创）</p>
	
			<!-- <form action="http://localhost:8080/Controller.uploadServlet" enctype="multipart/form-data" method="post"> -->
			<form action="changeInfoBookServlet" method="post">
			<!-- <form action="testSetvlet" enctype="multipart/form-data" method="post"> -->
				<div>
					<label>漫画名</label>
					<input type = "text" placeholder="输入漫画" id="bookName" name="bookName" class = "form">
				</div>
				<label>内容简介</label>
				<input type = "textarea" placeholder="漫画主要内容" id="intro" name="introduction" class = "form">
				<div>选择漫画类别</div>
				<select class ="form" name="category">
				  	<option value="少年热血">少年热血</option>
				  	<option value="少女爱情">少女爱情</option>
				  	<option value="爆笑喜剧">爆笑喜剧</option>
				  	<option value="科幻灵异">科幻灵异</option>
				  	<option value="耽美恋爱">耽美恋爱</option>
				  	<option value="武侠格斗">武侠格斗</option>
				</select>
				
				<input type="submit" name="Button1"  value="提交" class = "btn" id="Button" /> 
			</form>
	
			<form action="testSetvlet" enctype="multipart/form-data" method="post">
				<div>上传漫画封面</div> 
				<input id="File1" runat="server" name="myFile" type="file"  >  
				<input type="submit" name="Button1"  value="提交" class = "btn" id="Button" /> 
			</form>	
		</div>
	</div>

</body>
</html>