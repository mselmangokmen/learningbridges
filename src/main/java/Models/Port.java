package Models;

import Interfaces.NodeInterface;
import Utils.DeviceType;

import java.util.ArrayList;

public class Port extends  Device implements NodeInterface {

    String PortID;
    Package aPackage;
    public Port(String PortID) {
        super(DeviceType.PORT);
        this.PortID = PortID;
    }



    public String getPortID() {
        return PortID;
    }


    @Override
    public void sendPackage() {
        ArrayList<Line> lineList = getConnectedLines();

        for (int i=0;i<lineList.size();i++){ // package will be sent over all lines
            Bridge b = null;
            Line line = lineList.get(i);
            if(line.getD1() instanceof  Bridge)  // ports need to detect the connected bridges to the line
                b=(Bridge)line.getD1();
            else
                b=(Bridge)line.getD2();

            if(b.getID()!=this.aPackage.senderID){ // to prevent sending the package again same bridge
                int lineID =line.getLineID();
                this.aPackage.lineID=lineID;
            line.transferData(this.aPackage,getUid());}} // calling transferData function to send package
    }

    @Override
    public void receivePackage(Package p) {
        this.aPackage= p;
        sendPackage();
    }
}
