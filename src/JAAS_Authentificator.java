import com.sun.security.auth.module.KeyStoreLoginModule;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

/**
 * JAAS Authentificator
 * KeyStoreLoginModule used for authentificate with key pairs generated in keystore
 *
 * @author Pavel Vlasenko
 */
public class JAAS_Authentificator
{
    // ALL option needed for KeyStoreLoginModule
    private final String O_URL_KEYSTORE = "keyStoreURL";
    private final String O_ALIAS = "keyStoreAlias";
    private final String O_TYPE = "keyStoreType";   //NotUsed
    private final String O_SPASS = "keyStorePasswordURL";
    private final String O_KPASS = "privateKeyPasswordURL";
    private final String O_PPATH = "protected";       //NotUsed

    private KeyStoreLoginModule loginModule;
    public Subject subject;

    /**
     * Initialize KeyStoreLoginModule with options
     *
     * @param urlKeystore URL path to keystore file
     * @param alias allias in keystore
     * @param keystore_password  keystore password
     * @param alias_password   allias password
     */
    public void initialize(String  urlKeystore, String alias, String keystore_password, String alias_password)
    {
        loginModule = new KeyStoreLoginModule();
        subject = new Subject();
        Map options = new HashMap();

        options.put(O_URL_KEYSTORE, urlKeystore);
        options.put(O_ALIAS, alias);
        options.put(O_SPASS, keystore_password);
        options.put(O_KPASS, alias_password);
        loginModule.initialize(subject, null, null, options);
    }


    /**
     *  JAAS Authentificate
     *  Login using KeyStoreLoginModule
     *  Checks alias and private key in keystore
     */
    public void auth()
    {
        try
        {
            loginModule.login();
        }
        catch(LoginException le)
        {
            //le.printStackTrace();
            System.out.println("AUTH  NOT PASSED");
        }

        System.out.println("AUTH PASSED");
    }
}
