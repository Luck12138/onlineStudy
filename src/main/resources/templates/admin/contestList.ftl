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

        <form id="queryPageForm" class="mt-15"  action="" method="POST">
            <div class="row-fluid">
                <div class="cms-search">
                    <a href="javascript:void(0)"  sortField="update_time" class="search-sort-block" >
                        <span>试题列表</span>
                    </a>


                    <input type="button" class="search-btn" onclick="window.location.href='/admin/contestAdd'" value=" + 添加考试 " />
                </div>
            </div>

            <div class="row-fluid">
                <div class="block span6">
                    <div id="widget2container" class="block-body">
                        <table class="table list">
                            <tbody>
                            <#if (page.items)??>
                            <#list page.items as item>
                            <tr id="tr-${item.id!}">
                                <td style="width:600px;">
                                    <p>
                                        <a href="/admin/contestRead?id=${item.id!}">

                                                <img src="/res/i/course.png" style="width: 180px;height:100px;float: left;">

                                        </a>
                                        <div class="ml-15 w-350" style="float:left;">
                                            <a href="/admin/contestRead?id=${item.id!}">
                                    <p class="ellipsis"  title="${item.title!}"><strong>${item.title!}</strong></p>
                                    </a>
                                    <p class="ellipsis-multi h-40" title="${item.totalScore!}">总分：${item.totalScore!}</p>
                    </div>
                    </p>
                    </td>
                    <td>
                        <p>考试时长：${item.startTime!}</p>
                    </td>

                    <td style="width:120px;">
                        <p> <a href="/admin/contestRead?id=${item.id!}">详情</a></p>
                    </td>
                    </tr>
                    </#list>
                    </#if>
                    </tbody>
                    </table>

                    <p>
                        <#include "common/tailPage.ftl"/>
                    </p>

                </div>
            </div>
    </div>
    </form>

</div>
</div>

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
    $(function(){
        $('.search-sort-block').click(function(){
            //排序字段
            var field = $(this).attr('sortField');
            if(field != ''){
                $('#sortField').val(field);
            }else{
                return;
            }
            //排序方向
            var directSpan = $(this).find('span')[1];
            if(directSpan == undefined || directSpan.innerText == '▲'){
                $(directSpan).html('▼');
                $('#sortField').val(field);
                $('#sortDirection').val('DESC');
            }else{
                $(directSpan).html('▲');
                $('#sortField').val(field);
                $('#sortDirection').val('ASC');
            }
            _queryPage();//分页
        });
    });

    //课程上下架
    function doSale(id,sale){
        if(sale == 0){
            sale = 1;
        }else{
            sale = 0;
        }
        $.ajax({
            url:'/admin/courseDoSale',
            type:'POST',
            dataType:'json',
            data:{"id":id,onsale:sale},
            success:function(resp){
                var errcode = resp.errcode;
                if(errcode == 0){
                    _queryPage();
                }
            }
        });
    }

    //课程删除
    function doDelete(id){
        Modal.confirm('课程章节将一并删除，确定删除?',function(){
            $.ajax({
                url:'/admin/courseDelete',
                type:'POST',
                dataType:'json',
                data:{"id":id},
                success:function(resp){
                    var errcode = resp.errcode;
                    if(errcode == 0){
                        $('#tr-'+id).remove();
                    }
                }
            });
        });
    }


</script>
</body>
</html>
