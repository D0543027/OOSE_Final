import java.util.Random;

public class RPGGame {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    RPG rpg = new RPG();
    rpg.playGame();
  }
}

class RPG extends Game {
  Player p1;
  Player p2;
  private int round;
  
  void attack(Card c1, Card c2) {
    int dmg1 = c1.hp - c2.att;
    int dmg2 = c2.hp - c1.att;
    if (dmg1 < 0)
      p1.hp = p1.hp + dmg1;
    if (dmg2 < 0)
      p2.hp = p2.hp + dmg2;
  }

  @Override
  void initialize() {
    // TODO Auto-generated method stub
    p1 = new Player();
    p2 = new Player();
    round = 1;
  }

  @Override
  void play() {
    // TODO Auto-generated method stub
    Card card1;
    Card card2;
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
    if (round > 10)
      return true;
    return false;
  }

  @Override
  void printResult() {
    // TODO Auto-generated method stub
    if (p1.hp > p2.hp)
      System.out.println("p1 win!!!");
    else 
      System.out.println("p2 win!!!");
  }

}

class Player {
  int hp;
  Card[] handCard = new Card[10];
  int count;

  public Player() {
    hp = 20;
    for (int i = 0; i < 10; i++)
      handCard[i] = new Card();
    count = 0;
  }

  public Card playCard() {
    Card c = handCard[count];
    count = count + 1;
    c.CardInfo();
    return c;
  }
}

class Card {
  int hp;
  int att;

  public Card() {
    Random rand = new Random();
    hp = rand.nextInt(10) + 1;
    att = rand.nextInt(10) + 1;
  }

  public void CardInfo() {
    System.out.println("hp :" + Integer.toString(hp) + " / att : " + Integer.toString(att));
  }
}
