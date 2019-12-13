package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAOs.AlunoDAO;
import com.example.demo.DAOs.PedidoDAO;
import com.example.demo.Models.Aluno;
import com.example.demo.Models.Pedido;

@Service
public class ServiceAluno {

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	public void save(Aluno aluno, Pedido pedido) {
		Aluno aluno2 = pedido.getAluno();
		
		aluno2.setStatus(0);
		
		alunoDAO.save(aluno);
		pedidoDAO.save(pedido);
	}
}
