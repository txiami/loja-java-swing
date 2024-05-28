package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class TelaListagemProdutos extends JFrame {
    private JTable tblProdutos;
    private ProdutoController produtoController;

    public TelaListagemProdutos(ProdutoController produtoController) {
        super("Listagem de Produtos");
        this.produtoController = produtoController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Criar tabela para listar produtos
        String[] colunas = {"ID", "Descrição", "Preço"};
        Object[][] dados = obterDadosProdutos();
        tblProdutos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tblProdutos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Criar painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnNovo = new JButton("Novo");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JLabel lblPesquisar = new JLabel("Pesquisar por ID:");
        JTextField txtPesquisar = new JTextField(10);
        JButton btnPesquisar = new JButton("Pesquisar");

        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaNovoProduto telaNovoProduto = new TelaNovoProduto(produtoController, TelaListagemProdutos.this);
                telaNovoProduto.setVisible(true);
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabelaProdutos();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblProdutos.getSelectedRow();
                if (selectedRow >= 0) {
                    int produtoId = (int) tblProdutos.getValueAt(selectedRow, 0);
                    produtoController.removerProduto(produtoId);
                    atualizarTabelaProdutos();
                } else {
                    JOptionPane.showMessageDialog(TelaListagemProdutos.this, "Selecione um produto para excluir.");
                }
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int produtoId = Integer.parseInt(txtPesquisar.getText());
                    Produto produto = produtoController.buscarProdutoPorId(produtoId);
                    if (produto != null) {
                        Object[][] dados = {
                                {produto.getId(), produto.getDescricao(), produto.getPreco()}
                        };
                        tblProdutos.setModel(new DefaultTableModel(dados, new String[]{"ID", "Descrição", "Preço"}));
                    } else {
                        JOptionPane.showMessageDialog(TelaListagemProdutos.this, "Produto não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaListagemProdutos.this, "ID inválido.");
                }
            }
        });

        panelBotoes.add(btnNovo);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(lblPesquisar);
        panelBotoes.add(txtPesquisar);
        panelBotoes.add(btnPesquisar);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);
    }

    private Object[][] obterDadosProdutos() {
        List<Produto> produtos = produtoController.listarTodosProdutos();
        Object[][] dados = new Object[produtos.size()][3];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getId();
            dados[i][1] = produto.getDescricao();
            dados[i][2] = produto.getPreco();
        }

        return dados;
    }

    void atualizarTabelaProdutos() {
        tblProdutos.setModel(new DefaultTableModel(obterDadosProdutos(), new String[]{"ID", "Descrição", "Preço"}));
    }
}

