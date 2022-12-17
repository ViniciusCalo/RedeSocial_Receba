package br.com.Receba.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Receba.Model.Posts;
import br.com.Receba.Respository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Posts save(Posts posts) {
		return postRepository.save(posts);
	}
	
	public Optional<Posts> findById(Integer id) {
		return this.postRepository.findById(id);
	}

	public List<Posts> findAll(){
        return this.postRepository.findAll();    
        }
}
