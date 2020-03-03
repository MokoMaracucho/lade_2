<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="style.css">
		<title>Inscription - Les amis de l'escalade</title>
		
		<!-- CDN : Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	
	<body class="bg-grey">
		<header>
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  
  	<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
    	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	      	<li class="nav-item active">
	        	<a class="nav-link" href="/lade/Accueil">Accueil <span class="sr-only">(current)</span></a>
	      	</li>
	      	
	      	<c:if test="${empty sessionScope.sessionUtilisateur}">
	    		<li class="nav-item">
	        		<a class="nav-link" href="/lade/Inscription">Inscription <span class="sr-only">(current)</span></a>
	      		</li>
      		
	    		<li class="nav-item">
	        		<a class="nav-link" href="/lade/Connection">Connection <span class="sr-only">(current)</span></a>
	      		</li>
	      	</c:if>
	      	
	      	<c:if test="${!empty sessionScope.sessionUtilisateur}">
	    		<li class="nav-item">
	        		<a class="nav-link" href="/lade/Connection">Deconnection <span class="sr-only">(current)</span></a>
	      		</li>
	      	</c:if>
	      
	      	<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouts </a>
       			
       			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
         			<a class="dropdown-item" href="#">Ajout d'un site</a>
         				
         			<a class="dropdown-item" href="#">Ajout d'un secteur</a>
         
         			<a class="dropdown-item" href="#">Ajout d'une voie</a>
         
         			<a class="dropdown-item" href="#">Ajout d'une longueur</a>
         
         			<a class="dropdown-item" href="#">Ajout d'un topo</a>
       			</div>
      		</li>
	      
	      	<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Listes </a>
       			
       			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
         			<a class="dropdown-item" href="#">Liste des utilisateurs</a>
         				
         			<a class="dropdown-item" href="#">Liste des membres</a>
         			
         			<div class="dropdown-divider"></div>
         
         			<a class="dropdown-item" href="#">Liste des sites</a>
         
         			<a class="dropdown-item" href="#">Liste des secteurs</a>
         
         			<a class="dropdown-item" href="#">Liste des voies</a>
         
         			<a class="dropdown-item" href="#">Liste des longueurs</a>
         
         			<a class="dropdown-item" href="#">Liste des topos</a>
       			</div>
      		</li>
    	</ul>
    	
	    <form class="form-inline my-2 my-lg-0">
      		<input class="form-control mr-sm-2" type="search" aria-label="Search">
      		<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Recherche</button>
    	</form>
  	</div>
</nav>

<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<div class="succes small p-vous-etes-connecte text-secondary">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.emailUtilisateur}</div>
</c:if>
		</header>

		<div class="div-container">
			<div class="container">
				<div class="row">
					<div class="col-md-6 offset-md-3">
						<c:import url="include/formulaires/jsp_formulaire_inscription.jsp" ></c:import>
					</div>
	  			</div>
		    </div>
		</div>

		<!-- CDN : JQuery / Popper.js / Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> -->
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>