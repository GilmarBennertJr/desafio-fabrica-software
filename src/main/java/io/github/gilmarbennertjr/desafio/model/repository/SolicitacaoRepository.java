package io.github.gilmarbennertjr.desafio.model.repository;

import io.github.gilmarbennertjr.desafio.model.entity.Solicitacao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    @Query(value = "select * from solicitacao s where s.status like %:status% " +
            "and solicitante like %:solicitante% and descricao_produto like %:descricaoProduto%", nativeQuery = true)
    <S extends Solicitacao> List<S> findSolicitacoesByFilter(
            @Param("status") String status,
            @Param("solicitante") String solicitante,
            @Param("descricaoProduto") String descricaoProduto,
            Pageable p
    );
}
