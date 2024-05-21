package controller;

import DAO.ClienteDAO;
import model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void adicionarCliente(Cliente cliente) {
        clienteDAO.inserir(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDAO.atualizar(cliente);
    }

    public void removerCliente(int id) {
        clienteDAO.remover(id);
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.buscarTodos();
    }
}
