<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Questions</title>
		<script src="<%=request.getContextPath()%>/js/chrono.js"></script>
		<script type="text/javascript"></script>
		<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>
	</head>
	<body onload="IndiquerMinutes(${duree}); DemarrerChrono();">	
		<jsp:include page="/WEB-INF/jsp/template/navbar.jsp"></jsp:include>
		
		<div class="container">
			<div class="row">
		    	<div class="col-xs"></div>
	    		<div class="col-lg">
		    		<br><h1>${libelle}</h1><br><br>

					<p id="chrono">Il reste 0 min 0 sec</p><br>
			
					<c:set var="count" value="0" scope="page" />
					<c:set var="haveFound" value="0" scope="page" />

					<c:forEach items="${listeQuestions}" var="question">
						<c:set var="count" value="${count + 1}" scope="page"/>
				
						<form method="GET" action="Questions">
							<input type="hidden" value="${idEpreuve }" id="idEpreuve">
							<input type="hidden" value="${idTest}" name="idTest">
							<input type="hidden" value="${question.question.id}" name="idQuestionCourante">
							
							<!-- Si question en cours -->
							<c:if test="${question.question.id == questionEnCours.id && question.isMarquee() && question.isResolue()}">
								<input type="submit" class="btn btn-success" id="nomListe" value="Q${count}*">
							</c:if>
							<c:if test="${question.question.id == questionEnCours.id && !question.isMarquee() && question.isResolue()}">
								<input type="submit" class="btn btn-success" id="nomListe" value="Q${count}">
							</c:if>
							<c:if test="${question.question.id == questionEnCours.id && !question.isMarquee() && !question.isResolue()}">
								<input type="submit" class="btn btn-dark" id="nomListe" value="Q${count}">
							</c:if>
							<c:if test="${question.question.id == questionEnCours.id && question.isMarquee() && !question.isResolue()}">
								<input type="submit" class="btn btn-dark" id="nomListe" value="Q${count}*">
							</c:if>
							
							<!-- Si question pas en cours -->
							<c:if test="${question.question.id != questionEnCours.id && question.isMarquee() && question.isResolue()}">
								<input type="submit" class="btn btn-outline-success" id="nomListe" value="Q${count}*">
							</c:if>
							<c:if test="${question.question.id != questionEnCours.id && !question.isMarquee() && question.isResolue()}">
								<input type="submit" class="btn btn-outline-success" id="nomListe" value="Q${count}">
							</c:if>
							<c:if test="${question.question.id != questionEnCours.id && !question.isMarquee() && !question.isResolue()}">
								<input type="submit" class="btn btn-outline-dark" id="nomListe" value="Q${count}">
							</c:if>
							<c:if test="${question.question.id != questionEnCours.id && question.isMarquee() && !question.isResolue()}">
								<input type="submit" class="btn btn-outline-dark" id="nomListe" value="Q${count}*">
							</c:if>
						</form>
					</c:forEach><br><br>

					<h3>Question <c:out value="${ordreQuestion}"></c:out></h3><br>

					<p><c:if test="${isMulti}">Plusieurs réponses sont attendues</c:if>
					<c:if test="${!isMulti}">Une seule réponse est attendue</c:if></p><br>

					<h4><c:out value="${questionEnCours.enonce}"></c:out></h4><br><br>
					<div class="container">
					<div class="row justify-content-around">
    					<div class="col-4">
    						<form method="POST" action="Questions" name="formProp">
							<c:forEach items="${propositions}" var="proposition">
								<c:forEach items="${propSelected}" var="id">
									<c:if test="${proposition.id == id}">
										<c:set var="haveFound" value="1" scope="page"/>
								
										<c:if test="${isMulti}">
											<input type="checkbox" name="idPropositionUser" value="${proposition.id}" checked>
										</c:if>
										<c:if test="${!isMulti}">
											<input type="radio" name="idPropositionUser" value="${proposition.id}" checked>
										</c:if>
									</c:if>
								</c:forEach>
						
								<c:if test="${haveFound == 0 && isMulti}">
									<input type="checkbox" name="idPropositionUser" value="${proposition.id}">
								</c:if>
								
								<c:if test="${haveFound == 0 && !isMulti}">
									<input type="radio" name="idPropositionUser" value="${proposition.id}">
								</c:if>
								
								<c:set var="haveFound" value="0" scope="page"/>
								${proposition.enonce}<br>
							</c:forEach><br><br>
							
							<input type="hidden" value="${idEpreuve }" id="idEpreuve">
							<input type="hidden" name="chronoform" id="chronoform">
							<input type="hidden" value="${questionEnCours.id}" name="idQuestionCourante">
							<input type="hidden" value="${idTest}" name="idTest">
						</form>
						
						</div>
						<div class="col-4">
					      <img width="150" height="150" src="<%=request.getContextPath()%>/img/${questionEnCours.media}">	
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col align-self-center">
						<input type="submit" class="btn btn-success" value="Valider" onclick="document.formProp.submit()">
					</div>
				</div>
						<form method="POST" action="Marquage">
							<input type="hidden" value="${idEpreuve }" id="idEpreuve">
							<input type="hidden" value="${idTest}" name="idTest">
							<input type="hidden" value="${questionEnCours.id}" name="idQuestionCourante">
							<c:if test="${isMarquee == false}">
								<input type="submit" class="btn btn-info" id="marquage" value="Marquer">
							</c:if>
							<c:if test="${isMarquee == true}">
								<input type="submit" class="btn btn-info" id="marquage" value="Démarquer">
							</c:if>
						</form>
				<form method="GET" action="PreResultats">	
					<input type="hidden" value="${idTest}" name="idTest">
					<input type="submit" class="btn btn-danger" value="Terminer l'épreuve">
				</form>
				</div>
		    </div>
		    <div class="col-xs"></div>
		  </div>
		<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
	</body>
</html>