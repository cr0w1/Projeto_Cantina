package com.example.demo.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAOs.AlunoDAO;
import com.example.demo.DAOs.LancheDAO;
import com.example.demo.DAOs.PedidoDAO;
import com.example.demo.Models.Aluno;
import com.example.demo.Models.Lanche;
import com.example.demo.Models.Pedido;

@Service
public class ServicePedido {

	@Autowired
	private LancheDAO repositoryLan;
	
	@Autowired
	private PedidoDAO repositoryPed;
	
	@Autowired
	private AlunoDAO alunoDAO;
	
	
	public void savePedido(Pedido pedido) {
		pedido.setDataPedido(new Date());

		Aluno alunoChecado = alunoDAO.buscarAluno(pedido.getAluno().getMatricula());
	
		if(pedido.getQuantidade() <= pedido.getLanche().getQuantidadeDisponivel()) {

			Lanche lanche = pedido.getLanche();
			
			lanche.setQuantidadeDisponivel(lanche.getQuantidadeDisponivel() - pedido.getQuantidade());
			
			
			if(alunoChecado.getStatus() != 1) {
				repositoryPed.save(pedido);
				repositoryLan.save(lanche);
			
				Aluno aluno = pedido.getAluno();
			
				aluno.setStatus(1);
			
				alunoDAO.save(aluno);
				
			}

		}
		
		

	}
}
