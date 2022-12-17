package br.com.Receba.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.Receba.Model.Posts;
import br.com.Receba.Model.Reactions;


public interface ReactionsRepository extends JpaRepository<Reactions, Integer> {

	@Query("select r from Reactions r where r.post = ?1")
	List<Reactions> FindByReactions(Posts post);
	
	
	List<Reactions> findAll();
}
