<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Désinscription d'un candidat</title>
	<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Désinscription d'un candidat</h1>
			<h5 style="color: red;">${error}</h5>
		</div>
		<div id="content" style="display: block;width:200px;float: left; margin-left:45%;">	
				<form name="form" method="Post" action="Desinscription">
						Candidat <select name="candidatbox" id="candidatbox" onchange="this.form.submit()">	
						<c:forEach var="candidat"  items="${candidat}">
								<c:if test="${candidat.id == selectionid}">
					            <option  value="${candidat.id}" selected="selected">${candidat.nom} ${candidat.prenom}</option>
					            </c:if>
					            <c:if  test="${candidat.id != selectionid}">
					            <option  value="${candidat.id}">${candidat.nom} ${candidat.prenom}</option>
					            </c:if>
					    </c:forEach>
					    </select><br><br>
				    </form>
				    
				    <form name="form" method="Post" action="Desinscription">
					Test <select name="testbox" id="testbox">	
					<c:forEach items="${epreuve}" var="epreuve">
				            <option value="${epreuve.test.id}">${epreuve.test.libelle}</option>
				    </c:forEach>
				    </select><br><br>
				    
					<input type="submit" value="Supprimer"><br>
				</form>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>