<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新漫画</title>
<link href="style/submit.css" rel="stylesheet" type="text/css" />
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>


	<div class="top center">
		<a href="topBookServlet"><img class="logo" src="images/logo.png"></a><img src="images/tlogo.png">
	</div>
	

	<div id="main">
		
		<div class="set">
			<p>请选择您的作品上传（注：作品须为作者原创）</p>
			
			<form action="changeChapterNameServlet" method="post">
				<div>
					<label>标题</label>
					<input type = "text" name="chapterName" placeholder="输入章节名" class = "form">
				</div>
				<input type="submit" name="Button1"  value="确定" class = "btn" id="Button" /> 
			</form>
		<div style ="height:20px; width:100%;"></div>
				
			<form action="updateBookServlet" enctype="multipart/form-data" method="post"> 
				<input id="File1" runat="server" name="myFile" type="file"  >  
				<input type="submit" name="Button1"  value="上传" class = "btn" id="Button" /> 
			</form> 
		</div>
	</div>


</body>

</html>