<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 35%;
}

h5{
	position: absolute;
	top: 30%;
	left: 35%;
}

.msg{
	position: absolute;
	top: 10%;
	left: 35%;
	font-size: 15px;
	color: #664d03;
	background-color: #fff3cd;
	border-color: #ffecb5; 
}
</style>
</head>
<body>
	<h5>Bem vindo ao curso de JSP!</h5>
	
	<form action="<%=request.getContextPath()%>/ServletLogin1" method="post" class="row g-3 needs-validation" novalidate>
	
	<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
	
	<div class="md-3">
	<label class="form-label">Login:</label>
	<input class="form-control" name="login" type="text" required="required">
	    <div class="valid-feedback">
      ok
    </div>
    <div class="invalid-feedback">
        Obrigatório
      </div>
	</div>

    <div class="md-3">
	<label class="form-label">Senha:</label>
	<input class="form-control" name="senha" type="password" required="required">
	    <div class="valid-feedback">
      ok
    </div>
        <div class="invalid-feedback">
        Obrigatório
      </div>
	</div>
	
	<div class="col-12">
	<input class="btn btn-primary" type="submit" value="Acessar">
	</div>
	
	</form>
  <h5 class="msg">${msg}</h5>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
  
  <script type="text/javascript">

  (() => {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  const forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
	      if (!form.checkValidity()) {
	        event.preventDefault()
	        event.stopPropagation()
	      }

	      form.classList.add('was-validated')
	    }, false)
	  })
	})()
	
  </script>
</body>
</html>