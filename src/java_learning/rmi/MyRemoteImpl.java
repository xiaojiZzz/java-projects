package java_learning.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() {
        return "Server says, 'Hey'";
    }

    public static void main(String[] args) {
        try {
            MyRemoteImpl server = new MyRemoteImpl();
            Naming.rebind("Remote Hello", server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

