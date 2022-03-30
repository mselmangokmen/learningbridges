package Models;

import java.util.ArrayList;

public class Network {


    public  ArrayList<Port> ports = new ArrayList<>();
    public  ArrayList<Bridge> bridges = new ArrayList<>();
    public  ArrayList<Line> lines = new ArrayList<>();


    public ArrayList<Port> getPorts() {
        return ports;
    }

    public ArrayList<Bridge> getBridges() {
        return bridges;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public Bridge getBridgeByID(int id) {
        Bridge b = null;
        for (Bridge bridge: bridges){
            if(id==bridge.getID())
            {
                b=bridge;
                break;
            }
        }
        return  b;
    }

    public void setupBridge(String line){
        String[] vars = line.split(" ");
        int bID = Integer.parseInt(vars[0]); // getting bridgeID

        Bridge b = checkBridgeExist(bID); // check list of bridges whether contains the bridge with same id.
        if(b==null) b=addBridge(bID); // add to the list if not exist in the list.
        for(int i=1;i<vars.length;i++){

            Port s = checkPortExist(vars[i]);  // check list of ports whether contains the port with same id.
            if(s==null) s= addPort(vars[i]); // add port to the list of ports
            Line p = checkLineExist(b,s); // check the list of lines between ports and bridges
            if(p==null) p= addLine(b,s, lines.size()+1); // if the list of lines does not contain the line with same bridge and port
                                                            // add line between port and bridge
        }
        b.setInitialConfig(); // add initial configs for ports of the bridge like (1,0,1)


    }

    @Override
    public String toString() {
        String result ="";
        for (Bridge b:
             bridges) {
            result+=b.toString()+"\n";
        }
        return result;
    }

    Bridge checkBridgeExist(int id){ // check the bridge whether exist or not by id
        Bridge bridge = null;
        for (Bridge b : bridges)
            if( b.getID()==id){bridge=b;
            break;}


        return bridge;
    }

    Port checkPortExist(String id){ // check the port whether exist or not by id
        Port port = null;
        for (Port s : ports){
            if( s.getPortID().equals(id)){
                port=s;
            break;
            }}

        return port;
    }

    Line addLine(Device d1, Device d2, int id){ // adding line between ports and bridges

        Line p = new Line(d1,d2,id);
        d1.addLine(p);
        d2.addLine(p);
        lines.add(p);
        return p;
    }

    Line checkLineExist(Device d1, Device d2){ // check the line whether exist or not by devices (Bridge and port)
        Line line = null;
        for (Line p : lines){
            if((p.getd1ID().equals(d1.getUid()) && p.getd2ID().equals(d2.getUid()))
                    || (p.getd1ID().equals(d2.getUid()) && p.getd2ID().equals(d1.getUid()))) {
                line = p;
                break;
            }
        }
        return line;
    }

    Port addPort(String id){ // adding port

        Port s = new Port( id);
        ports.add(s);
        return  s;
    }

   Bridge  addBridge(int id){ // adding bridge

       Bridge bridge = new Bridge( id);
       bridges.add(bridge);
       return bridge;
    }
}
