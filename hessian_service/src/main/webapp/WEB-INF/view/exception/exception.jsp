<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;
%>
<base href="<%=basePath%>" >

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <title></title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    </head>
    <body>
     异常页面！！
    </body>
</html>