package Jamming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roi on 9/23/2016.
 */
public class ClientList {

    private List<Client> Clients;
    public static final int NumberOfClients=100;
    public void Init(double x1, double y1, double x2, double y2)
    {
        Clients = new ArrayList<>();
        for (int i=0; i< NumberOfClients; i++) {
            Client tmp = new Client(x1, y1, x2, y2);
            Clients.add(tmp);
        }

    }

    public void movebyCOGSOG()
    {
        for (int i=0; i< NumberOfClients; i++) {
            this.Clients.get(i).moveByCOGSOG();
        }
    }

    public void senseNoise(JammerParticle jammer)
    {
        for (int i=0; i< NumberOfClients; i++) {
            this.Clients.get(i).senseNoise(jammer);
        }
    }

}
