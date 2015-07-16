import com.sun.security.auth.module.KeyStoreLoginModule;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

/**
 * Authentificate
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
     * @param allias allias in keystore
     * @param keystore_password  keystore password
     * @param allias_password   allias password
     */
    public void initialize(String  urlKeystore, String allias, String keystore_password, String allias_password)
    {
        loginModule = new KeyStoreLoginModule();
        subject = new Subject();
        Map options = new HashMap();

        options.put(O_URL_KEYSTORE, urlKeystore);
        options.put(O_ALIAS, allias);
        options.put(O_SPASS, keystore_password);
        options.put(O_KPASS, allias_password);
        loginModule.initialize(subject, null, null, options);
    }

    public void auth()
    {
        try
        {
            loginModule.login();
        }
        catch(LoginException le)
        {
            // auth NOT passed
            //le.printStackTrace();
            System.out.println("AUTH  NOT PASSED");
        }

        System.out.println("AUTH PASSED");
    }
}
