//Unscrambler
import java.util.*;

public class Unscrambler {

    private static Scanner sc = new Scanner(System.in);

    // The boolean parameter "winScrambleGame" indicates if user has finished previously
    public static boolean playUnscrambler(String name, boolean winScrambleGame) {
        System.out.println("Welcome to Unscrambler, " + name);

        // Pools of words
        String[] wordListAll = {
            "python","coding","guitar","banana","rocket",
            "sunshine","chocolate","elephant","rainbow","library",
            "computer","giraffe","cupcake","explorer","keyboard",
            "whisper","penguin","butterfly","zeppelin","pineapple",
            "raindrop","firefly","victory","notebook","eleven",
            "dolphin","bluejay","marathon","umbrella","avalanche",
            "sandwich","shimmer","waterfall","symphony","illusion",
            "sapphire","cinnamon","whirlwind","gazelle","octopus",
            "paprika","solitude","captain","hurricane","alligator",
            "jamboree","kangaroo","longitude","whispering","velocity",
            "serenity","umbilical","chocolatey","bountiful",
            "elevator","magnificent","tangerine","adventure",
            "volunteer","harmonious","incredible","chrysalis",
            "discovery","juxtapose","labyrinth","mercurial",
            "overflow","quizzical","tantalize","vagabond",
            "whimsical","xylophone","haphazard","mysterious",
            "quintessential","serendipity","wanderlust","ludicrous",
            "syzygy","onomatopoeia","plethora","belligerent",
            "ephemeral","obfuscate","flabbergast","discombobulate",
            "sesquipedalian","lollygag","perspicacious","ubiquitous"
        };

        String[] wordListEasy = {
            "python","coding","guitar","banana","rocket",
            "sunshine","chocolate","elephant","rainbow","library",
            "computer","giraffe","cupcake","explorer","keyboard",
            "whisper","penguin","butterfly","zeppelin","pineapple",
            "raindrop","firefly","fantastic","notebook","eleven",
            "dolphin","bluejay","marathon","umbrella","avalanche",
            "sandwich"
        };

        String[] wordListMed = {
            "shimmer","waterfall","symphony","illusion",
            "sapphire","cinnamon","whirlwind","gazelle","octopus",
            "paprika","solitude","captain","hurricane","alligator",
            "jamboree","kangaroo","longitude","whispering","velocity",
            "serenity","umbilical","chocolate","bountiful",
            "elevator","magnificent","tangerine","adventure",
            "volunteer","harmonious","incredible","chrystal",
            "discovery"
        };

        String[] wordListHard = {
            "juxtapose","labyrinth","mercurial",
            "overflow","quizzical","tantalize","vagabond",
            "whimsical","xylophone","haphazard","mysterious",
            "quintessential","serendipity","wanderlust","ludicrous",
            "syzygy","onomatopoeia","plethora","belligerent",
            "ephemeral","obfuscate","antidisestablishmentarianism",
            "discombobulate","sesquipedalian","lollygag",
            "perspicacious","ubiquitous"
        };

        // finished = whether user already beat the unscramble for custom lengths
        boolean finished = winScrambleGame;

        Random rand = new Random();
        int hints = 5;

        // main loop
        while(true) {
            int wincount = 0;  // how many correct in that run
            hints = 5;
            String difficulty = "unfinished";

            // If user has finished, let them choose a difficulty
            if(finished) {
                while(true) {
                    System.out.print("Please enter one of these difficulties: Easy, Medium, Hard, & All\nInput: ");
                    difficulty = sc.nextLine().trim().toLowerCase();
                    if(difficulty.equals("easy") || difficulty.equals("medium") ||
                       difficulty.equals("hard") || difficulty.equals("all")) {
                        break;
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
            }

            // If the user has chosen a difficulty:
            if(difficulty.equals("easy")) {
                boolean exitGame = baseGame(wordListEasy, wincount, hints);
                if(exitGame) {
                    break;
                }
            }
            else if(difficulty.equals("medium")) {
                boolean exitGame = baseGame(wordListMed, wincount, hints);
                if(exitGame) {
                    break;
                }
            }
            else if(difficulty.equals("hard")) {
                boolean exitGame = baseGame(wordListHard, wincount, hints);
                if(exitGame) {
                    break;
                }
            }
            else if(difficulty.equals("all")) {
                boolean exitGame = baseGame(wordListAll, wincount, hints);
                if(exitGame) {
                    break;
                }
            }
            else {
                // If user hasn't beaten unscramble yet, they get 10 questions from the big pool
                int length = 10;
                for(int count=0; count<length; count++){
                    System.out.println();
                    System.out.println("Question " + (count+1) + "/10.");
                    String[] scrambled = wordScrambler(wordListAll, rand);
                    String word = scrambled[0];
                    String scrmbWord = scrambled[1];
                    // run the unscramble logic
                    int[] result = runUnscrambleRound(word, scrmbWord, hints);
                    // result[0] = win(1) or lose(0), result[1] = updated hints
                    wincount += result[0];
                    hints = result[1];
                }
                System.out.println("You have answered " + wincount + "/10 questions correctly.");
                if(wincount == 10) {
                    System.out.println("You have won the game!");
                    finished = true;
                    // ask user if they want to continue
                    if(!exitPrompt()) {
                        break;
                    }
                } else {
                    if(!exitPrompt()) {
                        break;
                    }
                }
            }
        }

        return finished;
    }

    // base(...) returning a boolean: if the user wants to exit
    private static boolean baseGame(String[] pool, int wincount, int hints) {
        int length = lengthGet();
        boolean exitGame = false;
        Random rand = new Random();

        for(int count=0; count<length; count++){
            System.out.println();
            System.out.println("Question " + (count+1)+ "/" + length);
            String[] scrambled = wordScrambler(pool, rand);
            String word = scrambled[0];
            String scrmbWord = scrambled[1];
            int[] result = runUnscrambleRound(word, scrmbWord, hints);
            wincount += result[0];
            hints = result[1];
        }
        System.out.println("You have answered " + wincount + "/" + length + " questions correctly.");
        if(wincount == length){
            System.out.println("You have won the game!");
        } else {
            // ask user if they want to exit
            if(!exitPrompt()) {
                exitGame = true;
            }
        }
        return exitGame;
    }

    // replicate "word_scrambler" logic
    private static String[] wordScrambler(String[] mainpool, Random rand) {
        // pick random word
        int index = rand.nextInt(mainpool.length);
        String word = mainpool[index];
        String newWord = word;
        String scrmbWord = "";

        // scramble
        while(newWord.length() > 0) {
            int letterIndex = rand.nextInt(newWord.length());
            char letter = newWord.charAt(letterIndex);
            scrmbWord += letter;
            // remove letter from newWord
            newWord = newWord.substring(0, letterIndex) +
                      ((letterIndex == newWord.length()-1) ? "" : newWord.substring(letterIndex+1));
        }
        return new String[]{word, scrmbWord};
    }

    // replicate "game" function
    private static int[] runUnscrambleRound(String word, String scrmbWord, int hints) {
        System.out.println("The scrambled word is " + scrmbWord);
        System.out.println("You have " + hints + " hints.");
        System.out.print("Unscramble it or type hint: ");
        String input1 = sc.nextLine().trim().toLowerCase();

        int win = 0;
        if(input1.equals(word)) {
            System.out.println("Correct!");
            win = 1;
        }
        else if(input1.equals("hint")) {
            if(hints > 0) {
                System.out.println("The word begins with " + word.charAt(0) +
                                   " and ends with " + word.charAt(word.length()-1));
                hints--;
                System.out.println("You have " + hints + " hints remaining.");
            } else {
                System.out.println("You have no more hints.");
            }
            System.out.print("Unscramble it: ");
            String input2 = sc.nextLine().trim().toLowerCase();
            if(input2.equals(word)) {
                System.out.println("Correct!");
                win = 1;
            } else {
                System.out.println("Incorrect. The word was " + word + ".");
                win = 0;
            }
        }
        else {
            System.out.println("Incorrect. The word was " + word + ".");
        }
        return new int[]{win, hints};
    }

    // replicate "length_get()"
    private static int lengthGet() {
        while(true) {
            try {
                System.out.print("How many questions do you want?(must be below 50) ");
                int length = Integer.parseInt(sc.nextLine());
                if(length < 50) {
                    return length;
                } else {
                    System.out.println("Must be below 50!");
                }
            } catch(Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    // replicate "exit()"
    private static boolean exitPrompt() {
        while(true) {
            System.out.print("Do you wish to continue? Y/N: ");
            String input = sc.nextLine().trim().toLowerCase();
            if(input.equals("y")) {
                return true;
            } else if(input.equals("n")) {
                System.out.println("Goodbye.");
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}
