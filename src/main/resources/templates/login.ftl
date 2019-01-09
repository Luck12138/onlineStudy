<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>网校在线学习平台</title>
		<#include "res.ftl">
	</head>

	<body>
		<#include "header2.ftl">
		<div class="f-main">
			<div class="types-block clearfix" style="text-align: center;">
				<h3 class="types-title">登录</h3>
				<form class="oc-form" id="infoForm" style="text-align: center;border: 1px solid #CCC;width: 600px;margin:0 auto;padding:20px;" method="post" action="/auth/doLogin">
						<li>请用：wangyangming/11111 登录</li>
						<li><label>用户名</label> 
							<input name="username"  value="" type="text"  class="input-text"  placeholder="请输入用户名" >
						</li>
						<li><label>密码</label> 
							<input id="password" name="password" type="password" class="input-text" placeholder="请输入密码" autocomplete="off" />
						</li>
						<li>
							<label style="background-color:#FFF;"></label> 
                           	<input type="checkbox" value="1" id="checkbox1" name="rememberMe" style="float: left;">
                           	<span class="text"  style="float: left;margin-left:2px;">下次自动登录</span>
                      	</li>


						<#if errcode ?? && errcode == 2>
						<li style="text-align: left;">
							<label style="background-color:#FFF;"></label>
							<span style="color:red;">用户名密码错</span>
						</li>
						</#if>
						
						<li class="clearfix" style="margin-top: 20px;">
							<input type="submit" value="登录" class="btn"style="margin-right:20px;">
							<div class="btn" onclick="window.location.href='/auth/register'">注册</div>
						</li>
					</form>
			</div>
		</div>
		<#include "footer.ftl">
	</body>
	
</html>
