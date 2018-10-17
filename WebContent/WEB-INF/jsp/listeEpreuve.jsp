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
		<br><br>
		<div class="container-fluid">
			<div class="row">
				<div class="card col-lg-5" style="width: 18rem; margin: auto; text-align: center;">
				  	<div class="card-body">
					    <h3 class="card-title">Liste des epreuves</h3><br><br>
					    <div class="card-text">
				    		<c:forEach items="${epreuve}" var="epreuve">
								<form name="form" method="GET" action="Questions">
									<input type="hidden" value="${epreuve.test.id}" name="idTest"/>
								 	<input type="submit" class="btn btn-outline-secondary" value="${epreuve.test.libelle}"><br>
								</form><br>
							</c:forEach>
					    </div>
				    </div>
				</div>
			</div>	
		</div>	
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>