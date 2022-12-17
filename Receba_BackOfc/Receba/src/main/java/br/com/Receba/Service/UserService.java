package br.com.Receba.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.Receba.Model.User;
import br.com.Receba.Respository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User userLogin(String email, String senha) {
		return userRepository.buscarLogin(email, senha);
	}
	
	public User verificarEmail(String email) {
		return userRepository.verificarEmail(email);
	}
	
	public Optional<User> findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public List<User> findAll(){
        return this.userRepository.findAll();    
        }
	
	public Optional<User> findById(Integer id) {
		return this.userRepository.findById(id);
	}
}
