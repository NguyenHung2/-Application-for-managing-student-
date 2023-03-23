package data;

import java.io.File;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class dataMusicList {

    JFileChooser fileBild = new JFileChooser("C:\\Java-JSP\\duan9\\src\\music");
    ArrayList musicList = new ArrayList();

    public void add(JFrame frame) {
        fileBild.setMultiSelectionEnabled(true);
        int fileValid = fileBild.showOpenDialog(frame);
        if (fileValid == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        } else if (fileValid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File[] file = fileBild.getSelectedFiles();
            musicList.addAll(Arrays.asList(file));

        }
    }

    ArrayList getListSong() {
        return musicList;

    }
//save playlist
    FileOutputStream fos;
    ObjectOutputStream oos;

    public void saveAsPlaylist(JFrame frame) {
        fileBild.setMultiSelectionEnabled(true);
        int Valid = fileBild.showOpenDialog(frame);
        if (Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        } else if (Valid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File[] pls = fileBild.getSelectedFiles();
            try {

                fos = new FileOutputStream(pls + ".tgr");
                oos = new ObjectOutputStream(fos);

                for (int i = 0; i < musicList.size(); i++) {
                    File tmp = (File) musicList.get(i);
                    oos.writeObject(tmp);
                }
                oos.close();

            } catch (Exception e) {

            }

        }
    }

    FileInputStream fis;
    ObjectInputStream ois;

    public void openPls(JFrame frame) {
        fileBild.setMultiSelectionEnabled(false);
        int Valid = fileBild.showOpenDialog(frame);
        if (Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
            return;
        }
        if (Valid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File pls = fileBild.getSelectedFile();
            try {
                fis = new FileInputStream(pls);
                ois = new ObjectInputStream(fis);
                File tmp;
                while ((tmp = (File) ois.readObject()) != null) {
                    musicList.add(tmp);
                }
                if ((tmp = (File) ois.readObject()) == null) {
                    musicList.isEmpty();
                }
                ois.close();
            } catch (Exception e) {
            }

        }
    }

}
