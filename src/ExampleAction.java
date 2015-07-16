/**
 * Example action executed from {@link JAAS_Authentificator}
 *
 * @author Pavel Vlasenko
 */
public class ExampleAction implements java.security.PrivilegedAction
{
    public Object run() {
        java.io.File f = new java.io.File("test.txt");

        // the following call invokes a security check
        if (f.exists()) {
            System.out.println("File foo.txt exists");
        }
        return null;
    }
}
