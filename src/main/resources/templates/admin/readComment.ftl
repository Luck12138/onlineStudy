
<form id="queryPageForm" queryPage="1"  >
<input type="hidden" id="courseId"  value="${(courseComment.courseId)!}">
<#if (page.items)??>
<#list page.items as item>
<div id="comment-div-${item.id!}" class="comment clearfix">
	<div class="comment-main">
		<div>
			<span class="label-title">评论人：</span><span>${item.username!}</span>
			<span class="label-title"  style="margin-left: 20px;">时间：</span><span>${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
			<span class="label-title"  style="margin-left: 20px;">章节：</span><span>${item.sectionTitle!}</span>
		</div>
		<div class="comment-content">${item.content!}</div>
		<div class="comment-footer">
			<span class="label-title">操作：</span><span><a onclick="doDeleteComment(${item.id!})" href="javascript:void(0);">删除</a></span>
		</div>
	</div>
</div>	
</#list>	
<#include "../common/tailPage.ftl">
</#if>
</form>

<script type="text/javascript">
function queryPage(page){
	var courseId = $('#courseId').val();
	var active = $('#qa').hasClass('active');
	var type = 0;
	var elId = 'comment';
	if(active){
		type = 1;
		elId = 'qa';
	}
	//异步load
	var url = '/admin/courseCommentList';
	$("#" + elId).load(
		url,
		{'courseId':courseId,'type':type,'pageNum':page},
		function(){ }
	);
}

function doDeleteComment(id){
	Modal.confirm('确定删除?',function(){
		$.ajax({
			url:'/admin/courseCommentDelete',
		    type:'POST',
		    dataType:'json',
		    data:{"id":id},
		    success:function(resp){
		    	var errcode = resp.errcode;
		    	if(errcode == 0){
		    		$('#comment-div-'+id).remove();
		    	}
		    }
		});
	});
}
</script>