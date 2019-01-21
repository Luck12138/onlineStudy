<!DOCTYPE html>
<html lang="utf-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>在线网校学习平台</title>
		<#include "common/res.ftl">
</head>

<body>
		<#include "common/header2.ftl">
<div class="f-main clearfix">
    <div class="main-course-left">
        <!-- 基础信息 - start -->
        <div class="course-info">
            <div class="course-title">${(course.name)!}</div>
            <div class="course-meta">
                <#if curCourseSection??>
                   <a href="/course/video/${(curCourseSection.id)!}" class="learn-btn" >继续学习</a>
                    <div class="static-item"  >
                        <div class="meta">上次学到</div>
                        <div class="meta-value" title="${(curCourseSection.name)!}">${(curCourseSection.name)!}</div>
                    </div>
                </#if>
                <div class="static-item"  >
                    <div class="meta">学习人数</div>
                    <div class="meta-value">${(course.studyCount)!}</div>
                </div>
                <div class="static-item">
                    <div class="meta">难度级别</div>
                    <div class="meta-value">
									<#if course?? && course.level??>
										<#if course.level == 1>
										初级
										<#elseif course.level == 2>
										中级
										<#else>
										高级
										</#if>
									</#if>
                    </div>
                </div>
                <div class="static-item" style="border:none;">
                    <div class="meta">课程时长</div>
                    <div class="meta-value">${(course.time)!}</div>
                </div>
                <a id="collectionSpan" onclick="doCollect(${(course.id)!})" href="javascript:void(0)" class="following" style="float: right;margin-top:5px;" >
                </a>
            </div>

            <div class="course-brief">
			${(course.brief)!}
            </div>

            <div class="course-menu">
                <a  href="javascript:void(0)" >
                    <span onclick="showTab(this,'courseSection')" class="menu-item cur">章节</span>
                </a>
                <a  href="javascript:void(0)" >
                    <span onclick="showTab(this,'commentQA',0)" class="menu-item">评论</span>
                </a>
                <a  href="javascript:void(0)" >
                    <span onclick="showTab(this,'commentQA',1)" class="menu-item">问答</span>
                </a>
            </div>
        </div>
        <!-- 基础信息 - end -->

        <!-- 章节信息 - start -->
        <div id="courseSection">
					<#if chapterSection??>
					<#list chapterSection as item>
					<div class="chapter">
                        <a href="javascript:void(0);" class="js-open">
                            <h3>
                                <strong><div class="icon-chapter">=</div>${item.name!}</strong>
                                <span class="drop-down">▼</span>
                            </h3>
                        </a>
                        <ul class="chapter-sub">
							<#if item.sections??>
							<#list item.sections as section>
							<a href="/course/video/${section.id!}" >
                                <li class="chapter-sub-li">
                                    <i class="icon-video">▶</i>${section.name!} (${section.time!})
                                </li>
                            </a>
							</#list>
							</#if>
                        </ul>
                    </div>
					</#list>
					</#if>

        </div>
        <!-- 章节信息 - end -->

        <!-- 评论区 start -->
        <div id="commentQA">
        </div>
        <!-- 评论区 end -->
    </div>

    <div class="main-course-right"  >
				<#if courseTeacher??>
				<div class="lecturer-item" style="width: 100%;">
					<#if courseTeacher.header?? && courseTeacher.header != ''>
					<img class="lecturer-uimg" src="${courseTeacher.header!}">
					<#else>
					<img class="lecturer-uimg" src="/i/header.jpg">
					</#if>
                    <span class="lecturer-name">${(courseTeacher.realname)!""}</span>
                    <span class="lecturer-title">${(courseTeacher.collegeName)!""} · ${(courseTeacher.education)!""}</span>
                    <span class="lecturer-p" >${(courseTeacher.title)!""}，${(courseTeacher.sign)!""}</span>
                    <a href="javascript:void(0)"  onclick="doFollow('${(courseTeacher.id!)}');">
					<span id="followSpan" class="follow-btn">
					<#if followFlag?? && followFlag>
					已关注
					<#else>
					关注+
					</#if>
                    </span>
                    </a>
                </div>
				</#if>

        <h4 class="mt-50">推荐课程</h4>
				<#if recommendCourse?? && recommendCourse?size gt 0 >
					<#list recommendCourse as item>
				<a href="/course/learn/${item.id}" target="_black" class="mb-5" title="${item.name!}"><li class="ellipsis oc-color-hover">${item.name!}</li></a>
					</#list>
				</#if>
    </div>

</div>

		<#include "common/footer.ftl">

<script type="text/javascript">

    $(function(){
        //实现 章节鼠标焦点 动态效果
        $('.chapter li').hover(function(){
            $(this).find('.icon-video').css('color','#FFF');
        },function(){
            $(this).find('.icon-video').css('color','#777');
        });

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

        if(SHIRO_LOGIN){
            //判断是否已经收藏
            var courseId = ${(course.id)!}
            if(courseId){
                var url = '/collections/isCollection';
                doCollect(courseId,url);
            }
            //判断是否已经关注教师
            var followId = ${(courseTeacher.id!)};
            if(followId){
                var url = '/follow/isFollow';
                doFollow(followId,url);
            }
        }

    });

    /**
     *展示tab commentQA
     **/
    function showTab(el,divId,type){
        $('.course-menu').find('span').each(function(i,item){
            $(item).removeClass('cur');
        });
        $(el).addClass('cur');

        if(divId == 'courseSection'){
            $('#courseSection').show();
            $('#commentQA').hide();
        }else {
            $('#commentQA').show();
            $('#courseSection').hide();
            _queryPage(1,type);//默认加载 第 1 页
        }
    }

    /**
     *加载 课程评论 & 问答
     * courseId：课程id
     * sectionId: 章节id
     * type : 类型 0-评论；1-答疑
     */
    var _type = 0; //全局变量
    function _queryPage(pageNum,type){
        if(type == undefined)
            type = _type;
        else
            _type = type;
        //加载评论或者QA
        if(pageNum == undefined)
            pageNum = 1;
        var courseId = ${(course.id)!};//课程id
        var url = '/courseComment/segment';
        $("#commentQA").load(
                url,
                {'courseId':courseId,'type':type,'pageNum':pageNum},
                function(){}
        );
    };

    //收藏
    function doCollect(courseId,url){
        if(url == undefined){
            url = '/collections/doCollection';
        }
        //处理收藏
        $.ajax({
            url:url,
            type:'POST',
            dataType:'json',
            data:{"courseId":courseId},
            success:function(resp){
                if(resp.errcode == 1){//已收藏
                    $('#collectionSpan').attr('class','followed');
                }else if(resp.errcode == 0){//未收藏
                    $('#collectionSpan').attr('class','following');
                }
            }
        });
    }

    //关注
    function doFollow(followId,url){
        if(url == undefined){
            url = '/follow/doFollow';
        }
        $.ajax({
            url:url,
            type:'POST',
            dataType:'json',
            data:{"followId":followId},
            success:function(resp){
                if(resp.errcode == 1){
                    $('#followSpan').html('已关注');
                }else if(resp.errcode == 0){
                    $('#followSpan').html('+关注');
                }
            }
        });
    }
</script>

</body>

</html>
