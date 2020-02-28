<form method="post" action="Inscription" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="text-light">INSCRIPTION</h1>
	
	<div>
		<p class="${ empty traitementFormulaireInscription.erreursInscription ? 'succesInscription' : 'erreurInscription' }">${ traitementFormulaireInscription.resultatInscription }</p>
	</div>

	<div class="form-row">
		<span class="erreurs">${ traitementFormulaireInscription.erreursInscription['prenomUtilisateur'] }</span>
	  	<div class="col form-group">
	    	<label for="prenomUtilisateur" class="form-label-color">Prénom</label>
	    	<input id="prenomUtilisateur" name="prenomUtilisateur" type="text" class="form-control" aria-describedby="prenomUtilisateur" value="<c:out value="${ nouvelUtilisateur.prenomUtilisateur }"/>">
	  	</div>
	  	
	  	<span class="erreurs">${ traitementFormulaireInscription.erreursInscription['nomUtilisateur'] }</span>
	  	<div class="col form-group">
	    	<label for="nomUtilisateur" class="form-label-color">Nom</label>
	    	<input id="nomUtilisateur" name="nomUtilisateur" type="text" class="form-control" aria-describedby="nomUtilisateur" value="<c:out value="${ nouvelUtilisateur.nomUtilisateur }"/>">
	  	</div>
  	</div>
  	
  	<div class="form-group">
  		<span class="erreurs">${ traitementFormulaireInscription.erreursInscription['emailUtilisateur'] }</span>
    	<label for="emailUtilisateur" class="form-label-color">Adresse email</label>
    	<input id="emailUtilisateur" name="emailUtilisateur" type="email" class="form-control" aria-describedby="emailUtilisateur" value="<c:out value="${ nouvelUtilisateur.emailUtilisateur }"/>">
  	</div>
  
  	<div class="form-group">
  		<span class="erreurs">${ traitementFormulaireInscription.erreursInscription['motDePasseUtilisateur'] }</span>
    	<label for="motDePasseUtilisateur" class="form-label-color">Mot-de-passe</label>
    	<input id="motDePasseUtilisateur" name="motDePasseUtilisateur" type="password" class="form-control" aria-describedby="motDePasseUtilisateur">
  	</div>
  
  	<div class="form-group">
    	<label for="confirmationMotDePasseUtilisateur" class="form-label-color">Confirmation du mot-de-passe</label>
    	<input id="confirmationMotDePasseUtilisateur" name="confirmationMotDePasseUtilisateur" type="password" class="form-control" aria-describedby="confirmationMotDePasseUtilisateur">
  	</div>
  	
  	<div class="form-group">
  		<span class="erreurs">${ traitementFormulaireInscription.erreursInscription['numeroMembreUtilisateur'] }</span>
    	<label for="numeroMembreUtilisateur" class="form-label-color">Numéro de membre</label>
    	<input id="numeroMembreUtilisateur" name="numeroMembreUtilisateur" type="text" class="form-control" aria-describedby="numeroMembreUtilisateur" value="<c:out value="${ nouvelUtilisateur.numeroMembreUtilisateur }"/>">
  	</div>
  
  	<button type="submit" class="btn btn-primary">S'inscrire</button>
</form>