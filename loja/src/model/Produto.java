package model;


import java.math.BigDecimal;

public class Produto {
    private int id;
    private String descricao;
    private BigDecimal preco;

    public Produto(int id, String descricao, BigDecimal preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
