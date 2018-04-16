<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link href="style/login.css" rel="stylesheet" type="text/css" />
</head>
<body>


      <div id = "log">
            <div id = "login">
                  <div id = "top">
                        <input class = "zc" type = "radio" name = "log" onclick = "dis1(this)"><div class = "log_zc">注册</div>
                        <input class = "dl" type = "radio" name = "log"  onclick = "dis2(this)" checked = "checked"><div class = "log_dl">登陆</div>
                  </div>
                  <div id="bottom">

                        <div id = "sign_in">
                              <div class = "blank"></div>

                          
                              <form action="addUserServlet" method="post">
                              <div>用户名</div>
                              <input class = "form" onfocus = "mouseDown(this)" onblur= "mouseUp(this)" type="text" name="newUserName" placeholder="输入用户名" >
                              <div >密码</div>
                              <input class = "form" onfocus = "mouseDown(this)" onblur= "mouseUp(this)" type="password" name="password1" placeholder="输入密码" >
                              <div>确认密码</div>
                              <input class = "form" onfocus = "mouseDown(this)" onblur= "mouseUp(this)" type="password" name="password2" placeholder="确认密码" >
                              <input class = "btn" value = "注册" type = "submit">
                              </form>
                            
                        </div>

                        <div id = "log_in">
                              <div class = "blank"></div>
                              
                              <form action="loginCheckServlet" method="post">
                              <div>用户名</div>
                              <input class = "form" onfocus = "mouseDown(this)" onblur= "mouseUp(this)" type="text" name="userName" placeholder="输入用户名" >
                              <div>密码</div>
                              <input class = "form" onfocus = "mouseDown(this)" onblur= "mouseUp(this)" type="password" name="password" placeholder="输入密码" >
                              <input class = "btn" value = "登陆" type = "submit">
                              <span class="forget"><a href="#">忘记密码？</a></span>
                              </form>
                              </div>
                              
                            
                      

                  </div>
            </div>
      </div>
      <img class = "a" src="images/a.png">

<!-- <a href="#">忘记密码?</a></li> -->



	<script type="text/javascript">
		function mouseDown(node){
		      node.style.borderColor = "red";
		}
		
		function mouseUp(node){
		      node.style.borderColor = "#ccc";
		}
		
		function dis1(node){    
		      var zc = document.getElementById("sign_in");
		      var dl = document.getElementById("log_in");
		      zc.style.display = "block";
		      dl.style.display = "none";
		
		}
		
		function dis2(node){
		      var zc = document.getElementById("sign_in");
		      var dl = document.getElementById("log_in");
		      dl.style.display = "block";
		      zc.style.display = "none";
		}
	</script>


</body>
</html>