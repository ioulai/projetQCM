<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inscription d'un candidat</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Inscription d'un candidat</h1>
			<h5 style="color: red;">${error}</h5>
		</div>
		<div id="content" style="display: block;width: 150px;float: left; margin-left:45%;">	
				<form name="form" method="Post" action="AjoutUtilisateur">
					Candidat <select name="candidatbox">	
					<c:forEach var="candidat" items="${candidat}">
				            <option>${candidat.nom} ${candidat.prenom}</option>
				    </c:forEach>
				    </select><br><br>
					Test <select name="testbox">	
					<c:forEach var="test" items="${test}">
				            <option>${test.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    Date de début de validité du test
				    <input type="date" name="debutdate"><br>
				    Date de fin de validité du test
				    <input type="date" name="findate"><br>
					<input type="submit" value="Ajouter"><br>
				</form>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>