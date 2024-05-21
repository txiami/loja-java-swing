package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TelaNovoCliente extends JDialog {
    private JTextField txtNome;
    private ClienteController clienteController;
    private TelaListagemClientes telaListagemClientes;

    public TelaNovoCliente(ClienteController clienteController, TelaListagemClientes telaListagemClientes) {
        super(telaListagemClientes, "Novo Cliente", true);
        this.clienteController = clienteController;
        this.telaListagemClientes = telaListagemClientes;

        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                Cliente cliente = new Cliente(0, nome, LocalDateTime.now());
                clienteController.adicionarCliente(cliente);
                telaListagemClientes.atualizarTabelaClientes();
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        add(panel);
    }
}

