<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Ajout d'un Candidat</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css"> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		<br>
		<center><h1>Ajout d'un Candidat</h1></center>
		
		<div id="content" style="text-align: center;">	

				<form name="form" method="Post" action="AjoutPromo">
				Nom
					<input type="text" name="Nom"><br>
				Prénom
					<input type="text" name="Prenom"><br>
				Email
					<input type="text" name="Email"><br>
				Mot de passe
					<input type="text" name="mdp"><br>
				<c:forEach var="profile" items="${profile}">
			        <tr>
			            <td>${profile.libelle}</td>
			        </tr>
			    </c:forEach>
					<input type="submit" value="Ajouter"><br>
				</form>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>