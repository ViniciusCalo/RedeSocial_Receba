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
@Table(name = "Posts")
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPost;
	
	@ManyToOne
	private User user;
	
	@CreationTimestamp
	private Time dtPost;
	
	private String post;
	
	public Posts() {
		
	}
	
	public Posts(User user, String post) {
		this.user = user;
		this.post = post;
		
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	public Time getDtPost() {
		return dtPost;
	}

	public void setDtPost(Time dtPost) {
		this.dtPost = dtPost;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
}
