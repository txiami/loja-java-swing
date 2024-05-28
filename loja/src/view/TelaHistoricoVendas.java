package view;

import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;
import model.Cliente;
import model.Item;
import model.Pedido;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TelaHistoricoVendas extends JFrame {
    private JTable tblHistorico;
    private PedidoController pedidoController;
    private ClienteController clienteController;
    private ProdutoController produtoController;

    public TelaHistoricoVendas(ClienteController clienteController, ProdutoController produtoController, PedidoController pedidoController) {
        super("Histórico de Vendas");
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.pedidoController = pedidoController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] colunas = {"ID Pedido", "Cliente", "Produto", "Valor Unitário", "Quantidade", "Valor Total"};
        Object[][] dados = obterDadosHistorico();
        tblHistorico = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tblHistorico);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private Object[][] obterDadosHistorico() {
        List<Pedido> pedidos = pedidoController.listarTodosPedidos();
        Object[][] dados = new Object[pedidos.size()][];

        int index = 0;
        for (Pedido pedido : pedidos) {
            Cliente cliente = clienteController.buscarClientePorId(pedido.getClienteId());
            List<Item> itens = pedidoController.listarItensPorPedidoId(pedido.getId());

            for (Item item : itens) {
                Produto produto = produtoController.buscarProdutoPorId(item.getProdutoId());
                dados[index] = new Object[]{
                        pedido.getId(),
                        cliente.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        item.getQuantidade(),
                        item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()))
                };
                index++;
            }
        }

        return dados;
    }
}
