package model.dao;

import interfaces.DaoI;
import java.sql.Date;
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
      String sql = "INSERT INTO CLIENTE(NOME,CEP, DATANASCIMENTO, CIDADE_ID) VALUES (?,?,?,?)";
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
        return pesquisarTodos("nome", "ASC");
    }

    public List<Cliente> pesquisarTodos(String orderBy, String ordenacao) {
        String sql = "SELECT "
                + "CLI.ID, CLI.NOME, CLI.CEP, CLI.DATANASCIMENTO, CLI.CIDADE_ID,"
                + "CID.NOME, CID.UF"
                + "FROM CLIENTE CLIENTE AS CLI"
                + "INNER JOIN CIDADE CIDADE AS CID ON"
                + "CLI.CIDADE_ID = CID_ID"
                + "WHERE ATIVO = 1 AND CID.ATIVO = 1 "
                + "ORDER BY " + orderBy + " " + ordenacao;
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listCliente = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCep(rs.getString("cli.cep"));
                c.setDataNascimento(rs.getDate("cli.dataNascimento"));
                c.setId(rs.getInt("cli;id"));
                c.setNome(sql);
                c.getCidade().setId(rs.getInt("cid.id"));
                c.getCidade().setNome(rs.getString("cid.nome"));
                c.getCidade().setUf(rs.getString("cid.uf"));
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
