<!DOCTYPE html>
<html>
   <head>
        <title>后台管理</title>
        <meta charset="utf-8">
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <!-- 引入资源文件 -->
        <#include "common/admin-res.ftl" />
   </head>
   
   <body class=""> 
  	<!-- top 导航 -->
    <#include "common/admin-header.ftl" />
	
	<!-- 菜单导航 -->
    <#include "admin-nav.ftl" />

	<div class="content">
		<div class="container-fluid">
			
			<div class="block span6">
				<div class="block-body collapse in">
					<table class="table list">
						<tbody>
							<tr>
								<input type="hidden" id="courseId" name="id" value="${course.id!}"/>
								<td style="width: 600px;">
									<#if (course.picture)?? && course.picture != ''>
									<img src="${course.picture!}" style="width: 180px;height:100px;float: left;">
									<#else>
									<img src="/res/i/course.png" style="width: 180px;height:100px;float: left;">
									</#if>
									<div class="ml-15 w-350" style="float:left;">
										<p class="ellipsis"  title="${course.name!}"><strong>${course.name!}</strong></p>
										<p class="ellipsis-multi h-40" title="${course.brief!}">${course.brief!}</p>
									</div> 
								</td>
								<td>
										<p>${course.time!}</p>
										<p style="color: red;">
											<#if course.free == 1>免费<#else>￥${course.price!}</#if>
										</p>
										<p>
										<#if course.level == 1>
										初级
										<#elseif course.level == 2>
										中级
										<#else>
										高级
										</#if>
										</p>
									</td>
									<td>
										<p>${course.classifyName!} / ${course.subClassifyName!}</p> 
										<p>${course.studyCount!}人在学</p>
										<p>教师：${course.username!}</p>
										<p>${course.updateTime?string('yyyy-MM-dd HH:mm')}</p>
									</td>
									<td style="width:120px;">
										<p>推荐权重：${course.weight!}</p> 
										<p><a href="/admin/courseRead?id=${course.id!}" >返回</a></p>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="row-fluid">
				<ul id="myTab" class="nav nav-tabs">
					<li class="active">
						<a href="#section" data-toggle="tab">章节</a>
					</li>
					<li>
						<input style="margin-top:5px;" type="button" onclick="addCourseSection();" class="search-btn" value="+ 添加章" >
					</li>
					<li>
						<input style="margin-top:5px;" type="button" onclick="saveCourseSection();" class="search-btn" value="保存章节信息" >
					</li>
					<li>
						<div  id="sectionTipsAlert"  style="display:none;float:left;margin-left:50px;margin-top:10px;height:30px;color:red;">
							<span id="sectionTipsAlert_msg" class="color-oc f-14"></span>
						</div>
					</li>
				</ul>
				<div id="myTabContent" class="tab-content" style="padding: 10px;">
									
				</div>
			</div>
			
		</div>
	</div>
	
	<!-- 添加章节 demo div  start -->
	<div id="demoSection4Clone">
		<div id="demoCourseSectionDiv" sid="demoCourseSectionDiv" style="padding:20px;border:1px solid #CCC; margin-bottom:20px;display:none;">
			<div class="form-group clearfix"> 
				<label class="control-label" >章名称</label> 
				<div class="col-sm-8">
					<input type="text" class="form-control" name="sectionName"  placeholder="请输入名称"  >
				</div>
				<div class="col-sm-1">
					<input type="button" onclick="deleteCourseSection(this);" class="search-btn" value="- 删除章" >
				</div>
				<div class="col-sm-1" style="padding-left:25px;">
					<input type="button" onclick="addSection(this);" class="search-btn" value="+ 添加节"  >
				</div>
			</div>
			
			<div id="demoSectionDiv" sid ="demoSectionDiv" class="form-group clearfix"> 
				<label class="control-label" style="margin-left:55px;">节信息</label> 
				<div class="col-sm-7">
					<input type="text" class="form-control" name="subSectionName"  placeholder="请输入节名称"  style="margin-bottom:5px;">
					<input type="text" class="form-control" name="videoUrl"  placeholder="请输入视频链接"   style="margin-bottom:5px;">
					<input type="text" class="form-control" name="time"  placeholder="请输入时长 00:00"   style="margin-bottom:5px;">
				</div>
				<div class="col-sm-1">
					<input type="button" onclick="deleteSection(this);" class="search-btn" value="- 删除节" >
				</div>
			</div>
			
		</div>
	</div>
	<!-- 添加章节 demo div  end -->
	
	<!-- 导入章节弹出层 -start -->

	<!-- 导入章节弹出层 -end -->
		
	<!-- 弹出层 alert 信息 -->  
    <div class="modal" id="_ocDialogModal" tabindex="-1" aria-hidden="true">
		<div class="modal_wapper small">
			<div class="modal-dialog w-4" >
				<div class="modal-content">
					<div class="modal-header">
						<div class="modal-title f-16" >提示信息</div>
					</div>
					<div class="modal-body">
						<div><span class="color-oc f-16 oc-content"></span></div>
					</div>
					<div class="modal-footer modal-center">
						<button type="button" class="btn btn-primary oc-ok">确 定</button>
						<button type="button" class="btn btn-default oc-cancel">取 消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
    <!-- 弹出层 alert 信息 --> 
	
	<script type="text/javascript">
		//添加章
		function addCourseSection(){
			var tmpDiv = $('#demoSection4Clone').clone().html();
			tmpDiv = tmpDiv.replace('display:none','display:block');
			$('#myTabContent').append(tmpDiv);
		}
		
		//删除章
		function deleteCourseSection(el){
			$(el).parent().parent().parent().remove();
		}
		
		//添加节
		function addSection(el){
			var sectionDiv = $('#demoSectionDiv').clone();
			$(el).parent().parent().parent().append(sectionDiv);
		}
		
		//删除节
		function deleteSection(el){
			$(el).parent().parent().remove();
		}
		
		//保存章节信息
		function saveCourseSection(){
			var courseId = $('#courseId').val();
			if(courseId == ''){
				$("#sectionTipsAlert").show().fadeOut(3000);//显示模态框
				$("#sectionTipsAlert_msg").html('请先保存课程基本信息');
				return;
			}
			
			var sectionDivs = $('#myTabContent').find("div[sid='demoCourseSectionDiv']");
			var batchSections = [];
			//章
			$.each(sectionDivs,function(i,item){
				var sectionName = $(item).find("input[name='sectionName']").val();
				if(sectionName && $.trim(sectionName) != ''){
					var obj = {};
					obj.name = $.trim(sectionName);
					obj.courseId = courseId;
					obj.sections = [];
					//节
					var subSectionDivs = $(item).find("div[sid='demoSectionDiv']");
					$.each(subSectionDivs,function(j,subItem){
						var subSectionName = $(subItem).find("input[name='subSectionName']").val();
						var videoUrl = $(subItem).find("input[name='videoUrl']").val();
						var time = $(subItem).find("input[name='time']").val();
						//正则表达式验证time
						var regTime = RegExp(/^([0-5][0-9]):([0-5][0-9])$/);
						if (!regTime.test(time)) {//如果验证不通过 
							time = "00:00";
						}
						if(subSectionName && $.trim(subSectionName) != ''){
							var subObj = {};
							subObj.name = $.trim(subSectionName);
							subObj.videoUrl = $.trim(videoUrl);
							subObj.time = $.trim(time);
							obj.sections.push(subObj);
						}
					});
					batchSections.push(obj);
				}
			});
			if(batchSections.length == 0){
				$("#sectionTipsAlert").show().fadeOut(3000);//显示模态框
				$("#sectionTipsAlert_msg").html('请填写章节信息');
				return;
			}
			//JSON.stringify(batchSections);
			$.ajax({
				url:'/courseSectionBatchAdd',
			    type:'POST',
			    contentType: "application/json",
			    dataType:'json',
			    data:JSON.stringify(batchSections),
			    success:function(resp){
			    	var errcode = resp.errcode;
			    	if(errcode == 0){
			    		//保存成功，跳转到详情页
			    		window.location.href='/admin/courseRead?id=' + courseId;
			    	}
			    }
			});
		}
		

		
	</script>
</body>
</html>

