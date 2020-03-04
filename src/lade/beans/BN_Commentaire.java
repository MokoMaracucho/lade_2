package lade.beans;

public class BN_Commentaire {
	
	private Long idCommentaire;
	private Long idAuteurCommentaire;
	private Long idSiteCommentaire;
	private String commentaire;
	
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
}
