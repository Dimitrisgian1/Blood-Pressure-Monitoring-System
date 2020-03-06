package BPMS;

import java.util.Random;

public class Measurement {

    static Random rand = new Random();

    static int date = rand.nextInt(1000);
    static int time = rand.nextInt(100);
    static float systolicBP = rand.nextFloat() * 10;
    static float diastolicBP = rand.nextFloat() * 10;
    static float heartRate = rand.nextFloat() * 180;
    static int userid = rand.nextInt(3);

    public static void PRINT() {
        System.out.println(date + "2020 " + time + " " + systolicBP + " " + diastolicBP + " " + heartRate + " " + userid);
    }
}
