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
	<div style="text-align: center; margin: 0 auto">
		<h1>Ajout d'un utilisateur</h1><br>
		<h5 style="color: red;">${error}</h5>
	</div>
	<div class="container-fluid">
		<div class="row ">
			<div class="card col-lg-6 col-md-9 "
				style="width: 18rem; margin: 0 auto">
				<div class="card-body">
					<div class="form-group" id="content">
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
									<option >${profil.libelle}</option>
								</c:forEach>
							</select>
							<div id="div" style="visibility: hidden;">
								<label for="promotionbox">Promotion</label> <select
									class="form-control" id="promotionbox" name="promotionbox">
									<c:forEach var="promotion" items="${promotion}">
										<option>${promotion.libelle}</option>
									</c:forEach>
								</select>
							</div>
							<input class="btn btn-default" type="submit" value="Ajouter">
						</form>
					</div>
				</div>
			</div>
			
			<div id="content" style="display: block; float: left;">
				<ul style="list-style-type: circle;">
					<c:forEach var="candidat" items="${candidat}">
						<li>${candidat.nom} ${candidat.prenom}</li>
					</c:forEach>
					<c:forEach var="collaborateur" items="${collaborateur}">
						<li>${collaborateur.nom} ${collaborateur.prenom}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
</body>
</html>