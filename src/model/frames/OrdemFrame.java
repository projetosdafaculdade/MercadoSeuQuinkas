package model.frames;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;

public class OrdemFrame {

    public static List<JInternalFrame> internalFrames = new ArrayList<>();
    public static List<Integer> idFrames = new ArrayList<>();

    public static void adicionar(JInternalFrame internalFrames, Integer idFrames) {
        OrdemFrame.internalFrames.add(internalFrames);
        OrdemFrame.idFrames.add(idFrames);
    }

}
