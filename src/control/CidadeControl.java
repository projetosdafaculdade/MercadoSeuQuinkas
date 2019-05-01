package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.dao.CidadeDao;
import model.table.PaginaTable;
import model.vo.Cidade;
import view.CadastrarCidade;
import view.ListarCidade;

public class CidadeControl {

    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfUf;
    private CadastrarCidade aThisCadastrarCidade;
    private ListarCidade aThisListarCidade;
    private javax.swing.JTextField jtfPesquisar;
    private javax.swing.JButton jbtnPesquisar;
    private javax.swing.JTable jtblCidades;

    PaginaTable paginaTable;
    CidadeDao cidadeDao;

    public CidadeControl(JButton jButton1, JTextField jtfNome, JTextField jtfUf, CadastrarCidade aThis) {
        this.jButton1 = jButton1;
        this.jtfNome = jtfNome;
        this.jtfUf = jtfUf;
        this.aThisCadastrarCidade = aThis;
        cidadeDao = new CidadeDao();
    }

    public CidadeControl(JTextField jtfPesquisar, JButton jbtnPesquisar, JTable jtblCidades, ListarCidade aThis) {
        this.jtfPesquisar = jtfPesquisar;
        this.jbtnPesquisar = jbtnPesquisar;
        this.jtblCidades = jtblCidades;
        this.aThisListarCidade = aThis;
        cidadeDao = new CidadeDao();
        List<String> colunas = new ArrayList<>();
        List<String> tabela = new ArrayList<>();
        colunas.add("ID");
        colunas.add("Cidade");
        colunas.add("UF");
        paginaTable = new PaginaTable(null, 10, colunas);
        jtblCidades.setModel(paginaTable);
        listarCidade();
    }

    public void cadastrarCidadeControl() {
        Cidade c = new Cidade();
        c.setNome(jtfNome.getText());
        c.setUf(jtfUf.getText());
        if (cidadeDao.cadastrar(c) > 0) {
            aThisCadastrarCidade.dispose();
        };
    }

    public void listarCidade() {
        paginaTable.excluirTodasAsLinhas();
        for (Cidade c : cidadeDao.pesquisarTodos()) {
            List<String> linha = new ArrayList<>();
            linha.add(String.valueOf(c.getId()));
            linha.add(c.getNome());
            linha.add(c.getUf());
            paginaTable.novaLinha(linha);
        }

    }

    public void listarCidadePesquisando() {
        paginaTable.excluirTodasAsLinhas();
        for (Cidade c : cidadeDao.pesquisarPor(jtfPesquisar.getText())) {
            List<String> linha = new ArrayList<>();
            linha.add(String.valueOf(c.getId()));
            linha.add(c.getNome());
            linha.add(c.getUf());
            paginaTable.novaLinha(linha);
        }
    }

    public void novaCidade() {
        CadastrarCidade cadastrarCidade = new CadastrarCidade(null, true);
        cadastrarCidade.setVisible(true);
        listarCidadePesquisando();
    }

    public int selecionarCidade() {
        int idDaCidade = Integer.parseInt(String.valueOf(paginaTable.getValueAt(jtblCidades.getSelectedRow(), 0)));
        aThisListarCidade.dispose();
        return idDaCidade;
    }
}
