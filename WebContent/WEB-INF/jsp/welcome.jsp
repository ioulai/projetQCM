<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css"> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		
		<div id="content" style="text-align: center;">	
			<h1>Listes de courses</h1><br><br>

			<c:forEach items="${liste}" var="listee">
				<span id="toto">${listee.nom}</span>
				
				<form method="GET" action="SelectionController">
					<input type="hidden" value="${listee.nom}" name="nomListe">
					<img src="img/icon-caddy.png" height="40" onclick="submit()">
				</form>
				
				<form method="POST" action="ListeArticleController">
					<input type="hidden" value="${listee.nom}" name="nomListe">
					<img src="img/icon-delete.png" height="30" onclick="submit()">
				</form>
				<br><br>
			</c:forEach>
			
			<hr width="50%">
			<br>
			
			<a href="ArticleController"><img src="img/icon-add.png" height="30"></a>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>