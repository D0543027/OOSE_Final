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
  
  PokerCard pcard = PokerCard.getInstance();
  
  void addPlayers(PokerPlayer[] p) {
    this.players = p;
  }
  
  @Override
  void initialize() {
    // TODO Auto-generated method stub
    pcard = new PokerCard();
    pcard.cutcards();
    for (PokerPlayer p : players ) {
      p.card[p.counts++] = pcard.deal();
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
    for (PokerPlayer p : players ) {
      if (p.countPoint() > 21)
        return true;
    }
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
      System.out.printf("Player " + Integer.toString(temp) + " 'cards : ");
      p.showCards();
      if(p.countPoint() < 21 && p.countPoint() > point) {
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
      p.card[p.counts++] = pcard.deal();
      System.out.println("The card you drawed was : " + p.card[p.counts - 1]);
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
  String[] card = new String[6];
  int counts = 0;
  PokerCard c = new PokerCard();

  int countPoint() {
    int points = 0;
    for (int i = 0; i < counts; i++) {
      points += c.CardCount(card[i]);
    }
    return points;
  }

  void showCards() {
    for (int i = 0; i < counts; i++) {
      System.out.printf(card[i] + " ");
    }
    System.out.printf("\n");
  }

}

// -------------------------------
class PokerCard {

  int index = 0; // 發牌索引下標
  String[] card = { "紅桃A", "紅桃2", "紅桃3", "紅桃4", "紅桃5", "紅桃6", "紅桃7", "紅桃8", "紅桃9", "紅桃10", "紅桃J", "紅桃Q", "紅桃K", "黑桃A",
      "黑桃2", "黑桃3", "黑桃4", "黑桃5", "黑桃6", "黑桃7", "黑桃8", "黑桃9", "黑桃10", "黑桃J", "黑桃Q", "黑桃K", "梅花A", "梅花2", "梅花3", "梅花4",
      "梅花5", "梅花6", "梅花7", "梅花8", "梅花9", "梅花10", "梅花J", "梅花Q", "梅花K", "方塊A", "方塊2", "方塊3", "方塊4", "方塊5", "方塊6", "方塊7",
      "方塊8", "方塊9", "方塊10", "方塊J", "方塊Q", "方塊K", };

  private static PokerCard uniqueInstance;
  
  public static PokerCard getInstance() {
    if(uniqueInstance == null) {
      uniqueInstance = new PokerCard();
    }
    
    return uniqueInstance;
  }
  
  // 洗牌，打亂牌的順序
  public void cutcards() {

    Random rand = new Random();
    for (int i = 0; i < 52; i++) {
      int n = rand.nextInt(52);
      String temp;
      temp = card[n];
      card[n] = card[i];
      card[i] = temp;
    }
  }

  // 發牌,按順序發牌，從下標0開始
  public String deal() {
    String c = card[index];
    index++;
    return c;
  }
  
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
