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
				<div class="block-body collapse in" style="padding-top:10px;">
					<form role="form" id="courseForm" method="post" action="/admin/contestDoMerge"  enctype="multipart/form-data">


							<div class="form-group clearfix"> 
								<label class="control-label" >考试名称</label>
								<div class="col-sm-10">
								<input type="text" class="form-control" name="title" id="name" placeholder="请输入名称">
								</div>
							</div>
							<div class="form-group clearfix"> 
									<label class="control-label" >考试科目</label>
                                    <div class="col-sm-4">
                                        <select id="classify" name="subjectId" class="form-control"  type="select">
											<#list page as item>
											<option value="${item.id!}">${item.name}</option>
											</#list>
										</select>
                                    </div>

							</div>


							<div class="form-group clearfix">
									<label class="control-label" ><span style="color:red;">考试时长</span></label>
                                    <div class="col-sm-4">
                                       <input type="text" class="form-control"  name="startTime" placeholder="请输入考试时长">
                                    </div>
							</div>


							<div class="form-group clearfix" style="height:50px;"> 
								<label class="control-label" ></label>
                                <div class="col-sm-4">
                                   <input type="button" class="search-btn" onclick="doSave();" value=" 保存考试信息"  style="float:left;height:40px;"/>
                                   <div  id="successAlert"  class="alert alert-success" style="display:none;float:left;margin-left:50px;margin-top:0px;">
										<span id="successAlert_msg" class="color-oc f-16">保存成功！</span>
									</div>
                                </div>
							</div>
							<div style="margin-bottom:5px;"><span id="failureMsg" style="color:red;"></span></div>
						</form>
				</div>
			</div>
			


	
	<script type="text/javascript">
		//过滤二级分类
        function filterSubClassify(classify){
            var flag = false;
            $('#subClassify').find("option").each(function(i,item){
                if($(item).attr('parentCode') == classify){
                    if(flag == false){
                        $('#subClassify').val($(item).attr('value'));//默认选中第一个
                        flag = true;
                    }
                    $(item).show();
                }else{
                    $(item).hide();
                }
            });
        }
        $(function(){
            filterSubClassify($('#classify').val());//第一次加载的时候过滤
            $('#classify').change(function(){
                filterSubClassify($('#classify').val());
            });
        });

        //选择图片
		function doUpload(){
			$('#pictureImg').click();
		}
		//选择图片
		function photoImgChange(){
			var img = $('#pictureImg').val();
			if(oc.photoValid(img)){
				oc.previewUploadImg('pictureImg','coursePicture');
				$('#coursePicture').show();
				$('#imgErrSpan').html('');
				return;
			}else{
				$('#imgErrSpan').html('&nbsp;请选择png,jpeg,jpg格式图片');
				$('#coursePicture').val('');
			}
		}
		
		//保存
		function doSave(){
			$('#courseForm').ajaxSubmit({
				datatype : 'json',
				success : function(resp) {
					var resp = $.parseJSON(resp);
					if (resp.errcode == 0) {
						$("#successAlert").show().fadeOut(2500);//显示模态框
						var id = resp.data.id;
						$('#courseId').val(id);
						$('#failureMsg').html('');
					} else if(resp.errcode == 1) {
						$('#failureMsg').html('保存失败，请输入正确的 教师登录名 (如 wangyangming )');
					}
				},
				error : function(xhr) {
				}
			});
		}
		
	</script>
</body>
</html>
