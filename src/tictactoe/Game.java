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
    int playerPlayedFirstCell=1;
    int fp_score;
    int sp_score;
    int tie_score;
    int cellFilled;
    boolean isComputer;
    String level;
    boolean playAgainPlayer1=false,playAgainPlayer2=false;
    
    public Game(Player player1, Player player2,int playerTurn,int pFirstCell, char[] board, int tie_score,  int fp_score, int sp_score) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.playerTurn = playerTurn;
        this.playerPlayedFirstCell=pFirstCell;
        this.fp_score = fp_score;
        this.sp_score = sp_score;
        this.tie_score = tie_score;
        cellFilled=countCells();
        System.out.println("board at Game = "+board[0]);
    }

    public Game(Player player1, Player player2) {
       this(player1, player2, player1.getId(), 1 ,new char[9]);
        for (int i=0;i<9;++i)
            board[i]=' ';
        
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public Game(Player player1, Player player2, int playerTurn,int pFCell, char[] board) {
        this(player1, player2,playerTurn,pFCell, board, 0, 0, 0);
    }
    
    public Game(Player player,boolean isComputer,String level){
        this(player,null);
        this.isComputer=isComputer;
        this.level=level;
        for (int i=0;i<9;++i)
            board[i]=' ';
    }
    
    int countCells(){
        int counter=0;
        for (char i : board){
            if (i=='x' || i=='o') ++counter;
        }
        return counter;
    }
    
    public boolean isAvailable(int cellNum){
        return board[cellNum]==' ';
    }
    
    public int chooseCell(int cellNum){
        char ch = getPlayerChar();
        board[cellNum]=ch;
        ++cellFilled;
        if  (checkWinner(ch)){ // current player win
            if (player1.getId()==playerTurn) ++fp_score;
            else ++sp_score;
            return 1;
        }
        if (cellFilled==9){ // tie
            ++tie_score;
            return -1;
        }
        changeTurn();
        return 0;
    }
    
    void changeTurn(){
        if(playerTurn == player1.getId()){
            if (isComputer){
                playerTurn=-1;
            }else{
                playerTurn=player2.getId();
            }
        }else{
            playerTurn=player1.getId();
        }
    }
    
    public char getPlayerChar(){
        if (playerTurn==player1.getId())
            return 'x';
        return 'o';
    }

    public int getPlayerPlayedFirstCell() {
        return playerPlayedFirstCell;
    }

    public void setPlayerPlayedFirstCell(int playerPlayedFirstCell) {
        this.playerPlayedFirstCell = playerPlayedFirstCell;
    }
    
    public boolean checkWinner(char ch){
        if (checkHorizontal(ch) || checkVertical(ch) || checkDiagonal(ch))
            return true;
        return false;
    }
    
    public boolean checkHorizontal(char ch){
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
    
    public boolean checkVertical(char ch){
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
    
    public boolean checkDiagonal(char ch){
        if (board[0]==board[4] && board[4]==board[8] && board[8]==ch){
            return true;
        }
        if (board[2]==board[4] && board[4]==board[6] && board[6]==ch){
            return true;
        }
        return false;
    }
    
    public boolean chkPlayersClickedPlayAgain(int pID){
        if (pID==player1.getId()) playAgainPlayer1=true;
        if (pID==player2.getId()) playAgainPlayer2=true;
        if (playAgainPlayer1 && playAgainPlayer2){
            playAgain();
            return true;
        }
        return false;
    }
    
    public void playAgain(){
        for (int i=0;i<9;++i){
            board[i]=' ';
        }
        cellFilled=0;
        if (playerPlayedFirstCell==1){
            playerPlayedFirstCell=2;
            if (isComputer){
                playerTurn=-1;
            }else playerTurn=player2.getId();
        }
        else{
            playerTurn=player1.getId();
            playerPlayedFirstCell=1;
        }
        playAgainPlayer1=false;
        playAgainPlayer2=false;
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
