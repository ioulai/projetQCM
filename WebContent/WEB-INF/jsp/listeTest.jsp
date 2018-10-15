<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Liste des tests</title>
	<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		<br>
		<div style="text-align:center;">
			<h1>Liste des tests</h1>
		</div>
		<div id="content" style="display: block;width:500px;float: left; margin-left:45%;">	
		<form name="form" method="Post" action="ListeTest">
			<select name="testbox" id="testbox" onchange="this.form.submit()">	
				<c:forEach var="test"  items="${test}">
					<c:if test="${test.id == selectionid}">
					    <option  value="${test.id}" selected="selected">${test.libelle}</option>
					</c:if>
					<c:if  test="${test.id != selectionid}">
				    	<option  value="${test.id}">${test.libelle}</option>
					</c:if>
				</c:forEach>
			</select><br><br>
		</form>
				    
		<ol style="list-style-type:circle;">
			<c:forEach var="question" items="${question}">
		 		<li>${question.enonce}</li>
		  	</c:forEach>
		</ol>
		
		<form name="form" method="Post" action="Suppr">
			<input type="submit" value="Supprimer">
		</form>
		
		<form name="form" method="GET" action="AjoutTest">
			<input type="submit" value="Ajouter">
		</form>
		</div>
        
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>