package model;

import java.time.LocalDateTime;

public class Cliente {
    private int id;
    private String nome;
    private LocalDateTime dtCadastroCliente;

    public Cliente(int id, String nome, LocalDateTime dtCadastroCliente) {
        this.id = id;
        this.nome = nome;
        this.dtCadastroCliente = dtCadastroCliente;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDtCadastroCliente() {
        return dtCadastroCliente;
    }

    @Override
    public String toString() {
        return nome;
    }
}
