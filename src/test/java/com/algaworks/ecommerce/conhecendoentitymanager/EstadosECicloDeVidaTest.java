package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria(); // Estado NOVO
        categoriaNovo.setNome("Eletrônicos");

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);// Estado GERENCIADO a partir do retorno do MERGE

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);// GERENCIADO pelo retorno do find

        entityManager.remove(categoriaGerenciada); // Estado REMOVIDA
        entityManager.persist(categoriaGerenciada); // GERENCIADA

        entityManager.detach(categoriaGerenciada);// DESANEXADO
    }

}
