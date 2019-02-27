<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>员工列表</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="container-fluid">

<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header">SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-hover table-bordered table-striped">
                    <tr>
                        <th>#</th>
                        <th>empName</th>
                        <th>gender</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list }" var="emp">
                        <tr>
                            <th>${emp.empId }</th>
                            <th>${emp.empName }</th>
                            <th>${emp.gender=="M"?"男":"女" }</th>
                            <th>${emp.email }</th>
                            <th>${emp.department.deptName }</th>
                            <th>
                                <div>
                                    <button type="button" class="btn btn-primary btn-sm" style="margin-right: 15px">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        编辑
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        删除
                                    </button>
                                </div>
                            </th>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
    <%--分页条--%>
    <div class="row">
        <%--分页文字信息--%>
            <div class="col-md-6" style="padding: 15px">
                <p class="text-left lead">当前 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</p>
            </div>
        <%--分页信息--%>
            <!-- 分页条信息 -->
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a href="${pageContext.request.contextPath}/emps?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage }">
                            <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum-1}"
                                   aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                            </a></li>
                        </c:if>


                        <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                            <c:if test="${page_Num == pageInfo.pageNum }">
                                <li class="active"><a href="#">${page_Num }</a></li>
                            </c:if>
                            <c:if test="${page_Num != pageInfo.pageNum }">
                                <li><a href="${pageContext.request.contextPath}/emps?pn=${page_Num }">${page_Num }</a></li>
                            </c:if>

                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage }">
                            <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum+1 }"
                                   aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                            </a></li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
    </div>
</div>


<%--引入script--%>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
