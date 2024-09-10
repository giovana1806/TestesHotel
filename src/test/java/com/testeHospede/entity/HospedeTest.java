package com.testeHospede.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HospedeTest {
	
	private Hospede hospede;
	
	@BeforeEach
	void setUp() {
		//Arrange
		hospede = new Hospede(1L, "Julia Silva", "julia@gmail.com", "(15)998765432");
	}

	@Test
	@DisplayName("Testando o getter e setter do id")
	void testId() {
		//Act
		hospede.setId(2L);
		//Assert
		assertEquals(2L, hospede.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do nome")
	void testNome() {
		//Act
		hospede.setNome("Joao");
		//Assert
		assertEquals("Joao", hospede.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do email")
	void testEmail() {
		//Act
		hospede.setEmail("maria@gmail.com");
		//Assert
		assertEquals("maria@gmail.com", hospede.getEmail());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do telefone")
	void testTelefone() {
		//Act
		hospede.setTelefone("(15)991243567");
		//Assert
		assertEquals("(15)991243567", hospede.getTelefone());
	}
	
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		//Act
		Hospede novoHospede = new Hospede(3L, "Yasmin", "yasmin@gmail.com", "(15)991247890");
		//Assert
		assertAll("novoHospede",
				()-> assertEquals(3L, novoHospede.getId()),
				()-> assertEquals("Yasmin", novoHospede.getNome()),
				()-> assertEquals("yasmin@gmail.com", novoHospede.getEmail()),
				()-> assertEquals("(15)991247890", novoHospede.getTelefone()));
	}
	
}
