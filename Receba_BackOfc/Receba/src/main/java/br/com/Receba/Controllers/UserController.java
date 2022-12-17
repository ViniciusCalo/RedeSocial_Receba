package br.com.Receba.Controllers;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Receba.Model.User;
import br.com.Receba.Service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
		
    @PostMapping("/cadastro")
	public ResponseEntity<User> create(@RequestBody User user) {
    	User ema = userService.verificarEmail(user.getEmail());
    	
    	if(ema == null) {
    		user.setSenha(encoder.encode(user.getSenha()));
    		
    		return ResponseEntity.ok(userService.save(user));
    	}else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/cadastro")
    public ResponseEntity<List<User>> findAll(){
      List<User> list = userService.findAll();     
      return ResponseEntity.ok(list);
  }
	 
	@PostMapping("/login")
		public ResponseEntity<Boolean> search(@RequestBody Map<String, String> log) {
						
		
		 Optional<User> opcusuario = userService.findByEmail(log.get("email"));
		 if(opcusuario.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		 }
		
		   boolean valid = false;
		   
		   User usuario = opcusuario.get();
		   
		   valid = encoder.matches(log.get("senha"), usuario.getSenha());
		   
		   HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		   
		   return ResponseEntity.status(status).body(valid);
		   
	}
	
}
