<form method="post" action="RechercheAvancee" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">RECHERCHE AVANCEE</h1>
	
	<div>
		<p class="${ empty traitementFormulaireConnection.erreursConnection ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireConnection.resultatConnection }</p>
	</div>
	
  	<div class="form-group">
    	<label for="parNomRechercheAvancee" class="form-label-color">Par nom</label>
  		<div class="small text-warning p-form-warning">${ traitementFormulaireRechercheAvancee.erreursRechercheAvancee['parNomRechercheAvancee'] }</div>
    	<input id="parNomRechercheAvancee" name="parNomRechercheAvancee" type="text" class="form-control" aria-describedby="parNomRechercheAvancee" value="<c:out value="${ utilisateur.emailUtilisateur }"/>">
  	</div>
  	
  	<div class="form-group">
    	<label for="parRegionRechercheAvancee" class="form-label-color">Par région</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireRechercheAvavncee.erreursRechercheAvavncee['parRegionRechercheAvancee'] }</div>	
    	<select class="custom-select mr-sm-2" id="parRegionRechercheAvancee" name="parRegionRechercheAvancee">
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
  
  	<button type="submit" class="btn btn-success">Rechercher</button>
</form>