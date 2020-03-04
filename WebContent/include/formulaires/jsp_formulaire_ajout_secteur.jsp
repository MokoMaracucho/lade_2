<form method="post" action="AjoutSecteur" class="form form-expand-lg form-dark bg-dark form-margin-padding form-border">
	<h1 class="font-weight-bold text-light">AJOUT D'UN SECTEUR</h1>
	
	<div>
		<p class="${ empty traitementFormulaireConnection.erreursConnection ? 'succesConnection text-success' : 'erreurConnection text-danger' } font-weight-bold">${ traitementFormulaireConnection.resultatConnection }</p>
	</div>
	
	<div class="form-group">
		<label for="nomSite" id="labelSite" class="form-label-color">Sélectionnez un site : </label><br />
		<select name="nomSite" id="site" class="custom-select mr-sm-2">
			<option value=""></option>
			<c:forEach  var="site" items="${ requestScope['listeSites'] }">
				<option value="<c:out value='${ site.nomSite }'/>"><c:out value='${ site.nomSite }'/></option>
			</c:forEach>
		</select>
	</div>

  	<div class="form-group">
    	<label for="nomSecteur" class="form-label-color">Nom du secteur</label>
		<div class="small text-warning p-form-warning">${ traitementFormulaireAjoutSecteur.erreursAjoutSecteur['nomSecteur'] }</div>
    	<input id="nomSecteur" name="nomSecteur" type="text" class="form-control" aria-describedby="nomSecteur" value="<c:out value="${ nouveauSecteur.nomSecteur }"/>">
  	</div>
   
	<button type="submit" class="btn btn-success">Ajouter le secteur</button>
</form>