/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hp
 */

import net.jxta.discovery.*;
import net.jxta.exception.*;
import net.jxta.peergroup.*;
import net.jxta.peer.*;
import java.util.Enumeration;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.protocol.DiscoveryResponseMsg;
import net.jxta.protocol.PeerAdvertisement;


/**
 * Classe permettant la decouverte des annonces (advertisements)
 * circulant sur le reseau
 */
public class Decouverte extends Thread implements DiscoveryListener {

  private PeerGroup netPeerGroup = null;  //groupe principal d'appartenance du pair
  private DiscoveryService discovery = null;  //service de decouverte du groupe
 private MesPaires monPair = null;
private boolean interrupted=false;
  /**
   * Constructeur de la classe Decouverte
   */
  public Decouverte(MesPaires pf,PeerGroup p) {

    netPeerGroup = p;
    discovery = netPeerGroup.getDiscoveryService();
    discovery.addDiscoveryListener(this);
monPair = pf;
  }
 public void interrupt(){
    interrupted=true;
  }

  /**
   * Lancement de la classe Decouverte
   */
  
  /**
   * Recherche des pairs dans le reseau
   */
  public void rechercher() {

    try {
      //decouverte des annonces distantes concernant les pairs
       System.out.println("Sending a Discovery Message");
      discovery.getRemoteAdvertisements(null, DiscoveryService.PEER, null, null,50);
  
    }
    catch (Exception e) {
      System.err.println("\nErreur dans Decouverte.rechercher() : ");
      e.printStackTrace();
    }
  }

    @Override
    public void discoveryEvent(DiscoveryEvent var1) {
  DiscoveryResponseMsg var2 = var1.getResponse();
        String var3 = "unknown";
        PeerAdvertisement var4 = var2.getPeerAdvertisement();
        if (var4 != null) {
            var3 = var4.getName();
        }

       
  //    monPair.affichedec("Got a Discovery Response [" + var2.getResponseCount() + " elements] from peer: " + var3);
        PeerAdvertisement var5 = null;
        Enumeration var6 = var2.getAdvertisements();
        if (var6 != null) {
            while(var6.hasMoreElements()) {
                var5 = (PeerAdvertisement)var6.nextElement();
               
                monPair.affichedec(" Peer name = " + var5.getName());
            }
        }

    }

  /**
   * Reception des evenements lies a la decouverte de pairs (d'annonces)
   */
 

}
