package br.com.Receba.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Receba.Model.Posts;
import br.com.Receba.Service.PostService;
import br.com.Receba.Service.UserService;

@CrossOrigin("*")
@RestController
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
		
    @PostMapping("/post")
	public ResponseEntity<Posts> create(@RequestBody Map<String, String> post) {
    	Posts posts = new Posts();
    	posts.setPost(post.get("post"));
    	posts.setUser(userService.findByEmail(post.get("email")).get());
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(posts));
	}
    
    @GetMapping("/post")
    public ResponseEntity<List<Posts>> findAll(){
      List<Posts> list = postService.findAll();     
      return ResponseEntity.ok(list);
  }
}
