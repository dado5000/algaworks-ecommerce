package com.algaworks.ecommerce.mapementobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapemanetoObjetoEmbutido() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("00000-00");
        endereco.setLogradouro("Rua das Laranjeiras");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Uberlândia");
        endereco.setEstado("MG");

        Pedido pedido = new Pedido();
        // pedido.setId(1); NÃO NECESSÁRIO DEVIDO ESTRATEGIA IDENTITY
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));

        pedido.setEnderecoEntrega(endereco);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());


    }
}
