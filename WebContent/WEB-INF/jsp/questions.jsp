<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Questions</title>
		<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		
		<div id="content" style="text-align: center;">	
			<h1>Listes des questions</h1><br><br>
			
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
		</div>
		
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>