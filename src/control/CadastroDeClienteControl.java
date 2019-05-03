package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.dao.CidadeDao;
import model.dao.ClienteDao;
import model.vo.Cidade;
import model.vo.Cliente;
import util.Validar;
import view.CadastroDeCliente;
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
    CadastroDeCliente aThis;

    public CadastroDeClienteControl(JButton btnBotao, JComboBox<Cidade> jcbCidade, JTextField jtfCep, JTextField jtfDtNascimento, JTextField jtfNome, CadastroDeCliente aThis) {
        this.btnBotao = btnBotao;
        this.jcbCidade = jcbCidade;
        this.jtfCep = jtfCep;
        this.jtfDtNascimento = jtfDtNascimento;
        this.jtfNome = jtfNome;
        this.aThis = aThis;
    }

    public void listarCidades() {
        jcbCidade.removeAllItems();
        for (Cidade cidade : cidadeDao.pesquisarTodos()) {
            jcbCidade.addItem(cidade);
        }
    }

    public void cadastrarCliente() {
        if (Validar.validarString(jtfNome.getText()) && Validar.validarCEP(jtfCep.getText())) {
            Cliente cliente = new Cliente(jtfNome.getText(), util.Conversor.dataUsuarioParaBanco((jtfDtNascimento.getText())), jtfCep.getText(), (Cidade) jcbCidade.getSelectedItem());
            if (clienteDao.cadastrar(cliente) > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                aThis.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Escreva todas as informações corretamente");
        }
    }

    public void listarCidade() {
        Cidade cidade = new Cidade();
        ListarCidade listarCidade = new ListarCidade(null, true, cidade);
        listarCidade.setVisible(true);
        listarCidades();
        for (int i = 0; i < jcbCidade.getItemCount(); i++) {
            if (jcbCidade.getItemAt(i).getId() == cidade.getId()) {
                jcbCidade.setSelectedIndex(i);
            }
        }

    }

}
