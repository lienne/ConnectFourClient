import connectfourclient.Connect4Client;
import connectfourclient.Player;

import java.util.*;

public class ConcretePlayer implements Player {



    public static void main (String args []) {

        Connect4Client c4c = new Connect4Client("139.126.184.118", 9000, new ConcretePlayer());

        c4c.turnOnDebugging();

        c4c.start();
    }

    public int nextMove(char input, char[][] board) {

        // input is X or O
        // check if column is full

//        System.out.println(board.length);
//        System.out.println(board[0].length);

        Random randNum = new Random();

        List<Integer> countArray = new ArrayList<>();
        int countVertical;
        int countHorizontal;

        for(int i = 0; i < board.length; i++){ // columns. board.length = 7
            countVertical = 0;
            countHorizontal = 0;

            // First time thru loop: i = 0, j = 5, if that is empty, go down one row
            if(board[i][5] != 'X' && board[i][5] != 'O') {
                // First time thru loop: i = 0, j = 5
                for (int j = board[i].length - 1; j >= 0; j--) { // rows. board[j].length = 6

                    // Check if it contains your input (either X or O)
                    if (board[i][j] == input) { // check for YOUR input (input is either X or O)
                        countVertical++; // Count the number of times you see your input
                    } else { // either the cell is empty or it contains enemy's input, so you should add the count so far
                        // to your array, and reset it (just break out of the loop, I suppose)
                        countArray.add(countVertical); // add the highest count to your array
                        break; // Break out of inner for loop, repeat process, find highest count of your input in next column
                    }
                }
                // NOTE: This doesn't check for cases where you have inputs on top of your enemy's inputs
                // EX:
                // X
                // X
                // X
                // O    <-- else statement will have count = 1 and add that to your array at this point
                // X
            }
        }

        // Array filled with your input counts for each column
        int max = Collections.max(countArray); // Find which column has max count. Array cells should match column indexes
        boolean flag = true;

        // Find column with highest count

        for(int i = 0; i < countArray.size() - 1; i++) {
            if(countArray.get(i) != countArray.get(i + 1)) {
                flag = false;
            }
        }

        if(flag) {
            return randNum.nextInt(7);
        }

        int columnToDropInto = countArray.indexOf(max); // Doesn't account for repeat numbers

        return columnToDropInto; // Should return column number
    }


    public String getTeamName() {
        String teamName = "Blue Jays";
        return teamName;
    }
}