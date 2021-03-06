<!DOCTYPE html>
<html lang="utf-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>在线网校学习平台</title>


    <#include "../common/res.ftl"/>
    <script type="text/javascript">
        CONETXT_PATH = '';
    </script>
</head>

<body>
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
    <!-- 课程列表-start -->
    <div class="types-block clearfix" style="padding:0px;">
        <h3 style="margin-bottom: 20px;">
            <span class="types-title" style="margin-right:40px;">题目列表</span>
        </h3>

        <table class="table">
            <thead class="thead-light bg-info" style="width: 50px">
            <tr>
                <th scope="col">题号</th>
                <th scope="col">题目</th>
                <th scope="col">难度</th>
                <th scope="col">题型</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <if questionList??>
                <#list questionList as item>
                    <tr style="width: 40px">
                    <td><span>${item_index}</span></td>
                    <td><span>${item.title}</span></td>
                    <td>
                        <div class="ui star rating" data-rating="3">${item.difficulty}</div>
                    </td>
                    <td>
                        <span>
                        <#if item.questionType==0>
                           单选题
                           <#elseif  item.questionType==1>
                            多选题
                            <#elseif  item.questionType==2>
                             问答题
                            <#elseif item.questionType==3>
                            编程题
                        </#if>
                       </span></td>
                    <td><a href="/exam/problemDetail/${item.id}" target="_blank">详情</a></td>
                </tr>
                </#list>
                <else>
                    <tr>
                        <td colspan="9" style="padding: 0px;"><div class="ui icon message" style="margin: 0px; box-shadow: none;"><i class="info circle icon"></i><div class="content"><p>我也是有底线的呢！_(:зゝ∠)_</p></div></div></td>
                    </tr>
                </else>
            </if>


            </tbody>
        </table>

    </div>
    <!-- 课程列表-end -->

</div>
<#include "../common/footer.ftl"/>

</body>

</html>
