<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>在线网校学习平台</title>
		
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="/css/reset.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="/js/bootstrap.min.js"></script>
		
		<!--[if lt IE 9]>
		  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<link rel="icon" type="image/png" href="/i/ico.png" sizes="16x16">
		<script type="text/javascript">
		CONETXT_PATH = '';
		</script>
        <script type="text/javascript">
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
            $(function(){
                $("#userdetail").popover({
                    trigger:'manual',
                    placement : 'bottom',
                    html: 'true',
                    content : '<div style="width:300px;height:300px;"></div>',
                    animation: false
                }).on("mouseenter", function () {
                    var _this = this;
                    $(this).popover("show");
                    $(this).siblings(".popover").on("mouseleave", function () {
                        $(_this).popover('hide');
                    });
                }).on("mouseleave", function () {
                    var _this = this;
                    setTimeout(function () {
                        if (!$(".popover:hover").length) {
                            $(_this).popover("hide")
                        }
                    }, 0);
                });
                //课程分类展示
                $(".category").popover({
                    trigger:'manual',
                    placement : 'right',
                    html: 'true',
                    content : '',
                    animation: false
                }).on("mouseenter", function () {
                    var cid = $(this).attr('c-id');
                    $('#' + cid).show();
                    $('#' + cid).hover(function(){
                        $('#' + cid).show();
                    },function(){
                        $('#' + cid).hide();
                    });
                }).on("mouseleave", function () {
                    var cid = $(this).attr('c-id');
                    $('#' + cid).hide();
                });
            });
        </script>
	</head>

	<body>
		<!-- 头部-start -->
		<div class="f-header">
			<div class="f-header-box clearfix">
				<a href=".." class="logo" title="网校在线学习平台"></a>
				<nav class="header-nav">
					<a href=".." class="header-nav-item">首 页</a>
					<a href="../course/list.html" class="header-nav-item">课 程</a>
					<a href="pages/user/home.html" class="header-nav-item">我的</a>
					<a href="../opt/index.html"  style="width:100px;" target="_blank" class="header-nav-item">运营CMS</a>
				</nav>
				
				<nav class="header-nav" style="float:right">
					<a href="#myModal" class="header-nav-item"  data-toggle="modal" onclick="login();"  style="margin-right:0px;font-size:14px;">登录</a>
					<a href="/auth/register" class="header-nav-item"   style="margin-left:0px;font-size:14px;">注册</a>
			        <a href="#" class="header-nav-item"  style="margin-left:0px;font-size:14px;" id="userdetail">头像</a>
				</nav>
			</div>
		</div>
		<!-- 头部-end -->
		
		<!-- 登录注册-start -->
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
		               	<form id="loginForm" class="form-horizontal" style="padding: 0px 20px;">
		                      <div class="form-group">
		                          <input type="email" class="form-control"  id="username"  placeholder="用户名">
		                      </div>
		                      <div class="form-group help">
		                          <input type="password" class="form-control"  id="password"  placeholder="密　码">
		                      </div>
		                      <div class="form-group">
		                          <label>
		                           <input type="checkbox" value="None" id="checkbox1" name="check">
		                           <span class="text" style="color: #787D82;font-size: 14px;">下次自动登录</span>
		                          </label>
		                      </div>
		                      
		                      <a href="javascript:void(0)">
		                      	<div class="header-login-btn">登 录</div>
		                      </a>
		                  </form>
		                  
		                   <form id="registeForm" class="form-horizontal" style="padding: 0px 20px;display: none;">
		                       <div class="form-group">
		                           <input type="email" class="form-control"  id="username"  placeholder="用户名">
		                       </div>
		                       <div class="form-group help">
		                           <input type="password" class="form-control"  id="password"  placeholder="密　码">
		                       </div>
		                       <a href="javascript:void(0)">
		                      	<div class="header-login-btn">注 册</div>
		                       </a>
		                   </form>
		                  
		            </div>
					
		        </div>
		    </div>
		</div>
		<!-- 登录注册-end -->
		
		<div class="f-main">
			<!-- 轮播 分类-start -->
			<div class="clearfix">
				<div class=main-category>
					<div class="main-bg">
						<#if carouseList?? && carouseList?size gt 0>
						<#list carouseList as carouse>
                         <a href="${carouse.url}">
						<div class="main-bg-item " style="background-image: url('${carouse.picture}'); ">
						</div>
                         </a>
                        </#list>
						</#if>
					</div>

                <div class="f-nav-box">
                    <div class="bg-nav">
							<#if carouseList?? && carouseList?size gt 0>
							<#list carouseList as carouse>
								<#if carouse_index == 0>
								<a class="cur"></a>
								<#else>
								<a></a>
								</#if>
							</#list>
							</#if>
                    </div>
                </div>
					
					<div class="main-category-menu">
						<#if classifyVo??>
						<#list  classifyVo as classify>
                        <#if classify_index lt 6>
						<div class="category" c-id="classify_${classify.id}">
							<a><div class="group">${classify.name}</div></a>
						</div>
						</#if>
						</#list>
						</#if>
					</div>
                    <#if classifyVo??>
		            <#list  classifyVo as classify>
					<#if classify_index lt 6>
					<div class="main-category-submenu-content"  id="classify_${classify.id}" >
                        <div class="clearfix" >
                            <div class="submenu-title clearfix">分类目录</div>
							<#if classify.subClassifyList??>
								<#list classify.subClassifyList as subItem>
								<a class="submenu-item" href="/course/list?c=${subItem.code!}">${subItem.name!}</a>
									<#if subItem_index lt (classify.subClassifyList?size - 1)>
								<a class="submenu-item">/</a>
									</#if>
								</#list>
							</#if>
                        </div>

                        <div class="clearfix" >
                            <div class="submenu-title" style="margin-top: 30px;">课程推荐</div>
                            <div>
								<#if classify.recomdCourseList?? && classify.recomdCourseList?size gt 0 >
								<#list classify.recomdCourseList as item>
								<a href="/course/learn/${item.id}.html" class="mt-10" title="${classify.name!}"><li class="ellipsis oc-color-hover">➤ ${item.name!}</li></a>
								</#list>
								</#if>
                            </div>
                        </div>

					</div>
					</#if>
					</#list>
				    </#if>
					

				</div>
			</div>
			<!-- 轮播 分类-end -->
			
			<!-- 实战推荐-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">实战推荐</h3>
				<div class="types-content">
					
					<a href="">
					<div class="course-card-container">
						<div class="course-card-top green-bg">
							<span>测试</span>
						</div>

						<div class="course-card-content">
							<h3 class="course-card-name">Android自动化测试实战 工具 框架 脚本</h3>
							<p title="找Android自动化测试工作必学的主流工具、框架和自动化脚本">找Android自动化测试工作必学的主流工具、框架和自动化脚本</p>
							<div class="course-card-bottom">
								<div class="course-card-info">178人在学</div>
								<div class="course-card-price">￥179.00</div>
							</div>
						</div>
					</div>
					</a>

					<a href="">
					<div class="course-card-container">
						<div class="course-card-top pink-bg">
							<span>WebApp</span>
						</div>
						<div class="course-card-content">
							<h3 class="course-card-name">AngularJS仿拉勾网WebApp 开发移动端单页应用</h3>
							<p title="基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用">基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">344人在学</div>
	
								<div class="course-card-price">￥99.00</div>
							</div>
						</div>
					</div>
					</a>
					
					<a href="">
					<div class="course-card-container">
						<div class="course-card-top brown-bg">
							<span>Django</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">强力django杀手级xadmin 打造上线标准的在线教育平台</h3>
							<p title="全面掌握django框架,轻松应对python web开发工作">全面掌握django框架,轻松应对python web开发工作</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">488人在学</div>
								<div class="course-card-price">￥369.00</div>
							</div>
						</div>
					</div>
					</a>
	
					<a href="">
					<div class="course-card-container">
						<div class="course-card-top purple-bg">
							<span>Python</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">Python高级编程技巧实战</h3>
							<p title="精选50个python训练任务，提升实战技能与高效编程技巧">精选50个python训练任务，提升实战技能与高效编程技巧</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">813人在学</div>
								<div class="course-card-price">￥168.00</div>
							</div>
						</div>
					</div>
					</a>
					
					<a href="">
					<div class="course-card-container" style="margin-right: 0px;">
						<div class="course-card-top green-bg">
							<span>PHP</span>
						</div>
						
						<div class="course-card-content">
							<h3 class="course-card-name">前端后台ThinkPHP开发整站</h3>
							<p title="用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想">用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">2324人在学</div>
								<div class="course-card-price">￥128.00</div>
							</div>
						</div>
					</div>
					</a>
					
				</div>
			</div>
			<!-- 实战推荐-end -->
			
			<!-- 免费好课-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">免费好课</h3>
				<div class="types-content">
					<a href="">
					<div class="course-card-container">
						<div class="course-card-top green-bg">
							<span>测试</span>
						</div>

						<div class="course-card-content">
							<h3 class="course-card-name">Android自动化测试实战 工具 框架 脚本</h3>
							<p title="找Android自动化测试工作必学的主流工具、框架和自动化脚本">找Android自动化测试工作必学的主流工具、框架和自动化脚本</p>
							<div class="course-card-bottom">
								<div class="course-card-info">初级<span>·</span>178人在学</div>
							</div>
						</div>
					</div>
					</a>

					<a href="">
					<div class="course-card-container">
						<div class="course-card-top pink-bg">
							<span>WebApp</span>
						</div>
						<div class="course-card-content">
							<h3 class="course-card-name">AngularJS仿拉勾网WebApp 开发移动端单页应用</h3>
							<p title="基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用">基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">初级<span>·</span>3444人在学</div>
							</div>
						</div>
					</div>
					</a>
					
					<div class="course-card-container">
						<div class="course-card-top brown-bg">
							<span>Django</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">强力django杀手级xadmin 打造上线标准的在线教育平台</h3>
							<p title="全面掌握django框架,轻松应对python web开发工作">全面掌握django框架,轻松应对python web开发工作</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">初级<span>·</span>4888人在学</div>
							</div>
						</div>
					</div>

					<div class="course-card-container">
						<div class="course-card-top purple-bg">
							<span>Python</span>
						</div>
	
						<div class="course-card-content">
							<h3 class="course-card-name">Python高级编程技巧实战</h3>
							<p title="精选50个python训练任务，提升实战技能与高效编程技巧">精选50个python训练任务，提升实战技能与高效编程技巧</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">高级<span>·</span>81333人在学</div>
							</div>
						</div>
					</div>
					
					<div class="course-card-container" style="margin-right: 0px;">
						<div class="course-card-top green-bg">
							<span>PHP</span>
						</div>
						
						<div class="course-card-content">
							<h3 class="course-card-name">前端后台ThinkPHP开发整站</h3>
							<p title="用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想">用PHP+MySQL+Ajax开完新闻资讯整站，实现“小全栈”的梦想</p>
							<div class="clearfix course-card-bottom">
								<div class="course-card-info">中级<span>·</span>6133人在学</div>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			<!-- 免费好课-end -->
			
			<!-- java课程-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">Java开发工程师</h3>
				<div class="types-content-left "
					style="background-image: url(http://img.mukewang.com/58ac18fd00012a4202240348.jpg);">
					<div class="course-card-container-fix">
						<div class="course-card-content">
							<h3 class="course-card-name">Java职业路径</h3>
							<p class="color-fff" title="带你研究Java技术框架">带你研究Java技术框架，系统地学习java技术</p>
							<div class="course-card-bottom" style="margin-top: 50px;">
								<div class="course-card-info color-fff">了解详情 →</div>
							</div>
						</div>
					</div>
				</div>
	
				<div class="types-content-right ">
					<div class="types-content-banner ">
						<a target="_blank" href="http://coding.imooc.com/class/81.html">
							<div class="types-content-banner-block green-bg"  style="margin-right:20px;" >
								Spring框架实战讲解！
							</div>
						</a>
						<a target="_blank" href="http://coding.imooc.com/class/76.html">
							<div class="types-content-banner-block gray-bg"  >
								MyBatis框架实战讲解！
							</div>
						</a>
					</div>
					
					<div class="clearfix">
						<a href="">
							<div class="course-card-container">
								<div class="course-card-top green-bg">
									<span>测试</span>
								</div>
	
								<div class="course-card-content">
									<h3 class="course-card-name">Android自动化测试实战 工具 框架 脚本</h3>
									<p title="找Android自动化测试工作必学的主流工具、框架和自动化脚本">找Android自动化测试工作必学的主流工具、框架和自动化脚本</p>
									<div class="course-card-bottom">
										<div class="course-card-info">
											初级<span>·</span>178人在学
										</div>
									</div>
								</div>
							</div>
						</a> 
						
						<a href="">
							<div class="course-card-container">
								<div class="course-card-top pink-bg">
									<span>WebApp</span>
								</div>
								<div class="course-card-content">
									<h3 class="course-card-name">AngularJS仿拉勾网WebApp 开发移动端单页应用</h3>
									<p title="基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用">基于AngularJS，仿拉勾网开发一个招聘类的移动端单页应用</p>
									<div class="clearfix course-card-bottom">
										<div class="course-card-info">
											初级<span>·</span>3444人在学
										</div>
									</div>
								</div>
							</div>
						</a>
	
						<div class="course-card-container">
							<div class="course-card-top brown-bg">
								<span>Django</span>
							</div>
	
							<div class="course-card-content">
								<h3 class="course-card-name">强力django杀手级xadmin 打造上线标准的在线教育平台</h3>
								<p title="全面掌握django框架,轻松应对python web开发工作">全面掌握django框架,轻松应对python
									web开发工作</p>
								<div class="clearfix course-card-bottom">
									<div class="course-card-info">
										初级<span>·</span>4888人在学
									</div>
								</div>
							</div>
						</div>
	
						<div class="course-card-container" style="margin-right: 0px;">
							<div class="course-card-top purple-bg">
								<span>Python</span>
							</div>
	
							<div class="course-card-content">
								<h3 class="course-card-name">Python高级编程技巧实战</h3>
								<p title="精选50个python训练任务，提升实战技能与高效编程技巧">精选50个python训练任务，提升实战技能与高效编程技巧</p>
								<div class="clearfix course-card-bottom">
									<div class="course-card-info">
										高级<span>·</span>81333人在学
									</div>
								</div>
							</div>
						</div>
	
					</div>
				</div>
			</div>
			<!-- java课程-end -->
			
			<!--名校讲师-start -->
			<div class="types-block clearfix">
				<h3 class="types-title">名校讲师</h3>
				<a href="">
					<div class="lecturer-card-container">
						<div class="lecturer-item">
							<img class="lecturer-uimg" src="/i/header.jpg">
							<span class="lecturer-name">王阳明</span>
							<span class="lecturer-title">中华大学</span>
							<span class="lecturer-p" >中华大学高级讲师，擅长写代码</span>
						</div>
					</div>
				</a>
					
				<a href="">
					<div class="lecturer-card-container">
						<div class="lecturer-item">
							<img class="lecturer-uimg" src="/i/header.jpg">
							<span class="lecturer-name">王阳明</span>
							<span class="lecturer-title">中华大学</span>
							<span class="lecturer-p" >中华大学高级讲师，擅长写代码</span>
						</div>
					</div>
				</a>

				<div class="lecturer-card-container">
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="/i/header.jpg">
						<span class="lecturer-name">王阳明</span>
						<span class="lecturer-title">中华大学</span>
						<span class="lecturer-p" >中华大学高级讲师，擅长写代码</span>
					</div>
				</div>

				<div class="lecturer-card-container"  >
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="/i/header.jpg">
						<span class="lecturer-name">王阳明</span>
						<span class="lecturer-title">中华大学</span>
						<span class="lecturer-p" >中华大学高级讲师，擅长写代码</span>
					</div>
				</div>
					
				<div class="lecturer-card-container" style="margin-right: 0px;">
					<div class="lecturer-item">
						<img class="lecturer-uimg" src="/i/header.jpg">
						<span class="lecturer-name">王阳明</span>
						<span class="lecturer-title">中华大学</span>
						<span class="lecturer-p" >中华大学高级讲师，擅长写代码</span>
					</div>
				</div>
					
			</div>
			<!--名校讲师-end -->
		</div>
		
		
		<!-- 页脚-start -->
		<div class="f-footer">
			<div class="f-footer-box clearfix">
				<div class="footer-link">
					<a href="javascript:void(0);"  target="_blank" title="企业合作">企业合作</a> 
					<a href="javascript:void(0);" target="_blank" title="联系我们">联系我们</a> 
					<a href="javascript:void(0);" target="_blank" title="常见问题">常见问题</a> 
					<a href="javascript:void(0);" target="_blank" title="意见反馈">意见反馈</a>
					<a href="javascript:void(0);" target="_blank" title="友情链接">友情链接</a>
				</div>
				<div class="footer-copyright">
					<span>©&nbsp;2017&nbsp; 备案 </span>
				</div>
			</div>
		</div>
		<!-- 页脚-end-->
		
	</body>

    <script>

		<#if carouseList?? && carouseList?size gt 0>
		var size = Number('${carouseList?size}');
		<#else>
		var size = 0;
		</#if>
        $(function(){
            var index = 0;
            var timer = 4000;
            $('.bg-nav a').click(function(){
                index = $('.bg-nav a').index($(this));
                rollBg(index);
            });
            $('.index-roll-item').click(function(){
                index = $('.index-roll-item').index($(this));
                rollBg(index);
            });
            var rollBg = function(i){
                $('.main-bg-item').fadeOut(1000);
                $($('.main-bg-item')[i]).fadeIn(1000);
                $('.bg-nav a').removeClass('cur');
                $($('.bg-nav a')[i]).addClass('cur');
                $('.index-roll-item').removeClass('cur');
                $($('.index-roll-item')[i]).addClass('cur');
            }
            setInterval(function(){
                index += 1;
                index = index%size;
                rollBg(index);
            }, timer);

        });
    </script>
</html>
