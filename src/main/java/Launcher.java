import Models.Network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
        List<String> lines=null;
        try {
            if(args.length>1){

                String filePath= args[0];
                if(filePath.trim().length()>0) {
                    lines = Files.readAllLines(Paths.get(filePath)); // reading lines from LanFile.txt
                }

                Network myNet = new Network(); // building network

                for (String l: lines) {
                    myNet.setupBridge(l); // setting up bridges
                }

                for(int i=1;i<args.length;i++){
                    myNet.getBridgeByID(Integer.parseInt(args[i])).sendPackage(); // package sending regarding to the numbers
                }
                System.out.println(myNet);


            }
            else{
                System.out.println("Please enter a valid path and list of numbers.");
                System.exit(0);
            }




           /* myNet.getBridgeByID(1).sendPackage();
            myNet.getBridgeByID(4).sendPackage();


                for(int i=1;i<args.length;i++){
                    if(args[i].trim().length()>0)
                    numbers.add(Integer.parseInt(args[i]));
                }

            */





        } catch (IOException e) {
            System.out.println("Please enter a valid path and list of numbers.");
            System.exit(0);
        }
    }

}
