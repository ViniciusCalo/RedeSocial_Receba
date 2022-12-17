package br.com.Receba.Respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.Receba.Model.Posts;



public interface PostRepository extends CrudRepository<Posts, Integer> {

	List<Posts> findAll();
	
	Optional<Posts> findById(Integer id);
	
}
