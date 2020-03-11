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
        	<option value="Auvergne-Rhône-Alpes">Auvergne-Rhône-Alpes</option>
	       	<option value="Bourgogne-Franche-Comté">Bourgogne-Franche-Comté</option>
	        <option value="Bretagne">Bretagne</option>
	        <option value="Centre-Val de Loire">Centre-Val de Loire</option>
	        <option value="Corse">Corse</option>
	        <option value="Grand Est">Grand Est</option>
	        <option value="Guadeloupe">Guadeloupe</option>
	        <option value="Guyane">Guyane</option>
	        <option value="Haut-de-France">Haut-de-France</option>
	        <option value="Île-de-France">Île-de-France</option>
	        <option value="Martinique">Martinique</option>
	        <option value="Mayotte">Mayotte</option>
	        <option value="Normandie">Normandie</option>
	        <option value="Nouvelle-Aquitaine">Nouvelle-Aquitaine</option>
	        <option value="Occitanie">Occitanie</option>
	        <option value="Pays de la Loire">Pays de la Loire</option>
	        <option value="Provence-Alpes-Cote d'Azur">Provence-Alpes-Côte d'Azur</option>
	        <option value="La Réunion" class="selectTexte">La Réunion</option>
 		</select>
   	</div>
   
   	<div class="form-group">
    	<label for="commentaireSite" class="form-label-color">Commentaire</label>
       	<textarea class="form-control" id="commentaireSite" name="commentaireSite"></textarea>
   	</div>
   
	<button type="submit" class="btn btn-success">Ajouter le site</button>
</form>