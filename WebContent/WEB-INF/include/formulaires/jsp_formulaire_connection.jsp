<form method="post" action="Connection" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="text-light">CONNECTION</h1>
	
	<div>
		<p class="${ empty traitementFormulaireConnection.erreursConnection ? 'succesConnection' : 'erreurConnection' } font-weight-bold text-danger">${ traitementFormulaireConnection.resultatConnection }</p>
	</div>
  	<div class="form-group">
    	<label for="emailUtilisateur" class="form-label-color">Adresse email</label>
  		<div class="small text-warning p-form-warning">${ traitementFormulaireConnection.erreursConnection['emailUtilisateur'] }</div>
    	<input id="emailUtilisateur" name="emailUtilisateur" type="email" class="form-control" aria-describedby="emailUtilisateur" value="<c:out value="${ utilisateur.emailUtilisateur }"/>">
  	</div>
  
  	<div class="form-group">
    	<label for="motDePasseUtilisateur" class="form-label-color">Mot-de-passe</label>
  		<div class="small text-warning p-form-warning">${ traitementFormulaireConnection.erreursConnection['motDePasseUtilisateur'] }</div>
    	<input id="motDePasseUtilisateur" name="motDePasseUtilisateur" type="password" class="form-control" id="motDePasseUtilisateur">
  	</div>
  
  	<button type="submit" class="btn btn-primary">Se connecter</button>
</form>