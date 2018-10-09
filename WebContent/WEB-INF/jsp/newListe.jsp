<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Accueil</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css"> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		
		<div id="content" style="text-align: center;">	
			<h1>Ajouter une liste</h1><br><br>
			
			<form action="AjoutController" method="POST">
				<c:if test="${isNew == true || isNew == null}">
					Nom de la liste <input type="text" name="nomListe" value="${nomListe}">
				</c:if>
				<c:if test="${isNew == false}">
					Nom de la liste <input type="text" name="nomListe" value="${nomListe}" disabled>
					<input type="hidden" name="nomListe" value="${nomListe}">
					<input type="hidden" name="isNew" value="${isNew}">
				</c:if>
				<br><br>
				Nom de l'article <input type="text" name="nomArticle"><img src="img/icon-add.png" height="40" onclick="submit()"/>
			</form>
			
			<br><br>
			
			<c:forEach items="${articles}" var="article">
				<form action="ArticleController" method="POST">
					<span id="toto">${article.nom}</span>
					<img src="img/icon-delete.png" height="40" onclick="submit()"/>
					<input type="hidden" name="id" value="${article.id}">
					<input type="hidden" name="nomListe" value="${nomListe}">
					<input type="hidden" name="isNew" value="${isNew}">
					<br><br>
				</form>
			</c:forEach>
			
			<br>
			<hr width="50%">
			<br>
			
			<form action="ListeArticleController" method="GET">
				<input type="submit" value="Retour">
			</form>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>