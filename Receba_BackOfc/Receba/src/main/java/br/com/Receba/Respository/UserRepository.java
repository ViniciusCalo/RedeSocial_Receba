package br.com.Receba.Respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.Receba.Model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findAll();
	
	@Query(value="select * from Usuarios where email = :email", nativeQuery=true)
	public User verificarEmail(String email);
	
	@Query(value="select * from Usuarios where email = :email and senha = :senha", nativeQuery=true)
	public User buscarLogin(String email, String senha);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Integer id);
}
