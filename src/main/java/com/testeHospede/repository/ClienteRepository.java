package com.testeHospede.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeHospede.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
