package interfaces;

import java.util.List;

public interface DaoI<T> {

    public int cadastrar(T obj);

    public boolean alterar(T obj);

    public boolean deletar(T obj);

    public List<T> pesquisarPor(String pesquisa);

    public List<T> pesquisarTodos();
    
    public T buscarPorId(int id);
    
}
