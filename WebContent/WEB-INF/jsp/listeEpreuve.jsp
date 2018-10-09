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

			<c:forEach items="${epreuve}" var="epreuve">
				<c:forEach items="${epreuve.test}" var="test">
					<span id="nomListe">${test.libelle}</span>
					<br><br>
				</c:forEach>
			</c:forEach>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>