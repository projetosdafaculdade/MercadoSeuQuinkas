package model.dao;

import interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.Cliente;
import util.Conversor;

public class ClienteDao extends Dao implements DaoI<Cliente> {

    @Override
    public int cadastrar(Cliente obj) {
      String sql = "INSERT INTO CLIENTE(NOME,CEP, DATANASCIMENTO, IDCIDADE) VALUES (?,?,?,?)";
      try{
          PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
          stmt.setString(1, obj.getNome());
          stmt.setString(2, obj.getCep());
          stmt.setDate(3, Conversor.dataUtilParaSql(obj.getDataNascimento()));
          stmt.setInt(4, obj.getCidade().getId());
          ResultSet rs;
          if(stmt.executeUpdate() > 0){
              rs = stmt.getGeneratedKeys();
              rs.next();
              return rs.getInt(1);
          }
      }catch(SQLException ex){
          System.out.println("ERRO");
          return -1;
      }
        return 0;
    }

    @Override
    public boolean alterar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisarPor(String pesquisa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisarTodos() {
        return pesquisarTodos("CLIENTE.NOME", "ASC");
    }

    public List<Cliente> pesquisarTodos(String orderBy, String ordenacao) {
        String sql = "SELECT CLIENTE.IDCLIENTE, CLIENTE.NOME, CLIENTE.CEP, CLIENTE.DATANASCIMENTO, CIDADE.IDCIDADE,CIDADE.NOME, CIDADE.UF " +
"FROM CLIENTE " +
"INNER JOIN CIDADE ON " +
"CLIENTE.IDCIDADE = CIDADE.IDCIDADE " +
"WHERE ATIVO = 1 AND CIDADE.ATIVO = 1 " +
"ORDER BY " + orderBy + " " + ordenacao;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listCliente = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCep(rs.getString("CLIENTE.CEP"));
                c.setDataNascimento(rs.getDate("CLIENTE.DATANASCIMENTO"));
                c.setId(rs.getInt("CLIENTE.IDCLIENTE"));
                c.setNome(rs.getString("CLIENTE.NOME"));
                c.getCidade().setId(rs.getInt("CIDADE.IDCIDADE"));
                c.getCidade().setNome(rs.getString("CIDADE.NOME"));
                c.getCidade().setUf(rs.getString("CIDADE.UF"));
                listCliente.add(c);
            }
            return listCliente;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
