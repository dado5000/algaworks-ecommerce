package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class EagerELazyTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        System.out.println(pedido.getClienteid().getNome());

        for (int i = 0; i < pedido.getItensPedido().size(); i++ ) {
            System.out.println(
                    pedido.getItensPedido().get(i).getProdutoid().getNome() + " - " +
                    pedido.getItensPedido().get(i).getQuantidade() + " - " +
                    pedido.getItensPedido().get(i).getPrecoProduto() + " - " +
                    pedido.getTotal()
            );
        }

//        pedido.getItens().isEmpty();
    }
}
