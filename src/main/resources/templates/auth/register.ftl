<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>在线网校学习平台</title>
        <#include "../common/res.ftl">
	</head>

	<body>
		<#include "../common/header2.ftl">
		<div class="f-main">
			<div class="types-block clearfix" style="text-align: center;">
				<h3 class="types-title">注册</h3>
				<form id="registerForm" method="post" action="/auth/doRegister" class="oc-form"  style="text-align: center;border: 1px solid #CCC;width: 600px;margin:0 auto;padding:20px;" >
						<li><label>用户名</label> 
							<input maxlength="20" id="username" name="username"  type="text"  class="input-text"  placeholder="请输入用户名（英文数字）" >
						</li>
						<li><label>密码</label> 
							<input maxlength="20" id="password" name="password" type="password" class="input-text" placeholder="请输入密码" autocomplete="off" />
						</li>

						<li id="errorMsg" class="clearfix" style="display: none;color:red;">用户名密码不能为空</li>
						<li class="clearfix" style="margin-top: 30px;">
							<input type="button" value="注册保存" class="btn" onclick="doSubmit()"/>
						</li>
						<li>
							<a style="float: left;" href="/auth/login">已有账号，去登录</a>
						</li>
					</form>
			</div>
		</div>
		<#include "../common/footer.ftl">
	</body>
        <script type="text/javascript">
                function doSubmit(){
                    var username = $('#username').val();
                    var password = $('#password').val();

                    //验证用户名
                    if(!oc.enNumValid(username)){
                        $('#errorMsg').show();
                        $('#errorMsg').html("用户名只能为英文或数字");
                        return;
                    }

                    //验证密码
                    if(oc.isEmpty(password) || password.length < 6){
                        $('#errorMsg').show();
                        $('#errorMsg').html("密码至少6位");
                        return;
                    }

                    //提交注册
                    $('#registerForm').ajaxSubmit({
                        datatype : 'json',
                        success : function(respText) {
                            var resp = $.parseJSON(respText);
                            if (resp.errcode == 0) {
                                $('#errorMsg').show();
                                $('#errorMsg').html("注册成功！3s后跳转到登陆页！");
                                setTimeout(function(){
                                    window.location.href="/auth/login";
                                },3000)
                            } else if(resp.errcode == 1) {
                                $('#errorMsg').show();
                                $('#errorMsg').html("用户名已被注册，请更换！");
                            }else if(resp.errcode == 2){
                                $('#errorMsg').show();
                                $('#errorMsg').html("验证码输入错误！");
                                reloadIndityImg('indeityImgRegister');
                            }
                        },
                        error : function(xhr) {

                        }
                    });

                }

		
	</script>
</html>
