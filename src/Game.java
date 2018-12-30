abstract class Game {
  
  protected final void playGame(){
    
    initialize();
    while(!endGame()) {
      play();
    }
    printResult();
  }
  
  abstract void initialize();
  abstract void play();
  abstract boolean endGame();
  abstract void printResult();
}
