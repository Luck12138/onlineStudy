<!DOCTYPE html>
<html lang="utf-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>在线网校学习平台</title>
    <#include "../common/res.ftl">
</head>

<body>
<!-- 头部-start -->
<#include "../common/header.ftl">
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

<!-- 登录注册-start -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  style="position:fixed; top:30%;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" style="font-size:18px;">×</span>
                </button>
                <h4 class="modal-title"  id="loginTitle"  style="float: left; color: #337Ab7;cursor: pointer; " onclick="login();">登 录</h4>
                <h4 class="modal-title"  id="registeTitle"  style="float: left;margin-left: 20px;cursor: pointer;" onclick="registe();">注 册</h4>
                <div class="clearfix"></div>
            </div>

            <div class="modal-body">
                <form id="loginForm" class="form-horizontal" style="padding: 0px 20px;">
                    <div class="form-group">
                        <input type="email" class="form-control"  id="username"  placeholder="用户名">
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control"  id="password"  placeholder="密　码">
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="checkbox" value="None" id="checkbox1" name="check">
                            <span class="text" style="color: #787D82;font-size: 14px;">下次自动登录</span>
                        </label>
                    </div>

                    <a href="javascript:void(0)">
                        <div class="header-login-btn">登 录</div>
                    </a>
                </form>

                <form id="registeForm" class="form-horizontal" style="padding: 0px 20px;display: none;">
                    <div class="form-group">
                        <input type="email" class="form-control"  id="username"  placeholder="用户名">
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control"  id="password"  placeholder="密　码">
                    </div>
                    <a href="javascript:void(0)">
                        <div class="header-login-btn">注 册</div>
                    </a>
                </form>

            </div>

        </div>
    </div>
</div>
<!-- 登录注册-end -->


<div class="f-main clearfix">
    <!-- 基本信息 -->

    <#if page.items??>
        <#list page.items as question>
            <#if question.questionType==0>
    <div class="main-course-left">
        <div class="course-info">
            <div class="course-title">题目详情</div>


            <div class="course-brief">
                ${question.content}
            </div>

            <#if question.questionType==0>
                <div class="course-menu">
                    <a  href=""><span class="menu-item" style="width:200px">A. ${question.optionA}</span></a>
                    <a  href=""><span class="menu-item" style="width:200px">B. ${question.optionB}</span></a>
                    <a  href=""><span class="menu-item" style="width:200px">C. ${question.optionC}</span></a>
                    <a  href=""><span class="menu-item" style="width:200px">D. ${question.optionD}</span></a>
                </div>
            </#if>


        </div>

        <!-- 基本信息-end -->

        <!-- 课程章节 - start -->




        <div>
            <div class="chapter">




                <div class="course-meta">
                    <span>选项：</span>
                </div>
                    <div class="course-menu">
                        <select  id="myAnswer" name="myAnswer" class="form-control"  type="select">
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                        </select>
                    </div>



                    <div class="course-meta">
                        <a  href="javascript:void(0)" onclick="toSaveGrade(${question.contestId!},${question.id!})" class="learn-btn" >提交本题</a>
                        <a href="/grade/check?id=${question.contestId!}" class="learn-btn" >离开并查看成绩</a>

                    </div>



            </div>



        </div>
        <!-- 课程章节 - end -->
    </div>

    <!-- 教师信息&推荐课程 - start -->
    <div class="main-course-right"  >

        <div class="lecturer-item" style="width: 100%;">
            <img class="lecturer-uimg" src="/res/i/header.jpg">
            <span class="lecturer-name">题型：<#if question.questionType==0>
                           单选题
                           <#elseif  question.questionType==1>
                               多选题
            <#elseif  question.questionType==2>
                问答题
            <#elseif question.questionType==3>
                编程题
                </#if></span>
            <span class="lecturer-name">难度：${question.difficulty}</span>
            <span class="lecturer-name">分值：${question.score}</span>
        </div>

    </div>
    <!-- 教师信息&推荐课程 - end -->
</#if>






    </#list>
        <#include "../common/tailPage3.ftl">
    </#if>
</div>



<!-- 页脚-start -->
<#include "../common/footer.ftl"/>
<!-- 页脚-end-->

<script type="text/javascript">
    $(function(){
        $('.chapter li').hover(function(){
            $(this).find('.icon-video').css('color','#FFF');
        },function(){
            $(this).find('.icon-video').css('color','#777');
        });
        $('.js-open').parent().find('ul').css('display','none');

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

    var _code = '${curCode}';
    var _subCode = '${curSubCode}';
    var _sort = '${sort!}';

    function _queryPage(pageNum,code,sort){
        var params = '?pageNum='+pageNum;
        //分类，参数没有就用页面缓存
        if(code == undefined){//来自于分页按钮
            code = _code;
            if(_subCode != '-2'){
                code = _subCode; //优先使用 subCode
            }
        }
        if(code == '-2'){//点击分类的全部
            code = _code;
        }
        if(code != '-1' && code != ''){
            params += '&c='+code;
        }

        //排序，函数参数没有就用页面缓存
        if(sort == undefined && _sort != ''){
            sort = _sort;
        }
        if(sort != undefined){
            params += '&sort='+sort;
        }
        window.location.href='/contest/detail/${id}'+params;
    }

    //课程上下架
    function toSaveGrade(id,question){
        var myAnswer = $('#myAnswer').val();
        $.ajax({
            url:'/grade/doAdd',
            type:'POST',
            dataType:'json',
            data:{"id":id,question:question,myAnswer:myAnswer},
            success:function(resp){
                var errcode = resp.errcode;
                if(errcode == 0){
                  alert("提交成功");
                }else {
                    alert("提交失败");
                }
            }
        });
    }

</script>

</body>

</html>
