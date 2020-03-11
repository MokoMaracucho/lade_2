<form method="post" action="AjoutLongueur" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">AJOUT D'UNE LONGUEUR</h1>
	
	<div>
		<p class="${ empty traitementFormulaireAjoutLongueur.erreursAjoutLongueur ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireAjoutLongueur.resultatAjoutLongueur }</p>
	</div>
	
	<div class="form-group">
		<label for="nomVoie" id="labelSecteur" class="form-label-color">Sélectionnez une voie : </label><br />
		<select name="nomVoie" id="voie" class="custom-select mr-sm-2">
			<option value=""></option>
			<c:forEach  var="voie" items="${ requestScope['listeVoies'] }">
				<option value="<c:out value='${ voie.nomVoie }'/>"><c:out value='${ voie.nomVoie }'/></option>
			</c:forEach>
		</select>
	</div>

  	<div class="form-group">
    	<label for="nomLongueur" class="form-label-color">Nom de la longueur</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireAjoutLongueur.erreursAjoutLongueur['nomLongeur'] }</div>
    	<input id="nomLongueur" name="nomLongueur" type="text" class="form-control" aria-describedby="nomLongueur" value="<c:out value="${ nouvelleLongueur.nomLongueur }"/>">
  	</div>
	
	<div class="form-group">
		<div class="small text-warning p-form-warning">${traitementFormulaireAjoutLongueur.erreursAjoutLongueur['cotationLongueur']}</div>
     	
     	<span id="cotationLongueur" class="form-label-color">Cotation de la longueur :</span><br />
		
		<div class="divRadio">
			<span>
				<input type="radio" name="cotationLongueur" value="3" id="3" /><label for="3" class="small form-label-color ml-2"> 3</label>
			</span>
		</div>
		
		<div class="divRadio">
      		<span class="spanRadio">
      			<input type="radio" name="cotationLongueur" value="4a" id="4a" /><label for="4a" class="small form-label-color ml-2"> 4a</label>
      		</span>
      		<span class="spanRadio">
	      		<input type="radio" name="cotationLongueur" value="4b" id="4b" class="ml-4" /><label for="4b" class="small form-label-color ml-2"> 4b</label>
	      	</span>
	      	<span class="spanRadio">
      			<input type="radio" name="cotationLongueur" value="4c" id="4c" class="ml-4" /><label for="4c" class="small form-label-color ml-2"> 4c</label>
      		</span>
      	</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="5a" id="5a" /><label for="5a" class="small form-label-color ml-2"> 5a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="5b" id="5b" class="ml-4" /><label for="5b" class="small form-label-color ml-2"> 5b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="5c" id="5c" class="ml-4" /><label for="5c" class="small form-label-color ml-2"> 5c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="6a" id="6a" /><label for="6a" class="small form-label-color ml-2"> 6a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="6b" id="6b" class="ml-4" /><label for="6b" class="small form-label-color ml-2"> 6b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="6c" id="6c" class="ml-4" /><label for="6c" class="small form-label-color ml-2"> 6c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="7a" id="7a" /><label for="7a" class="small form-label-color ml-2"> 7a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="7b" id="7b" class="ml-4" /><label for="7b" class="small form-label-color ml-2"> 7b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="7c" id="7c" class="ml-4" /><label for="7c" class="small form-label-color ml-2"> 7c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="8a" id="8a" /><label for="8a" class="small form-label-color ml-2"> 8a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="8b" id="8b" class="ml-4" /><label for="8b" class="small form-label-color ml-2"> 8b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="8c" id="8c" class="ml-4" /><label for="8c" class="small form-label-color ml-2"> 8c</label>
      		</span>
		</div>

		<div class="divRadio">
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="9a" id="8a" /><label for="8a" class="small form-label-color ml-2"> 9a</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="9a+" id="9a+" class="ml-4" /><label for="9a_plus" class="small form-label-color ml-2"> 9a+</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="9b" id="9b" class="ml-3" /><label for="9b" class="small form-label-color ml-2"> 9b</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="9b+" id="9b+" class="ml-4" /><label for="9b_plus" class="small form-label-color ml-2"> 9b+</label>
      		</span>
      		<span class="spanRadio">
				<input type="radio" name="cotationLongueur" value="9c" id="9c" class="ml-3" /><label for="9c" class="small form-label-color ml-2"> 9c</label>
      		</span>
		</div>
   	</div>
   
	<button type="submit" class="btn btn-success">Ajouter la longueur</button>
</form>