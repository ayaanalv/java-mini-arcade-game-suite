import java.util.*;

public class FishingMinigame {
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    private static int countFish = 0;
    private static double catchWeight = 0.0;
    private static int[] fishCounts = new int[15];

    public static boolean playFishingMinigame(String username) {
        System.out.println("Welcome to Fishing, " + username + ".");
        System.out.println("There are 2 gamemodes available to play.");
        System.out.println();
        System.out.println("Competition Mode:");
        System.out.println("Compete in tournaments against other fishers!");
        System.out.println();
        System.out.println("Collection Mode:");
        System.out.println("Fish to your hearts content!");
        System.out.println();

        boolean won = false;

        while (true) {
            System.out.print("Which gamemode would you like to play? (Competition/Collection): ");
            String choice = sc.nextLine().trim().toLowerCase();
            System.out.println();

            if (choice.equals("competition")) {
                boolean result = competitionMode();
                if (result) {
                    won = true;
                }
            } else if (choice.equals("collection")) {
                collectionMode();
            } else {
                System.out.println("Please select a valid gamemode.");
                System.out.println();
            }
        }
    }

    private static boolean competitionMode() {
        System.out.println("You have entered The Great Canadian Fishing Competition!");
        System.out.println("You will travel to islands and compete by catch weight!");
        System.out.print("Are you ready to travel to the first island? Yes/No?: ");
        String travel1 = sc.nextLine().trim().toLowerCase();
        System.out.println();

        if (travel1.equals("no")) {
            System.out.println("OK, return when you're ready!");
            System.out.println();
            return false;
        }

        if (travel1.equals("yes")) {
            boolean competing = true;
            while (competing) {
                System.out.println("You arrive at the Island of Maui with 10 bait.");
                System.out.println("There are 10 other competitors...");
                System.out.println("Heavier the catch, the closer you are to winning!");
                System.out.println();

                int bait = 10;
                countFish = 0;
                catchWeight = 0.0;
                Arrays.fill(fishCounts, 0);

                while (true) {
                    if (bait == 0) {
                        System.out.println();
                        System.out.print("Would you like to review your collection? Yes/No: ");
                        String yesorno = sc.nextLine().trim().toLowerCase();
                        System.out.println();

                        if (yesorno.equals("yes")) {
                            collectionFun();
                        }

                        ArrayList<String> competitorNames = new ArrayList<>();
                        ArrayList<Double> competitorWeights = new ArrayList<>();
                        for (int i = 0; i < 9; i++) {
                            competitorNames.add("Competitor" + (i + 1));
                            competitorWeights.add(Math.round((rand.nextDouble() * 499 + 1) * 10.0) / 10.0);
                        }
                        competitorNames.add("You");
                        competitorWeights.add(catchWeight);

                        class Fisher implements Comparable<Fisher> {
                            String name;
                            double weight;
                            Fisher(String n, double w) { name = n; weight = w; }
                            public int compareTo(Fisher o) {
                                return Double.compare(o.weight, this.weight);
                            }
                        }
                        ArrayList<Fisher> fishers = new ArrayList<>();
                        for (int i = 0; i < competitorNames.size(); i++) {
                            fishers.add(new Fisher(competitorNames.get(i), competitorWeights.get(i)));
                        }
                        Collections.sort(fishers);

                        int indexOfYou = 0;
                        for (int i = 0; i < fishers.size(); i++) {
                            if (fishers.get(i).name.equals("You")) {
                                indexOfYou = i;
                                break;
                            }
                        }
                        for (int i = 0; i < fishers.size(); i++) {
                            System.out.println((i + 1) + ". " + fishers.get(i).name + " => " + fishers.get(i).weight + " kg");
                        }

                        int yourRank = indexOfYou + 1;
                        if (yourRank == 1) {
                            System.out.println("\nCongratulations! You got 1st Place!");
                            System.out.println("You beat fishing!");
                            return true;
                        } else {
                            System.out.println("\nYou placed " + yourRank + "!");
                            System.out.println("\nYou didn't win...");
                            System.out.println("But you still did good!");
                            System.out.println("Nice Try! You fished like a pro!");
                            System.out.print("\nWould you like to play again? Yes/No: ");
                            String travel2 = sc.nextLine().trim().toLowerCase();
                            if (travel2.equals("yes")) {
                                break;
                            } else {
                                System.out.println("\nAfter competing...");
                                System.out.println("You return back home and sleep");
                                System.out.println("Excited for tomorrow's fishing!");
                                System.out.println();
                                competing = false;
                                break;
                            }
                        }
                    } else {
                        System.out.println("Would you like to:");
                        System.out.println("View your Collection or Fish?");
                        System.out.println("You have " + bait + " bait left!");
                        System.out.println("Collection/Fish\n");
                        System.out.print("Decision: ");
                        String fish_or_col = sc.nextLine().trim().toLowerCase();
                        System.out.println();

                        if (fish_or_col.equals("collection")) {
                            collectionFun();
                        } else if (fish_or_col.equals("fish")) {
                            bait--;
                            System.out.println("You cast your line and wait...");
                            int countdown = rand.nextInt(13) + 3;
                            for (int x = 0; x < countdown; x++) {
                                System.out.println();
                                try { Thread.sleep(250); } catch (Exception e) {}
                            }
                            System.out.println("A fish bit the line!");
                            System.out.println();
                            fishSelection();
                        } else {
                            System.out.println("Please select a valid option!");
                            System.out.println();
                        }
                    }
                }
            }
        }
        return false;
    }

