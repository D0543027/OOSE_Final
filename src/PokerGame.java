import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PokerGame {

  public static void main(String[] args) {
    PokerPlayer p1 = new PokerPlayer();
    PokerPlayer p2 = new PokerPlayer();
    PokerPlayer p3 = new PokerPlayer();
    BlackJack bjGame = new BlackJack();
    PokerPlayer[] players = {p1,p2,p3};
    bjGame.addPlayers(players);
    bjGame.playGame();

  }
}

class BlackJack extends Game {
  PokerPlayer[] players;
  int turn = 1;
  int people = 0;
  private Scanner sc;
  boolean end = false;
  
  Deck deck = Deck.getInstance();  //Singleton
  
  void addPlayers(PokerPlayer[] p) {
    this.players = p;
  }
  
  @Override
  void initialize() {
    // TODO Auto-generated method stub
    deck.cutcards();
    for (PokerPlayer p : players ) {
      p.addCard(deck.deal());
      people++;
    }
    System.out.println("Game Start !  Player 1 First \n");
  }

  @Override
  void play() {
    // TODO Auto-generated method stub
    sc = new Scanner(System.in);
    System.out.println("Player " + Integer.toString(turn) + "'s Trun");
    showHandCards(players[turn-1]);
    askCard(players[turn-1]);
    
    System.out.println("------------------------------------");
  }

  @Override
  boolean endGame() {
    // TODO Auto-generated method stub
    if (turn > people)
      return true;
    if (end == true)
      return true;
    return false;
  }

  @Override
  void printResult() {
    // TODO Auto-generated method stub
    System.out.println("------------------------------------");
    System.out.println("The Game is over !");
    
    int temp = 1;
    int winner = 0;
    int point = 0;
    for (PokerPlayer p : players ) {
      System.out.print("Player " + Integer.toString(temp) + " 'cards : ");
      p.showCards();
      System.out.println("Player " + Integer.toString(temp) + " 'points : " + Integer.toString(p.countPoint()));
      if(p.countPoint() <= 21 && p.countPoint() > point) {
        winner = temp;
        point = p.countPoint();
      }
      temp++;
    }
    System.out.println("The winner is Player " + Integer.toString(winner) + " ! ");
      
  }

  void showHandCards(PokerPlayer p) {
    System.out.printf("Your cards are : ");
    p.showCards();
    System.out.println("The points you got : " + p.countPoint());
  }

  void askCard(PokerPlayer p) {
    System.out.println("Do you want to draw another card ? [Y/N] ");
    String Sc = sc.next();
    if (Sc.equals("Y")) {
      PokerCard c = deck.deal();
      p.addCard(c);
      System.out.println("The card you drawed was : " + c);
    } else if (Sc.equals("N")) {
      if(turn >= people) end = true;
      else turn++;
    }
    System.out.println("The points you got : " + p.countPoint());
    if (p.countPoint() > 21) {
      System.out.println("Your points are bigger than 21 !!!!!");
      turn++;
    }
  }

}

// -------------------------------
class PokerPlayer {
  ArrayList<PokerCard> handCard = new ArrayList<PokerCard>();
  public PokerPlayer() {

  }
  
  public void showCards() {
    for(int i = 0; i < handCard.size(); i++) {
      System.out.print(handCard.get(i) + " ");
    }
    System.out.println("");
  }
  
  public int countPoint() {
    int sum = 0;
    for(int i = 0; i < handCard.size(); i++) {
      sum = sum + handCard.get(i).getPoint();
    }
    return sum;
  }
  
  public void addCard(PokerCard pCard) {
    handCard.add(pCard);
  }
}

class Deck{
  private static Deck uniqueInstance;
  private PokerCard[] pCard = new PokerCard[52];
  private int index;
  
  public static Deck getInstance() {
    if(uniqueInstance == null) {
      uniqueInstance = new Deck();
    }
    return uniqueInstance;
  }
  private Deck() {
    index = 0;
    String[] type = { "Spades", "Clubs", "Diamonds", "Hearts" };
    int count = 0;
    for (String s : type) {
      for (int n = 2; n <= 10; n++) {
        pCard[count++] = new PokerCard(s, Integer.toString(n));
      }
      pCard[count++] = new PokerCard(s,"A");
      pCard[count++] = new PokerCard(s,"J");
      pCard[count++] = new PokerCard(s,"Q");
      pCard[count++] = new PokerCard(s,"K");
    }
  }
  
  public void cutcards() {
    Random rand = new Random();
    for (int i = 0; i < 52; i++) {
      int n = rand.nextInt(52);
      PokerCard temp;
      temp = pCard[i];
      pCard[i] = pCard[n];
      pCard[n] = temp;
    }
  }

  public PokerCard deal() {
    PokerCard c = pCard[index];
    index++;
    return c;
  }
  
}

// -------------------------------
class PokerCard {
  String type;
  String number;

  public PokerCard(String type, String number) {
    this.type = type;
    this.number = number;
  }
  
  public String toString() {
    return type+number;
  }
  
  public int getPoint() {
    if (number.equals("A")) {
      return 1;
    } else if (number.equals("J") || number.equals("Q") || number.equals("K")
        || number.equals("10")) {
      return 10;
    } else {
      return Integer.parseInt(number);
    }
  }
}
