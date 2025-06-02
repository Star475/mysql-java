package cardgame;

public class App {
  public static void main(String[] args) {
      Deck deck = new Deck();
      deck.shuffle();

      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");

      // Deal cards
      for (int i = 0; i < 52; i++) {
          if (i % 2 == 0) {
              player1.draw(deck);
          } else {
              player2.draw(deck);
          }
      }

      // Play 26 rounds
      for (int i = 0; i < 26; i++) {
          Card card1 = player1.flip();
          Card card2 = player2.flip();

          System.out.print(player1.getName() + " plays: ");
          card1.describe();
          System.out.print(player2.getName() + " plays: ");
          card2.describe();

          if (card1.getValue() > card2.getValue()) {
              player1.incrementScore();
              System.out.println(player1.getName() + " wins this round\n");
          } else if (card2.getValue() > card1.getValue()) {
              player2.incrementScore();
              System.out.println(player2.getName() + " wins this round\n");
          } else {
              System.out.println("It's a tie!\n");
          }
      }

      // Final scores
      System.out.println(player1.getName() + " score: " + player1.getScore());
      System.out.println(player2.getName() + " score: " + player2.getScore());

      if (player1.getScore() > player2.getScore()) {
          System.out.println(player1.getName() + " wins the game!");
      } else if (player2.getScore() > player1.getScore()) {
          System.out.println(player2.getName() + " wins the game!");
      } else {
          System.out.println("The game is a draw!");
      }
  }
}



  


