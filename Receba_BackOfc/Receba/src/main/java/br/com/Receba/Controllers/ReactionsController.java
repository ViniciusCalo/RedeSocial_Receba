package br.com.Receba.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Receba.Model.Posts;
import br.com.Receba.Model.Reactions;
import br.com.Receba.Service.PostService;
import br.com.Receba.Service.ReactionService;
import br.com.Receba.Service.UserService;

@RestController
public class ReactionsController {
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/reaction")
	public ResponseEntity<Reactions> create(@RequestBody Map<String,String > reaction) {
		Reactions react = new Reactions();
		react.setPost(postService.findById(Integer.parseInt(reaction.get("post"))).get());
		react.setReacao(Integer.parseInt(reaction.get("reacao")));
		react.setUser(userService.findByEmail(reaction.get("email")).get());
		return ResponseEntity.status(HttpStatus.CREATED).body(reactionService.save(react));
	}
	
	@GetMapping("/reaction")
    public ResponseEntity<List<Reactions>> findAll(){
      List<Reactions> list = reactionService.findAll();     
      return ResponseEntity.ok(list);
  }
	
	@GetMapping("/reaction/{id}")
    public ResponseEntity<List<Reactions>> teste(@PathVariable(name="id") Integer id){
	  Posts p =  postService.findById(id).get();
      List<Reactions> list = reactionService.FindByReactions(p);     
      return ResponseEntity.ok(list);
  }

}
