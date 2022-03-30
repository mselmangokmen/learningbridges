package Interfaces;

import Models.Package;

public interface NodeInterface { // Interface for sending and receiving packages

     void sendPackage();
     void receivePackage(Package p);


}
