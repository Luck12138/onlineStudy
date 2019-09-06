<#if (page.pageTotalCount)?? && page.pageTotalCount gt 1>
<div class="page-box clearfix">
	<div class="page clearfix">
		<div style="float:left;">
			<!-- <input type="hidden" id="_id_pageNum" name="pageNum" value="${page.pageNum}"/> -->
			<#if !page.firstPage>
				<a class="page-next" href="javascript:void(0);" onclick="_queryPage(1)">第一题</a>
				<a class="page-next" href="javascript:void(0);" onclick="_queryPage(${page.pageNum-1})">上一题</a>
			</#if>

			
			<#if !page.lastPage>
			<a class="page-next" href="javascript:void(0);"  onclick="_queryPage(${page.pageNum+1})">下一题</a>
			<a class="page-next" href="javascript:void(0);"  onclick="_queryPage(${page.pageTotalCount!})">最后一题</a>
			</#if> 
		</div>
	</div>
</div>
</#if>
