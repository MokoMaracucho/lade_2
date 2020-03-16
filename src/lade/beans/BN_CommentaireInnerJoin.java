package lade.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_commentaire")
public class BN_CommentaireInnerJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_commentaire")
	private Long idCommentaire;
	@Column(name="id_auteur_commentaire")
	private Long idAuteurCommentaire;
	@Column(name="id_site_commentaire")
	private Long idSiteCommentaire;
	@Column(name="commentaire")
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
