//Roulette.java
import java.util.*;

public class Roulette {

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static boolean playRoulette(String username){
        System.out.println("Welcome to Roulette, " + username + ".");
        System.out.println("Bet on what colour the ball will land on using Nuggets");
        System.out.println();
        int nug = 100;
        boolean wonroulette = false;
        boolean finalWin = false;
        boolean playing = true;

        while(playing){
            System.out.println("You have " + nug + " Nuggets");
            System.out.print("How many Nuggets will you bet?: ");
            int nugget_bet;
            try{
                nugget_bet = Integer.parseInt(sc.nextLine());
            } catch(Exception e){
                System.out.println("Please enter a valid bet.\n");
                continue;
            }
            if(nugget_bet <= 0 || nugget_bet > nug){
                System.out.println("Please enter a valid bet.\n");
                continue;
            }

            System.out.println();
            System.out.println("You can bet on: ");
            System.out.println("Red (18 Numbers)");
            System.out.println("Black (18 Numbers)");
            System.out.println("Green (2 Numbers)");
            System.out.println();
            System.out.print("What Colour will you bet on?: ");
            String colBet = sc.nextLine().trim().toLowerCase();
            System.out.println();

            if(colBet.equals("red") || colBet.equals("black") || colBet.equals("green")){
                int colour = rand.nextInt(38)+1; // 1..38
                String corCol;
                if(colour>=1 && colour<=18) corCol="Red";
                else if(colour>=19 && colour<=36) corCol="Black";
                else corCol="Green";

                if(corCol.toLowerCase().equals(colBet) && (colBet.equals("red")||colBet.equals("black"))){
                    nugget_bet*=2;
                    nug+=nugget_bet;
                    System.out.println("The correct colour was " + corCol + "!");
                    System.out.println("You Won " + nugget_bet + " Nuggets!\n");
                    if(!wonroulette){
                        wonroulette=true;
                        finalWin=true;
                    }
                }
                else if(corCol.toLowerCase().equals(colBet) && colBet.equals("green")){
                    nugget_bet*=14;
                    nug+=nugget_bet;
                    System.out.println("The correct colour was " + corCol + "!");
                    System.out.println("You Won " + nugget_bet + " Nuggets!\n");
                    if(!wonroulette){
                        wonroulette=true;
                        finalWin=true;
                    }
                }
                else {
                    nug-=nugget_bet;
                    System.out.println("The correct colour was " + corCol + "!");
                    System.out.println("You lost " + nugget_bet + " Nuggets!\n");
                }

                if(nug==0){
                    System.out.println("You ran out of money! Game Over!");
                    playing=false;
                }
                else{
                    System.out.print("Would you like to play again? Y/N: ");
                    String yesNo = sc.nextLine().trim().toLowerCase();
                    System.out.println();
                    if(!yesNo.equals("y")){
                        System.out.println("Thanks For Playing!");
                        if(finalWin){
                            return true;
                        } else {
                            playing=false;
                        }
                    }
                }
            }
            else {
                System.out.println("Please select a valid colour of Red, Black, or Green\n");
            }
        }
        return finalWin;
    }
}