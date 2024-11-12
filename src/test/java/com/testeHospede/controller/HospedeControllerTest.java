package com.testeHospede.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.testeHospede.entity.Hospede;
import com.testeHospede.repository.HospedeRepository;
import com.testeHospede.service.HospedeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospedeControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private HospedeService hospedeService;
	
	@BeforeEach
	void SetUp() {
		hospedeRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Teste de criação de Hospede")
	void testCriarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 0000-0000");
		ResponseEntity<Hospede> response = restTemplate.postForEntity("/api/hospedes", hospede, Hospede.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Julia Maria", response.getBody().getNome());
	}
	
	@Test
	@DisplayName("Teste de listar de Hospede")
	void testListaTodosHospede() {
		Hospede hospede1 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 0000-0000");
		Hospede hospede2 = new Hospede(null, "Juliana", "juliana@gmail.com", "(00) 0000-0000");
		
		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);
		
		ResponseEntity<Hospede[]> response = restTemplate.getForEntity("/api/hospedes", Hospede[].class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode(), "A resposta deve ser 200 OK");
		assertNotNull(response.getBody(), "O corpo da resposta nao deve ser nulo");
		assertEquals(2, response.getBody().length, "A quantidade de hospedes deve ser 2");
	}
	
	@Test
	@DisplayName("Teste de busca por id de Hospede")
	void testBuscaHospedePorId() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 0000-0000");
		
		Hospede hospedeSalvo = hospedeRepository.save(hospede);
		
		ResponseEntity<Hospede> response = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(), Hospede.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Julia Maria", response.getBody().getNome());
	}
	
	@Test
	@DisplayName("Teste de atualização de Hospede")
	void testAtualizarHospede() {
		Hospede hospedeSalvo = hospedeRepository.save(new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 0000-0000"));
		
		Hospede hospedeAtualizado = new Hospede(hospedeSalvo.getId(), "Juliana", "juliana@gmail.com", "(00) 0000-0000");
		
		HttpEntity<Hospede> requestUpdate = new HttpEntity<>(hospedeAtualizado);
		ResponseEntity<Hospede> response = restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(),HttpMethod.PUT, requestUpdate, Hospede.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Juliana", response.getBody().getNome());
		assertEquals("juliana@gmail.com", response.getBody().getEmail());
	}
	
	@Test
	@DisplayName("Teste de deletar Hospede")
	void testDeletarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00) 0000-0000");
		Hospede hospedeSalvo = hospedeService.salvarHospede(hospede);
		
		ResponseEntity<Void> response = restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(), HttpMethod.DELETE, null, Void.class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "A resposta deve ser 204 No Content");
		
		ResponseEntity<Hospede> checkDeleted = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(), Hospede.class);
		
		assertEquals(HttpStatus.NOT_FOUND, checkDeleted.getStatusCode(), "Após o DELETE não deve encontrar o hospede");
	}
}
