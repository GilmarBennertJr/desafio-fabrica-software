package io.github.gilmarbennertjr.desafio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="descricao_produto", nullable = false, length = 50)
    @NotEmpty(message = "{campo.descricaoproduto.obrigatorio}")
    private String descricaoProduto;

    @Column(name="valor_produto", nullable = false)
    @DecimalMin(value="0.0", message = "{campo.valorproduto.invalido}")
    private BigDecimal valorProduto;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.solicitante.obrigatorio}")
    private String solicitante;

    @Column(nullable = false)
    private String status;

    @Column
    private String observacao;

    @PrePersist
    public void prePersist() {
        setStatus("Pendente");
    }

}
