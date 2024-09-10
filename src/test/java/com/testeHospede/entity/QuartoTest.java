package com.testeHospede.entity; 

import static org.junit.jupiter.api.Assertions.assertAll; 
import static org.junit.jupiter.api.Assertions.assertEquals; 
 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.DisplayName; 
import org.junit.jupiter.api.Test; 
 
class QuartoTest { 
	 
	private Quarto quarto; 
	 
	@BeforeEach 
	void setUp() { 
		//Arrange 
		quarto = new Quarto(1L, "225", "Casal"); 
	} 
 
	@Test 
	@DisplayName("Testando o getter e setter do id") 
	void testId() { 
		//Act 
		quarto.setId(2L); 
		//Assert 
		assertEquals(2L, quarto.getId()); 
	} 
	 
	@Test 
	@DisplayName("Testando o getter e setter do num") 
	void testNum() { 
		//Act 
		quarto.setNum("226"); 
		//Assert 
		assertEquals("226", quarto.getNum()); 
	} 
	 
	@Test 
	@DisplayName("Testando o getter e setter do tipo") 
	void testTipo() { 
		//Act 
		quarto.setTipo("Suite Familia"); 
		//Assert 
		assertEquals("Suite Familia", quarto.getTipo()); 
	} 
	 
	@Test 
	@DisplayName("Testando o construtor com todos os argumentos") 
	void testConstrutorAll() { 
		//Act 
		Quarto novoQuarto = new Quarto(3L, "200", "Solteiro"); 
		//Assert 
		assertAll("novoQuarto", 
				()-> assertEquals(3L, novoQuarto.getId()), 
				()-> assertEquals("200", novoQuarto.getNum()), 
				()-> assertEquals("Solteiro", novoQuarto.getTipo())); 
	} 
	 
} 