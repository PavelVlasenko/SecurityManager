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
    private final String O_TYPE = "keyStoreType";
    private final String O_SPASS = "keyStorePasswordURL";
    private final String O_KPASS = "privateKeyPasswordURL";
    private final String O_PPATH = "protected";

    KeyStoreLoginModule loginModule;

    /**
     *  Initialize KeyStoreLoginModule
     */
    public void initialize(String  urlKeystore, String allias, String keystore_password, String allias_password)
    {
        loginModule = new KeyStoreLoginModule();
        Subject s = new Subject();
        Map options = new HashMap();

        options.put(O_URL_KEYSTORE, urlKeystore);
        options.put(O_ALIAS, allias);
        options.put(O_SPASS, keystore_password);
        options.put(O_KPASS, allias_password);
        loginModule.initialize(s, null, null, options);
    }

    public void auth()
    {
        try
        {
            loginModule.login();
            //throw new SecurityException("expected exception");
        }
        catch(LoginException le)
        {
            // good
            //le.printStackTrace();
            System.out.println("test  NOT passed");
        }

        System.out.println("test PASSED");
    }
}
