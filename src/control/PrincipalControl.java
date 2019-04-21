package control;

import java.awt.Desktop;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import model.OrdemFrame;
import view.CadastroDeCliente;
import view.TelaPrincipal;
import view.TelaSobre;

public class PrincipalControl {

    List<Integer> listFrames;

    public PrincipalControl() {
        this.listFrames = new ArrayList<>();
        adicionarInternalFrames();
    }

    public void telaCadastroClienteAction() {
        verificarAbrir(CadastroDeCliente.ordemFrame);
    }

    public void telaSobreAction() {
        verificarAbrir(TelaSobre.ordemFrame);
    }

    private boolean verificarAbrir(int numero) {
        JInternalFrame tela = OrdemFrame.internalFrames.get(numero);
        for (Integer listFrame : listFrames) {
            if (listFrame == numero) {
                if (tela.isIcon()) {
                    System.out.println("Tá minimizado");
                    try {
                        tela.setIcon(false);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return false;
                } else {
                    System.out.println("Criando outra X2");
                    TelaPrincipal.dpPrincipal.add(tela);
                    tela.show();
                    return true;
                }
            }
        }
        System.out.println("Criando Outra");
        TelaPrincipal.dpPrincipal.add(tela);
        listFrames.add(numero);
        tela.show();
        return true;
    }

    private void adicionarInternalFrames() {
        OrdemFrame.adicionar(new CadastroDeCliente(), CadastroDeCliente.ordemFrame);
        OrdemFrame.adicionar(new TelaSobre(), TelaSobre.ordemFrame);
    }

    public void telaManualAction() {
      
    }

}
