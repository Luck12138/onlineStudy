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
			<#include "common/header2.ftl">
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

		<div class="f-main clearfix">
			<div class="main-course-left">
					<div class="course-info">
						<div class="course-title" style="font-size: 24px;">${courseSection.name}</div>
						
						<div class="course-video">
							<video src="/res/demo.mp4" width="100%" height="100%" controls preload></video>
						</div>
						
						<div class="course-menu">
							<a  href="javascript:void(0)"><span class="menu-item  cur">评论</span></a>
						</div>
				</div>
				
				<!-- 评论-start -->
				<div>
						<div class="comment clearfix">
							<div class="comment-header"><img class="lecturer-uimg" src="/res/i/header.jpg"></div>
							<div class="comment-main">
								<div class="user-name">我是张三</div>
								<div class="comment-content">这门课真实用！一个列表嵌套好多种布局的时候，用recyclerview控件优雅实现，效率果然提高不少，老板肯定又会夸我，啊哈哈！！！</div>
								<div class="comment-footer">
									<span>时间：2016-12-05 </span>
									<a href="">2-2 我是java第二节</a>
								</div>
							</div>
						</div>		
								
						<div class="comment clearfix">
							<div class="comment-header"><img class="lecturer-uimg" src="../res/i/header.jpg"></div>
							<div class="comment-main">
								<div class="user-name">我是张三</div>
								<div class="comment-content">这门课真实用！一个列表嵌套好多种布局的时候，用recyclerview控件优雅实现，效率果然提高不少，老板肯定又会夸我，啊哈哈！！！</div>
								<div class="comment-footer">
									<span>时间：2016-12-05 </span>
									<a href="">2-2 我是java第二节</a>
								</div>
							</div>
						</div>		
				</div>
				
				<!-- 发布评论-start -->
				<div style="margin-top: 20px;">
					<div>
						<span id="commentTitle">发布评论：</span>
						<span id="commentTip" style="margin-left: 10px;color:#777;">长度小于200</span>
					</div>
					<form id="commentForm" action="/courseComment/doComment" method="post"  style="margin: 5px 0px;">
						<input type="hidden" id="commentType" name="type" value="0"/>
						<input type="hidden" name="sectionId" value="ss"/>
						<input type="hidden" name="courseId" value="ss"/>
						
						<textarea id="content" name="content" rows="" cols="100"></textarea>
					</form>
					<input type="button" value="发布" class="btn" onclick="doComment();">
				</div>
				
				<!-- 评论-end -->
			</div>
			
			<!-- 章节-start -->
			<div class="main-course-right"  >
				<h4 class="mt-50">所有章节</h4>
				<div class="video-course-fix-parent">
					<div class="video-course-fix">
						<#if chaptSections??>
						<#list chaptSections as item>
						<div class="chapter" style="padding: 0px ;border: none;">
							<a href="javascript:void(0);" class="js-open">
								<h3>
									<strong>${item.name}</strong>
									<span class="drop-down">▼</span> 
								</h3>
							</a>
							<ul class="chapter-sub" style="padding-left:10px;">
								<#if item.sections??>
								<#list item.sections as section>
								<a href="/course/video/${section.id}" >
									<li class="ellipsis video-li"><i class="icon-video">▶</i> ${section.name}</li>
								</a>
								</#list>
								</#if>
							</ul>
						</div>
						</#list>
						</#if>
					</div>
				</div>
			</div>
			<!-- 章节-end -->
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
		
		<script type="text/javascript">
			$(function(){
				$('.chapter li').hover(function(){
					$(this).find('.icon-video').css('color','#0089D2');
				},function(){
					$(this).find('.icon-video').css('color','#777');
				});
				
				$('.js-open').click(function(){
					var display = $(this).parent().find('ul').css('display');
					if(display == 'none'){
						$(this).parent().find('ul').css('display','block');
						$(this).find('.drop-down').html('▼');
					}else{
						$(this).parent().find('ul').css('display','none');
						$(this).find('.drop-down').html('▲');
					}
				});
			});	
		</script>
		
	</body>
	
</html>
