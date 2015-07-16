import com.sun.jmx.remote.security.SubjectDelegator;

import javax.security.auth.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) throws Exception
    {
        //testAuthJAAS();

    }

    public static void testAuthJAAS()
    {
        // Initialize JAAS Authentificator  and try to login
        JAAS_Authentificator auth = new JAAS_Authentificator();
        auth.initialize("file:///home/pvlasenko/security.jks", "bob", "file:///home/pvlasenko/key.txt", "file:///home/pvlasenko/prkey.txt");
        auth.auth();

        //DoAs Method
        Subject.doAs(auth.subject, new ExampleAction());

        //DoAsPrivileged
        Subject.doAsPrivileged(auth.subject, new ExampleAction(), null);
    }


    public static void testPolicy()
    {
        SecurityManagerTool securityManagerTool = new SecurityManagerTool();

        securityManagerTool.loadPolicyFile("/home/pvlasenko/Documents/Upwork/SecurityManager/security.config");

        //test
        System.out.println("Unsafe operation test");
        securityManagerTool.checkOpenFile("/home/pvlasenko/test/testFileUnsafe.txt");

        System.out.println("Safe operation");
        securityManagerTool.checkOpenFile("/home/pvlasenko/test/testFileSafe.txt");
    }

    public static void testCert()
    {
        FileInputStream is = null;
        try {

            File file = new File("/home/pvlasenko/security.jks");
            is = new FileInputStream(file);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "123123";
            keystore.load(is, password.toCharArray());

            // Print all keys and certificate
            /*Enumeration enumeration = keystore.aliases();
            while(enumeration.hasMoreElements()) {
                String alias = (String)enumeration.nextElement();
                System.out.println("alias name: " + alias);
                Certificate certificate = keystore.getCertificate(alias);
                System.out.println(certificate.toString());
            }*/

            String allias = "bob";
            Certificate c = keystore.getCertificate(allias);

            CertificateFactory fact = CertificateFactory.getInstance("X.509");
            FileInputStream is2 = new FileInputStream ("/home/pvlasenko/bob.crt");
            Certificate cer =  fact.generateCertificate(is2);
            boolean compare = c.equals(cer);



        } catch (java.security.cert.CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is)
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}
