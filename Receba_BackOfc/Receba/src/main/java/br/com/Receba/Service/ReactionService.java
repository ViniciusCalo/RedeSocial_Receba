package br.com.Receba.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Receba.Model.Posts;
import br.com.Receba.Model.Reactions;
import br.com.Receba.Respository.ReactionsRepository;

@Service
public class ReactionService {

	@Autowired
	private ReactionsRepository reactionRepository;
	
	public Reactions save(Reactions reaction) {
		return reactionRepository.save(reaction);
	}
	
	public List<Reactions> findAll(){
        return this.reactionRepository.findAll();    
        }
	
	public List<Reactions> FindByReactions(Posts post){
        return this.reactionRepository.FindByReactions(post);    
        }
}
