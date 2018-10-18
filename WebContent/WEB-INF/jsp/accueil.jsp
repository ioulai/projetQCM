<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Accueil</title>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
	<div class="container-fluid">
<br>
		<div style="text-align: center;">
			<h1>Accueil</h1>
		</div><br><br>		
		<div class="container-fluid">
			<div class="row ">
				<div class="card col-lg-6 col-md-9"	style="width: 18rem; margin: 0 auto">
					<div class="card-body" style="text-align: center; margin: 30px;">
						<c:choose>
							<c:when test="${profil == 'CANDIDAT'}">
								<h5 class="card-title">Profil candidat</h5>
								<i class="far fa-user"></i>
								<i>${utilisateurConnecte.nom} ${utilisateurConnecte.prenom}</i><br><br>
								<p class="card-text">Afficher le(s) épreuve(s) ci-dessous</p>
								<a href="ListeEpreuve" class="btn btn-primary">Afficher</a>
							</c:when>
							<c:when test="${profil == 'COLLABORATEUR'}">
								<h5 class="card-title">Profil collaborateur</h5><br>
								<i class="far fa-user"></i>
								<i>${utilisateurConnecte.nom} ${utilisateurConnecte.prenom}</i><br><br>
								<p class="card-text">Administration ci-dessous</p>
								<a href="AjoutUtilisateur" class="btn btn-link">Création	utilisateur</a>
								<a href="SupprUtilisateur" class="btn btn-link">Suppression utilisateur</a>
								<a href="Inscription" class="btn btn-link">Inscription</a>
								<a href="Desinscription" class="btn btn-link">Désinscription</a>
								<a href="AjoutPromo" class="btn btn-link">Ajout promotion</a>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
</body>
</html>