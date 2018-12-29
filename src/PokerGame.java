import java.util.Random;
import java.util.Scanner;

public class PokerGame {

  public static void main(String[] args) {
    PokerPlayer p1 = new PokerPlayer();
    PokerPlayer p2 = new PokerPlayer();
    BlackJack bjGame = new BlackJack();
    bjGame.initialize();
    bjGame.playGame();
  }
}

class BlackJack extends Game {
  PokerPlayer p1;
  PokerPlayer p2;
  PokerCard pcard;
  int turn = 1;
  private Scanner sc;

  @Override
  void initialize() {
    // TODO Auto-generated method stub
    p1 = new PokerPlayer();
    p2 = new PokerPlayer();
    pcard = new PokerCard();
    pcard.cutcards();
    p1.card[p1.counts++] = pcard.deal();
    p2.card[p2.counts++] = pcard.deal();
  }

  @Override
  void play() {
    // TODO Auto-generated method stub
    sc = new Scanner(System.in);
    if (turn == 1) { // player 1
      System.out.println("Player 1 Trun");
      System.out.printf("Your cards are : ");
      p1.showCards();
      if (p1.counts == 5) {
        System.out.println("Your handcards is full. Player 2 turn!");
        turn = 2;
      } else {

        System.out.println("Do you want to draw another card ? [Y/N] ");
        String Sc = sc.next();
        if (Sc.equals("Y")) {
          p1.card[p1.counts++] = pcard.deal();
          System.out.println("The card you drawed was : " + p1.card[p1.counts - 1]);
        } else if (Sc.equals("N"))
          turn = 2;
      }
      p1.countPoint();
    } else if (turn == 2) { // player 2
      System.out.println("Player 2 Trun");
      System.out.println("Your cards are : ");
      p2.showCards();
      if (p2.counts == 5) {
        //// isn't finished .end game ////------------------------------------------------------------
      } else {
        System.out.println("Do you want to draw another card ? [Y/N] ");

        String Sc = sc.next();
        if (Sc.equals("Y")) {
          p2.card[p2.counts++] = pcard.deal();
          System.out.println("The card you drawed was : " + p2.card[p2.counts - 1]);
        } else if (Sc.equals("N"))
          ; //// isn't finished .end game ////------------------------------------------------------------
      }
      p2.countPoint();
    }
    System.out.println("------------------------------------");
  }

  @Override
  boolean endGame() {
    // TODO Auto-generated method stub
    if (p1.points > 21 || p2.points > 21)
      return true;
    return false;
  }

  @Override
  void printResult() {
    // TODO Auto-generated method stub
    System.out.println("------------------------------------");
    System.out.println("The Game is over !");
    System.out.printf("Player 1'cards = ");
    p1.showCards();
    System.out.printf("Player 2'cards = ");
    p2.showCards();

    if (p1.points > 21) {
      System.out.println("The winner is Player 2 ! ");
    } else if (p2.points > 21) {
      System.out.println("The winner is Player 1 ! ");
    }
  }

}

// -------------------------------
class PokerPlayer {
  String[] card = new String[6];
  int points = 0;
  int counts = 0;

  CardCount cc = new CardCount();

  void countPoint() {
    for (int i = 0; i < counts; i++) {
      points += cc.CardCount(card[i]);
    }
  }

  void showCards() {
    for (int i = 0; i < counts; i++) {
      System.out.printf(card[i] + " ");
    }
    System.out.println(points);
    System.out.printf("\n");
  }

}

// -------------------------------
class PokerCard {

  int index = 0; // �o�P���ޤU��
  String[] card = { "����A", "����2", "����3", "����4", "����5", "����6", "����7", "����8", "����9", "����10", "����J", "����Q", "����K", "�®�A",
      "�®�2", "�®�3", "�®�4", "�®�5", "�®�6", "�®�7", "�®�8", "�®�9", "�®�10", "�®�J", "�®�Q", "�®�K", "����A", "����2", "����3", "����4",
      "����5", "����6", "����7", "����8", "����9", "����10", "����J", "����Q", "����K", "���A", "���2", "���3", "���4", "���5", "���6", "���7",
      "���8", "���9", "���10", "���J", "���Q", "���K", };

  // �~�P�A���õP������
  public void cutcards() {
    int index = 0;
    Random rand = new Random();
    for (int i = 0; i < 52; i++) {
      int n = rand.nextInt(52);
      String temp;
      temp = card[n];
      card[n] = card[i];
      card[i] = temp;
    }

  }

  // �o�P,�����ǵo�P�A�q�U��0�}�l
  public String deal() {
    String c = card[index];
    index++;
    return c;
  }
}

// --------------------------------
class CardCount {
  
  int CardCount(String num) {
    String word = num.substring(2);
    if (word.equals("A")) {
      return 1;
    } else if (num.substring(2).equals("J") || num.substring(2).equals("Q") || num.substring(2).equals("K")
        || num.substring(2).equals("10")) {
      return 10;
    } else {
      int n = (int) num.substring(2).charAt(0) - 48;
      return n;
    }
  }
}
