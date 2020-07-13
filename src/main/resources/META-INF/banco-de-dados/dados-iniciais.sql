insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (id,nome) values (1, 'Thiago Leite Choma');
insert into cliente (id,nome) values (2, 'Patricia Vedolim');

insert into pedido (id, cliente_id, data_pedido, total, statusPedido) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');

insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);
insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 3, 15.0, 5);

insert into categoria (id, nome) values (1, 'Eletrônicos');