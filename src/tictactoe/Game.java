/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Me5a
 */
public class Game {
    
    Player player1,player2;
    char board[];
    int playerTurn;
    int fp_score;
    int sp_score;
    int tie_score;
    int cellFilled;

    public Game(Player player1, Player player2, char[] board, int playerTurn, int fp_score, int sp_score, int tie_score) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.playerTurn = playerTurn;
        this.fp_score = fp_score;
        this.sp_score = sp_score;
        this.tie_score = tie_score;
        cellFilled=countCells();
    }
    
    public Game(Player player1, Player player2, char[] board, int playerTurn) {
        this(player1, player2, board, playerTurn, 0, 0, 0);
    }
    
    int countCells(){
        int counter=0;
        for (char i : board){
            if (i=='x' || i=='o') ++counter;
        }
        return counter;
    }
    
    public int chooseCell(int x,int y){
        char ch = getPlayerChar();
        board[x*3+y]=ch;
        ++cellFilled;
        if  (checkWinner(ch)) // current player win
            return 1;
        if (cellFilled==9) // tie
            return -1;
        changeTurn();
        return 0;
    }
    
    void changeTurn(){
        playerTurn%=2;
        playerTurn++;
    }
    
    char getPlayerChar(){
        if (playerTurn==1)
            return 'x';
        return 'o';
    }
    
    boolean checkWinner(char ch){
        if (checkHorizontal(ch) || checkVertical(ch) || checkDiagonal(ch))
            return true;
        return false;
    }
    
    boolean checkHorizontal(char ch){
        if (board[0]==board[1] && board[1]==board[2] && board[2]==ch){
            return true;
        }
        if (board[3]==board[4] && board[4]==board[5] && board[5]==ch){
            return true;
        }
        if (board[6]==board[7] && board[7]==board[8] && board[8]==ch){
            return true;
        }
        return false;
    }
    
    boolean checkVertical(char ch){
        if (board[0]==board[3] && board[3]==board[6] && board[6]==ch){
            return true;
        }
        if (board[1]==board[4] && board[4]==board[7] && board[7]==ch){
            return true;
        }
        if (board[2]==board[5] && board[5]==board[8] && board[8]==ch){
            return true;
        }
        return false;
    }
    
    boolean checkDiagonal(char ch){
        if (board[0]==board[4] && board[4]==board[8] && board[8]==ch){
            return true;
        }
        if (board[2]==board[4] && board[4]==board[6] && board[6]==ch){
            return true;
        }
        return false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public char[] getBoard() {
        return board;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getFp_score() {
        return fp_score;
    }

    public int getSp_score() {
        return sp_score;
    }

    public int getTie_score() {
        return tie_score;
    }    
    
}
