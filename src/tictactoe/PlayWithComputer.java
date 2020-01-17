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
public class PlayWithComputer {
    public static int easy(char board[]){
        int x = (int) (Math.random()*9);
        while (board[x]=='x' || board[x]=='o' ){
            x = (int) (Math.random()*9);
        }
        return x;
    }
    
    
    static int evaluate(char board[]){
        if (PlayWithComputer.checkWinner('o', board))
            return 10;
        if (PlayWithComputer.checkWinner('x', board))
            return -10;
        return 0;
    }
    
    static boolean checkWinner(char ch,char board[]){
        if (checkHorizontal(ch,board) || checkVertical(ch,board) || checkDiagonal(ch,board))
            return true;
        return false;
    }
    
    static boolean checkHorizontal(char ch,char board[]){
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
    
    static boolean checkVertical(char ch,char board[]){
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
    
    static boolean checkDiagonal(char ch,char board[]){
        if (board[0]==board[4] && board[4]==board[8] && board[8]==ch){
            return true;
        }
        if (board[2]==board[4] && board[4]==board[6] && board[6]==ch){
            return true;
        }
        return false;
    }
    
    static int minimax(char board[],int depth,boolean isMax ){
        int score = evaluate(board); 

        if (score == 10)
            return score; 

        if (score == -10)
            return score; 
        
        boolean chk=false;
        
        for (int i=0;i<9;++i){
            if (board[i]==' ') chk=true;
        }
        if (!chk) return 0;
        
        if (isMax) 
        { 
            int best = -1000; 

            for (int i = 0; i < 9; i++) 
            {  
                if (board[i]==' ') 
                { 
                    board[i] = 'o'; 

                    best = Math.max(best, minimax(board,  
                                    depth + 1, !isMax)); 

                    board[i] = ' '; 
                } 
            }
            return best; 
        } 
        else
        { 
            int best = 1000; 

            for (int i = 0; i < 9; i++) 
            { 
                { 
                    if (board[i]==' ') 
                    { 
                        board[i] = 'x'; 

                        best = Math.min(best, minimax(board,  
                                        depth + 1, !isMax)); 

                        board[i] = ' '; 
                    } 
                } 
            } 
            return best; 
        }   
    }
    
    public static int hard(char board[]){
        int bestVal = -1000; 
        int bestMove = -1;

        for (int i = 0; i < 9; i++) 
        { 
            if (board[i] == ' ') 
            { 
                board[i] = 'o'; 

                int moveVal = minimax(board, 0, false); 

                board[i] = ' '; 

                if (moveVal > bestVal) 
                { 
                    bestMove=i;
                    bestVal = moveVal; 
                } 
            } 
        } 
        return bestMove; 
    }
    
    public static int medium(char board[]){
        int x =(int)(Math.random()*2);
        if (x==1)
            return PlayWithComputer.hard(board);
        return PlayWithComputer.easy(board);
    }
    public static void main(String[] args) {
        
    }
}
