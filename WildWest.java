//WildWest.java
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class WildWest {

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static boolean playWildWest(String username) {
        System.out.println("Welcome to Wild West!," + username);
        System.out.println();

        boolean gameCompleted = false; // track if we’ve ever won at least once

        // optional rules
        while(true) {
            System.out.print("Do you wish to read the rules? Y/N: ");
            String rules = sc.nextLine().trim().toLowerCase();
            if(rules.equals("y")) {
                System.out.println("Wild West is a reaction time based game.\n" +
                                   "There are 3 different options for difficulty.\n" +
                                   "The higher the difficulty, the faster you must be to win.\n" +
                                   "When the random prompt appears, press ENTER as quickly as possible!");
                System.out.println();
                break;
            } else if(rules.equals("n")) {
                break;
            }
            System.out.println("Please enter Y or N");
        }

        while(true) {
            System.out.println("Please choose your enemy (easiest to hardest):");
            System.out.println("Johnny Ringo (1)");
            System.out.println("Bat Masterson (2)");
            System.out.println("Wyatt Earp (3)");

            String enemyStr;
            while(true) {
                System.out.print("Selection: ");
                enemyStr = sc.nextLine().trim();
                if(enemyStr.equals("1") || enemyStr.equals("2") || enemyStr.equals("3")) {
                    break;
                } else {
                    System.out.println("Please select only 1,2 or 3.");
                }
            }

            String name = "Johnny Ringo";
            double time1 = 0.5;  // default
            if(enemyStr.equals("1")) {
                name = "Johnny Ringo";
                time1 = 0.5;
            } else if(enemyStr.equals("2")) {
                name = "Bat Masterson";
                time1 = 0.35;
            } else {
                name = "Wyatt Earp";
                time1 = 0.25;
            }

            System.out.println("You are facing " + name + "!");
            System.out.println("The duel begins when the bell rings at a random time, be ready to hit ENTER!");

            int countdown = rand.nextInt(13) + 3;  // 3 to 15
            for(int x=0; x<countdown; x++){
                System.out.println();
                try { Thread.sleep(250); } catch(Exception e){}
            }

            System.out.println("The clock rings!");
            System.out.println("QUICK, SHOOT!");

            // measure reaction time
            Instant start = Instant.now();
            sc.nextLine(); // user presses enter
            Instant end = Instant.now();
            double timeDifference = ChronoUnit.MILLIS.between(start, end) / 1000.0;

            boolean win = false;

            if(timeDifference < 0.05) {
                System.out.println("You hit the enter key too early, causing your gun to jam.");
                lossMessage();
                win = false;
            }
            else if(timeDifference < time1) {
                System.out.println("A flash,");
                try{Thread.sleep(1000);}catch(Exception e){}
                System.out.println("a bang,");
                try{Thread.sleep(1000);}catch(Exception e){}
                System.out.println("and " + name + " drops to the floor.");
                try{Thread.sleep(1000);}catch(Exception e){}
                System.out.println();
                System.out.println("You won!!!");
                try{Thread.sleep(2000);}catch(Exception e){}
                win = true;
                gameCompleted = true;
            } else {
                lossMessage();
                win = false;
            }

            double roundedTime = Math.round(timeDifference*100.0)/100.0;
            double beatBy = Math.round((time1 - roundedTime)*100.0)/100.0;
            double lostBy = Math.round((roundedTime - time1)*100.0)/100.0;

            System.out.println("Your reaction time: " + roundedTime + " seconds.");
            System.out.println(name + "'s reaction time: " + time1 + " seconds.");

            if(!win && lostBy == 0.0){
                System.out.println(name + " beat you by a fraction of a millisecond.");
            }
            else if(win && beatBy == 0.0) {
                System.out.println("You beat " + name + " by a fraction of a millisecond.");
            }
            else if(!win && lostBy > 0.0) {
                System.out.println(name + " beat you by " + lostBy + " seconds.");
            }
            else if(win) {
                System.out.println("You beat " + name + " by " + beatBy + " seconds.");
            }

            System.out.print("Do you wish to continue? Press any key to continue or N: ");
            String input1 = sc.nextLine().trim().toLowerCase();
            if(input1.equals("n")) {
                break;
            }
        }

        return gameCompleted;
    }

    private static void lossMessage() {
        System.out.println("A flash,");
        try{Thread.sleep(1000);}catch(Exception e){}
        System.out.println("a bang,");
        try{Thread.sleep(1000);}catch(Exception e){}
        System.out.println("and you drop to the floor.");
        try{Thread.sleep(1000);}catch(Exception e){}
        System.out.println();
        System.out.println("You lost.");
        try{Thread.sleep(2000);}catch(Exception e){}
    }
}