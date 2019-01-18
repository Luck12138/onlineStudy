<!DOCTYPE html>
<html lang="utf-8">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
		<meta name="keywords" content="">
		<meta name="description" content="">
		<title>个人主页</title>
		<#include "../common/res.ftl">
	</head>

	<body>
		<#include "../common/header2.ftl">
		<div class="f-main clearfix">
			<div class="setting-left">
			<#include "../common/nav.ftl">
			</div>
			
			<div class="setting-right"  >
				<div><span class="f-16">最近学习</span></div>
				<div class="split-line" style="margin: 20px 0px;"></div>
				
				<form id="queryPageForm" action="">
				<#if (page.items)??>
				<#list page.items as item>
				<div class="comment clearfix">
					<div class="comment-main" style="width: 100%">
						<a href="/course/learn/${item.courseId!}" target="_blank" class="user-name link-a" style="font-size:20px;">${item.courseName!}</a>
						<div class="comment-content">

							<span>
							<a href="/course/video/${item.sectionId!}" target="_blank" >
							${item.sectionName!}
							</a>
							</span>
						</div>
						<div class="comment-footer">
							<span>时间：
							<#if item.createTime??>
							${item.createTime?string('yyyy-MM-dd')}
							</#if>
							</span>
							<a href="/course/video/${item.sectionId!}" target="_blank" >
							<span class="continue-btn" style="margin-left: 50px;">继续学习</span>
							</a>
						</div>
					</div>
				</div>		
				</#list>
				
				<#include "../common/tailPage2.ftl">
				</#if>
				</form>
				
			</div>
			
		</div>
		
		<#include "../common/footer.ftl">
		
	</body>
	
</html>
