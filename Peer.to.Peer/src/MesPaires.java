
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.peergroup.PeerGroupID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class MesPaires extends Thread implements Runnable{

    static Object getGraphe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public  PeerGroup groupe = null;  //groupe de pairs d'appartenance
  private Client client = null;  //envoie des messages
  private Serveur serveur = null;  //reception des messages
  public Decouverte decouverte = null;  //recherche des pairs

    public Decouverte getDecouverte() {
        return decouverte;
    }
   public static  PeertoPeer graphe = null;  //interface graphique du pair
   private String monNom = null;  //nom du pair dans le groupe
   private FenetreSecondaire secondaire = null;  //fenetre d'affichage des documents
  private Vector documentsConnus = new Vector();  //liste des documents connus sur le reseau
 private ExplorationLocale exploration = null;
  //exploration des fichiers locaux


  /**
   * Constructeur de la classe MesPairs
   */
  public MesPaires() {

    try {
        
     groupe = PeerGroupFactory.newNetPeerGroup();  //cree un nouveau groupe general
    } catch (Exception e) {System.err.println("MesPairs() : Erreur de creation du groupe");}

    //instanciation des services de decouverte et de communications
   decouverte = new Decouverte(this,groupe);
   client = new Client("PropagatePipeAdv.xml",groupe);
    serveur = new Serveur(this,"PropagatePipeAdv.xml",groupe);

    //recuperation du nom du pair dans le groupe general
     monNom = groupe.getPeerName().toString();
     
  
    //instanciation de l'interface graphique
   graphe = new PeertoPeer(monNom,this);

    //creation du module d'exploration des documents locaux
   ExplorationLocale exploration = new ExplorationLocale(graphe.getBr());

    //creation de la fenetre secondaire pour les documents
   // secondaire = new FenetreSecondaire();
  }
  @SuppressWarnings("unchecked")
  public void aff(){
   
   
   graphe.affiche("Hello from JXTA group " + groupe.getPeerGroupName()+"\n");
    graphe.affiche("Group ID = " + groupe.getPeerGroupID().toString()+"\n");
     graphe.affiche("Peer name = " + groupe.getPeerName()+"\n");
      graphe.affiche("Peer ID = " + groupe.getPeerID().toString()+"\n");
    
  }
  
  
   


  /**
   * Methode de lancement de la classe MesPairs
   * lance le client, le serveur, la decouverte, l'interface graphique et la fenetre secondaire
   */
  public void run() {
      graphe.run();
    // decouverte.run();
    client.start();
    aff();
    
    serveur.start();
    
    
   // secondaire.run();
   
  }
  public void clier(){
  client.demanderDocument(groupe.getPeerID(), graphe.getjTextField1().toString());
  }
   public void traiterDemandeDocument(String sender_pid,String nom_doc) {
    String le_fichier = new String(exploration.recupererFichier(nom_doc));

    //recuperation du PeerID du destinataire
    PeerID dest_pid = createPeerID(groupe.getPeerGroupID(),sender_pid);

    //envoi du fichier au demandeur
    client.envoyerFichier(nom_doc,le_fichier,dest_pid);

  }

   public void affichedec(String dec){
   
   graphe.affich(dec);
   }
   public void rune(){
   travail();
   }
    public void ru(){
   finir();
   }


  /**
   * Oblige le pair à effectuer une recherche de pairs
   * dans le réseau toutes les 5 secondes
   */
  public void travail() {
    while (true) {
      decouverte.rechercher();  //recherche des pairs dans le reseau
      try {
           
          Thread .sleep(5000);   graphe.bontonactive();graphe.bontoactive();
          break;//endormissement du thread
   
      } catch (Exception e) {System.err.println("MesPairs.travail() : Impossible de dormir");}
    }
  }
   public  PeerID createPeerID(PeerGroupID pgID, String peerName) {
        String seed = peerName;
        return IDFactory.newPeerID(pgID, hash(seed.toLowerCase()));
    }
 private static byte[] hash(final String expression) {
        byte[] result;
        MessageDigest digest;

        if (expression == null) {
            throw new IllegalArgumentException("Invalid null expression");
        }

        try {
            digest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException failed) {
            failed.printStackTrace(System.err);
            RuntimeException failure = new IllegalStateException("Could not get SHA-1 Message");
            failure.initCause(failed);
            throw failure;
        }

        try {
            byte[] expressionBytes = expression.getBytes("UTF-8");
            result = digest.digest(expressionBytes);
        } catch (UnsupportedEncodingException impossible) {
            RuntimeException failure = new IllegalStateException("Could not encode expression as UTF8");

            failure.initCause(impossible);
            throw failure;
        }
        return result;
    }
 public void receptionDocument(String nom_doc,String contenu_doc) {

    //mise en forme puis affichage du document recu
    secondaire.afficher("   ...    "+nom_doc+"    ...\n\n"+contenu_doc);
    secondaire.montrer();
     System.out.println("document recue avec succé");

 }
 
  public void finir() {
    while (true) {
      //recherche des pairs dans le reseau
      try {
         
          decouverte.interrupt();
          break;//endormissement du thread
      } catch (Exception e) {System.err.println("MesPairs.travail() : Impossible de dormir");}
    }
     
    }

   public void sendMessage(String message) {
    //envoi d'un message a l'ensemble des pairs
    client.sendMessage(message);
  }
    public void afficheMessage(String message) {
    graphe.affichee(message);
  }
   }
       
