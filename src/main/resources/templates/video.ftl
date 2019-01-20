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

                        <div  class="course-menu">
                            <a  href="javascript:void(0)" >
                                <span onclick="showTab(this,0);" class="menu-item  cur">评论</span>
                            </a>
                            <a  href="javascript:void(0)" >
                                <span onclick="showTab(this,1);" class="menu-item">问答</span>
                            </a>
                        </div>
				</div>
				
				<!-- 评论-start -->
				<div id="commentQA">
				</div>
				
				<!-- 发布评论-start -->
				<div style="margin-top: 20px;">
					<div>
						<span id="commentTitle">发布评论：</span>
						<span id="commentTip" style="margin-left: 10px;color:#777;">长度小于200</span>
					</div>
					<form id="commentForm" action="/courseComment/doComment" method="post"  style="margin: 5px 0px;">
						<input type="hidden" id="commentType" name="type" value="0"/>
						<input type="hidden" name="sectionId" value="${courseSection.id}"/>
						<input type="hidden" name="courseId" value="${courseSection.courseId}"/>
						
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
		<#include "common/footer.ftl"/>
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

                //默认加载评论
                _queryPage(1,0);
			});

            /**
             *展示tab commentQA
             **/
            function showTab(el,type){
                $('.course-menu').find('span').each(function(i,item){
                    $(item).removeClass('cur');
                });
                $(el).addClass('cur');

                _queryPage(1,type);//默认加载 第 1 页
                //设置评论类型
                $('#commentType').val(type);
            }

            /**
             *加载 课程评论 & 问答
             * courseId：课程id
             * sectionId: 章节id
             * type : 类型 0-评论；1-答疑
             */
            var _type = 0; //全局变量
            function _queryPage(pageNum,type){
                if(type == undefined)
                    type = _type;
                else
                    _type = type;
                //加载评论或者QA
                if(pageNum == undefined)
                    pageNum = 1;
                var courseId = ${(courseSection.courseId)!};//课程id
                var sectionId = ${(courseSection.id)!};//章节id
                var url = '/courseComment/segment';
                $("#commentQA").load(
                        url,
                        {
                            'courseId':courseId,
                            'sectionId':sectionId,
                            'type':type,
                            'pageNum':pageNum
                        },
                        function(){}
                );
            };

            <@shiro.user>
			//提交
			function doComment(){
                var content = $('#content').val();
                if($.trim(content).length > 200 || $.trim(content).length == 0){
                    $('#commentTip').css('color','red').html('评论内容长度请 &gt; 0 , &lt; 200');
                    return;
                }

                $('#commentForm').ajaxSubmit({
                    datatype : 'json',
                    success : function(resp) {
                        var resp = $.parseJSON(resp);
                        if (resp.errcode == 0) {
                            $('#commentTip').css('color','green').html('评论成功');
                            //自动刷新

                        } else if(resp.errcode == 1) {
                            $('#commentTip').css('color','red').html('评论失败');
                        } else if(resp.errcode == 3) {
                            $('#commentTip').css('color','red').html('评论内容长度请 &gt; 0 , &lt; 200');
                        }

                        $('#content').val('');
                    },
                    error : function(xhr) {
                    }
                });
            }
			</@shiro.user>

		</script>
		
	</body>
	
</html>
