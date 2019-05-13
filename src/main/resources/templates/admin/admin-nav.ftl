
<div class="sidebar-nav">
    <a href="#dashboard-menu" class="nav-header" data-toggle="collapse">
        <span style="font-weight: bold;font-size: 16px;">工作区</span>
    </a>
    <ul id="dashboard-menu" class="nav nav-list collapse in">
        <li <#if curNav?? && curNav =="home">class="active"</#if>>
            <a href="/admin/index">主 页</a>
        </li>
        <li <#if curNav?? && curNav =="course">class="active"</#if>>
            <a href="/admin/coursePageList">课程管理</a>
        </li>
        <li <#if curNav?? && curNav =="carousel">class="active"</#if>>
            <a href="/admin/carouselQueryPage">轮播配置</a>
        </li>
        <li <#if curNav?? && curNav =="classify">class="active"</#if>>
            <a href="/admin/classifyIndex">课程分类管理</a>
        </li>
        <li <#if curNav?? && curNav =="exam">class="active"</#if>>
            <a href="/admin/examIndex">题库管理</a>
        </li>
        <li <#if curNav?? && curNav =="user">class="active"</#if>>
            <a href="/admin/userPageList">用户管理</a>
        </li>
    </ul>

    <a href="#accounts-menu" class="nav-header" data-toggle="collapse">
        <span style="font-weight: bold;font-size: 16px;">我的账号</span>
    </a>
    <ul id="accounts-menu" class="nav nav-list collapse in">
        <li ><a href="/logout">退出</a></li>
    </ul>

	<#include "common/admin-footer.ftl"/>

</div>

