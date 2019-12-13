package br.ifpe.web2.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifpe.web2.model.Pedido;
import br.ifpe.web2.persistence.AlunoDAO;
import br.ifpe.web2.persistence.LancheDAO;
import br.ifpe.web2.persistence.PedidoDAO;

@Controller
public class PedidoController {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Autowired
	private LancheDAO lancheDAO;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	@RequestMapping("/home")
	public String exibirPedido(Pedido pedido, Model model) {
		model.addAttribute("listaAlunos", this.alunoDAO.findAll());
		model.addAttribute("listaLanches", this.lancheDAO.findAll());
		model.addAttribute("listaPedidos", this.pedidoDAO.findAll());
		return "pedido";
	}
	
	@PostMapping("/registrarPedido")
	public String registrarPedido(@Valid Pedido pedido, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return exibirPedido( pedido,  model);
		}
		pedido.setDataPedido(new Date());
		this.pedidoDAO.save(pedido);
		return "redirect:/";
	}

	@GetMapping("/pagar")
	public String registrarPagamento(Integer id, Model model) {
		Pedido pedido = this.pedidoDAO.getOne(id);
		pedido.setDataPagamento(new Date());
		this.pedidoDAO.save(pedido);
		return "redirect:/";
	}
}
