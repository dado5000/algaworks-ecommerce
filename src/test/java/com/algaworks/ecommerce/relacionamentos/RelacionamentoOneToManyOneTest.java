package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoOneToManyOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);

        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse(clienteVerificacao.getPedidos().isEmpty());
    }

    @Test
    public void verificarRelacionamentoPedido() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedidoid(pedido);
        itemPedido.setProdutoid(produto);

        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();


        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertFalse(pedidoVerificacao.getItensPedido().isEmpty());

    }

}
