package online_exam;

import java.rmi.registry.Registry;

/**
 * Server side class for online exam program.
 * @author Nick
 */
public class Exam_Server {
    public static void main(String args[]) {
        try{
            Registry r =  java.rmi.registry.LocateRegistry.createRegistry(1099);
            
            r.rebind("ServerMethods", new ServerMethods());
            
            System.out.println("Server is connected and ready for operation");
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
    }
}
