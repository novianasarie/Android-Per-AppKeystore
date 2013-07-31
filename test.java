
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.io.FileInputStream;
import java.util.*;
public class test
{
    public static void main(String[] args)throws Exception
    {
        final MessageDigest digest = MessageDigest.getInstance("SHA-1");
              
        FileInputStream is = new FileInputStream("zbetanew.crt");
        
        CertificateFactory cf = CertificateFactory.getInstance( "X.509" );
        
        Iterator<? extends Certificate> i = cf.generateCertificates( is ).iterator();
        
        while ( i.hasNext() )
            
        {
            X509Certificate c = (X509Certificate)i.next();
            MessageDigest  md = MessageDigest.getInstance("SHA-1");
            final byte[] spki          = c.getPublicKey().getEncoded();
            final byte[] pin           = md.digest(spki);
                final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
                char[] hexChars = new char[pin.length * 2];
                int v;
                for ( int j = 0; j < pin.length; j++ ) {
                    v = pin[j] & 0xFF;
                    hexChars[j * 2] = hexArray[v >>> 4];
                    hexChars[j * 2 + 1] = hexArray[v & 0x0F];
                }
            String x=new String(hexChars);
            System.out.println("Cert:" + c.getIssuerX500Principal().getName()+"\n");
           

            System.out.println("PIN:" + x );

       	}
    
    

       
        
    }
    
}