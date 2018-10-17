<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Résultats</title>
		<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		
		<div id="content" style="margin-left:30%;">	
			<h1>Résultats</h1><br><br>
			
			<h3>${niveau}</h3>
			
			<br><br>
			
			<h3>Note : <c:out value="${note}"></c:out>/20</h3><br><br>
		
		<ul style="list-style-type:circle;">
		<c:forEach var="themeResultats" items="${themeResultats}">
			<li>${themeResultats.getTauxReussite()} % de bonne reponse pour le theme ${themeResultats.theme}</li>
		</c:forEach>
		</ul>
		</div>
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>