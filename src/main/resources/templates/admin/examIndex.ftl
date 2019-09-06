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
    <input type="button" class="search-btn mt-10" style="margin-left: 15px;"  onclick="toEditExam();" value=" 添加分类" />
    <input type="button" class="search-btn mt-10" style="margin-left: 15px;" onclick="toEditQuestion();" value=" 添加题目" />

    <div class="container-fluid pt-10" >

        <div class="panel panel-5" >
            <a class="block-heading f-14" data-toggle="collapse">题目分类</a>
            <div class="block-body collapse in">
                <table class="table">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if examList??>
                        <#list examList as item>
                            <tr class="tr-bg-gray">
                                <td>${item.name!}</td>
                                <td>
                                    <a class="link-a" href="javascript:void(0)" onclick="toEditExam(${item.id})">修改</a>
                                    <a class="link-a" href="javascript:void(0)" onclick="doDeleteExam(${item.id})">删除</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="panel panel-5" >
            <a class="block-heading f-14" data-toggle="collapse">题目列表</a>
            <div class="block-body collapse in">
                <table id="subClassifysTable" class="table">
                    <thead>
                    <tr name="th">
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if questionList??>
                        <#list questionList as item>
                            <tr>
                                <td>${item.title!}</td>
                                <td>
                                    <a class="link-a" href="javascript:void(0)" onclick="toEditQuestion(${item.id})">修改</a>
                                    <a class="link-a" href="javascript:void(0)" onclick="doDeleteQuestion(${item.id})">删除</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- 弹出层 -start -->
<div class="modal" id="myModalExam" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal_wapper">
        <div class="modal-dialog w-8" >
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" >新增分类</h4>
                </div>

                <div class="modal-body">
                    <form role="form" id="myFormExam" method="post" action="/admin/examSave"  enctype="multipart/form-data">
                        <input type="hidden" name="id" value=""/>
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
                            <label class="control-label" >概述</label>
                            <div class="col-sm-10">
                                <textarea name="brief" rows="2" cols="60"></textarea>
                            </div>
                        </div>
                    </form>
                    <!-- tip提示-start -->
                    <div id="_ocAlertTip" class="alert alert-success f-16" style="display: none;"></div>
                    <!-- tip提示-end -->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="doSaveExam();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 弹出层 -end -->

