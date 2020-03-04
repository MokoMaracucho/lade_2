<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" rel="stylesheet" href="style.css">
		<title>Liste des sites - Les amis de l'escalade</title>
		
		<!-- CDN : Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	
	<body class="bg-grey">
		<header>
			<c:import url="include/navbar/jsp_navbar.jsp" ></c:import>
		</header>
		
		<div class="div-container">
			<div class="container">
				<div class="row">
					<c:choose>
			        	<c:when test="${ empty listeSites }">
			        		<p>Il n'existe aucun site.</p>
			        	</c:when>
			        	
			        	<c:otherwise>
			        		<c:forEach var="site" items="${ requestScope['listeSites'] }">
		        				<div class="col-sm-3 form form-expand-lg form-dark bg-dark form-margin-padding form-border m-1 mt-3">
		        					<h5 class="font-weight-bold text-light">${ site.nomSite }</h5>
			        				
			        				<div class="corpsSection">
				        				<div class="small text-secondary"><strong>Créateur : </strong>${ site.createurSite.prenomUtilisateur } ${ site.createurSite.nomUtilisateur }</div>
				        				
				        				<div class="small text-secondary mb-2"><strong>Region : </strong>${ site.regionSite }</div>
				        				
				        				<div>
				        					<a type="button" href="/oc_lade/ListeSecteurs?nomSite=${ site.nomSite }" class="btn btn-success btn-sm text-dark mb-2">Voir les secteurs</a>
		
				        					<a type="button" href="/oc_lade/DetailSite?nomSite=${ site.nomSite }" class="btn btn-success btn-sm text-dark mb-2">Afficher les détails</a>
				        				</div>
			        				</div>
		        				</div>
			        		</c:forEach>
			        	</c:otherwise>
			        </c:choose>
	  			</div>
		    </div>
		</div>
		
		<!-- CDN : JQuery / Popper.js / Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> -->
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>