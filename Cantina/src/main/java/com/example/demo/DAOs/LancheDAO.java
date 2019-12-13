package com.example.demo.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Lanche;



public interface LancheDAO extends JpaRepository<Lanche, Integer> {

}
