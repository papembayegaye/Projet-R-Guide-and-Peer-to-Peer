
import java.io.File;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import net.jxta.peergroup.PeerGroup;
import net.jxta.share.ContentAdvertisement;
import net.jxta.share.client.GetContentRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Rdownloading {
       private PeerGroup netPeerGroup = null; 
       JTable table;
       JProgressBar progress;
       GetRemoteFile mydonwloader;
    public Rdownloading(ContentAdvertisement contentAdv,File destination,JTable table,JProgressBar progrss,PeerGroup netPeerGroup)
    {
    
    
    this.netPeerGroup=netPeerGroup;
    mydonwloader = new GetRemoteFile(netPeerGroup,contentAdv,destination,this.table,progress);
    
    }
    public class GetRemoteFile extends GetContentRequest{
    JProgressBar progress;
        public GetRemoteFile(PeerGroup group, ContentAdvertisement cAdv, File destination,JTable table, JProgressBar progress) {
            super(group, cAdv, destination);
        }
    public void notifyUpdate(int percentage){
    
    this.progress.setValue(percentage);
    }
    public void notifyDone(){
    table.setValueAt("complete", table.getSelectedRow(), 2);
    }
    public void notifyFailure(){
    table.setValueAt("Failed", table.getSelectedRow(), 2);
    
    }
    }
}
