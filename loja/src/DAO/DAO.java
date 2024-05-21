package DAO;

import java.util.List;

public interface DAO<T> {
    void inserir(T objeto);
    void atualizar(T objeto);
    void remover(int id);
    T buscarPorId(int id);
    List<T> buscarTodos();
}