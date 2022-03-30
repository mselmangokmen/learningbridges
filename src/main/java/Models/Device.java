package Models;

import Utils.DeviceType;
import Utils.Helpers;

import java.util.ArrayList;

public class Device {
        String uid= Helpers.generateUUID(); // generating unique ID.
        DeviceType type; // device type. it is bridge or port

        ArrayList<Line> connectedLines = new ArrayList<>(); // connected list of lines for ports and bridges
        public Device(DeviceType type) {
                this.type = type;
        }

        public ArrayList<Line> getConnectedLines() {
                return connectedLines;
        }


        Line getLineByID(int id){ // get connected line to the port or bridge by line ID.
                Line line = null;
                for (Line l: this.connectedLines
                     ) {
                        if(l.lineID==id)
                        {
                                line=l;
                                break;
                        }

                }
                return  line;

        }

        public void addLine(Line line){
                connectedLines.add(line);
        }
        public String getUid() {
                return uid;
        }
}
