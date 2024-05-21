package model;

import java.math.BigDecimal;

public class Item {
    private int id;
    private int pedidoId;
    private int produtoId;
    private BigDecimal preco;
    private int quantidade;

    public Item(int id, int pedidoId, int produtoId, BigDecimal preco, int quantidade) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
}