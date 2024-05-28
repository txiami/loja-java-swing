

package view;

import DAO.ClienteDAO;
import DAO.ItemDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.DatabaseConnection;
import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaPrincipal extends JFrame {
    private ClienteController clienteController;
    private ProdutoController produtoController;
    private PedidoController pedidoController;

    public TelaPrincipal(ClienteController clienteController, ProdutoController produtoController, PedidoController pedidoController) {
        super("Gerenciador de Loja");
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.pedidoController = pedidoController;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnClientes = new JButton("CLIENTES");
        JButton btnProdutos = new JButton("LISTAGEM DE PRODUTOS");
        JButton btnVendas = new JButton("VENDAS");

        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaListagemClientes telaListagemClientes = new TelaListagemClientes(clienteController);
                telaListagemClientes.setVisible(true);
            }
        });

        btnProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaListagemProdutos telaListagemProdutos = new TelaListagemProdutos(produtoController);
                telaListagemProdutos.setVisible(true);
            }
        });

        btnVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVendas telaVendas = new TelaVendas(clienteController, produtoController, pedidoController);
                telaVendas.setVisible(true);
            }
        });

        panel.add(btnClientes);
        panel.add(btnProdutos);
        panel.add(btnVendas);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DatabaseConnection databaseConnection;
                    Connection connection = DatabaseConnection.getConnection();
                    ClienteDAO clienteDAO = new ClienteDAO(connection);
                    ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                    PedidoDAO pedidoDAO = new PedidoDAO(connection);
                    ItemDAO itemDAO = new ItemDAO(connection);

                    ClienteController clienteController = new ClienteController(clienteDAO);
                    ProdutoController produtoController = new ProdutoController(produtoDAO);
                    PedidoController pedidoController = new PedidoController(pedidoDAO, itemDAO);

                    TelaPrincipal telaPrincipal = new TelaPrincipal(clienteController, produtoController, pedidoController);
                    telaPrincipal.setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}