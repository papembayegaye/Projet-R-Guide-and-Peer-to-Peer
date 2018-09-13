import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hp
 */
public class MesDocuments extends javax.swing.JFrame{

  private String nom = null;  //nom du document
  private String proprietaire = null;  //identifiant du pair possedant le document


  /**
   * Constructeur de la classe MesDocuments
   */
  public MesDocuments(String n,String pid) {
    nom = n;
    proprietaire = pid;
  }


  /**
   * Retourne le nom du document
   */
  public String getNom() {
    return nom;
  }


  /**
   * Retourne l'identifiant du proprietaire du document
   */
  public String getProprietaire() {
    return proprietaire;
  }

    
 public static void main(String[] var0) {
    
   /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new PeertoPeer().setVisible(true);
            }
        });*/
     
        }
}