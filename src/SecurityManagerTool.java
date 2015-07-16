import java.security.AccessController;

/**
 * TODO: Java docs
 *
 * @author Pavel Vlasenko
 */
public class SecurityManagerTool
{
    SecurityManager security;

    public void loadPolicyFile(String filePath)
    {
        System.setProperty("java.security.policy","file:"+filePath);
        security = new SecurityManager();
        System.setSecurityManager(security);
    }

    public void checkOpenFile(String filePath)
    {
       // SecurityManager security = System.getSecurityManager();
        try
        {
            if(security != null)
            {
                security.getSecurityContext();
                security.checkRead(filePath);
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


    //check Sertification


}
