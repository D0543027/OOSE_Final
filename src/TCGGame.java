import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class TCGGame {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    /*
    HumanPlayer p1 = new HumanPlayer();
    comPlayer p2 = new comPlayer();
    TCG tcg = new TCG(p1,p2);
    tcg.playGame();
    */
    
    /*
    HumanPlayer p3 = new HumanPlayer();
    HumanPlayer p4 = new HumanPlayer();
    TCG tcg1 = new TCG(p3,p4);
    tcg1.playGame();
    */
    
    comPlayer p5 = new comPlayer();
    comPlayer p6 = new comPlayer();
    TCG tcg2 = new TCG(p5,p6);
    tcg2.playGame();
  }
}

class TCG extends Game {
  private TCGPlayer p1;
  private TCGPlayer p2;
  private int round;
  
  public TCG(TCGPlayer p1, TCGPlayer p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  void initialize() {
    // TODO Auto-generated method stub
    System.out.println("Trading Card Game Start !!!!");
    round = 1;
  }

  void play() {
    // TODO Auto-generated method stub
    TCGCard card1;
    TCGCard card2;
    
    System.out.println("Round " + Integer.toString(round) + " :");
    p1.drawCard();
    p2.drawCard();
    
    System.out.println("p1 : ");
    p1.ShowHandCard();
    card1 = p1.playCard();
    
    System.out.println("p2 : ");
    p2.ShowHandCard();
    card2 = p2.playCard();
    
    attack(card1, card2);

    System.out.println("p1`hp = " + Integer.toString(p1.hp));
    System.out.println("p2`hp = " + Integer.toString(p2.hp));
    System.out.println("---------------------");

    round = round + 1;
  }

  boolean endGame() {
    // TODO Auto-generated method stub
    if (p1.hp <= 0 || p2.hp <= 0)
      return true;
    if (round > 30)
      return true;
    return false;
  }

  void printResult() {
    // TODO Auto-generated method stub
    if (isDraw(p1,p2))
      System.out.println("Draw!!!");
    else if (p1.hp > p2.hp)
      System.out.println("p1 win!!!");
    else
      System.out.println("p2 win!!!");
  }
  
  private boolean isDraw(TCGPlayer p1, TCGPlayer p2) {
    if(p1.hp < 0 && p2.hp < 0)
      return true;
    else if(p1.hp == p2.hp)
      return true;
    return false;
  }

  void attack(TCGCard c1, TCGCard c2) {
    System.out.print("p1`s card : ");
    c1.CardInfo();
    System.out.print("p2`s card : ");
    c2.CardInfo();
    int dmg1 = c1.hp - c2.att;
    int dmg2 = c2.hp - c1.att;
    if (dmg1 < 0)
      p1.hp = p1.hp + dmg1;
    if (dmg2 < 0)
      p2.hp = p2.hp + dmg2;
  }

}

class HumanPlayer extends TCGPlayer {
  Scanner sc = new Scanner(System.in);
  
  
  public HumanPlayer() {
    super();
  }
  
  public TCGCard playCard() {
    System.out.print("choose the card you want play: ");
    int cardSeq = sc.nextInt();
    TCGCard c = HandCard.get(cardSeq - 1);
    HandCard.remove(cardSeq - 1);
    return c;
  }
  

}

class comPlayer extends TCGPlayer{

  public comPlayer() {
    super();
  }

  public TCGCard playCard() {
    Random rand = new Random();
    int r = rand.nextInt(HandCard.size());
    TCGCard c = HandCard.get(r);
    HandCard.remove(r);
    return c;
    
  }


}

abstract class TCGPlayer {

  int hp;
  ArrayList<TCGCard> deck = new ArrayList<TCGCard>();
  ArrayList<TCGCard> HandCard = new ArrayList<TCGCard>();
  Iterator<TCGCard> diter;

  public TCGPlayer() {
    hp = 20;
    for (int i = 0; i < 30; i++)
      deck.add(new TCGCard()); // initialize deck;

    Collections.shuffle(deck); // shuffle the deck, thought the deck has created randomly

    diter = deck.iterator(); // using iterator

    for (int i = 0; i < 5; i++) { // each player has five cards initially
      if (diter.hasNext())
        HandCard.add(diter.next());
    }

  }

  public void drawCard() {
    if (diter.hasNext()) {
      HandCard.add(diter.next());
    }
  }

  public void ShowHandCard() {
    System.out.print("HandCard : ");
    Iterator hiter = HandCard.iterator();
    while(hiter.hasNext()) {
      System.out.print(hiter.next());
    }
    System.out.println("");
  }

  abstract TCGCard playCard();
}

class TCGCard {
  int hp;
  int att;

  public TCGCard() { // random each card`s hp and attack
    Random rand = new Random();
    hp = rand.nextInt(10) + 1;
    att = rand.nextInt(11);
  }

  public void CardInfo() {
    System.out.println("hp :" + Integer.toString(hp) + " / att : " + Integer.toString(att));
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "(" + Integer.toString(hp) + "," + Integer.toString(att) + ") ";
  }
  
  
}