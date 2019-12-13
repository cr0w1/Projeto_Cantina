package com.example.demo.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Integer> {
}
