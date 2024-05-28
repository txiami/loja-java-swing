package DAO;

import model.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements DAO<Produto> {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Produto produto) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Produto (Descricao, Preco) VALUES (?, ?)");
            statement.setString(1, produto.getDescricao());
            statement.setBigDecimal(2, produto.getPreco());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Produto produto) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Produto SET Descricao = ?, Preco = ? WHERE Id = ?");
            statement.setString(1, produto.getDescricao());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Produto WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerProdutoComItens(int produtoId) {
        try {
            connection.setAutoCommit(false); // Início da transação

            // Remover itens vinculados ao produto
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM item WHERE ProdutoId = ?");
            stmt.setInt(1, produtoId);
            stmt.executeUpdate();

            // Remover o produto
            remover(produtoId);

            connection.commit(); // Confirmar a transação
        } catch (SQLException e) {
            try {
                connection.rollback(); // Desfazer a transação em caso de erro
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Restaurar o modo de confirmação automática
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Produto buscarPorId(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Produto WHERE Id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String descricao = resultSet.getString("Descricao");
                BigDecimal preco = resultSet.getBigDecimal("Preco");
                return new Produto(id, descricao, preco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Produto");
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String descricao = resultSet.getString("Descricao");
                BigDecimal preco = resultSet.getBigDecimal("Preco");
                produtos.add(new Produto(id, descricao, preco));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}