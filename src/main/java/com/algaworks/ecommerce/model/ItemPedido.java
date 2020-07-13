package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedidoid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produtoid;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;


//    @Column(name = "produto_id")
//    private Integer produtoId; SEM RELACIONAMENTO
//    @Column(name = "pedido_id")
//    private Integer pedidoId; SEM RELACIONAMENTO
}
