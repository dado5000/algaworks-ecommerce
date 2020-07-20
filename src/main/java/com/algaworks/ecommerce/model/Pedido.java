package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

//    @Column(name = "cliente_id") sem relacionamento
//    private Integer clienteid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey =  @ForeignKey(name = "fk_pedido_cliente" ))
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido") // non-owner NÃO DONO da relação
    private NotaFiscal notaFiscal;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToMany(mappedBy = "pedidoid", fetch = FetchType.EAGER)
    private List<ItemPedido> itensPedido;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(statusPedido);
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void aoAtualiar() {
        dataUltimaAtualizacao = LocalDateTime.now();
    }

    //    @PrePersist
//    @PreUpdate
    public void calcularTotal() {
        if (itensPedido != null) {
            total = itensPedido.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
        calcularTotal();
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
        calcularTotal();
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}
