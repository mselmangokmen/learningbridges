package Utils;

import java.util.UUID;

public class Helpers {


    public static String generateUUID() { // generates unique ID for bridges and ports
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "").substring(0, 8);
    }
}
