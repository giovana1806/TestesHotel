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

import com.testeHospede.entity.Veiculo;

@DataJpaTest
class VeiculoRepositoryTest {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		Veiculo veiculo1 = new Veiculo(null, "Hyundai", "HB20", 2020, "Branco");
		Veiculo saveVeiculo = veiculoRepository.save(veiculo1);
		assertNotNull(saveVeiculo);
		assertTrue(saveVeiculo.getId() > 0);
	}
	
	@DisplayName("Testando o getAll")
	@Test
	void testGetAllRepository() {
		
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Hyundai", "HB20", 2020, "Branco");
		Veiculo veiculo2 = new Veiculo(null, "Fiat", "Uno", 2020, "Branco");
		
		veiculoRepository.save(veiculo1);
		veiculoRepository.save(veiculo2);
		
		//When / Act
		List<Veiculo> veiculoList = veiculoRepository.findAll();
		
		//Then / Assert
		assertNotNull(veiculoList);
		assertEquals(2, veiculoList.size());
	}
	
	@DisplayName("Testando o getById")
	@Test
	void testGetByIdRepository() {
		
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Hyundai", "HB20", 2020, "Branco");
		
		veiculoRepository.save(veiculo1);
		
		//When / Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get();
		
		//Then / Assert
		assertNotNull(saveVeiculo);
		assertEquals(veiculo1.getId(), saveVeiculo.getId());
	}
	
	@DisplayName("Testando o update")
	@Test
	void testUpdateRepository() {
		
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Hyundai", "HB20", 2020, "Branco");
		
		veiculoRepository.save(veiculo1);
		
		//When / Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get();
		veiculo1.setMarca("Fiat");
		veiculo1.setModelo("Uno");
		
		Veiculo updateVeiculo = veiculoRepository.save(saveVeiculo);
		
		//Then / Assert
		assertNotNull(updateVeiculo);
		assertEquals("Fiat", updateVeiculo.getMarca());
		assertEquals("Uno", updateVeiculo.getModelo());		
	}
	
	@DisplayName("Testando o delete")
	@Test
	void testDeleteRepository() {
		
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Hyundai", "HB20", 2020, "Branco");
		
		veiculoRepository.save(veiculo1);
		
		//When / Act
		veiculoRepository.deleteById(veiculo1.getId());
		Optional<Veiculo> veiculoOptional = veiculoRepository.findById(veiculo1.getId());
		
		//Then / Assert
		assertTrue(veiculoOptional.isEmpty());
	}
}