    private static void collectionMode() {
        boolean fishing = true;
        System.out.println("You are fishing on your private yacht.");
        System.out.println();

        while (fishing) {
            System.out.println("Would you like to:");
            System.out.println("View your Collection, Fish, or go Home?");
            System.out.println("Collection/Fish/Home\n");
            System.out.print("Decision: ");
            String fish_or_col = sc.nextLine().trim().toLowerCase();
            System.out.println();

            if (fish_or_col.equals("collection")) {
                collectionFun();
            } else if (fish_or_col.equals("fish")) {
                System.out.println("You cast your line and wait...");
                int countdown = rand.nextInt(13) + 3;
                for (int x = 0; x < countdown; x++) {
                    System.out.println();
                    try { Thread.sleep(250); } catch (Exception e) {}
                }
                System.out.println("A fish bit the line!");
                System.out.println();
                fishSelection();
            } else if (fish_or_col.equals("home")) {
                System.out.println("After a long day out...");
                System.out.println("You return back home and sleep");
                System.out.println("Excited for tomorrow's fishing!");
                System.out.println();
                fishing = false;
            } else {
                System.out.println("Please select a valid option!");
                System.out.println();
            }
        }
    }

    private static void collectionFun() {
        System.out.println();
        System.out.println("You have " + countFish + " fish in your collection.");
        System.out.println();
        System.out.println("Weighing a total of " + catchWeight + " kg!");
        System.out.println();
        System.out.println("The fish you have in collection are:");
        System.out.println("Pike: " + fishCounts[0]);
        System.out.println("Trout: " + fishCounts[1]);
        System.out.println("Crappie: " + fishCounts[2]);
        System.out.println("Catfish: " + fishCounts[3]);
        System.out.println("Perch: " + fishCounts[4]);
        System.out.println("Bass: " + fishCounts[5]);
        System.out.println("Eel: " + fishCounts[6]);
        System.out.println("Barracuda: " + fishCounts[7]);
        System.out.println("Squid: " + fishCounts[8]);
        System.out.println("Yellowfin Tuna: " + fishCounts[9]);
        System.out.println("Blue Marlin: " + fishCounts[10]);
        System.out.println("Sturgeon: " + fishCounts[11]);
        System.out.println("Mako Shark: " + fishCounts[12]);
        System.out.println("Bluefin Tuna: " + fishCounts[13]);
        System.out.println();
    }

