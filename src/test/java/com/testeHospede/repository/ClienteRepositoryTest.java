package com.testeHospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testeHospede.entity.Cliente;

@DataJpaTest
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		Cliente cliente1 = new Cliente(null, "Harry", "15 998765432", "123.456.789-98", "1234.567.890-7");
		Cliente saveCliente = clienteRepository.save(cliente1);
		assertNotNull(saveCliente);
		assertTrue(saveCliente.getId() > 0);
	}
	
	@DisplayName("Testando o getAll")
	@Test
	void testGetAllRepository() {
		
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Harry", "15 998765432", "123.456.789-98", "1234.567.890-7");
		Cliente cliente2 = new Cliente(null, "Helena", "15 998765432", "123.456.789-98", "1234.567.890-7");
		
		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
		
		//When / Act
		List<Cliente> clienteList = clienteRepository.findAll();
		
		//Then / Assert
		assertNotNull(clienteList);
		assertEquals(2, clienteList.size());
	}
	
	@DisplayName("Testando o getById")
	@Test
	void testGetByIdRepository() {
		
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Harry", "15 998765432", "123.456.789-98", "1234.567.890-7");
		
		clienteRepository.save(cliente1);
		
		//When / Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get();
		
		//Then / Assert
		assertNotNull(saveCliente);
		assertEquals(cliente1.getId(), saveCliente.getId());
	}
	
	@DisplayName("Testando o update")
	@Test
	void testUpdateRepository() {
		
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Harry", "15 998765432", "123.456.789-98", "1234.567.890-7");
		
		clienteRepository.save(cliente1);
		
		//When / Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get();
		cliente1.setNome("Helena");
		cliente1.setCpf("987.654.321-10");
		
		Cliente updateCliente = clienteRepository.save(saveCliente);
		
		//Then / Assert
		assertNotNull(updateCliente);
		assertEquals("Helena", updateCliente.getNome());
		assertEquals("987.654.321-10", updateCliente.getCpf());		
	}
	
	@DisplayName("Testando o delete")
	@Test
	void testDeleteRepository() {
		
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Harry", "15 998765432", "123.456.789-98", "1234.567.890-7");
		
		clienteRepository.save(cliente1);
		
		//When / Act
		clienteRepository.deleteById(cliente1.getId());
		Optional<Cliente> clienteOptional = clienteRepository.findById(cliente1.getId());
		
		//Then / Assert
		assertTrue(clienteOptional.isEmpty());
	}
}
