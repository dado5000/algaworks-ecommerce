package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @OneToOne(optional = false) // Toda vez que salvar um registro no estoque o produto deverá estar presente
    @JoinColumn(name = "produto_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_estoque_produto"))
    private Produto produtoId;

    private Integer quantidade;
}