<!-- 弹出层 -start -->
<div class="modal" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal_wapper">
        <div class="modal-dialog w-8" >
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" >新增题目</h4>
                </div>

                <div class="modal-body">
                    <form role="form" id="myFormQuestion" method="post" action="/admin/questionSave">
                        <input type="hidden" name="id" value=""/>
                        <div class="form-group">
                            <label for="name">题目分类</label>
                            <select name="subjectId" class="form-control"  type="select">
                                <#if examList??>
                                    <#list examList as item>
                                        <option value="${item.id!}">${item.name!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="title">题目标题</label>
                            <input type="text" class="form-control" name="title" id="title" placeholder="请输入标题">
                        </div>
                        <div class="form-group">
                            <label for="context">题目内容</label>
                            <input type="text" class="form-control" name="content" id="content" placeholder="请输入题目内容">
                        </div>
                        <div class="form-group">
                            <label for="questionType">题目类型</label>
                            <select name="questionType" class="form-control"  type="select" onclick="changeOption(this)">
                              <option value="0">单项选择</option>
                              <option value="1">多项选择</option>
                              <option value="2">问答</option>
                              <option value="3">编程</option>
                            </select>
                        </div>
                        <div class="form-group" id="optiondiv1">
                            <label for="context">选项A</label>
                            <input type="text" class="form-control" name="optionA" id="optionA" placeholder="选项内容">
                        </div>
                        <div class="form-group" id="optiondiv2">
                            <label for="context">选项B</label>
                            <input type="text" class="form-control" name="optionB" id="optionB" placeholder="选项内容">
                        </div>
                        <div class="form-group" id="optiondiv3">
                            <label for="context">选项C</label>
                            <input type="text" class="form-control" name="optionC" id="optionC" placeholder="选项内容">
                        </div>
                        <div class="form-group" id="optiondiv4">
                            <label for="context">选项D</label>
                            <input type="text" class="form-control" name="optionD" id="optionD" placeholder="选项内容">
                        </div>
                        <div class="form-group">
                            <label for="answer">题目答案</label>
                            <input type="text" class="form-control" name="answer" id="answer" placeholder="题目答案">
                        </div>
                        <div class="form-group">
                            <label for="parse">答案解析</label>
                            <input type="text" class="form-control" name="parse" id="parse" placeholder="答案解析">
                        </div>
                        <div class="form-group">
                            <label for="difficulty">题目难度</label>
                            <select name="difficulty" class="form-control"  type="select">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </select>
                        </div>
                    </form>
                    <!-- tip提示-start -->
                    <div id="_ocAlertTip" class="alert alert-success f-16" style="display: none;"></div>
                    <!-- tip提示-end -->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="doSaveQuestion();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 弹出层 -end -->

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
        //一级分类点击，过滤二级分类
        $('.tr-bg-gray').click(function(){
            var code = $(this).attr('code');
            $('#subClassifysTable').find('tr').each(function(i,item){
                if($(item).attr('parentCode') == code){
                    $(item).show();
                }else{
                    if($(item).attr('name') != 'th'){
                        $(item).hide();
                    }
                }
            });
        });

    });

    function changeOption(obj) {
        var _selectedvalue = obj.value;
        var option_a = document.getElementById("optiondiv1");
        var option_b = document.getElementById("optiondiv2");
        var option_c = document.getElementById("optiondiv3");
        var option_d = document.getElementById("optiondiv4");
        if(_selectedvalue==1){
            option_a.style.display = 'block';
            option_b.style.display = 'block';
            option_c.style.display = 'block';
            option_d.style.display = 'block';
        }else if (_selectedvalue==0) {
            option_a.style.display = 'block';
            option_b.style.display = 'block';
            option_c.style.display = 'block';
            option_d.style.display = 'block';
        }else {
                option_a.style.display = 'none';
                option_b.style.display = 'none';
                option_c.style.display = 'none';
                option_d.style.display = 'none';
            }
        }


    //添加分类 & 编辑
    function toEditExam(id){
        if(id == undefined){//添加
            Modal.show('myModalExam');
            $('#code').removeAttr('disabled');
        } else{
            $('#code').attr('disabled','disabled');
            $.ajax({
                url:'/admin/examGetById',
                type:'POST',
                dataType:'json',
                data:{"id":id},
                success:function(resp){
                    var errcode = resp.errcode;
                    if(errcode == 0){
                        Modal.show('myModalExam');
                        $("#myFormExam").fill(resp.data);
                    }
                }
            });
        }
    }

    //添加题目 & 编辑
    function toEditQuestion(id){
        if(id == undefined){//添加
            Modal.show('myModal');
            $('#code').removeAttr('disabled');
        } else{
            $('#code').attr('disabled','disabled');
            $.ajax({
                url:'/admin/questionGetById',
                type:'POST',
                dataType:'json',
                data:{"id":id},
                success:function(resp){
                    var errcode = resp.errcode;
                    if(errcode == 0){
                        Modal.show('myModal');
                        $("#myFormQuestion").fill(resp.data);
                    }
                }
            });
        }
    }

    //保存
    function doSaveExam(){
        $('#myFormExam').ajaxSubmit({
            datatype : 'json',
            success : function(resp) {
                var resp = $.parseJSON(resp);
                if (resp.errcode == 0) {
                    $('#myFormExam').modal('hide');
                    window.location.reload();//刷新
                } else {
                    Modal.tipFailure(resp.message);//失败提示
                }
            },
            error: function(){
                Modal.tipFailure('保存失败');
                //alert(arguments[0].responseText);
            }
        });
    }


    //保存
    function doSaveQuestion(){
        $('#myFormQuestion').ajaxSubmit({
            datatype : 'json',
            success : function(resp) {
                var resp = $.parseJSON(resp);
                if (resp.errcode == 0) {
                    $('#myFormQuestion').modal('hide');
                    window.location.reload();//刷新
                } else {
                    Modal.tipFailure(resp.message);//失败提示
                }
            },
            error: function(){
                Modal.tipFailure('保存失败');
                //alert(arguments[0].responseText);
            }
        });
    }

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


    //删除
    function doDeleteExam(id){
        Modal.confirm('确定删除?',function(){
            $.ajax({
                url:'/admin/examDelete',
                type:'POST',
                dataType:'json',
                data:{"id":id},
                success:function(resp){
                    var errcode = resp.errcode;
                    if(errcode == 0){
                        Modal.alert('删除成功!',function(){
                            window.location.reload();//刷新
                        });
                        window.location.reload();//刷新
                    }
                }
            });
        });
    }

    //删除
    function doDeleteQuestion(id){
        Modal.confirm('确定删除?',function(){
            $.ajax({
                url:'/admin/questionDelete',
                type:'POST',
                dataType:'json',
                data:{"id":id},
                success:function(resp){
                    var errcode = resp.errcode;
                    if(errcode == 0){
                        Modal.alert('删除成功!',function(){
                            window.location.reload();//刷新
                        });
                        window.location.reload();//刷新
                    }
                }
            });
        });
    }

</script>

</body>
</html>
