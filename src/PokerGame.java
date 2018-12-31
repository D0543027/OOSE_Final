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
  PokerCard pcard;
  int turn = 1;
  int people = 0;
  private Scanner sc;
  boolean end = false;

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

  int index = 0; // µoµP¯Á¤Þ¤U¼Ð
  String[] card = { "¬õ®çA", "¬õ®ç2", "¬õ®ç3", "¬õ®ç4", "¬õ®ç5", "¬õ®ç6", "¬õ®ç7", "¬õ®ç8", "¬õ®ç9", "¬õ®ç10", "¬õ®çJ", "¬õ®çQ", "¬õ®çK", "¶Â®çA",
      "¶Â®ç2", "¶Â®ç3", "¶Â®ç4", "¶Â®ç5", "¶Â®ç6", "¶Â®ç7", "¶Â®ç8", "¶Â®ç9", "¶Â®ç10", "¶Â®çJ", "¶Â®çQ", "¶Â®çK", "±öªáA", "±öªá2", "±öªá3", "±öªá4",
      "±öªá5", "±öªá6", "±öªá7", "±öªá8", "±öªá9", "±öªá10", "±öªáJ", "±öªáQ", "±öªáK", "¤è¶ôA", "¤è¶ô2", "¤è¶ô3", "¤è¶ô4", "¤è¶ô5", "¤è¶ô6", "¤è¶ô7",
      "¤è¶ô8", "¤è¶ô9", "¤è¶ô10", "¤è¶ôJ", "¤è¶ôQ", "¤è¶ôK", };

  // ¬~µP¡A¥´¶ÃµPªº¶¶§Ç
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

  // µoµP,«ö¶¶§ÇµoµP¡A±q¤U¼Ð0¶}©l
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
