<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Liste des epreuves</title>
	<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Liste des epreuves</h1>
		</div>
		<div id="content" style="text-align: center;">	

			<c:forEach items="${epreuve}" var="epreuve">
					<form name="form" method="GET" action="Questions">
						<input type="hidden" value="${epreuve.test.id}" name="idTest"/>
					 	<input type="submit" value="${epreuve.test.libelle}"><br>
					</form>
			</c:forEach>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>