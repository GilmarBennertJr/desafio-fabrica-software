package io.github.gilmarbennertjr.desafio.rest;

import io.github.gilmarbennertjr.desafio.model.entity.Solicitacao;
import io.github.gilmarbennertjr.desafio.model.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin("*")
public class SolicitacaoController {

    private final SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitacao saveSolicitacao(@RequestBody @Valid Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    @GetMapping
    public List<Solicitacao> getAllSolicitacoes(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(defaultValue = "", required = false) String status,
            @RequestParam(defaultValue = "", required = false) String solicitante,
            @RequestParam(defaultValue = "", required = false) String descricaoProduto
    ) {
        Pageable p = PageRequest.of(page, size);
        return solicitacaoRepository.findSolicitacoesByFilter(status, solicitante, descricaoProduto, p);
    }

    @GetMapping("{id}")
    public Solicitacao getByIdSolicitacao(@PathVariable Integer id) {
        return solicitacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrado!"));
    }


}
