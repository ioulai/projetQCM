<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="ListeArticleController">QCM</a>

	<div class="collapse navbar-collapse justify-content-end"
		id="navbarNav">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
		</ul>
	</div>

	<div class="collapse navbar-collapse" id="navbarNav"
		style="margin-left: 700px">
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${candidatConnecter.nom}</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					 <a	class="dropdown-item" href="#"><button class="btn btn-danger">Deconnecter</button></a></button>
				</div></li>
		</ul>
	</div>
</nav>