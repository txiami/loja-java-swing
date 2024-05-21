package DAO;

import model.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements DAO<Item> {
    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Item (PedidoId, ProdutoId, Preco, Quantidade) VALUES (?, ?, ?, ?)");
            statement.setInt(1, item.getPedidoId());
            statement.setInt(2, item.getProdutoId());
            statement.setBigDecimal(3, item.getPreco());
            statement.setInt(4, item.getQuantidade());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Item item) {
        // Não é necessário implementar atualização para a tabela Item
    }

    @Override
    public void remover(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Item WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item buscarPorId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Item WHERE Id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int pedidoId = resultSet.getInt("PedidoId");
                int produtoId = resultSet.getInt("ProdutoId");
                BigDecimal preco = resultSet.getBigDecimal("Preco");
                int quantidade = resultSet.getInt("Quantidade");
                return new Item(id, pedidoId, produtoId, preco, quantidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Item> buscarPorPedidoId(int pedidoId) {
        List<Item> itens = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Item WHERE PedidoId = ?");
            statement.setInt(1, pedidoId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int produtoId = resultSet.getInt("ProdutoId");
                BigDecimal preco = resultSet.getBigDecimal("Preco");
                int quantidade = resultSet.getInt("Quantidade");
                itens.add(new Item(id, pedidoId, produtoId, preco, quantidade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }
}