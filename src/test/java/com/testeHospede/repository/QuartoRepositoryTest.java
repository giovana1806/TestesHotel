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

import com.testeHospede.entity.Quarto;

@DataJpaTest
class QuartoRepositoryTest {
	@Autowired
	private QuartoRepository quartoRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		Quarto quarto1 = new Quarto(null, "111", "Solteiro");
		
		Quarto saveQuarto = quartoRepository.save(quarto1);
		
		assertNotNull(saveQuarto);
		assertTrue(saveQuarto.getId()>0);
	}
	
	@DisplayName("Testando o getAll")
	@Test
	void testGetAllRepository() {
		Quarto quarto1 = new Quarto(null, "111", "Solteiro");
		Quarto quarto2 = new Quarto(null, "112", "Casal");
		
		quartoRepository.save(quarto1);
		quartoRepository.save(quarto2);
		
		List<Quarto> quartoList = quartoRepository.findAll();
		
		assertNotNull(quartoList);
		assertEquals(2, quartoList.size());
	}
	
	@DisplayName("Testando o getById")
	@Test
	void testGetByIdRepository() {
		Quarto quarto1 = new Quarto(null, "111", "Solteiro");
		
		quartoRepository.save(quarto1);
		
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();
		
		assertNotNull(saveQuarto);
		assertEquals(quarto1.getId(), saveQuarto.getId());
	}
	
	@DisplayName("Testando o update")
	@Test
	void testUpdateRepository() {
		Quarto quarto1 = new Quarto(null, "111", "Solteiro");
		
		quartoRepository.save(quarto1);
		
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();
		quarto1.setNum("115");
		quarto1.setTipo("Suite Master");
		
		Quarto updateQuarto = quartoRepository.save(saveQuarto);
		
		assertNotNull(updateQuarto);
		assertEquals("115", updateQuarto.getNum());
		assertEquals("Suite Master", updateQuarto.getTipo());
	}
	
	@DisplayName("Testando o delete")
	@Test
	void testDeleteRepository() {
		Quarto quarto1 = new Quarto(null, "111", "Solteiro");
		
		quartoRepository.save(quarto1);
		
		quartoRepository.deleteById(quarto1.getId());
		Optional<Quarto> quartoOptional = quartoRepository.findById(quarto1.getId());
		
		assertTrue(quartoOptional.isEmpty());
	}
}
