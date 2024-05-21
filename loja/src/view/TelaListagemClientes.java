package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class TelaListagemClientes extends JFrame {
    private JTable tblClientes;
    private ClienteController clienteController;

    public TelaListagemClientes(ClienteController clienteController) {
        super("Listagem de Clientes");
        this.clienteController = clienteController;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Criar tabela para listar clientes
        String[] colunas = {"ID", "Nome", "Data de Cadastro"};
        Object[][] dados = obterDadosClientes();
        tblClientes = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tblClientes);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Criar painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        JButton btnNovo = new JButton("Novo");
        JButton btnAtualizar = new JButton("Atualizar");

        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaNovoCliente telaNovoCliente = new TelaNovoCliente(clienteController, TelaListagemClientes.this);
                telaNovoCliente.setVisible(true);
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabelaClientes();
            }
        });

        panelBotoes.add(btnNovo);
        panelBotoes.add(btnAtualizar);
        panel.add(panelBotoes, BorderLayout.SOUTH);

        add(panel);
    }

    private Object[][] obterDadosClientes() {
        List<Cliente> clientes = clienteController.listarTodosClientes();
        Object[][] dados = new Object[clientes.size()][3];

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            dados[i][0] = cliente.getId();
            dados[i][1] = cliente.getNome();
            dados[i][2] = cliente.getDtCadastroCliente();
        }

        return dados;
    }

    void atualizarTabelaClientes() {
        tblClientes.setModel(new DefaultTableModel(obterDadosClientes(), new String[]{"ID", "Nome", "Data de Cadastro"}));
    }
}
