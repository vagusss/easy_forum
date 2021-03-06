<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>版块内容</title>
    <link rel="stylesheet" href="../../resources/css/material-icons.css">
    <link rel="stylesheet" href="../../resources/css/material.min.css">
    <link rel="stylesheet" href="../../resources/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="../../resources/js/material.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../resources/bootstrap/js/bootstrap.js"></script>
    <style>
        .card-width {
            width: 600px;
        }
        .clear-height {
            min-height: auto;
        }
        .center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<!-- Uses a header that scrolls with the text, rather than staying
  locked at the top -->
<div class="mdl-layout mdl-js-layout">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--grey-50">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <a class="mdl-layout-title mdl-navigation__link mdl-color-text--pink-400" href="/main">Excited</a>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation -->
            <nav class="mdl-navigation">
                <c:choose>
                    <c:when test="${username != null}">
                        <a class="mdl-navigation__link mdl-color-text--pink-400" href="/user/listUserInfo?username=${username}">${username}</a>
                        <c:if test="${username == 'admin'}">
                            <a class="mdl-navigation__link mdl-color-text--black" href="/admin/manageCenter">登入管理后台</a>
                        </c:if>
                        <a class="mdl-navigation__link mdl-color-text--black" href="/addPost?userName=${username}&boardId=${boardId}">
                            发布新主题
                        </a>
                        <a class="mdl-navigation__link mdl-color-text--black" href="/user/loginOut">注销</a>
                    </c:when>
                    <c:when test="${username == null}">
                        <a class="mdl-navigation__link mdl-color-text--pink-400" href="/userLogin">登录 </a>
                        <a class="mdl-navigation__link mdl-color-text--pink-400" href="/userRegister">注册</a>
                    </c:when>
                </c:choose>
            </nav>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="page-content">
            <c:forEach items="${pageInfo.list}" var="post" varStatus="status">
                <div class="mdl-grid">
                    <div class="mdl-cell mdl-cell--3-col"></div>
                    <div class="mdl-cell mdl-cell--6-col">
                        <div class="card-width clear-height center mdl-card mdl-shadow--2dp">
                            <div class="mdl-card__title">
                                <h4 class="mdl-color-text--pink-400">
                                    <a href="/post/postContent-${post.postId}">${post.postTitle}</a>
                                </h4>
                            </div>
                            <div class="mdl-card__supporting-text">
                                <div class="mdl-grid">
                                    <div class="mdl-cell--2-col" align="left">
                                            ${post.postUserName}
                                    </div>
                                    <div class="mdl-cell--4-offset"></div>
                                    <div class="mdl-cell--4-offset"></div>
                                    <div class="mdl-cell--2-col" align="right">
                                            ${post.postReplyCount}
                                    </div>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${username != null && username == 'admin'}">
                                    <div class="mdl-card__actions mdl-card--border">
                                        <a class="mdl-button mdl-js-button mdl-color-text--pink-400"
                                           href="/admin/deletePost?postId=${post.postId}&postBoardId=${post.postBoardId}">删除</a>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="mdl-cell mdl-cell--3-col"></div>
                </div>
            </c:forEach>
        </div>
    </main>

    <%--分页模块--%>
    <nav aria-label="..." class="center">
        <%--<ul class="pagination">
            <li class="page-item ">
                <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.prePage}" tabindex="-1">Previous</a>
            </li>
            <c:if test="${pageInfo.pageNum-1 gt 0}">
            <li class="page-item ">
                <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.pageNum-1}">${pageInfo.pageNum-1}</a>
            </li>
            </c:if>
            <li class="page-item ">
                <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.pageNum}">${pageInfo.pageNum} <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${pageInfo.pageNum+1 le pageInfo.lastPage}">
            <li class="page-item">
                <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.pageNum+1}">${pageInfo.pageNum+1}</a>
            </li>
            </c:if>
            <li class="page-item">
                <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.nextPage}">Next</a>
            </li>
        </ul>--%>

        <ul class="pagination">
            <c:if test="${!pageInfo.isFirstPage}">
                <li class="page-item ">
                    <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.firstPage}">首页</a>
                </li>
                <li class="page-item ">
                    <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.prePage}">上一页</a>
                </li>
            </c:if>
            <c:forEach items="${pageInfo.navigatepageNums}" var="navigatepageNum">

                <c:if test="${navigatepageNum==pageInfo.pageNum}">
                    <li class="page-item active">
                        <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${navigatepageNum}">${navigatepageNum}</a>
                    </li>
                </c:if>
                <c:if test="${navigatepageNum!=pageInfo.pageNum}">
                    <li class="page-item ">
                        <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${navigatepageNum}">${navigatepageNum}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${!pageInfo.isLastPage}">
                <li class="page-item ">
                    <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.nextPage}">下一页</a>
                </li>
                <li class="page-item ">
                    <a class="page-link" href="/board/listPosts-${boardId}?pageNum=${pageInfo.lastPage}">最后一页</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <script>

    </script>

</div>
</body>
</html>