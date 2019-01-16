<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>在线网校学习平台</title>
		
		<#include "common/res.ftl">
		<script type="text/javascript">
		CONETXT_PATH = '';
		</script>
	</head>

	<body>
		<#include "common/header.ftl">
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


		
		<div class="f-main clearfix">
			<!-- 一级分类-start -->
			<div class="course-nav-row clearfix">
				<span class="hd">方向：</span>
				<ul class="course-nav">
					<li class="course-nav-item <#if curCode?? && curCode=='-1'>cur-course-nav</#if>">
                        <a href="javascript:void(0)" onClick="_queryPage(1,'-1')">全部</a>
					</li>
					<#if classify??>
					<#list classify as item>
					<li class="course-nav-item <#if curCode?? && curCode == item.code>cur-course-nav</#if>">
                        <a href="javascript:void(0)" onClick="_queryPage(1,'${item.code!}')">${item.name!}</a>
                    </li>
					</#list>
					</#if>


				</ul>
			</div>
			<!-- 一级分类-end -->
			
			<!-- 二级分类-start -->
			<div class="course-nav-row clearfix">
				<span class="hd">分类：</span>
				<ul class="course-nav">
                    <li class="course-nav-item <#if curSubCode?? && curSubCode=='-2'>cur-course-nav</#if>">
                        <a href="javascript:void(0)" onClick="_queryPage(1,'-2')">全部</a>
                    </li>
					<#if subClassify??>
					<#list  subClassify as item>
					<li class="course-nav-item <#if curSubCode?? && curSubCode == item.code>cur-course-nav</#if>">
                        <a href="javascript:void(0)" onClick="_queryPage(1,'${item.code!}')">${item.name!}</a>
                    </li>
					</#list>
					</#if>
				</ul>
			</div>
			<!-- 二级分类-end -->
			
			<!-- 课程列表-start -->
			<div class="types-block clearfix" style="padding:0px;">
				<h3 style="margin-bottom: 20px;">
                    <span class="types-title" style="margin-right:40px;">课程列表</span>
                    <a href="javascript:void(0)" style="display: inline-block;margin-right:20px;" onclick="_queryPage(1,undefined,'last')">
                        <span <#if sort?? && sort=='last'>class="color-oc"</#if> >最新</span>
                    </a>
                    <a href="javascript:void(0)" style="display: inline-block;" onclick="_queryPage(1,undefined,'pop')">
                        <span <#if sort?? && sort=='pop'>class="color-oc"</#if> >最热</span>
                    </a>
				</h3>
				<div class="types-content clearfix" style="margin-bottom: 20px;">
                    <#if page.items??>
                    <div>
                    <#list page.items as item>
                    <#assign n = item_index + 1 />
					<a href="/course/learn/${item.id}" target="_blank">
                        <div class="course-card-container"  <#if n %5 == 0>style="margin-right: 0px;" </#if> >
							<#if n %5 == 0>
							<div class="course-card-top green-bg">
                            <#elseif n %4 == 0>
							<div class="course-card-top pink-bg">
                            <#elseif n %3 == 0>
							<div class="course-card-top purple-bg">
                            <#elseif n %2 == 0>
							<div class="course-card-top gray-bg">
                            <#else>
							<div class="course-card-top brown-bg">
                            </#if>
                            <span>${item.subClassifyName!}</span>
                        </div>
						<div class="course-card-content">
                            <h3 class="course-card-name" title="${item.name!}">${item.name!}</h3>
                            <p  class="course-card-brief" title="${item.brief!}">${item.brief!}</p>
							<div class="course-card-bottom">
								<#if item.free == 1>
                                    <div class="course-card-info">
									<#if item.level == 1>
									初级
                                    <#elseif item.level == 2>
									中级
                                    <#else>
									高级
                                    </#if>
                                        <span>·</span>${item.studyCount!}人在学
                                    </div>
                                <#else>
									<div class="course-card-info">${item.studyCount!}人在学</div>
									<div class="course-card-price">￥${item.price!}</div>
                                </#if>
							</div>
						</div>
					</div>
					</a>
                    </#list>
                        <#include "common/tailPage.ftl">
                    </#if>
				</div>

			</div>
			<!-- 课程列表-end -->
			
			<!-- 分页-start -->

			<!-- 分页-end -->	
		</div>
		
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
	</body>
    <script type="text/javascript">
        var _code = '${curCode}';
        var _subCode = '${curSubCode}';
        var _sort = '${sort!}';

        function _queryPage(pageNum,code,sort){
            var params = '?pageNum='+pageNum;
            //分类，参数没有就用页面缓存
            if(code == undefined){//来自于分页按钮
                code = _code;
                if(_subCode != '-2'){
                    code = _subCode; //优先使用 subCode
                }
            }
            if(code == '-2'){//点击分类的全部
                code = _code;
            }
            if(code != '-1' && code != ''){
                params += '&c='+code;
            }

            //排序，函数参数没有就用页面缓存
            if(sort == undefined && _sort != ''){
                sort = _sort;
            }
            if(sort != undefined){
                params += '&sort='+sort;
            }
            window.location.href='/course/list'+params;
        }
    </script>
</html>
