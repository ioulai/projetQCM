<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentification</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">

<style type="text/css">
:root { -
	-input-padding-x: .75rem; -
	-input-padding-y: .75rem;
}

html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 420px;
	padding: 15px;
	margin: auto;
}

.form-label-group {
	position: relative;
	margin-bottom: 1rem;
}

.form-label-group>input, .form-label-group>label {
	padding: var(- -input-padding-y) var(- -input-padding-x);
}

.form-label-group>label {
	position: absolute;
	top: 0;
	left: 0;
	display: block;
	width: 100%;
	margin-bottom: 0; /* Override default `<label>` margin */
	line-height: 1.5;
	color: #495057;
	cursor: text; /* Match the input under the label */
	border: 1px solid transparent;
	border-radius: .25rem;
	transition: all .1s ease-in-out;
}
</style>
</head>
<body>
	<form class="form-signin" method="POST"
		action="AuthentificationPostController">
		<div class="text-center mb-4">
			<img class="mb-4" src="<%=request.getContextPath()%>/img/rocket.png"
				alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal">QCM</h1>
		</div>
			${messageErreur}
		<label for="inputEmail">Email address</label>
		<div class="form-label-group">

			<input type="email" id="inputEmail" name="email" class="form-control"
				placeholder="Email address" required autofocus>

		</div>
		<label for="inputPassword">Password</label>
		<div class="form-label-group">
			<input type="password" id="inputPassword" name="password"
				class="form-control" placeholder="Password" required>

		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
			
		<p class="mt-5 mb-3 text-muted text-center">&copy; TEAMROCKET
			2018-2019</p>
			
	</form>

</body>
</html>
