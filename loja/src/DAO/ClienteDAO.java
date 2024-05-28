package DAO;

import model.Cliente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente> {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Cliente (Nome, dtCadastroCliente) VALUES (?, ?)");
            statement.setString(1, cliente.getNome());
            statement.setTimestamp(2, Timestamp.valueOf(cliente.getDtCadastroCliente()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Cliente SET Nome = ?, dtCadastroCliente = ? WHERE Id = ?");
            statement.setString(1, cliente.getNome());
            statement.setTimestamp(2, Timestamp.valueOf(cliente.getDtCadastroCliente()));
            statement.setInt(3, cliente.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void remover(int id) {
        try {
            // Excluir itens relacionados aos pedidos do cliente
            PreparedStatement statement = connection.prepareStatement("DELETE i FROM Item i JOIN Pedido p ON i.PedidoId = p.Id WHERE p.ClienteId = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            // Excluir pedidos do cliente
            statement = connection.prepareStatement("DELETE FROM Pedido WHERE ClienteId = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            // Excluir o cliente
            statement = connection.prepareStatement("DELETE FROM Cliente WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE Id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                LocalDateTime dtCadastroCliente = resultSet.getTimestamp("dtCadastroCliente").toLocalDateTime();
                return new Cliente(id, nome, dtCadastroCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cliente");
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String nome = resultSet.getString("Nome");
                LocalDateTime dtCadastroCliente = resultSet.getTimestamp("dtCadastroCliente").toLocalDateTime();
                clientes.add(new Cliente(id, nome, dtCadastroCliente));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}

