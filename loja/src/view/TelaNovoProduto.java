package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class TelaNovoProduto extends JDialog {
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private ProdutoController produtoController;
    private TelaListagemProdutos telaListagemProdutos;

    public TelaNovoProduto(ProdutoController produtoController, TelaListagemProdutos telaListagemProdutos) {
        super(telaListagemProdutos, "Novo Produto", true);
        this.produtoController = produtoController;
        this.telaListagemProdutos = telaListagemProdutos;

        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel lblDescricao = new JLabel("Descrição:");
        JLabel lblPreco = new JLabel("Preço:");
        txtDescricao = new JTextField();
        txtPreco = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDescricao.getText();
                BigDecimal preco = new BigDecimal(txtPreco.getText());
                Produto produto = new Produto(0, descricao, preco);
                produtoController.adicionarProduto(produto);
                telaListagemProdutos.atualizarTabelaProdutos();
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(lblDescricao);
        panel.add(txtDescricao);
        panel.add(lblPreco);
        panel.add(txtPreco);
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        add(panel);
    }
}
