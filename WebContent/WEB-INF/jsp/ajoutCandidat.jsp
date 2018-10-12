<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
		    	
		    	if(content == "Candidat"){
		    		doc.style.visibility = 'visible';   
		    	}else{
		    		doc.style.visibility = 'hidden';  
		    	}
		    }
		</script> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Ajout d'un utilisateur</h1>
			<h5 style="color: red;">${error}</h5>
		</div>
		<div id="content" style="display: block;width: 150px;float: left; margin-left:45%;">	
				<form name="form" method="Post" action="AjoutUtilisateur">
				Nom
					<input type="text" name="Nom" required><br>
				Prénom
					<input type="text" name="Prenom" required><br>
				Email
					<input type="text" name="Email" required><br>
				Mot de passe
					<input type="password" name="mdp" required><br>
					Profil <select id="profilbox" name="profilbox" onclick="affichePromo()">	
					<c:forEach var="profil" items="${profil}">
				            <option>${profil.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    <div id ="div" style="visibility:hidden;">
					Promo <select name="promotionbox">	
					<c:forEach var="promotion" items="${promotion}">
				            <option>${promotion.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    </div>
					<input type="submit" value="Ajouter"><br>
				</form>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>