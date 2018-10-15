<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Fin de test</title>
		<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		
		<div id="content" style="text-align: center;">
			<h1>Fin de l'épreuve</h1><br><br>
			
			<c:set var="count" value="0" scope="page" />
			
			<table style="margin: 0px auto;">
		  		<tr>
				    <td>Numéro</td>
				    <td>Marquée</td>
				    <td>Résolue</td>
				    <td></td>
			  	</tr>
			  	
				<c:forEach items="${questions}" var="question">
					<c:set var="count" value="${count + 1}" scope="page"/>
					
				
				  	<tr>
					    <td>${count}</td>
					    <td>
						    <c:if test="${question.isMarquee()}"><span style="color:orange">Oui</span></c:if>
						    <c:if test="${!question.isMarquee()}">Non</c:if>
					    </td>
					    <td>
						    <c:if test="${question.isResolue()}"><span style="color:green">Oui</span></c:if>
						    <c:if test="${!question.isResolue()}"><span style="color:red">Non</span></c:if>
					    </td>
					    <td>
						    <form method="GET" action="Questions">
								<input type="hidden" value="${idTest}" name="idTest">
								<input type="hidden" value="${question.id}" name="idQuestionCourante">
								<input type="submit" id="nomListe" value="Modifier"><br>
							</form>
					    </td>
				  	</tr>
				</c:forEach>
			</table>
			<br><br>
			<form method="POST" action="FinEpreuve">	
				<input type="hidden" value="${idTest}" name="idTest">
				<input type="submit" value="Terminer l'épreuve">
			</form>

		</div>
		
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>