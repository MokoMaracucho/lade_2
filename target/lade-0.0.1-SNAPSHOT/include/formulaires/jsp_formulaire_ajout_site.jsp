<form method="post" action="AjoutSite" class="blocFormulaire">
	<div>
		<div class="titreFormulaire">
			AJOUT D'UN SITE
		</div>
		<div class="formulaire">
   	 		<p class="${empty traitementFormulaireAjoutSite.erreursAjoutSite ? 'succesAjoutSite' : 'erreurAjoutSite'}">${traitementFormulaireAjoutSite.resultatAjoutSite}</p>
	
	    	<span class="erreurs">${traitementFormulaireAjoutSite.erreursAjoutSite['nomSite']}</span>
	        <input type="text" name="nomSite" id="nomSite" class="inputBase" value="<c:out value="${nouveauSite.nomSite}"/>" placeholder="Nom du site" maxlength="30 required" />
    	
       		<span class="erreurs">${traitementFormulaireAjoutSite.erreursAjoutSite['regionSite']}</span><br/>
    		<label for="regionSite" id="labelRegion" >Région :</label><br/>
       		<select name="regionSite" id="regionSite">
				<option value=""></option>
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
   
       		<textarea name="commentaireSite" id="commentaireSite" placeholder="Commentaire..."></textarea>
   
        	<input type="submit" id="submitAjoutSite" value="Valider" />
    	</div>
    </div>
</form>