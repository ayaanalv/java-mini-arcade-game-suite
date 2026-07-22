//GoFish.java
import java.util.*;

public class GoFish {

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static boolean playGoFish(String username) {
        System.out.println("Welcome to Go Fish, " + username + ".");
        System.out.print("Start Go Fish? (Y = yes, N = no) ");
        String roll = sc.nextLine().trim().toLowerCase();
        boolean finalWin = false;

        while(roll.equals("y")) {
            ArrayList<String> pMtch = new ArrayList<>();
            ArrayList<String> cMtch = new ArrayList<>();

            DeckResult deckResult = initializeDeck();
            ArrayList<String> deck = deckResult.deck;
            ArrayList<String> ranks = deckResult.ranks;

            // deal
            ArrayList<String> pHand = new ArrayList<>(deck.subList(0,5));
            ArrayList<String> cHand = new ArrayList<>(deck.subList(5,10));
            deck = new ArrayList<>(deck.subList(10, deck.size()));

            addingSpace();
            welcomeMessage();

            boolean run = true;
            while(deck.size() >= 2 && run) {
                if(pHand.size() < 1 || cHand.size() < 1) {
                    run = false;
                    break;
                }
                addingSpace();

                // player hand checks
                pHand = checkHands(pHand, pMtch);
                // player turn
                TurnResult result1 = playTurn(pHand, cHand, deck, pMtch, ranks);
                pHand = result1.playerHand;
                cHand = result1.computerHand;
                deck = result1.deck;

                // re-check
                pHand = checkHands(pHand, pMtch);

                if(pHand.size() < 1 || cHand.size() < 1) {
                    run = false;
                    break;
                }
                addingSpace();

                // computer
                cHand = checkHands(cHand, cMtch);
                TurnResult result2 = compTurn(pHand, cHand, deck, cMtch);
                pHand = result2.playerHand;
                cHand = result2.computerHand;
                deck = result2.deck;
                cHand = checkHands(cHand, cMtch);
            }

            // end game
            System.out.println("\n GAME OVER");
            System.out.println("\nComputer has " + cMtch.size() + " matches");
            System.out.println("You have " + pMtch.size() + " matches");

            if(cMtch.size() != pMtch.size()) {
                if(cMtch.size() > pMtch.size()) {
                    System.out.println("COMPUTER HAS WON");
                } else {
                    System.out.println("YOU HAVE WON");
                    finalWin = true;
                }
            } else {
                System.out.println("THE GAME IS A TIE");
            }

            System.out.print("Start Go Fish? (Y = yes, N = no) ");
            roll = sc.nextLine().trim().toLowerCase();
        }
        return finalWin;
    }


    static class DeckResult {
        ArrayList<String> deck;
        ArrayList<String> ranks;
        public DeckResult(ArrayList<String> deck, ArrayList<String> ranks) {
            this.deck = deck;
            this.ranks = ranks;
        }
    }

    private static DeckResult initializeDeck() {
        ArrayList<String> ranks = new ArrayList<>(Arrays.asList(
            "2","3","4","5","6","7","8","9","10","Jack",
            "Queen","King","Ace"
        ));
        ArrayList<String> deck = new ArrayList<>();
        // shuffle ranks
        Collections.shuffle(ranks);
        // 4 of each rank
        for(int x=0; x<4; x++){
            for(String r: ranks){
                deck.add(r);
            }
        }
        Collections.shuffle(deck);
        return new DeckResult(deck, ranks);
    }

    private static ArrayList<String> checkHands(ArrayList<String> hand, ArrayList<String> match) {
        // tries to find pairs in hand
        // for each rank, if count >=2 remove them from hand
        HashMap<String,Integer> freq = new HashMap<>();
        for(String card: hand) {
            freq.put(card, freq.getOrDefault(card,0)+1);
        }
        // for each rank that has >=2
        for(String rank: freq.keySet()){
            if(freq.get(rank) >= 2){
                // remove 2 of them from hand
                int removeCount = 2; // remove exactly 2
                while(removeCount>0){
                    hand.remove(rank);
                    removeCount--;
                }
                match.add(rank);
            }
        }
        return hand;
    }

    // replicate "play_turn"
    static class TurnResult {
        ArrayList<String> playerHand;
        ArrayList<String> computerHand;
        ArrayList<String> deck;
        public TurnResult(ArrayList<String> p, ArrayList<String> c, ArrayList<String> d) {
            playerHand = p; computerHand = c; deck = d;
        }
    }

