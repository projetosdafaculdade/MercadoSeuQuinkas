package control;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import view.CadastroDeCliente;
import view.TelaPrincipal;
import view.TelaSobre;

public class PrincipalControl {

    private CadastroDeCliente telaCadastroCliente = null;
    private TelaSobre telaSobre = null;

    public void telaCadastroClienteAction() {
        verificarExistencia(telaCadastroCliente);
    }

    public void telaSobreAction() {
        verificarExistenciaSobre(telaSobre);
    }

    private void verificarExistencia(JInternalFrame frame) {
        if (frame != null) {
            if (frame.isIcon() == true) {
                try {
                    frame.setIcon(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                frame = new CadastroDeCliente();
                TelaPrincipal.dpPrincipal.add(frame);
                frame.show();

            }
        } else {
            frame = new CadastroDeCliente();
            TelaPrincipal.dpPrincipal.add(frame);
            frame.show();
        }
    }

    private void verificarExistenciaSobre(JInternalFrame frame) {
        if (frame != null) {
            if (frame.isIcon() == true) {
                try {
                    frame.setIcon(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(PrincipalControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                frame = new TelaSobre();
                TelaPrincipal.dpPrincipal.add(frame);
                frame.show();

            }
        } else {
            frame = new TelaSobre();
            TelaPrincipal.dpPrincipal.add(frame);
            frame.show();
        }
    }

}
