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
		<br><br>
		<div class="container-fluid">
			<div class="row">
				<div class="card col-lg-5" style="width: 18rem; margin: auto; text-align: center;">
				  	<div class="card-body">
					    <h3 class="card-title">Résultats</h3><br><br>
					    <div class="card-text">
							<h4>${niveau}</h4><br>
							<h2><c:out value="${note}"></c:out>/20</h2><br><br>
		
							<ul style="list-style-type:none;">
								<c:forEach var="themeResultats" items="${themeResultats}">
									<li>${themeResultats.getTauxReussite()} % de bonne reponse pour le theme "${themeResultats.theme}"</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>