    private static TurnResult playTurn(ArrayList<String> hand, ArrayList<String> computerHand,
                                       ArrayList<String> deck, ArrayList<String> match,
                                       ArrayList<String> ranks) {

        if(hand.size() > 0) {
            playerMessage();
            System.out.println("Matching Cards: ");
            System.out.println(match);
            try{Thread.sleep(1000);}catch(Exception e){}
            String rank_to_ask = getInput(hand, ranks);

            if(!computerHand.contains(rank_to_ask)) {
                System.out.println("\nGo Fish! You draw a card from the deck. ");
                if(!deck.isEmpty()) {
                    String drawnCard = deck.remove(0);
                    hand.add(drawnCard);
                }
            } else {
                System.out.println("\nThe computer has " + rank_to_ask);
                match.add(rank_to_ask);
                computerHand.remove(rank_to_ask);
                hand.remove(rank_to_ask);
            }

        } else {
            System.out.println("No cards in hand");
        }
        return new TurnResult(hand, computerHand, deck);
    }

    private static String getInput(ArrayList<String> hand, ArrayList<String> ranks) {
        System.out.println("\nYour current hand:");
        System.out.println(hand);
        while(true) {
            System.out.print("\nAsk the computer for which rank? (e.g: 7 or King): ");
            String rank_to_ask = sc.nextLine().trim();
            if(ranks.contains(rank_to_ask)) {
                if(hand.contains(rank_to_ask)) {
                    return rank_to_ask;
                } else {
                    System.out.println("You don't have that card");
                }
            } else {
                System.out.println("Invalid Option");
            }
        }
    }

    // replicate "comp_turn"
    private static TurnResult compTurn(ArrayList<String> playerHand, ArrayList<String> hand,
                                       ArrayList<String> deck, ArrayList<String> match) {
        if(hand.size() > 0) {
            int num = rand.nextInt(hand.size());
            String rank_to_ask = hand.get(num);

            computerMessage();
            try{Thread.sleep(1000);}catch(Exception e){}
            System.out.println("Matching Cards: ");
            System.out.println(match);

            System.out.println("\nThe computer has asked if you have " + rank_to_ask);

            if(!playerHand.contains(rank_to_ask)) {
                System.out.println("\nComputer went to fish. It has picked another card");
                if(!deck.isEmpty()) {
                    String drawn = deck.remove(0);
                    hand.add(drawn);
                }
            } else {
                System.out.println("\n You have " + rank_to_ask);
                match.add(rank_to_ask);
                hand.remove(rank_to_ask);
                playerHand.remove(rank_to_ask);
            }
        } else {
            System.out.println("\nComputer has no cards to play.");
        }
        return new TurnResult(playerHand, hand, deck);
    }

    // fancy text
    private static void welcomeMessage(){
        System.out.println("░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗");
        System.out.println("░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝");
        System.out.println("░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░");
        System.out.println("░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗");
        System.out.println("░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝");
        System.out.println();
    }

    private static void playerMessage(){
        System.out.println("██╗░░░██╗░█████╗░██╗░░░██╗██████╗░");
        System.out.println("╚██╗░██╔╝██╔══██╗██║░░░██║██╔══██╗");
        System.out.println("░╚████╔╝░██║░░██║██║░░░██║██████╔╝");
        System.out.println("░░╚██╔╝░░██║░░██║██║░░░██║██╔══██╗");
        System.out.println("░░░██║░░░╚█████╔╝╚██████╔╝██║░░██║");
        System.out.println("░░░╚═╝░░░░╚════╝░░╚═════╝░╚═╝░░╚═╝");
    }

    private static void computerMessage(){
        System.out.println("░█████╗░░█████╗░███╗░░░███╗███╗░░░███╗██████╗░██╗░░░██╗███████╗");
        System.out.println("██╔══██╗██╔══██╗████╗░████║████╗░████║██╔══██╗██║░░░██║╚══██╔══╝");
        System.out.println("██║░░╚═╝██║░░██║██╔████╔██║██╔████╔██║██████╔╝██║░░░██║░░░██║░░░");
        System.out.println("██║░░██╗██║░░██║██║╚██╔╝██║██║╚██╔╝██║██╔═══╝░██║░░░██║░░░██║░░░");
        System.out.println("╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░╚═╝░██║██║░░░░░╚██████╔╝░░░██║░░░");
        System.out.println("░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝╚═╝░░░░░░╚═════╝░░░░╚═╝░░░");
    }

    private static void addingSpace() {
        for(int x=0; x<5; x++){
            System.out.println();
            try{Thread.sleep(250);}catch(Exception e){}
        }
    }
}
