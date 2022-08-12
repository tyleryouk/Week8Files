/** Tyler Youk ChutesAndLadders class */

import java.util.*;

public class ChutesAndLadders {
  private int[] board;
  private int[] players;
  
  public ChutesAndLadders(int numPlayers){
    board = new int[101];
    players = new int[numPlayers];
    
    for(int i = 0; i < players.length; i++){
      players[i]=0; //all players have value of 0;
    }
    
    for(int i = 0; i < board.length; i++){
      board[i]=0; //all board spots have value of 0;
    }
    
    //creating ladders
    board[1] = 38;  //want to land on certain board values to jump to new board spot
    board[4] = 14;
    board[9] = 31;
    board[28] = 84;
    board[21] = 42;
    board[80] = 100;
    //creating chutes
    board[92] = 73;
    board[16] = 6;
    board[87] = 24;
    board[62] = 19;
    board[49] = 11;
    board[98] = 78;
  
  }
  
  
  

  public int nextPlayer(int currentPlayer, int[] players){
    return (currentPlayer+1)%players.length; 
  }
  
  /** 
   * Determines if player has won the game
   * @param player : the player to check
   * @param players : the list of players
   * @returns true if the player has won, false if not */
  public boolean isWinner(int player, int[] players){
    return players[player] == board.length-1; 
  }
  
  public void movePlayer(int player, int roll, int[] playerLocations, int[] board){
    try { //exception
      int currentSpace = playerLocations[player];
      int nextSpace = currentSpace+roll;
      int finalSpace = board[nextSpace];
     
      playerLocations[player] = finalSpace;
    }  
    catch (ArrayIndexOutOfBoundsException e){
      // throw if the die roll takes a player past 100
      // in this case, player skips their turn
    }
     
  }
  
  public int playGame(){
    int currentPlayer = 0;
    Die d = new Die(6); //6 sided die
    
    //until we have a winner, move the player and change players
    while(!isWinner(currentPlayer, players)){
      //move currentPlayer
      movePlayer(currentPlayer, d.roll(), players, board);
      //update to the next player
      currentPlayer = nextPlayer(currentPlayer, players);
    }
    return currentPlayer;
  }
  
  public static void main(String[] args){
    ChutesAndLadders game = new ChutesAndLadders(4); 
    int winner = game.playGame();
    System.out.println(winner);
  }
  
  
}