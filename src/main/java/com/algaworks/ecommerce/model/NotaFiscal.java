package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "pedido_id")
    private Integer id;

    //    @JoinTable(name = "pedido_nota_fiscal",
    //            joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
    //            inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true)) TAMBÉM FUNCIONA COM JoinTable

    @MapsId /* @MapsId determina que sua PK será a própria PK de seu relacionamento  */
    @OneToOne(optional = false) // Não é opcianal, sempre que gerar uma nota fiscal deverá haver um pedido
    @JoinColumn(name = "pedido_id") // DONO DA RELAÇÃO Owner
    private Pedido pedido;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;
}
