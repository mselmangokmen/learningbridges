package Models;

import Interfaces.NodeInterface;
import Utils.DeviceType;

public class Line {
    Device d1;
    Device d2;
    int lineID;
    boolean closed=false;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Line(Device d1, Device d2, int lineID) {
        this.d1 = d1; // first device connected to the line, it is port or bridge
        this.d2 = d2; // second  device connected to the line, it is port or bridge
        this.lineID =lineID; // assignment of line ID.
    }

    public int getLineID() {
        return lineID;
    }

    Bridge getBridge(){ // get connected bridge to the line

        if(this.d1.type== DeviceType.BRIDGE)
            return (Bridge)d1;
        else return (Bridge)d2;
    }

    Port getPort(){ // get connected bridge to the line

        if(this.d1.type== DeviceType.PORT)
            return (Port)d1;
        else return (Port)d2;
    }
    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public Device getD1() {
        return d1;
    }

    public Device getD2() {
        return d2;
    }

    public  String getd1ID(){
        return d1.uid;
    }

    public  String getd2ID(){
        return d2.uid;
    }

    public void transferData(Package p, String uid){   try {
       // System.out.println("Package transferring...");

         //   TimeUnit.MILLISECONDS.sleep(200);

        Port prt = getPort();
        p.lineID=lineID;

                if (d1.uid.equals(uid))  // sending the package from one device to another device.
                    ((NodeInterface) d2).receivePackage(p);
                else if (d2.uid.equals(uid))
                    ((NodeInterface) d1).receivePackage(p);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
