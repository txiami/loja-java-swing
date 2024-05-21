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

        panelBotoes.add(btnNovo);
        panelBotoes.add(btnAtualizar);
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

    private void atualizarTabelaProdutos() {
        tblProdutos.setModel(new DefaultTableModel(obterDadosProdutos(), new String[]{"ID", "Descrição", "Preço"}));
    }
}