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
		
		<div id="content" style="text-align: center;">	
			<h1>Listes des questions</h1><br><br>
			
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${questions}" var="question">
				<c:set var="count" value="${count + 1}" scope="page"/>
				
				<form method="POST" action="SelectionController">
					<input type="hidden" value="${question.idQuestion}" name="idQuestion">
					
					<span id="nomListe">Question ${count}</span>
					<br><br>
				</form>
			</c:forEach>	
		</div>
		
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</body>
</html>