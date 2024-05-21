package controller;

import DAO.ItemDAO;
import DAO.PedidoDAO;
import model.Item;
import model.Pedido;

import java.util.List;

public class PedidoController {
    private PedidoDAO pedidoDAO;
    private ItemDAO itemDAO;

    public PedidoController(PedidoDAO pedidoDAO, ItemDAO itemDAO) {
        this.pedidoDAO = pedidoDAO;
        this.itemDAO = itemDAO;
    }

    public void realizarVenda(Pedido pedido, List<Item> itens) {
        // Iniciar transação
        pedidoDAO.inserir(pedido);

        for (Item item : itens) {
            item.setPedidoId(pedido.getId());
            itemDAO.inserir(item);
        }

        // Confirmar transação
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(id);
    }

    public List<Pedido> listarTodosPedidos() {
        return pedidoDAO.buscarTodos();
    }

    public List<Item> listarItensPorPedidoId(int pedidoId) {
        return itemDAO.buscarPorPedidoId(pedidoId);
    }
}