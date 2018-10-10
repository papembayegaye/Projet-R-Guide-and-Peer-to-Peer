
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.jxta.peergroup.PeerGroup;
import net.jxta.share.ContentAdvertisement;
import net.jxta.share.client.CachedListContentRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Rsearching extends Thread {
     private PeerGroup netPeerGroup = null;
     String searchKey;JTable table;
     ListRequestor reqestor;
       ContentAdvertisement[] searchResult;
       boolean bol = true; 
    public Rsearching(String searchKey, JTable table,PeerGroup netPeerGroup){
    this.netPeerGroup= netPeerGroup; this.table=table;
    }
    
     


    public void run(){
    while(true){
    
    reqestor = new ListRequestor(netPeerGroup,searchKey,table);
    reqestor.activateRequest();
  
    }
    
    }
    public void stope(){
    
    bol=false;
    }
    public ContentAdvertisement [] gestContentAdvs(){
    return reqestor.searchResult;
    }
}
class ListRequestor extends CachedListContentRequest{
JTable table;  ContentAdvertisement[] searchResult;
    public ListRequestor(PeerGroup netPeerGroup, String inSubStr,JTable table) {
       
        super(netPeerGroup, inSubStr); this.table=table;
    }
    public void notifyMoreResults()
    {
 searchResult = getResults();
    String [] titles = {"File","Length Bytes","Status"};
    DefaultTableModel  TableModell = new DefaultTableModel(titles,
    searchResult.length);
    table.setModel(TableModell);
    for(int i =0;i< searchResult.length;i++){
    table.setValueAt(searchResult[i].getName(),i, 0);
     table.setValueAt(searchResult[i].getLength(),i, 1);
     table.setValueAt("Waiting",i, 2);
     
    }
    } public ContentAdvertisement [] gestContentAdvs(){
    return searchResult;
    }


}

