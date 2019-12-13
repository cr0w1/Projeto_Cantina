package com.example.demo.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Models.Aluno;



public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from aluno where matricula = ?1", nativeQuery = true)
	Aluno buscarAluno(String matricula);

}
