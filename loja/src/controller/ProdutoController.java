package controller;

import DAO.ProdutoDAO;
import model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void adicionarProduto(Produto produto) {
        produtoDAO.inserir(produto);
    }

    public void atualizarProduto(Produto produto) {
        produtoDAO.atualizar(produto);
    }

    public void removerProduto(int id) {
        produtoDAO.remover(id);
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoDAO.buscarTodos();
    }
}
