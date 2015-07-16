/**
 * Example action executed from {@link JAAS_Authentificator}
 *
 * @author Pavel Vlasenko
 */
public class ExampleAction implements java.security.PrivilegedAction
{
    public Object run() {
        java.io.File f = new java.io.File("test.txt");

        //try to open a file
        if (f.canRead()) {
            System.out.println("File foo.txt exists");
        }
        return null;
    }
}
