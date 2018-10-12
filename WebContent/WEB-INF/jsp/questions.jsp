<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Questions</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/style.css"> 
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/navbar.jsp"></jsp:include>
		
		<div id="content" style="margin-left:30%;">	
			<h1>${libelle}</h1><br><br>
			
			<form method="POST" action="Marquage">
				<input type="hidden" value="${idTest}" name="idTest">
				<input type="hidden" value="${questionEnCours.id}" name="idQuestionCourante">
				<c:if test="${isMarquee == false}">
					<input type="submit" id="marquage" value="Marquer">
				</c:if>
				<c:if test="${isMarquee == true}">
					<input type="submit" id="marquage" value="Démarquer">
				</c:if>
			</form>
			
			<br>
			
			<c:set var="count" value="0" scope="page" />

			<c:forEach items="${listeQuestions}" var="question">
				<c:set var="count" value="${count + 1}" scope="page"/>
				
				<form method="GET" action="Questions">
					<input type="hidden" value="${idTest}" name="idTest">
					<input type="hidden" value="${question.id}" name="idQuestionCourante">
					<input type="submit" id="nomListe" value="Q${count}">
				</form>
			</c:forEach><br><br>
			
			<h3><c:out value="${questionEnCours.enonce}"></c:out></h3><br><br>
			
			<form method="POST" action="Questions">	
				<c:forEach items="${propositions}" var="proposition">
				<c:if test="${proposition.id == propSelected}">
					<input type="radio" name="idPropositionUser" value="${proposition.id}" checked>	 
				</c:if>
				<c:if test="${proposition.id != propSelected}">
					<input type="radio" name="idPropositionUser" value="${proposition.id}">	 
				</c:if> 	
					${proposition.enonce}<br>
				</c:forEach>
				<br><br>
				<input type="hidden" value="${idTest}" name="idTest">
				<input type="submit" value="Valider">
			</form>
			<br>
			<form method="POST" action="FinEpreuve">	
				<input type="hidden" value="${idTest}" name="idTest">
				<input type="submit" value="Terminer l'épreuve">
			</form>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>