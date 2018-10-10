/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument.Content;
import net.jxta.exception.PeerGroupException;
import net.jxta.id.ID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.share.CMS;
import net.jxta.share.ContentManager;
import net.jxta.share.SearchListener;



public class Rsharing extends Thread implements SearchListener {

    public JTextArea log = null;
    private  CMS cms =null;
      private PeerGroup netPeerGroup = null; 
      public File  myPath;
      private File repertoire = null;
    public Rsharing(JTextArea log , String nom) throws PeerGroupException, IOException{
       
       
    repertoire = new File(nom);
        this.log=log;
    launchCMS();
    
    }
    public Rsharing(String nom) throws PeerGroupException, IOException{
       
       
    repertoire = new File(nom);
       
    
    }
    @Override
    public void queryReceived(String query) {
       log.append(query);   }

    private void launchCMS() throws PeerGroupException, IOException {
    cms = new CMS();
    cms.init(this.netPeerGroup,null,null);
    ContentManager contentManager = null;
    contentManager = cms.getContentManager();
    File []list = repertoire.listFiles();
  
    for(int i=0;i<list.length;i++){
    if(list[i].isFile()){ GetMD5ForFile checkSum = new GetMD5ForFile(list[i]);
    contentManager.share(checkSum.comparemd5sum(list[i],checkSum.getMD5().toString()));
    }
    
    }
    Content [] content = (Content[]) cms.getContentManager().getContent();
    log.append("All Content are Successfully shared\n");
    }
     public String lister() {
    String liste = "";
    String[] table = repertoire.list();

    for (int i=0;i<table.length;i++) {
      //creation de la chaine a retourner
      liste += table[i]+";";
     
    
    }

    return liste;
  
   
        
       
    }

    public void stopCMS(){
    cms.stopApp();
    File temp = new File(repertoire.getAbsolutePath()+ File.separator +"shares.ser");
    if(temp.delete())
    {
    log.append("FILE" + repertoire.getAbsolutePath() + "successfully");
    }
    }
     public byte[] recupererFichier(String nom_fichier) {

    byte[] table = null;
    RandomAccessFile lire = null;  //flux de lecture sur le fichier

    try {
      lire = new RandomAccessFile("documents/"+nom_fichier, "r");
      int taille = (int) lire.length();
      table = new byte[taille];  //creation du tableau a la bonne taille
    } catch (IOException ioe) {System.err.println("Erreur dans ExplorationLocale.recupererFichier()_1"); ioe.printStackTrace();}

    try {
      lire.readFully(table);  //stockage du fichier sous forme de bytes dans le tableau
    } catch (IOException ioe) {System.err.println("Erreur dans ExplorationLocale.recupererFichier()_2"); ioe.printStackTrace();}

    return table;

  }



    
    
   
    
}
