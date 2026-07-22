import java.util.*;

public class Trivia {

    private static Scanner sc = new Scanner(System.in);


    public static boolean playTrivia(String name) {
        System.out.println("Welcome to Trivia, " + name);
        System.out.println();

        int wincount = 0;

        // main loop
        while(true) {
            boolean singleWin = game();  // one round
            if(singleWin) {
                wincount++;
            }
            System.out.print("Do you wish to continue? Press any key to continue or N: ");
            String cont = sc.nextLine().trim().toLowerCase();
            if(cont.equals("n")) {
                System.out.println("Goodbye.");
                break;
            }
        }
        // if wincount > 0, user has at least 1 success
        return (wincount > 0);
    }

   // show different trivia options
    private static boolean game() {
        System.out.println("Music (1)");
        System.out.println("History (2)");
        System.out.println("Computer Science (3)");
        System.out.println("General Knowledge (4)");
        System.out.println("Science (5)");

        ArrayList<String[]> pool = new ArrayList<>(); // each question is an array

        while(true) {
            System.out.print("Which genre would you like to choose? ");
            String selection = sc.nextLine().trim();
            if(selection.equals("1")) {
                pool = buildMusicPool();
                break;
            } else if(selection.equals("2")) {
                pool = buildHistoryPool();
                break;
            } else if(selection.equals("3")) {
                pool = buildCompSciPool();
                break;
            } else if(selection.equals("4")) {
                pool = buildGKPool();
                break;
            } else if(selection.equals("5")) {
                pool = buildSciencePool();
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        return triviaGame(pool);
    }

    // replicate "trivia_game(pool)"
    private static boolean triviaGame(ArrayList<String[]> pool) {
        // each question is: [0]=Q, [1]=A1, [2]=A2, [3]=A3, [4]=Correct
        int winTotal = 0;
        for(int i=0; i<pool.size(); i++) {
            String[] q = pool.get(i);
            System.out.println("Question " + (i+1) + ". " + q[0]);
            int result = qDisplay(q);
            winTotal += result;
        }
        System.out.println("You have answered " + winTotal + "/5 questions correctly.");
        if(winTotal == 5) {
            System.out.println("You have won the game, congratulations!");
            return true;
        } else {
            System.out.println("You did not win the game.");
            return false;
        }
    }

    // replicate "q_display(q)"
    private static int qDisplay(String[] q) {
        // q: [0]=Q, [1], [2], [3], [4]=correct
        // randomize answers except q[0]
        ArrayList<String> answers = new ArrayList<>();
        answers.add(q[1]);
        answers.add(q[2]);
        answers.add(q[3]);
        answers.add(q[4]);
        // shuffle
        Collections.shuffle(answers);

        System.out.println();
        for(String ans : answers) {
            System.out.println(ans);
        }
        while(true) {
            System.out.println();
            System.out.print("Please enter your selection: ");
            String userinput = sc.nextLine().trim().toLowerCase();
            if(userinput.equalsIgnoreCase(q[1]) || userinput.equalsIgnoreCase(q[2]) ||
               userinput.equalsIgnoreCase(q[3]) || userinput.equalsIgnoreCase(q[4])) {
                // check correctness
                if(userinput.equalsIgnoreCase(q[4])) {
                    System.out.println("Correct!");
                    System.out.println();
                    return 1;
                } else {
                    System.out.println("Incorrect.");
                    System.out.println();
                    return 0;
                }
            } else {
                System.out.println("Invalid Input.");
            }
        }
    }

    // build question pools
    private static ArrayList<String[]> buildMusicPool() {
        ArrayList<String[]> music = new ArrayList<>();
        // each question: Q, wrong, wrong, wrong, correct
        music.add(new String[]{"When was David Bowie's birthday?", "October 9th", "February 20th",
                               "December 13th", "January 8th"});
        music.add(new String[]{"Who is the world's best selling musician of all time?", "Taylor Swift",
                               "Elvis Presley", "Michael Jackson", "The Beatles"});
        music.add(new String[]{"Where is AC/DC originally from?", "USA","Britain","South Africa","Australia"});
        music.add(new String[]{"Which musician has the most sold record of all time?", "Whitney Houston",
                               "Celine Dion","ABBA","Michael Jackson"});
        music.add(new String[]{"How long was the longest guitar solo ever?", "2 hours and 3 minutes",
                               "19 minutes","1 hour and 34 minutes","25 hours"});
        return music;
    }

    private static ArrayList<String[]> buildHistoryPool() {
        ArrayList<String[]> history = new ArrayList<>();
        history.add(new String[]{"In which year did Nazi Germany initiate World War II?",
                                 "1936","1938","1941","1939"});
        history.add(new String[]{"When did the fall of Rome occur?",
                                 "589 AD","211 AD","971 AD","476 AD"});
        history.add(new String[]{"When did the Black Death end?",
                                 "1670","1256","1560","1353"});
        history.add(new String[]{"Which empire was the largest by landmass?",
                                 "Mongol","Roman","Russian","British"});
        history.add(new String[]{"When did the last dodo die?",
                                 "1542","1723","1601","1681"});
        return history;
    }

    private static ArrayList<String[]> buildCompSciPool() {
        ArrayList<String[]> compsci = new ArrayList<>();
        compsci.add(new String[]{"HTML acronym?", "HandyTab Makeup","Hill-Taylor ModeL",
                                 "HighTech Mainline","HyperText Markup"});
        compsci.add(new String[]{"Guido van Rossum's language?", "Java","C++","Python","Python"});
        compsci.add(new String[]{"Binary for 42?", "100101","110110","101001","101010"});
        compsci.add(new String[]{"CPU acronym?", "Common Power Universal","Computer Pentacore Unlocker",
                                 "Capped Pit Unicode","Central Processing Unit"});
        compsci.add(new String[]{"Java function keyword?", "defining","def","define","function"});
        return compsci;
    }

    private static ArrayList<String[]> buildGKPool() {
        ArrayList<String[]> gk = new ArrayList<>();
        gk.add(new String[]{"Capital of France?", "Berlin","Madrid","Paris","Paris"});
        gk.add(new String[]{"Largest ocean?", "Atlantic","Indian","Pacific","Pacific"});
        gk.add(new String[]{"Currency of Japan?", "Yuan","Won","Yin","Yen"});
        gk.add(new String[]{"Longest river?", "Amazon","Yangtze","Mississippi","Nile"});
        gk.add(new String[]{"Number of continents?", "5","6","8","7"});
        return gk;
    }

    private static ArrayList<String[]> buildSciencePool() {
        ArrayList<String[]> sci = new ArrayList<>();
        sci.add(new String[]{"Chemical symbol for gold?", "Ag","Fe","Go","Au"});
        sci.add(new String[]{"What gas do plants absorb from the atmosphere?",
                             "Oxygen","Ozone","Nitrogen","Carbon Dioxide"});
        sci.add(new String[]{"In which galaxy is our solar system located?",
                             "Andromeda","Antennae","Triangulum","Milky Way"});
        sci.add(new String[]{"What is the chemical symbol for water?",
                             "Aq1","CO2","O2","H2O"});
        sci.add(new String[]{"Which planet is known as the Red Planet?",
                             "Mercury","Venus","Jupiter","Mars"});
        return sci;
    }

}
