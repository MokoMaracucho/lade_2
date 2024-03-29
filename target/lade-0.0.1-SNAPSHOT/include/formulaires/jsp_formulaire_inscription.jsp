<form method="post" action="Inscription" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">INSCRIPTION</h1>
	
	<div>
		<p class="${ empty traitementFormulaireInscription.erreursInscription ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireInscription.resultatInscription }</p>
	</div>

  	<div class="form-group">
    	<label for="prenomUtilisateur" class="form-label-color">Pr�nom</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireInscription.erreursInscription['prenomUtilisateur'] }</div>
    	<input id="prenomUtilisateur" name="prenomUtilisateur" type="text" class="form-control" aria-describedby="prenomUtilisateur" value="<c:out value="${ nouvelUtilisateur.prenomUtilisateur }"/>">
  	</div>
  	
  	<div class="form-group">
    	<label for="nomUtilisateur" class="form-label-color">Nom</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireInscription.erreursInscription['nomUtilisateur'] }</div>
    	<input id="nomUtilisateur" name="nomUtilisateur" type="text" class="form-control" aria-describedby="nomUtilisateur" value="<c:out value="${ nouvelUtilisateur.nomUtilisateur }"/>">
  	</div>
  	
  	<div class="form-group">
    	<label for="emailUtilisateur" class="form-label-color">Adresse email</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireInscription.erreursInscription['emailUtilisateur'] }</div>
    	<input id="emailUtilisateur" name="emailUtilisateur" type="email" class="form-control" aria-describedby="emailUtilisateur" value="<c:out value="${ nouvelUtilisateur.emailUtilisateur }"/>">
  	</div>
  
  	<div class="form-group">
    	<label for="motDePasseUtilisateur" class="form-label-color">Mot-de-passe</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireInscription.erreursInscription['motDePasseUtilisateur'] }</div>
    	<input id="motDePasseUtilisateur" name="motDePasseUtilisateur" type="password" class="form-control" aria-describedby="motDePasseUtilisateur">
  	</div>
  
  	<div class="form-group">
    	<label for="confirmationMotDePasseUtilisateur" class="form-label-color">Confirmation du mot-de-passe</label>
		<div class="small text-warning p-form-warning"></div>
    	<input id="confirmationMotDePasseUtilisateur" name="confirmationMotDePasseUtilisateur" type="password" class="form-control" aria-describedby="confirmationMotDePasseUtilisateur">
  	</div>
  	
  	<div class="form-group">
    	<label for="numeroMembreUtilisateur" class="form-label-color">Num�ro de membre</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireInscription.erreursInscription['numeroMembreUtilisateur'] }</div>
    	<input id="numeroMembreUtilisateur" name="numeroMembreUtilisateur" type="text" class="form-control" aria-describedby="numeroMembreUtilisateur" value="<c:out value="${ nouvelUtilisateur.numeroMembreUtilisateur }"/>">
  	</div>
  
  	<button type="submit" class="btn btn-success">S'inscrire</button>
</form>