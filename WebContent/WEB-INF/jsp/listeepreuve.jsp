<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Liste des epreuves</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css"> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		
		<div id="content" style="text-align: center;">	

			<c:forEach items="${epreuve}" var="article">
				<form method="POST" action="SelectionController">
					<input type="hidden" value="${nomListe}" name="nomListe">
					<input type="hidden" value="${article.id}" name="idArticle">
					
					<c:if test="${article.selected == true}">
						<input type="checkbox" name="isSelected" onclick="submit()" checked>
					</c:if>
					
					<c:if test="${article.selected != true}">
						<input type="checkbox" name="isSelected" onclick="submit()">
					</c:if>
					
					<span id="nomListe">${article.nom}</span>
					<br><br>
				</form>
			</c:forEach>	
			<br>
			<hr width="50%">
			<br>
			
			<form action="ListeArticleController" method="GET">
				<input type="submit" value="Retour">
			</form>
			<form action="SelectionController" method="POST">
				<input type="hidden" value="${nomListe}" name="nomListe">
				<input type="hidden" value="true" name="isReinitialiser">
				<input type="submit" value="Réinitialiser">
			</form>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>