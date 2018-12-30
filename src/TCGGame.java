import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class TCGGame {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TCG tcg = new TCG();
    tcg.playGame();
  }
}

class TCG extends Game {
  private TCGPlayer p1;
  private TCGPlayer p2;
  private int round;

  @Override
  void initialize() {
    // TODO Auto-generated method stub
    p1 = new TCGPlayer();
    p2 = new TCGPlayer();
    round = 1;
  }

  @Override
  void play() {
    // TODO Auto-generated method stub
    TCGCard card1;
    TCGCard card2;
    System.out.println("Round " + Integer.toString(round) + " :");
    System.out.print("p1`s card : ");
    card1 = p1.playCard();
    System.out.print("p2`s card : ");
    card2 = p2.playCard();

    attack(card1, card2);

    System.out.println("p1`hp = " + Integer.toString(p1.hp));
    System.out.println("p2`hp = " + Integer.toString(p2.hp));
    System.out.println("---------------------");

    round = round + 1;
  }

  @Override
  boolean endGame() {
    // TODO Auto-generated method stub
    if (p1.hp <= 0 || p2.hp <= 0)
      return true;
    if (round > 30)
      return true;
    return false;
  }

  @Override
  void printResult() {
    // TODO Auto-generated method stub
    if (p1.hp < 0 && p2.hp < 0)
      System.out.println("Draw!!!");
    else if (p1.hp > p2.hp)
      System.out.println("p1 win!!!");
    else
      System.out.println("p2 win!!!");

  }

  void attack(TCGCard c1, TCGCard c2) {
    int dmg1 = c1.hp - c2.att;
    int dmg2 = c2.hp - c1.att;
    if (dmg1 < 0)
      p1.hp = p1.hp + dmg1;
    if (dmg2 < 0)
      p2.hp = p2.hp + dmg2;
  }

}

class TCGPlayer {

  int hp;
  ArrayList<TCGCard> deck = new ArrayList<TCGCard>();

  Iterator<TCGCard> diter;

  public TCGPlayer() {
    hp = 20;
    for (int i = 0; i < 30; i++)
      deck.add(new TCGCard()); // initialize deck;

    Collections.shuffle(deck); // shuffle the deck, thought the deck has created randomly

    diter = deck.iterator(); // using iterator

  }

  public TCGCard playCard() {
    TCGCard c = null;
    if (diter.hasNext()) { // Iterator
      c = diter.next(); // play the top card from deck
      c.CardInfo();
    }
    return c;
  }

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
}