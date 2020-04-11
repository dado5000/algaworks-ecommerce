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

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "pedido_id")
//    private Integer pedidoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedidoid;

//    @Column(name = "produto_id")
//    private Integer produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produtoid;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;


}
