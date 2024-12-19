<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="logInOutLink" value="${sessionScope.id==null ? '/login/login' : '/login/logout'}" />
<c:set var="logInOut" value="${sessionScope.id==null ? 'Login' : 'Logout'}" />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>LJR</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
	<ul>
	    <li id="logo">LJR</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='/board/list'/>">Board</a></li>
	    <li><a href="<c:url value='${logInOutLink}'/>">${logInOut}</a></li>  
	    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div><div style="text-align:center">
	<h1>BOARD</h1>
</div>
</body>
</html>