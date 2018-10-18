<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription d'un candidat</title>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
	<br>
	<div style="text-align:center;">
			<h1>Inscription d'un candidat</h1>
			<h5 style="color: red;">${error}</h5>
			<h5 style="color: green;">${validate}</h5>
		</div>
	<div class="container-fluid">
		<div class="row ">
			<div class="card col-lg-6 col-md-9 "
				style="width: 18rem; margin: 0 auto">
				<div class="card-body">
					<div class="form-group" id="content">
						<form name="form" method="Post" action="Inscription">
							Candidat <select class="form-control" name="candidatbox">
								<c:forEach var="candidat" items="${candidat}">
									<option value="${candidat.id}">${candidat.nom}
										${candidat.prenom}</option>
								</c:forEach>
							</select><br> <br> Test <select class="form-control"
								name="testbox">
								<c:forEach var="test" items="${test}">
									<option value="${test.id}">${test.libelle}</option>
								</c:forEach>
							</select><br> <br> Date de début de validité du test <input
								class="form-control" type="date" name="debutdate" required><br>
							Date de fin de validité du test <input class="form-control"
								type="date" name="findate" required><br> 
								<input class="btn btn-default" type="submit" value="Ajouter"><br>
						</form>
					</div>
				</div>
			</div>
			</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
</body>
</html>