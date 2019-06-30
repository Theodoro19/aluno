package com.aluno.api.aluno;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.aluno.api.aluno.controller.AlunoController;
import com.aluno.api.aluno.model.Aluno;
import com.aluno.api.aluno.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlunoController.class)
public class AlunoIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlunoService alunoService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void buscarAlunosTest() {

		Aluno aluno = new Aluno();

		aluno.setId(1L);
		aluno.setIdadeAluno(27);
		aluno.setNomeAluno("Raphael Theodoro");

		List<Aluno> listaAluno = new ArrayList<>();

		listaAluno.add(aluno);

		when(alunoService.buscarTodos()).thenReturn(listaAluno);

		try {
			mockMvc.perform(get("/aluno").contentType(APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nome", is(aluno.getNomeAluno())))
					.andExpect(jsonPath("$[0].idade", is(aluno.getIdadeAluno())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getAlunoByIdTest() throws Exception {

		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNomeAluno("Joseph");
		aluno.setIdadeAluno(27);

		when(alunoService.buscarPeloId(1L)).thenReturn(aluno);

		mvc.perform(get("/aluno/1").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is(aluno.getNomeAluno())))
				.andExpect(jsonPath("$.idade", is(aluno.getIdadeAluno())));
	}

	@Test
	public void saveValidAlunoTest() throws Exception {

		String json = "{\"nome\": \"testeNome\", \"idadeAluno\": 05}";

		mvc.perform(post("/aluno").contentType(APPLICATION_JSON).content(json)).andExpect(status().isCreated())
				.andReturn();
	}

	@Test
	public void saveInvalidAlunoWithoutNomeTest() throws Exception {
		String json = "{\"idadeAluno\": 05}";

		MvcResult mvcResult = mockMvc.perform(post("/aluno").contentType(APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertThat(mvcResult.getResolvedException().getLocalizedMessage()).contains("Informe o nome do aluno");
	}

	@Test
	public void saveInvalidAlunoWithInvalidIdadeTest() throws Exception {
		String json = "{\"nomeAluno\": \"testeNome\"}";

		MvcResult mvcResult = mockMvc.perform(post("/aluno").contentType(APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertThat(mvcResult.getResolvedException().getLocalizedMessage()).contains("Informe uma idade valida");
	}

	@Test
	public void updateAlunoTest() throws Exception {

		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNomeAluno("Raphael Theodoro");
		aluno.setIdadeAluno(27);

		when(alunoService.atualizarAluno(aluno, 1L)).thenReturn(aluno);

		mockMvc.perform(put("/aluno/1").contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(aluno)))
				.andExpect(status().isOk());
	}

	@Test
	public void updateInvalidAlunoWithoutNomeTest() throws Exception {
		String json = "{\"id\": 1, \"idadeAluno\": 05}";

		MvcResult mvcResult = mockMvc.perform(put("/aluno/1").contentType(APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertThat(mvcResult.getResolvedException().getLocalizedMessage()).contains("Informe o nome do aluno");
	}

	@Test
	public void updateInvalidAlunoWithInvalidIdadeTest() throws Exception {
		String json = "{\"id\": 1, \"nomeAluno\": \"testeNome\"}";

		MvcResult mvcResult = mockMvc.perform(put("/aluno/1").contentType(APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertThat(mvcResult.getResolvedException().getLocalizedMessage()).contains("Informe uma idade valida");
	}

	@Test
	public void deleteAlunoTest() throws Exception {

		mockMvc.perform(delete("/aluno/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}
}
