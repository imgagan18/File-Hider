package Service;

import java.util.Random;

public class GenerateOTP {
    public static String GetOTP(){
        Random random=new Random();
        return String.format("%04d",random.nextInt(10000));// this is important code

    }
}
