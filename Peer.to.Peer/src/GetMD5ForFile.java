import java.awt.Desktop;
import static java.awt.PageAttributes.MediaType.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.util.test.Test;

public class GetMD5ForFile {

    private File file;

    public GetMD5ForFile(String filePath) {
        this.file = new File(filePath);
    }

    public GetMD5ForFile(File file) {
        this.file = file;
    }

    public String getMD5() {
        String md5 = null;

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(this.file);

            // md5Hex converts an array of bytes into an array of characters representing the hexadecimal values of each byte in order.
            // The returned array will be double the length of the passed array, as it takes two characters to represent any given byte.

            md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fileInputStream));

            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return md5;
    }
   GetMD5ForFile filet ;
    public boolean getFileSum(File list){
        
    GetMD5ForFile file = new GetMD5ForFile(list);
   if( filet.getMD5().toString().equals(file.getMD5().toString())){
   
   return true;
   }
    
    
    return false;}
    public File comparemd5sum(File file, String sha1sum) {
             GetMD5ForFile filet = new GetMD5ForFile(file);
		if (sha1sum.equals(filet.getMD5())) {
                        return file;
                }
                
        return null;}
     public static void main(String[] args) {

        File filePath = new File("C:\\Users\\hp\\Desktop/Test.txt");

        GetMD5ForFile file = new GetMD5ForFile(filePath);
        
        System.out.println("MD5: " + file.getMD5());
    }
}