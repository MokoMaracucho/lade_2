<form method="post" action="AjoutVoie" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">AJOUT D'UNE VOIE</h1>
	
	<div>
		<p class="${ empty traitementFormulaireAjoutVoie.erreursAjoutVoie ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireAjoutVoie.resultatAjoutVoie }</p>
	</div>
	
	<div class="form-group">
		<label for="nomSecteur" id="labelSecteur" class="form-label-color">Sélectionnez un secteur : </label><br />
		<select name="nomSecteur" id="secteur" class="custom-select mr-sm-2">
			<option value=""></option>
			<c:forEach  var="secteur" items="${ requestScope['listeSecteurs'] }">
				<option value="<c:out value='${ secteur.nomSecteur }'/>"><c:out value='${ secteur.nomSecteur }'/></option>
			</c:forEach>
		</select>
	</div>

  	<div class="form-group">
    	<label for="nomVoie" class="form-label-color">Nom de la voie</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireAjoutVoie.erreursAjoutVoie['nomVoie'] }</div>
    	<input id="nomVoie" name="nomVoie" type="text" class="form-control" aria-describedby="nomVoie" value="<c:out value="${ nouvelleVoie.nomVoie }"/>">
  	</div>
  	
  	<div class="form-group">
    	<input type="checkbox" name="equipeVoie" id="equipeVoie" /> <label for="equipeVoie" id="equipeVoie" class="form-label-color"> Voie équipée</label>
	</div>
	
	<div class="form-group">
		<p class="erreurs">${traitementFormulaireAjoutVoie.erreursAjoutVoie['cotationVoie']}</p>
     		<span id="cotationVoie" class="form-label-color">Cotation de la voie :</span><br />
		<div class="divRadio">
			<span>
				<input type="radio" name="cotationVoie" value="3" id="3" /><label for="3" class="small form-label-color ml-2"> 3</label>
			</span>
		</div>
		<div class="divRadio">
      		<span class="spanRadio">
      			<input type="radio" name="cotationVoie" value="4a" id="4a" /><label for="4a" class="small form-label-color ml-2"> 4a</label>
      		</span>
      		<span class="spanRadio">
	      		<input type="radio" name="cotationVoie" value="4b" id="4b" class="ml-4" /><label for="4b" class="small form-label-color ml-2"> 4b</label>
	      	</span>
	      	<span class="spanRadio">
      			<input type="radio" name="cotationVoie" value="4c" id="4c" class="ml-4" /><label for="4c" class="small form-label-color ml-2"> 4c</label>
      		</span>
      	</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="5a" id="5a" /><label for="5a" class="small form-label-color ml-2"> 5a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="5b" id="5b" class="ml-4" /><label for="5b" class="small form-label-color ml-2"> 5b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="5c" id="5c" class="ml-4" /><label for="5c" class="small form-label-color ml-2"> 5c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="6a" id="6a" /><label for="6a" class="small form-label-color ml-2"> 6a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="6b" id="6b" class="ml-4" /><label for="6b" class="small form-label-color ml-2"> 6b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="6c" id="6c" class="ml-4" /><label for="6c" class="small form-label-color ml-2"> 6c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="7a" id="7a" /><label for="7a" class="small form-label-color ml-2"> 7a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="7b" id="7b" class="ml-4" /><label for="7b" class="small form-label-color ml-2"> 7b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="7c" id="7c" class="ml-4" /><label for="7c" class="small form-label-color ml-2"> 7c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="8a" id="8a" /><label for="8a" class="small form-label-color ml-2"> 8a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="8b" id="8b" class="ml-4" /><label for="8b" class="small form-label-color ml-2"> 8b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="8c" id="8c" class="ml-4" /><label for="8c" class="small form-label-color ml-2"> 8c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="9a" id="8a" /><label for="8a" class="small form-label-color ml-2"> 9a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="9a+" id="9a+" class="ml-4" /><label for="9a_plus" class="small form-label-color ml-2"> 9a+</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="9b" id="9b" class="ml-3" /><label for="9b" class="small form-label-color ml-2"> 9b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="9b+" id="9b+" class="ml-4" /><label for="9b_plus" class="small form-label-color ml-2"> 9b+</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationVoie" value="9c" id="9c" class="ml-3" /><label for="9c" class="small form-label-color ml-2"> 9c</label>
      		</span>
		</div>
   	</div>
   
	<button type="submit" class="btn btn-success">Ajouter la voie</button>
</form>