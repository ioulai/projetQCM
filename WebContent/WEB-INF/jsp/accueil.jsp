<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Accueil</title>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
	<div class="container-fluid">

		<div id="content" style="text-align: center;">
			<h1>Accueil</h1>
		</div>
		<div class="row">
			<div class="card col-lg-6" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">Profil</h5>
<%-- 					${utilisateurConnecte} --%>
					<i>${utilisateurConnecte.nom}	${utilisateurConnecte.prenom}</i> 
					<p class="card-text"> Afficher le.s épreuve·s ci-dessous </p>
					<a href="ListeEpreuve" class="btn btn-primary">Afficher</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
</body>
</html>