package Models;

import Utils.Helpers;

public class Package {

    int rootID;
    int senderID;
    int cost;
    int lineID;

    String packageID = Helpers.generateUUID();
    @Override
    public String toString() {
        return "<" + rootID +
                ", " + cost +
                ", " + senderID +
                '>';
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public int getLineID() {
        return lineID;
    }

    public Package(int RootID, int Cost, int SenderID) {
        this.rootID = RootID;
        this.cost = Cost;
        this.senderID = SenderID;
    }
}
