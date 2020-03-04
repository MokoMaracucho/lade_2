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
	        		<a class="nav-link" href="/lade/Deconnection">Deconnection <span class="sr-only">(current)</span></a>
	      		</li>
	      	</c:if>
	      
	      	<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouts </a>
       			
       			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
         			<a class="dropdown-item" href="AjoutSite">Ajout d'un site</a>
         				
         			<a class="dropdown-item" href="AjoutSecteur">Ajout d'un secteur</a>
         
         			<a class="dropdown-item" href="AjoutVoie">Ajout d'une voie</a>
         
         			<a class="dropdown-item" href="AjoutLongueur">Ajout d'une longueur</a>
         
         			<a class="dropdown-item" href="#">Ajout d'un topo</a>
       			</div>
      		</li>
	      
	      	<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Listes </a>
       			
       			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
         			<a class="dropdown-item" href="#">Liste des utilisateurs</a>
         				
         			<a class="dropdown-item" href="#">Liste des membres</a>
         			
         			<div class="dropdown-divider"></div>
         
         			<a class="dropdown-item" href="ListeSites">Liste des sites</a>
         
         			<a class="dropdown-item" href="ListeSecteurs">Liste des secteurs</a>
         
         			<a class="dropdown-item" href="ListeVoies">Liste des voies</a>
         
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