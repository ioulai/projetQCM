<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout d'un utilisateur</title>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
<script type="text/javascript">
	function affichePromo() {
		var idx = profilbox.selectedIndex;
		var content = profilbox.options[idx].innerHTML;
		var doc = document.getElementById("div");

		if (content == "CANDIDAT") {
			doc.style.visibility = 'visible';
		} else {
			doc.style.visibility = 'hidden';
		}
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
	<br>
	<div class="row" style="width: 35rem; margin: 0 auto">
		<h1>Ajout d'un utilisateur</h1>
		<br> <br>
	</div>
	<div style="width: 20rem; margin: 0 auto">
		<h5 style="color: red;">${error}</h5>
		<h5 style="color: green;">${validate}</h5>
	</div>
	<br>
	<br>
	<div class="container-fluid">
		<div class="row">
			<div class="card col-lg-9 col-md-12 "
				style="width: 18rem; margin: 0 auto">
				<div class="card-body">
					<div class="row">
						<div class="form-group col-lg-6 col-md-6 " id="content">
							<form name="form" method="Post" action="AjoutUtilisateur">
								<label for="Nom">Nom</label> <input class="form-control"
									type="text" id="Nom" name="Nom" required> <label
									for="Prenom">Prénom</label> <input class="form-control"
									type="text" id="Prenom" name="Prenom" required> <label
									for="Email">Email</label> <input class="form-control"
									type="text" id="Email" name="Email" required> <label
									for="MDP">Mot de passe</label> <input class="form-control"
									type="password" id="MDP" name="mdp" required> <label
									for="profilbox">Profil</label> <select class="form-control"
									id="profilbox" name="profilbox" onclick="affichePromo()">
									<c:forEach var="profil" items="${profil}">
										<option>${profil.libelle}</option>
									</c:forEach>
								</select>
								<br>
								<div id="div" style="visibility: hidden;">
									<label for="promotionbox">Promotion</label> <select
										class="form-control" id="promotionbox" name="promotionbox">
										<c:forEach var="promotion" items="${promotion}">
											<option>${promotion.libelle}</option>
										</c:forEach>
									</select>
								</div>
								<br>
								<input class="btn btn-default" type="submit" value="Ajouter">
							</form>
						</div>
						<div class="col-lg-3 col-md-5">
							<ul style="list-style-type: circle;">
								<c:forEach var="utilisateur" items="${utilisateur}">
									<li>${utilisateur.nom}&nbsp;${utilisateur.prenom}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
</body>
</html>