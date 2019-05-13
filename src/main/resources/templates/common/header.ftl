<div class="f-header">
	<div class="f-header-box clearfix">
		<a href="/"  class="logo" title="在线学习平台"></a>
		<nav class="header-nav">
			<a href="/" class="header-nav-item">首 页</a>
			<a href="/course/list" class="header-nav-item">课 程</a>
            <a href="/exam/list" class="header-nav-item">题库</a>
			<a href="/user/home" class="header-nav-item">我的</a>
			<a href="/admin/index" target="_blank" class="header-nav-item" style="width:100px;">教师端</a>
		</nav>
		
		<nav class="header-nav" style="float:right">
            <@shiro.guest>
			<a href="#myModal" class="header-nav-item"  data-toggle="modal" onclick="login();"  style="margin-right:0px;font-size:14px;">登录</a>
			<a href="#myModal" class="header-nav-item" data-toggle="modal" onclick="registe();"  style="margin-left:0px;font-size:14px;">注册</a>
			</@shiro.guest>

            <@shiro.user>
			<a href="/user/home" class="header-nav-item"  style="margin-left:0px;width:40px;height:40px;">
	        	<img id="headerUserHeader" alt="" src='<@shiro.principal property="header"/>' style="width: 35px;height: 35px;border-radius:50%;">
                <a  href="/logout" class="header-nav-item"  style="margin-left:0px;font-size:14px;">登出</a>
	        </a>
            </@shiro.user>
		

<@shiro.user>
		<!-- 登录之后显示用户的基本信息-start -->
		<div id="userdetailHtml" style="display: none;">
            <div style="padding:10px;">
                <div style="margin-top:10px;">
                    <span style="font-size: 16px;">用户名：<@shiro.principal property="username"/></span>
                </div>
                <div style="margin-top:20px;border-top:1px solid #eee;width:200px;padding-top:10px;">
                    <a class="link-a" href="/logout">
                        <span>退出</span>
                    </a>
                </div>
            </div>
        </div>
		<!-- 登录之后显示用户的基本信息-end -->
</@shiro.user>

	</div>
</div>

<@shiro.guest>
<!-- 用户登录注册modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  style="position:fixed; top:30%;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" style="font-size:18px;">×</span>
                </button>
                <h4 class="modal-title"  id="loginTitle"  style="float: left; color: #337Ab7;cursor: pointer; " onclick="login();">登 录</h4>
                <h4 class="modal-title"  id="registeTitle"  style="float: left;margin-left: 20px;cursor: pointer;" onclick="registe();">注 册</h4>
                <div class="clearfix"></div>
            </div>

            <div class="modal-body">
               	<form id="loginForm" class="form-horizontal" style="padding: 0px 20px;" method="POST" action="/auth/ajaxlogin">
                      <div class="form-group">
                          <input id="usernameHeader1"  type="text" class="form-control"  name="username" placeholder="用户名">
                      </div>
                      <div class="form-group help">
                          <input id="passwordHeader1"  type="password" class="form-control"  name="password"  placeholder="密　码">
                      </div>
                      <div class="form-group">
                          <label>
                           <input type="checkbox" value="1" id="checkbox1" name="rememberMe">
                           <span class="text" style="color: #787D82;font-size: 14px;">下次自动登录</span>
                              <span id="loginError" style="margin-left:10px;height:33px;line-height:33px;color:red;display:none;">错误信息</span>
                          </label>
                      </div>
                      <a href="javascript:void(0)">
                      	<div onclick="doAjaxLogin();" class="header-login-btn">登 录</div>
                      </a>
                  </form>

                   <form id="registeForm" method="POST" action="/auth/doRegister"  class="form-horizontal" style="padding: 0px 20px;display: none;">
                       <div class="form-group">
                           <input id="usernameHeader2"  name="username" type="text" class="form-control"   placeholder="用户名">
                       </div>
                       <div class="form-group help">
                           <input id="passwordHeader2"  name="password"  type="password" class="form-control"  placeholder="密　码">
                       </div>
                       <div class="form-group">
                           <span id="registerError" style="margin-left:10px;height:33px;line-height:33px;color:red;display:none;">错误信息</span>
                       </div>
                       <a href="javascript:void(0)">
                      	<div class="header-login-btn" onclick="doAjaxRegister()">注 册</div>
                       </a>
                   </form>
            </div>
        </div>
    </div>
</div>
</@shiro.guest>


<script type="text/javascript">
    $(function(){
        var headPhoto = $('#headerUserHeader').attr('src');
        if(headPhoto == null || headPhoto == '' || headPhoto == 'null'){
            var headPhoto = "../res/i/header.jpg";
            $('#headerUserHeader').attr('src',headPhoto);
        }


    });





<@shiro.guest>
	//登录注册实现 
        function login(){
            $('#loginTitle').css('color','#337Ab7');
            $('#loginForm').show();
            $('#registeTitle').css('color','#000');
            $('#registeForm').hide();
        }
        function registe(){
            $('#loginTitle').css('color','#000');
            $('#loginForm').hide();
            $('#registeTitle').css('color','#337Ab7');
            $('#registeForm').show();
        }


        function doAjaxLogin(){
		var username = $('#usernameHeader1').val();
		var password = $('#passwordHeader1').val();
		
		//验证用户名 
		if(!oc.enNumValid(username)){
			$('#loginError').show();
			$('#loginError').html("用户名只能为英文或数字");
			return;
		}
		
		//验证密码
		if(oc.isEmpty(password) || password.length < 6){
			$('#loginError').show();
			$('#loginError').html("密码至少6位");
			return;
		}
		
		//登录
		$('#loginForm').ajaxSubmit({
			datatype : 'json',
			success : function(respText) {
				var resp = $.parseJSON(respText);
				if (resp.errcode == 0) {
					window.location.reload();
				} else if(resp.errcode == 1) {
					$('#loginError').show();
					$('#loginError').html("用户名密码错误");
				}
			},
			error : function(xhr) {
			}
		});
	}
	
	//ajax注册
	function doAjaxRegister(){
		var username = $('#usernameHeader2').val();
		var password = $('#passwordHeader2').val();
		
		//验证用户名 
		if(!oc.enNumValid(username)){
			$('#registerError').show();
			$('#registerError').html("用户名只能为英文或数字");
			return;
		}
		
		//验证密码
		if(oc.isEmpty(password) || password.length < 6){
			$('#registerError').show();
			$('#registerError').html("密码至少6位");
			return;
		}
		//注册
		$('#registeForm').ajaxSubmit({
			datatype : 'json',
			success : function(respText) {
				var resp = $.parseJSON(respText);
				if (resp.errcode == 0) {
					window.location.href="/auth/login";
				} else if(resp.errcode == 1) {
					$('#registerError').show();
					$('#registerError').html("用户名已被注册，请更换！");
				}
			},
			error : function(xhr) {
			}
		});
	}
	</@shiro.guest>
</script>




