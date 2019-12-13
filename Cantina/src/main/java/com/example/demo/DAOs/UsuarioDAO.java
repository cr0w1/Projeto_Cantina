package com.example.demo.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Usuario;



public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	public Usuario findByLoginAndSenha(String login, String senha);

}
