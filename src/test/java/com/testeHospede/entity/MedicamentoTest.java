package com.testeHospede.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedicamentoTest {

	private Medicamento medicamento;
	
	@BeforeEach
	void setUp() {
		medicamento = new Medicamento(1L, "Dipirona", "Contraindicado em caso de suspeita de dengue", "10/2025");
	}
	
	@Test
	@DisplayName("Testando o getter e setter do id")
	void testId() {
		//Act
		medicamento.setId(2L);
		//Assert
		assertEquals(2L, medicamento.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do nome")
	void testNome() {
		//Act
		medicamento.setNome("Paracetamol");
		//Assert
		assertEquals("Paracetamol", medicamento.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter da bula")
	void testBula() {
		//Act
		medicamento.setBula("indicado para a redução da febre e para o alívio");
		//Assert
		assertEquals("indicado para a redução da febre e para o alívio", medicamento.getBula());
	}
	
	@Test
	@DisplayName("Testando o getter e setter da dataValidade")
	void testDataValid() {
		//Act
		medicamento.setDataValidade("09/2025");
		//Assert
		assertEquals("09/2025", medicamento.getDataValidade());
	}
	
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		//Act
		Medicamento novoMedicamento = new Medicamento(3L, "Buscopan", "indicado para alivio de dores abdominais", "07/2025");
		//Assert
		assertAll("novoMedicamento",
				()-> assertEquals(3L, novoMedicamento.getId()),
				()-> assertEquals("Buscopan", novoMedicamento.getNome()),
				()-> assertEquals("indicado para alivio de dores abdominais", novoMedicamento.getBula()),
				()-> assertEquals("07/2025", novoMedicamento.getDataValidade()));
	}
}
