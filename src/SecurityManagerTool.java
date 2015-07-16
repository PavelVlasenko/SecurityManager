import java.security.AccessController;

/**
 * Security manager for policy files
 *
 * @author Pavel Vlasenko
 */
public class SecurityManagerTool
{
    SecurityManager security;

    /**
     * Init security manager with policy file
     *
     * @param filePath Security policy file path
     */
    public void loadPolicyFile(String filePath)
    {
        System.setProperty("java.security.policy","file:"+filePath);
        security = new SecurityManager();
        System.setSecurityManager(security);
    }

    public void checkOpenFile(String filePath)
    {
        try
        {
            if(security != null)
            {
                security.getSecurityContext();
                security.checkRead(filePath);
            }

            else
            {
                System.out.println("Security manager not init");
                SecurityManager security = System.getSecurityManager();
            }
            //if safe do this action
            System.out.println("Safe operation");
        }
        catch(SecurityException e)
        {
            //if unsafe do this action
            System.out.println("Unsafe operation");
        }
    }

}
