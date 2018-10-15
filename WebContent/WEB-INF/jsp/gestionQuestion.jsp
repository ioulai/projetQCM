<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Ajout d'un test</title>
		<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Ajout d'un test</h1>
			<h5 style="color: red;">${error}</h5>
		</div>
		<div id="content" style="display: block;width: 150px;float: left; margin-left:45%;">	
				<form name="form" method="Post" action="AjoutTest">
				Intitulé du test
					<input type="text" name="libelle" required><br>
				Description
					<input type="text" name="Prenom" required><br>
				Durée
					<input type="text" name="Email" required><br>
				Seuil Haut
					<input type="text" name="Email" required><br>
				Seuil Bas
					<input type="text" name="Email" required><br>
					
					
					
				Mot de passe
					<input type="password" name="mdp" required><br>
					Profil <select id="profilbox" name="profilbox" onclick="affichePromo()">	
					<c:forEach var="profil" items="${profil}">
				            <option>${profil.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    <div id ="div" style="visibility:hidden;">
					Promo <select name="promotionbox">	
					<c:forEach var="promotion" items="${promotion}">
				            <option>${promotion.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    </div>
					<input type="submit" value="Ajouter"><br>
				</form>	
		</div>
		
	<div id="content" style="display: block;float: left;">
			<ul style="list-style-type:circle;">
			<c:forEach var="candidat" items="${candidat}">
		 		<li>${candidat.nom} ${candidat.prenom}</li>
		  	</c:forEach>
			</ul>
<ol style="list-style-type:circle;">
			<c:forEach var="question" items="${question}">
		 		<li>${question.enonce}</li>
		  	</c:forEach>
		</ol>
		
		<form name="form" method="Post" action="Suppr">
			<input type="submit" value="Supprimer">
		</form>
		
		<form name="form" method="GET" action="AjoutTest">
			<input type="submit" value="Ajouter">
		</form>
		</div>
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>