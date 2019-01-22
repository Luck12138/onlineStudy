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
        <div class="row-fluid">

            <div class="row-fluid">
                <div class="block span6">
                    <a href="#tablewidget" class="block-heading" data-toggle="collapse">用户注册增长</a>
                    <div id="report-1" style="width:100%;height:400px;"></div>
                     <#if staticsVo??>
                    <script type="text/javascript">
                        var staticsVo = ${staticsVo!};//后台数据
                        var categories = staticsVo.categories;
                        var data = staticsVo.data;
                        // var categories =  ['03-19', '03-20', '03-21', '03-22', '03-23', '03-24','03-25', '03-26', '03-27', '03-28'];
                        // var data = [100, 200, 300, 145, 182, 215, 252, 265, 280, 300];
                        $(function () {
                            $('#report-1').highcharts({
                                title: {
                                    text: '用户注册增长人数(10天)',
                                    x: -20 //center
                                },
                                subtitle: {
                                    text: '',
                                    x: -20
                                },
                                credits: {
                                    enabled: false
                                },
                                xAxis: {
                                    categories:categories //分类
                                },
                                yAxis: {
                                    title: {
                                        text: '注册人数'
                                    },
                                    plotLines: [{
                                        value: 0,
                                        width: 1,
                                        color: '#0089D2'
                                    }]
                                },
                                tooltip: {
                                    valueSuffix: '人'
                                },
                                legend: {
                                    layout: 'vertical',
                                    align: 'center',
                                    verticalAlign: 'middle',
                                    borderWidth: 0
                                },
                                series: [{
                                    name: '注册人数',
                                    data: data //数据
                                }]
                            });
                        });
                    </script>
                     </#if>
                </div>

            </div>

                <#include "common/admin-footer.ftl"/>
        </div>
    </div>
</div>

</body>
</html>
