package com.testeHospede.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.testeHospede.entity.Hospede;
import com.testeHospede.repository.HospedeRepository;

import jakarta.transaction.Transactional;

@SpringBootTest //Carrega o contexto completo spring
@Transactional //Garante que as operações no banco de dados serao revertidas apos cada teste
class HospedeServiceTest {
	
	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll(); //Limpa o banco de dados antes de cada teste
	}
	
	@DisplayName("Testando salvar hospede")
	@Test
	void testSalvarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 98765432");
		
		Hospede resultado = hospedeService.salvarHospede(hospede);
		
		assertNotNull(resultado);
		assertEquals("Julia Maria", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}
	
	@DisplayName("Testando listar todos hospede")
	@Test
	void testListarTodos() {
		Hospede hospede1 = new Hospede (null, "Julia Maria", "julia@gmail.com", "(00) 98765432");
		Hospede hospede2 = new Hospede (null, "Julia Vitoria", "julia@gmail.com", "(00) 98765432");
		
		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);
		
		List<Hospede> resultado = hospedeService.buscarTodos();
		
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}
	
	@DisplayName("Testando buscar por id hospede")
	@Test
	void testBuscarPorId() {
		Hospede hospede1 = new Hospede (null, "Julia Maria", "julia@gmail.com", "(00) 98765432");
		
		Hospede salvo = hospedeService.salvarHospede(hospede1);
		Optional<Hospede> resultado = hospedeService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isPresent());
		assertEquals("Julia Maria", resultado.get().getNome());
	}
	
	@DisplayName("Testando o atualizar hospede")
	@Test
	void testAturalizarHospede() {
		Hospede hospede1 = new Hospede (null, "Julia Maria", "julia@gmail.com", "(00) 98765432");
		Hospede salvo = hospedeService.salvarHospede(hospede1);
		
		salvo.setNome("Leonardo");
		salvo.setEmail("leonardo@gmail.com");
		
		Hospede atualizado = hospedeService.atualizarHospede(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Leonardo", atualizado.getNome());
		assertEquals("leonardo@gmail.com", atualizado.getEmail());
	}
	
	@DisplayName("Testando o deletar hospede")
	@Test
	void testDeletarHospede() {
		Hospede hospede1 = new Hospede (null, "Julia Maria", "julia@gmail.com", "(00) 98765432");
		
		Hospede salvo = hospedeService.salvarHospede(hospede1);
		hospedeService.deletarHospede(salvo.getId());
		
		Optional<Hospede> resultado = hospedeService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isEmpty());
	}
}
