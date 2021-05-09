package io.github.gilmarbennertjr.desafio.rest;
import io.github.gilmarbennertjr.desafio.model.entity.Solicitacao;
import io.github.gilmarbennertjr.desafio.model.repository.SolicitacaoRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class SolicitacaoControllerTest {

    @Autowired
    private SolicitacaoController solicitacaoController;

    @MockBean
    private SolicitacaoRepository solicitacaoRepository;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.solicitacaoController);
    }

    @Autowired
    private MockMvc mvc;

    /**
     * Busca de solicitação enviando dados de paginação
     * RETORNO: STATUS CODE 200
     */
    @Test
    public void sucessoBuscaDeSolicitacoes() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get("/api/solicitacoes?page=0&&size=10")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    /**
     * Busca de solicitação sem enviar dados de paginação
     * RETORNO: STATUS CODE 400
     */
    @Test
    public void erroBuscaDeSolicitacoesSemPaginacao() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/solicitacoes")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Busca de solicitação enviando código no path
     * RETORNO: STATUS CODE 200
     */
    @Test
    public void sucessoAoBuscarSolicitacao() {
        Mockito.when(solicitacaoRepository.findById(1))
                .thenReturn(Optional.of(new Solicitacao()));

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/solicitacoes/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    /**
     * Insert de solicitação
     * RETORNO: STATUS CODE 201
     */
    @Test
    public void successoInsertSolicitacao() {
        Solicitacao s = new Solicitacao();
        s.setSolicitante("Solicitante Teste");
        s.setDescricaoProduto("Produto Teste");
        s.setValorProduto(new BigDecimal(1));

        Mockito.when(solicitacaoRepository.save(s)).thenReturn(s);

        given()
                .contentType("application/json")
                .body(s)
                .when()
                .post("/api/solicitacoes")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    /**
     * Erro de insert de solicitação. falta de solicitante
     * RETORNO: Status code 400
     */
    @Test
    public void erroInsertSolicitacao() {
        Solicitacao s = new Solicitacao();
        s.setDescricaoProduto("Produto Teste");
        s.setValorProduto(new BigDecimal(1));

        Mockito.when(solicitacaoRepository.save(s)).thenReturn(s);

        given()
                .contentType("application/json")
                .body(s)
                .when()
                .post("/api/solicitacoes")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Erro de insert de solicitação. Valor de produto menor que zero
     * RETORNO: Status code 400
     */
    @Test
    public void erroInsertSolicitacaoValorMenorQueZero() {
        Solicitacao s = new Solicitacao();
        s.setSolicitante("Solicitante Teste");
        s.setDescricaoProduto("Produto Teste");
        s.setValorProduto(new BigDecimal(-1));

        Mockito.when(solicitacaoRepository.save(s)).thenReturn(s);

        given()
                .contentType("application/json")
                .body(s)
                .when()
                .post("/api/solicitacoes")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


}
