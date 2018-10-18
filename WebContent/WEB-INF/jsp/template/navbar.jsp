<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("utilisateurConnecte") == null) {
		response.sendRedirect("authentification");
	}
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="home">QCM</a>

	<div class="collapse navbar-collapse justify-content-end hidden"
		id="navbarNav">
		<ul class="navbar-nav mr-auto hidden">
			<li class="nav-item active"><a class="nav-link" href="#">
					<span class="sr-only">(current)</span>
			</a></li>
		</ul>
	</div>

	<div class=" pull-right" id="navbarNav">
		<form action="deconnecter" method="post">
			<button class="btn btn-outline-danger btn-sm" type="submit">Deconnecter</button>
			</button>
		</form>

	</div>
</nav>