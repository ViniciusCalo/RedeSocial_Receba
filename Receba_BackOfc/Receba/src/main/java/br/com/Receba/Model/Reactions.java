package br.com.Receba.Model;

import java.sql.Time;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Reactions")
public class Reactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReaction;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Posts post;
	
	@CreationTimestamp
	private Time dtReaction;
	
	private int tpReacao;
	
	
    public Reactions() {
		
	}
	
	public Reactions(User user, Posts post, int reacao) {
		this.user = user;
		this.post = post;
		this.tpReacao = reacao;
	}

	
	public int getIdReaction() {
		return idReaction;
	}

	public void setIdReaction(int idReaction) {
		this.idReaction = idReaction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
	}

	public Time getDtReaction() {
		return dtReaction;
	}

	public void setDtReaction(Time dtReaction) {
		this.dtReaction = dtReaction;
	}

	public int getReacao() {
		return tpReacao;
	}

	public void setReacao(int tpreacao) {
		this.tpReacao = tpreacao;
	}

	

	
	
	
}
