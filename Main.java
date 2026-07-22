import java.util.*;    // for Scanner, Random, ArrayList, etc.


public class Main {

    /********************************************************************
     *  PROJECT-WIDE FIELDS & MAIN MENU
     ********************************************************************/
    private static Scanner sc = new Scanner(System.in);

    // Keep track of each game’s “win” state so we can print (Complete/Incomplete)
    private static boolean winUnscramble = false;
    private static boolean winTrivia = false;
    private static boolean winWildWest = false;
    private static boolean winGoFish = false;
    private static boolean winRoulette = false;
    private static boolean winBalloonPop = false;
    private static boolean winFishing = false;

    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.print("What is your desired username? ");
        String username = sc.nextLine();
        System.out.println();

        while(true) {
            // Print main menu
            System.out.println("Which game would you like to play?");
            System.out.println();
            System.out.print("Unscrambler ");
            if (winUnscramble) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Trivia ");
            if (winTrivia) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Wild West ");
            if (winWildWest) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Go Fish ");
            if (winGoFish) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Roulette ");
            if (winRoulette) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Balloon Pop ");
            if (winBalloonPop) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.print("Fishing ");
            if (winFishing) System.out.println("(Complete)");
            else System.out.println("(Incomplete)");
            System.out.println();

            System.out.println("Exit");
            System.out.println();

            System.out.print("Selection: ");
            String selection = sc.nextLine().trim().toLowerCase();
            System.out.println();

            switch(selection) {
                case "unscrambler":
                    // run unscrambler and store the returned boolean
                    winUnscramble = Unscrambler.playUnscrambler(username, winUnscramble);
                    break;
                case "trivia":
                    winTrivia = Trivia.playTrivia(username);
                    break;
                case "wild west":
                    winWildWest = WildWest.playWildWest(username);
                    break;
                case "go fish":
                    winGoFish = GoFish.playGoFish(username);
                    break;
                case "roulette":
                    winRoulette = Roulette.playRoulette(username);
                    break;
                case "balloon pop":
                    winBalloonPop = BalloonPop.playBalloonPop(username);
                    break;
                case "fishing":
                    winFishing = FishingMinigame.playFishingMinigame(username);
                    break;
                case "exit":
                    System.out.println("Goodbye.");
                    return;   // end the program entirely
                default:
                    System.out.println("Invalid input.");
                    break;
            }

            // If all games have been won, show this final msg
            if (winUnscramble && winTrivia && winWildWest && winGoFish
                && winRoulette && winFishing && winBalloonPop) {
                System.out.println("You have won all of the games!");
            } else {
                System.out.println("Continue playing to win all of the trophies!");
            }
            System.out.println();
        }
    }
}