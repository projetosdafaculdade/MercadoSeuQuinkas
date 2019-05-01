package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.dao.CidadeDao;
import model.dao.ClienteDao;
import model.vo.Cidade;
import model.vo.Cliente;
import view.CadastrarCidade;
import view.ListarCidade;

public class CadastroDeClienteControl {

    private javax.swing.JButton btnBotao;
    private javax.swing.JComboBox<Cidade> jcbCidade;
    private javax.swing.JTextField jtfCep;
    private javax.swing.JTextField jtfDtNascimento;
    private javax.swing.JTextField jtfNome;

    List<Cidade> cidades = new ArrayList<>();
    CidadeDao cidadeDao = new CidadeDao();
    ClienteDao clienteDao = new ClienteDao();

    public CadastroDeClienteControl(JButton btnBotao, JComboBox<Cidade> jcbCidade, JTextField jtfCep, JTextField jtfDtNascimento, JTextField jtfNome) {
        this.btnBotao = btnBotao;
        this.jcbCidade = jcbCidade;
        this.jtfCep = jtfCep;
        this.jtfDtNascimento = jtfDtNascimento;
        this.jtfNome = jtfNome;
    }

    public void listarCidades() {
        jcbCidade.removeAllItems();
        for (Cidade cidade : cidadeDao.pesquisarTodos()) {
            jcbCidade.addItem(cidade);
        }
    }

    public void cadastrarCliente() {
        Cliente cliente = new Cliente(jtfNome.getText(), util.Conversor.dataUtilParaSql(new Date(jtfDtNascimento.getText())), jtfCep.getText(), (Cidade) jcbCidade.getSelectedItem());
        clienteDao.cadastrar(cliente);
    }

    public void listarCidade() {
        int idCidade = 0; // CRIAR CLASSE EM VEZ DE INT PARA RETORNAR VALOR
        ListarCidade listarCidade = new ListarCidade(null, true,idCidade);
        listarCidade.setVisible(true);
        listarCidades();
        System.out.println("ID:CIDADE"+idCidade);
        for (int i = 0; i < jcbCidade.getItemCount(); i++) {
            if (jcbCidade.getItemAt(i).getId() == idCidade) {
                jcbCidade.setSelectedIndex(i);
            }
        }

    }

}
