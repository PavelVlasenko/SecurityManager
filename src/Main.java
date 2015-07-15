public class Main {

    public static void main(String[] args)
    {
        System.out.println("===== Start security tool");
        SecurityManagerTool securityManagerTool = new SecurityManagerTool();

        securityManagerTool.loadPolicyFile("/home/pvlasenko/Documents/Upwork/SecurityManager/security.policy");

        //test
        System.out.println("Unsafe operation test");
        securityManagerTool.checkOpenFile("/home/pvlasenko/test/testFileUnsafe.txt");

        System.out.println("Safe operation");
        securityManagerTool.checkOpenFile("/home/pvlasenko/test/testFileSafe.txt");
    }
}
