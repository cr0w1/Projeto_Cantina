package com.example.demo.Controlles;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DAOs.AlunoDAO;
import com.example.demo.DAOs.LancheDAO;
import com.example.demo.DAOs.PedidoDAO;
import com.example.demo.Models.Pedido;
import com.example.demo.Services.ServiceAluno;
import com.example.demo.Services.ServicePedido;


@Controller
public class PedidoController {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private LancheDAO lancheDAO;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private ServicePedido servicePedido;
	
	@Autowired
	private ServiceAluno serviceAluno;
	
	@RequestMapping("/home")
	public String exibirPedido(Pedido pedido, Model model) {
		model.addAttribute("listaAlunos", this.alunoDAO.findAll());
		model.addAttribute("listaLanches", this.lancheDAO.findAll());
		model.addAttribute("listaPedidos", this.pedidoDAO.findAll());
		return "pedido";
	}
	
	@PostMapping("/registrarPedido")
	public String registrarPedido(@Valid Pedido pedido, BindingResult br, Model model ) {
		if (br.hasErrors()) {
			return exibirPedido( pedido,  model);
		}
		
		servicePedido.savePedido(pedido);

		return "redirect:/home";
	}

	@GetMapping("/pagar")
	public String registrarPagamento(Integer id, Model model) {
		Pedido pedido = this.pedidoDAO.getOne(id);
		pedido.setDataPagamento(new Date());
		
		
		
		serviceAluno.save(pedido.getAluno(), pedido);
		
		return "redirect:/home";
	}
}
