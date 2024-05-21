package DAO;

import model.Pedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements DAO<Pedido> {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Pedido pedido) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Pedido (dtCadastroPedido, ClienteId) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, Timestamp.valueOf(pedido.getDtCadastroPedido()));
            statement.setInt(2, pedido.getClienteId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                pedido.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        // Não é necessário implementar atualização para a tabela Pedido
    }

    public void atualizarStatus(int id, String status) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Pedido SET Status = ? WHERE Id = ?");
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Pedido WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPorId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Pedido WHERE Id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LocalDateTime dtCadastroPedido = resultSet.getTimestamp("dtCadastroPedido").toLocalDateTime();
                int clienteId = resultSet.getInt("ClienteId");
                return new Pedido(id, dtCadastroPedido, clienteId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Pedido> buscarTodos() {
        List<Pedido> pedidos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Pedido");

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                LocalDateTime dtCadastroPedido = resultSet.getTimestamp("dtCadastroPedido").toLocalDateTime();
                int clienteId = resultSet.getInt("ClienteId");
                pedidos.add(new Pedido(id, dtCadastroPedido, clienteId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
}
