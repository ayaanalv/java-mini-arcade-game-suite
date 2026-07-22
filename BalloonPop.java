//BalloonPop.java
import java.util.*;

public class BalloonPop {

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static boolean playBalloonPop(String username) {
        System.out.println("Welcome to Balloon Pop!");
        boolean wonballoon = false; // track if we’ve ever seen user “win”
        boolean playingOuter = true;
        boolean finalWin = false;

        while(playingOuter) {
            System.out.print("Would you like to play Singleplayer or Multiplayer?: ");
            String singOrMult = sc.nextLine().trim().toLowerCase();

            if(singOrMult.equals("singleplayer")) {
                int inflationLev = rand.nextInt(15)+1;  // random start 1..15
                boolean playing = true;
                while(playing) {
                    // user
                    System.out.print(username + " Press Enter to Inflate the Balloon.");
                    sc.nextLine();
                    System.out.println(username + " Pumps air into the balloon!.");
                    int inflate = rand.nextInt(3)+1;  // 1..3
                    inflationLev += inflate;
                    if(inflationLev > 15) {
                        System.out.println("The inflation level is too high!\n");
                    } else {
                        System.out.println("The inflation level is " + inflationLev + "\n");
                    }
                    if(inflationLev > 15) {
                        System.out.println("The Balloon Pops!.\n");
                        System.out.println("You Lost! Better luck next time.");
                        playing = false;
                    } else {
                        System.out.println("The Balloon Does Not Pop!\n");
                    }

                    // computer’s turn
                    if(playing) {
                        System.out.println("Computer Pumps air into the balloon!.");
                        inflate = rand.nextInt(3)+1;
                        inflationLev += inflate;
                        if(inflationLev > 15) {
                            System.out.println("The inflation level is too high!\n");
                        } else {
                            System.out.println("The inflation level is " + inflationLev + "\n");
                        }
                        if(inflationLev > 15) {
                            System.out.println("The Balloon Pops!.\n");
                            System.out.println(username + " Wins the Game! Good Job!");
                            if(!wonballoon) {
                                wonballoon = true;
                                finalWin = true;
                            }
                            playing = false;
                        } else {
                            System.out.println("The Balloon Does Not Pop!\n");
                        }
                    }
                } // end while game

                System.out.print("Do you want to play again? (yes/no): ");
                String play_again = sc.nextLine().trim().toLowerCase();
                if(!play_again.equals("yes")) {
                    break; // out of outer loop
                }

            } else if(singOrMult.equals("multiplayer")) {
                System.out.print("Player One Name: ");
                String player1 = sc.nextLine().trim();
                System.out.print("Player Two Name: ");
                String player2 = sc.nextLine().trim();
                System.out.println();
                boolean playing = true;
                int inflationLev = rand.nextInt(15)+1;
                boolean p1Alive = true;

                while(playing) {
                    System.out.print(player1 + " Press Enter to Inflate the Balloon.");
                    sc.nextLine();
                    System.out.println(player1 + " Pumps air into the balloon!.");
                    int inflate = rand.nextInt(3)+1;
                    inflationLev += inflate;
                    if(inflationLev > 15) {
                        System.out.println("The inflation level is too high!\n");
                    } else {
                        System.out.println("The inflation level is " + inflationLev + "\n");
                    }
                    if(inflationLev > 15) {
                        System.out.println("The Balloon Pops!.\n");
                        System.out.println(player2 + " Wins the Game!");
                        playing = false;
                        p1Alive = false;
                    } else {
                        System.out.println("The Balloon Does Not Pop!\n");
                    }

                    if(p1Alive) {
                        System.out.print(player2 + " Press Enter to Inflate the Balloon.");
                        sc.nextLine();
                        System.out.println(player2 + " Pumps air into the balloon!.");
                        inflate = rand.nextInt(3)+1;
                        inflationLev += inflate;
                        if(inflationLev > 15) {
                            System.out.println("The inflation level is too high!\n");
                        } else {
                            System.out.println("The inflation level is " + inflationLev + "\n");
                        }
                        if(inflationLev > 15) {
                            System.out.println("The Balloon Pops!.\n");
                            System.out.println(player1 + " Wins the Game!");
                            playing = false;
                        } else {
                            System.out.println("The Balloon Does Not Pop!\n");
                        }
                    }
                }

                System.out.print("Do you want to play again? (yes/no): ");
                String play_again = sc.nextLine().trim().toLowerCase();
                if(!play_again.equals("yes")) {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter 'Singleplayer' or 'Multiplayer'.");
            }
        }
        return finalWin;
    }
}