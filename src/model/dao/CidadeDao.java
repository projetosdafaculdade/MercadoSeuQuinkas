package model.dao;


import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.vo.Cidade;

public class CidadeDao extends Dao implements DaoI<Cidade> {
    
  

    @Override
    public int cadastrar(Cidade obj) {
      String sql = "INSERT INTO CIDADE(NOME, UF) VALUES (?,?)";
      try{
          PreparedStatement stmt = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
          stmt.setString(1, obj.getNome());
          stmt.setString(2, obj.getUf());
          ResultSet rs;
          if(stmt.executeUpdate() > 0){
              rs = stmt.getGeneratedKeys();
              rs.next();
              return rs.getInt(1);
          }
      } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 0;
    }

    @Override
    public boolean alterar(Cidade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(Cidade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> pesquisarPor(String pesquisa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> pesquisarTodos() {
      return pesquisarTodos("nome", "asc");
    }

    
    public List<Cidade> pesquisarTodos(String orderby, String ordenacao) {
           String sql = "SELECT ID, NOME, UF FROM CIDADE WHERE ATIVO = 1 ORDER BY "+orderby+" "+ordenacao;
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
               ResultSet rs = stmt.executeQuery();
               List<Cidade> listCidade = new ArrayList<>();
               while(rs.next()){
                   Cidade c = new Cidade();
                   c.setId(rs.getInt("id"));
                   c.setNome(rs.getString("nome"));
                   c.setUf(rs.getString("uf"));
                   listCidade.add(c);
               }
               return listCidade;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    
    @Override
    public Cidade buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
