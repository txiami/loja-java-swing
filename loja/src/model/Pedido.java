package model;
import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private LocalDateTime dtCadastroPedido;
    private int clienteId;

    public Pedido(int id, LocalDateTime dtCadastroPedido, int clienteId) {
        this.id = id;
        this.dtCadastroPedido = dtCadastroPedido;
        this.clienteId = clienteId;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public LocalDateTime getDtCadastroPedido() {
        return dtCadastroPedido;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setId(int anInt) {
        id = anInt;
    }
}
