package Models;

import Interfaces.NodeInterface;
import Utils.DeviceType;

import java.util.ArrayList;

public class Bridge  extends  Device implements NodeInterface {



    int ID;
    int rootID;
    int cost =0;
    String bestConfigPortID="none";
    int bestSenderID;
    Config bestConfig;
    ArrayList<Config> configList = new ArrayList<>();
    public  Bridge(int ID){
        super(DeviceType.BRIDGE);
        this.ID=ID;
        this.rootID = ID;
        this.bestSenderID=ID;
    }


    public void setInitialConfig(){


        ArrayList<Line> lineList = getConnectedLines();
        for (int i=0;i<lineList.size();i++){
           String portID= lineList.get(i).getPort().PortID;
            String val = this.rootID+" "+this.cost+" "+this.getID()+" "+portID;
            configList.add(new Config(portID,this.rootID,this.cost,this.getID()));
            //System.out.println(c);
        }
    }

    public void setRootID(int rootID) {
        this.rootID = rootID;
    }

    public int getID() {
        return ID;
    }



    @Override
    public void sendPackage() {
       ArrayList<Line> lineList = getConnectedLines();
       for (int i=0;i<lineList.size();i++){
           Package p = new Package(this.rootID,this.cost,this.ID);
           int lineID =lineList.get(i).getLineID();
           p.lineID=lineID;
           lineList.get(i).transferData(p,getUid());}
    }


    @Override
    public String toString() {
        setBestPortConfig();
        setAccessibility();
        String res = "Bridge "+getID()+": Best configuration <"+this.rootID+", "+(this.cost)+"> from "+this.bestSenderID+" via "+this.bestConfigPortID;
        res+="\n";

        for(int i=0;i<configList.size();i++) {
            res+= configList.get(i)+"\n" ;
        }
        return res;
    }

    public void setAccessibility(){


        for(int i=0;i<configList.size();i++){
            Config c = configList.get(i);
                if(c.rootID==this.getID() && c.cost==0 && c.senderID==this.getID() && this.getID()!=this.rootID){
                    c.rootID=bestConfig.rootID;
                    c.cost=c.cost+1;
                }
          else if(c.rootID==this.getID() && c.cost==0 && c.senderID==this.getID() && this.getID()==this.rootID){
                this.bestConfigPortID="none";
                this.cost=0;
            }
        }
        for(int i=0;i<configList.size();i++){
            Config c = configList.get(i);
            if(!c.equals(this.bestConfig) && c.senderID!=this.getID() && c.senderID!=this.rootID &&  c.cost<=this.cost){
                    c.setOpen(false);
            }
        }

    }

    public void setBestPortConfig(){
        Config best=configList.get(0);

        for(int i=0;i<configList.size();i++){
             Config c = configList.get(i);
             if(c.rootID<best.rootID){
                 best=configList.get(i);
             }
             else if( c.rootID== best.rootID && c.cost<best.cost){
                 best= configList.get(i);

             }
             else if (c.rootID == best.rootID && c.cost == best.cost && c.senderID<best.senderID){
                 best= configList.get(i);
             }

        }
        this.cost=best.cost+1;
        this.rootID=best.rootID;
        this.bestSenderID=best.senderID;
        this.bestConfigPortID=best.portID;
        this.bestConfig=best;

    }


    @Override
    public void receivePackage(Package p) {

        Line l = getLineByID(p.getLineID());
        Port port= l.getPort();
        String portID = port.getPortID();
        if(this.getID()!=p.rootID) {
            if (this.rootID > p.rootID) {
                this.rootID = p.rootID;
                this.cost = p.cost + 1;
                addConfig(new Config(portID,p.rootID,p.cost,p.senderID));
             }
            else if (this.rootID == p.rootID && this.cost > p.cost) {
                    this.cost = p.cost + 1;
                addConfig(new Config(portID,p.rootID,p.cost,p.senderID));
                }
            else if (this.rootID == p.rootID && this.cost == p.cost && p.senderID<this.getID()) {

                addConfig(new Config(portID,p.rootID,p.cost,p.senderID));
             }

        }

    }

    public void addConfig(Config c){
    for(int i=0;i<configList.size();i++){
        if(configList.get(i).portID.equals(c.portID)){
            configList.get(i).cost=c.cost;
        configList.get(i).rootID=c.rootID;
        configList.get(i).senderID=c.senderID;
        break;
        }

    }

    }
}
