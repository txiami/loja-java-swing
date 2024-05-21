package view;

import model.Item;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TelaVendas extends JFrame {
    private JComboBox<Cliente> cbClientes;
    private JComboBox<Produto> cbProdutos;
    private JTextField txtQuantidade;
    private JTable tblPedido;
    private List<Item> itensVenda;

    private ClienteController clienteController;
    private ProdutoController produtoController;
    private PedidoController pedidoController;

    public TelaVendas(ClienteController clienteController, ProdutoController produtoController, PedidoController pedidoController) {
        super("Vendas de Produtos");
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.pedidoController = pedidoController;
        this.itensVenda = new ArrayList<>();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Criar painel superior para seleção de cliente, produto e quantidade
        JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel lblCliente = new JLabel("Cliente:");
        cbClientes = new JComboBox<>(obterClientes());
        JLabel lblProduto = new JLabel("Produto:");
        cbProdutos = new JComboBox<>(obterProdutos());
        JLabel lblQuantidade = new JLabel("Quantidade:");
        txtQuantidade = new JTextField();
        JButton btnAdicionar = new JButton("Adicionar");

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = (Cliente) cbClientes.getSelectedItem();
                Produto produto = (Produto) cbProdutos.getSelectedItem();
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                Item item = new Item(0, 0, produto.getId(), produto.getPreco(), quantidade);
                itensVenda.add(item);
                atualizarTabelaPedido();
            }
        });

        panelSuperior.add(lblCliente);
        panelSuperior.add(cbClientes);
        panelSuperior.add(lblProduto);
        panelSuperior.add(cbProdutos);
        panelSuperior.add(lblQuantidade);
        panelSuperior.add(txtQuantidade);
        panelSuperior.add(btnAdicionar);
        panel.add(panelSuperior, BorderLayout.NORTH);

        // Criar tabela para listar itens do pedido
        String[] colunas = {"Produto", "Preço", "Quantidade", "Total"};
        Object[][] dados = obterDadosPedido();
        tblPedido = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tblPedido);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Criar painel inferior para botão de finalizar venda
        JPanel panelInferior = new JPanel(new FlowLayout());
        JButton btnFinalizarVenda = new JButton("Finalizar Venda");
        btnFinalizarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = (Cliente) cbClientes.getSelectedItem();
                Pedido pedido = new Pedido(0, LocalDateTime.now(), cliente.getId());
                pedidoController.realizarVenda(pedido, itensVenda);
                JOptionPane.showMessageDialog(TelaVendas.this, "Venda realizada com sucesso!");
                itensVenda.clear();
                atualizarTabelaPedido();
            }
        });
        panelInferior.add(btnFinalizarVenda);
        panel.add(panelInferior, BorderLayout.SOUTH);

        add(panel);
    }

    private Cliente[] obterClientes() {
        List<Cliente> clientes = clienteController.listarTodosClientes();
        return clientes.toArray(new Cliente[0]);
    }

    private Produto[] obterProdutos() {
        List<Produto> produtos = produtoController.listarTodosProdutos();
        return produtos.toArray(new Produto[0]);
    }

    private Object[][] obterDadosPedido() {
        Object[][] dados = new Object[itensVenda.size()][4];

        for (int i = 0; i < itensVenda.size(); i++) {
            Item item = itensVenda.get(i);
            Produto produto = produtoController.buscarProdutoPorId(item.getProdutoId());
            dados[i][0] = produto.getDescricao();
            dados[i][1] = produto.getPreco();
            dados[i][2] = item.getQuantidade();
            dados[i][3] = item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
        }

        return dados;
    }

    private void atualizarTabelaPedido() {
        tblPedido.setModel(new DefaultTableModel(obterDadosPedido(), new String[]{"Produto", "Preço", "Quantidade", "Total"}));
    }
}
