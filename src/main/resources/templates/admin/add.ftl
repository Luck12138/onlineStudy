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
					<form role="form" id="courseForm" method="post" action="/admin/courseDoMerge"  enctype="multipart/form-data">
							<input type="hidden" id="courseId" name="id" value=""/>
							<div class="form-group clearfix"> 
								<label class="control-label" >图片 <span id="imgErrSpan" style="color:red;font-weight:normal;"></span> </label> 
								<div class="col-sm-10">
										<input type="file" id="pictureImg" name="pictureImg" style="display: none;" onchange="photoImgChange();">
										<div class="ui-avatar-box">
										<img id="coursePicture" style="max-width: 250px;height:120px;">
										<a href="javascript:void(0);" onclick="doUpload();" style="text-decoration: underline;margin-top:5px;">选择图片</a>
										</div>
								</div>
							</div>
							<div class="form-group clearfix"> 
								<label class="control-label" >名称</label> 
								<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="name" placeholder="请输入名称">
								</div>
							</div>
							<div class="form-group clearfix"> 
									<label class="control-label" >分类</label>
                                    <div class="col-sm-4">
                                        <select id="classify" name="classify" class="form-control"  type="select">
											<#list classify as item>
											<option value="${item.code!}">${item.name}</option>
											</#list>
										</select>
                                    </div>
                                    <label class="control-label" >二级分类</label>
                                    <div class="col-sm-4">
                                        <select id="subClassify" name="subClassify" class="form-control"  type="select">
											<#list subClassify as item>
											<option parentCode="${item.parentCode!}" value="${item.code!}">${item.name}</option>
											</#list>
										</select>
                                    </div>
							</div>
							<div class="form-group clearfix"> 
									<label class="control-label" >级别</label>
                                    <div class="col-sm-4">
                                       <select name="level" class="form-control"  type="select">
											<option value="1">初级</option>
											<option value="2">中级</option>
											<option value="3">高级</option>
										</select>
                                    </div>
                                    <label class="control-label" >推荐权重</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="weight" class="form-control" />
                                    </div>
							</div>
							<div class="form-group clearfix"> 
									<label class="control-label" >是否免费</label>
                                    <div class="col-sm-4">
                                       <select name="free" class="form-control"  type="select">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
                                    </div>
                                    <label class="control-label" >价格</label>
                                    <div class="col-sm-4">
                                        <input type="text" name="price" class="form-control" />
                                    </div>
							</div>
							<div class="form-group clearfix"> 
									<label class="control-label" >讲师<span style="color:red;">*</span></label>
                                    <div class="col-sm-4">
                                       <input type="text" class="form-control"  name="username" placeholder="请输入讲师登录名">
                                    </div>
							</div>
							<div class="form-group clearfix"> 
								<label class="control-label" >概述</label> 
								<div class="col-sm-10">
								<textarea name="brief" rows="2" cols="60"></textarea>
								</div>
							</div>
							<div class="form-group clearfix" style="height:50px;"> 
								<label class="control-label" ></label>
                                <div class="col-sm-4">
                                   <input type="button" class="search-btn" onclick="doSave();" value=" 保存基本信息"  style="float:left;height:40px;"/>
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
