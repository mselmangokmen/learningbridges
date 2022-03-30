package Models;

public class Config { // config class includes information about root, cost, sender ID and situation of open or close.
    String portID;
    int rootID;
    int cost;
    int senderID;
    boolean open=true;

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public Config(String portID, int rootID, int cost, int senderID) {
        this.portID = portID;
        this.rootID = rootID;
        this.cost = cost;
        this.senderID = senderID;
    }

    @Override
    public String toString() {

        String result = "port "+ portID+ ": <"+rootID+", "+cost+", "+senderID+" >" + (this.open ? "Open":"Close");

        return result;
    }
}