    private static void fishSelection() {
        int fishRarity = rand.nextInt(10) + 1;
        String fishName = "";

        if (fishRarity >= 1 && fishRarity <= 7) {
            System.out.println("A common fish has bitten the line!");
            String[] choices = {"Pike", "Trout", "Crappie"};
            fishName = choices[rand.nextInt(choices.length)];
            double weight = randomDouble(0.5, 2.5);
            catchFish(fishName, weight, "Common", 0.6, fishIndexOf(fishName));
        } else if (fishRarity > 7 && fishRarity <= 9) {
            System.out.println("A rare fish has bitten the line!");
            String[] choices = {"Catfish", "Perch", "Bass"};
            fishName = choices[rand.nextInt(choices.length)];
            double weight = randomDouble(1.1, 8.0);
            catchFish(fishName, weight, "Uncommon", 0.5, fishIndexOf(fishName));
        } else {
            System.out.println("A rare fish has bitten the line!");
            String[] choices = {"Eel", "Barracuda", "Squid"};
            fishName = choices[rand.nextInt(choices.length)];
            double weight = randomDouble(5.5, 25.0);
            catchFish(fishName, weight, "Rare", 0.4, fishIndexOf(fishName));
        }
    }

    private static void catchFish(String fishName, double fishWeight, String rarity, double catchSpeed, int fishIndex) {
        double roundedweight = Math.round(fishWeight * 10.0) / 10.0;
        System.out.println("QUICK, REEL IT IN!");
        System.out.print("Hit the enter key to reel!: ");
        long start = System.currentTimeMillis();
        sc.nextLine();
        long end = System.currentTimeMillis();
        double timeDifference = (end - start) / 1000.0;
        System.out.println();

        if(timeDifference <= 0.08){
            System.out.println("The line snapped!");
            System.out.println("You reeled in too fast and lost the fish!");
            System.out.println("You must be patient while fishing :)\n");
        } else if(timeDifference < catchSpeed){
            System.out.println("Congrats! You caught a " + fishName + "!");
            System.out.println("It is a " + rarity + " fish and weighs " + roundedweight + " kg!");
            System.out.print("Would you like to release or store it? (Store/Release): ");
            String catch_rel = sc.nextLine().trim().toLowerCase();
            System.out.println();

            if(catch_rel.equals("store")){
                fishCounts[fishIndex] ++;
                countFish++;
                catchWeight += roundedweight;
                catchWeight = Math.round(catchWeight * 10.0) / 10.0;
                System.out.println("The " + fishName + " has been stored.\n");
                System.out.println("You now have " + catchWeight + " kg in fish!\n");
            } else if (catch_rel.equals("release")) {
                System.out.println("The " + fishName + " was released.\n");
            } else {
                System.out.println("Invalid Input! The " + fishName + " was released.\n");
            }
        } else {
            System.out.println("You missed the fish! Maybe next time!\n");
        }
    }

    // small helper to find fish index
    private static int fishIndexOf(String fishName) {
        // 0->Pike,1->Trout,2->Crappie,3->Catfish,4->Perch,5->Bass,6->Eel,7->Barracuda,8->Squid,
        // 9->YellowfinTuna,10->BlueMarlin, etc.
        switch (fishName.toLowerCase()) {
            case "pike":
                return 0;
            case "trout":
                return 1;
            case "crappie":
                return 2;
            case "catfish":
                return 3;
            case "perch":
                return 4;
            case "bass":
                return 5;
            case "eel":
                return 6;
            case "barracuda":
                return 7;
            case "squid":
                return 8;
            case "yellowfin tuna":
                return 9;
            case "blue marlin":
                return 10;
            case "sturgeon":
                return 11;
            case "mako shark":
                return 12;
            case "bluefin tuna":
                return 13;
            default:
                return 0; // fallback
        }
    }

    private static double randomDouble(double min, double max) {
        return rand.nextDouble() * (max - min) + min;
    }
}
