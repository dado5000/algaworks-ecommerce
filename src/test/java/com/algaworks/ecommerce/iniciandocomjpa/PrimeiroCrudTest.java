package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirCliente() {
        Cliente cliente = new Cliente();

        // cliente.setId(3); Não é necessário na asserção para operação de remoção.
        cliente.setNome("Maria das Graças Leitão");
        cliente.setCpf("99999999999999");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.persist(cliente);
        getTransacao();

        entityManager.clear();

        Cliente clienteValida = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteValida);
    }

    @Test
    public void buscarCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Cliente clienteEncontrado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Thiago Leite Choma", clienteEncontrado.getNome());
    }

    @Test
    public void atualizaCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        String novoNome = "Patricia Vedolim de Andrade";
        cliente.setNome(novoNome);
        cliente.setCpf("99999");
        cliente.setSexo(SexoCliente.FEMININO);

        getTransacao();

        entityManager.clear();

        Cliente clienteValida = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals(novoNome, clienteValida.getNome());
    }

    @Test
    public void impedirOperacaoBancoDeDados() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        /* detach desanexa o objeto da transação e impede que seja feito qualquer operação com o BANCO DE DADOS */
        entityManager.detach(cliente);

        String novoNome = "Thiago Leite";

        cliente.setNome(novoNome);
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setCpf("88888888888888");
        getTransacao();

        entityManager.clear();

        Cliente clienteValida = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Thiago Leite Choma", clienteValida.getNome());
    }

    @Test
    public void removerCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.remove(cliente);
        getTransacao();

        entityManager.clear();

        Cliente clienteValida = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(clienteValida);
    }

    public void getTransacao() {
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
    }


}
