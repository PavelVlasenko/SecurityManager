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
public class Authentificator
{
    private static final String O_URL = "keyStoreURL";
    private static final String O_ALIAS = "keyStoreAlias";
    private static final String O_TYPE = "keyStoreType";
    private static final String O_SPASS = "keyStorePasswordURL";
    private static final String O_KPASS = "privateKeyPasswordURL";
    private static final String O_PPATH = "protected";

    public static void auth() throws Exception
    {

        // if keyStoreType is PKCS11, keyStoreURL must be NONE

        KeyStoreLoginModule m = new KeyStoreLoginModule();
        Subject s = new Subject();
        Map options = new HashMap();

        options.put(O_URL, "/home/pvlasenko/security.jks");
        options.put(O_ALIAS, "bob");
        options.put(O_SPASS, "123123");
        options.put(O_KPASS, "123qwe");
        m.initialize(s, null, null, options);
        s.
        try
        {
            m.login();
            throw new SecurityException("expected exception");
        }
        catch(LoginException le)
        {
            // good
            //le.printStackTrace();
            System.out.println("test  passed");
        }
    }
}
