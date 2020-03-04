package lade.beans;

public class BN_CommentaireInnerJoin {
	
	private Long idCommentaire;
	private Long idAuteurCommentaire;
	private Long idSiteCommentaire;
	private String commentaire;
	private BN_Utilisateur auteurCommentaire;
	private BN_Site siteCommentaire;

	public Long getIdCommentaire() {
		return idCommentaire;
	}
	
	public void setIdCommentaire(Long idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	
	public Long getIdAuteurCommentaire() {
		return idAuteurCommentaire;
	}

	public void setIdAuteurCommentaire(Long idAuteurCommentaire) {
		this.idAuteurCommentaire = idAuteurCommentaire;
	}

	public Long getIdSiteCommentaire() {
		return idSiteCommentaire;
	}

	public void setIdSiteCommentaire(Long idSiteCommentaire) {
		this.idSiteCommentaire = idSiteCommentaire;
	}

	public String getCommentaire() {
		return commentaire;
	}
	
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	public BN_Utilisateur getAuteurCommentaire() {
		return auteurCommentaire;
	}

	public void setAuteurCommentaire(BN_Utilisateur auteurCommentaire) {
		this.auteurCommentaire = auteurCommentaire;
	}

	public BN_Site getSiteCommentaire() {
		return siteCommentaire;
	}

	public void setSiteCommentaire(BN_Site siteCommentaire) {
		this.siteCommentaire = siteCommentaire;
	}
}
