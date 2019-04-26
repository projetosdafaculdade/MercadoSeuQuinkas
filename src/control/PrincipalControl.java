package control;

import static com.sun.java.accessibility.util.SwingEventMonitor.addInternalFrameListener;
import java.awt.Desktop;
import java.beans.PropertyVetoException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import model.frames.OrdemFrame;
import view.CadastroDeCliente;
import static view.CadastroDeCliente.ordemFrame;
import view.TelaAjuda;
import view.TelaPrincipal;
import view.TelaSobre;

public class PrincipalControl {

    List<Integer> listFrames;

    public PrincipalControl() {
        this.listFrames = new ArrayList<>();
        adicionarInternalFrames();
    }

    private void adicionarInternalFrames() {
        OrdemFrame.adicionar(new CadastroDeCliente(this), CadastroDeCliente.ordemFrame);
        OrdemFrame.adicionar(new TelaSobre(this), TelaSobre.ordemFrame);
        OrdemFrame.adicionar(new TelaAjuda(this), TelaAjuda.ordemFrame);
    }

    public void telaCadastroClienteAction() {
        try {
            verificarAbrir(CadastroDeCliente.ordemFrame);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void telaSobreAction() {
        try {
            verificarAbrir(TelaSobre.ordemFrame);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void telaAjudaAction() {
        try {
            verificarAbrir(TelaAjuda.ordemFrame);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean verificarAbrir(int numero) throws PropertyVetoException {
        JInternalFrame tela = OrdemFrame.internalFrames.get(numero);
        for (Integer listFrame : listFrames) {
            if (listFrame == numero) {
                if (tela.isIcon()) {
                    tela.setIcon(false);
                    return false;
                } else {
                    tela.setIcon(false);
                    return false;
                }
            }
        }
        TelaPrincipal.dpPrincipal.add(tela);
        listFrames.add(numero);
        tela.show();
        return true;
    }

    private boolean verificarFechar(int numero) {
        for (int i = 0; i < listFrames.size(); i++) {
            if (numero == listFrames.get(i)) {
                listFrames.remove(numero);
            }
        }

        return false;
    }

    public void abrirManualAction() {
        openWebpage("C:/Users/johnatan.souza/Desktop/MercadoSeuQuinkas/src/manual/Manual.pdf");
    }

    private void openWebpage(String url) {
        URI uri = URI.create(url);
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fecharAction(JInternalFrame aThis, int ordemFrame) {
        verificarFechar(ordemFrame);
    }

    public void fecharAction(JInternalFrame aThis) {
        aThis.dispose();
    }

    public void acessarLink(String url) {
        openWebpage(url);
    }

 
}
