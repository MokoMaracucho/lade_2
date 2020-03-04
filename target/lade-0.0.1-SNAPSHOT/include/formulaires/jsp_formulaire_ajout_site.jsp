<form method="post" action="AjoutSite" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">AJOUT D'UN SITE</h1>
	
	<div>
		<p class="${ empty traitementFormulaireAjoutSite.erreursAjoutSite ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireAjoutSite.resultatAjoutSite }</p>
	</div>

  	<div class="form-group">
    	<label for="nomSite" class="form-label-color">Nom du site</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireAjoutSite.erreursAjoutSite['nomSite'] }</div>
    	<input id="nomSite" name="nomSite" type="text" class="form-control" aria-describedby="nomSite" value="<c:out value="${ nouveauSite.nomSite }"/>">
  	</div>
  	
  	<div class="form-group">
    	<label for="regionSite" class="form-label-color">Région</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireAjoutSite.erreursAjoutSite['regionSite'] }</div>
		
      		
    	<select class="custom-select mr-sm-2" id="regionSite" name="regionSite">
      		<option selected></option>
        	<option value="Auvergne-Rhône-Alpes" class="selectTexte">Auvergne-Rhône-Alpes</option>
	       	<option value="Bourgogne-Franche-Comté" class="selectTexte">Bourgogne-Franche-Comté</option>
	        <option value="Bretagne" class="selectTexte">Bretagne</option>
	        <option value="Centre-Val de Loire" class="selectTexte">Centre-Val de Loire</option>
	        <option value="Corse" class="selectTexte">Corse</option>
	        <option value="Grand Est" class="selectTexte">Grand Est</option>
	        <option value="Guadeloupe" class="selectTexte">Guadeloupe</option>
	        <option value="Guyane" class="selectTexte">Guyane</option>
	        <option value="Haut-de-France" class="selectTexte">Haut-de-France</option>
	        <option value="Île-de-France" class="selectTexte">Île-de-France</option>
	        <option value="Martinique" class="selectTexte">Martinique</option>
	        <option value="Mayotte" class="selectTexte">Mayotte</option>
	        <option value="Normandie" class="selectTexte">Normandie</option>
	        <option value="Nouvelle-Aquitaine" class="selectTexte">Nouvelle-Aquitaine</option>
	        <option value="Occitanie" class="selectTexte">Occitanie</option>
	        <option value="Pays de la Loire" class="selectTexte">Pays de la Loire</option>
	        <option value="Provence-Alpes-Cote d'Azur" class="selectTexte">Provence-Alpes-Côte d'Azur</option>
	        <option value="La Réunion" class="selectTexte">La Réunion</option>
 		</select>
   	</div>
   
   	<div class="form-group">
    	<label for="commentaireSite" class="form-label-color">Commentaire</label>
		<div class="small">${ traitementFormulaireAjoutSite.erreursAjoutSite['regionSite'] }</div>
    	
       	<textarea class="form-control" id="commentaireSite" name="commentaireSite"></textarea>
   	</div>
   
	<button type="submit" class="btn btn-success">Ajouter le site</button>
</form>