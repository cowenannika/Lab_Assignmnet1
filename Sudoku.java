import java.util.*;
public class Sudoku
{
    //9 by 9 board
    private int BOARD_SIZE = 9;
    //initialize double array grid
    private int[][] board;

    //set up board
    public Sudoku(int[][] board)
    {
        int BOARD_SIZE = 9;
        this.board = new int[BOARD_SIZE][BOARD_SIZE];

        for(int row = 0; row < BOARD_SIZE; row++)

        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                this.board[row][col] = board[row][col];
            }
        }
    }

    //looks to see if number is already in col
    private boolean isInCol(int col, int number)
    {
        for(int row = 0; row < 9; row++)
        {
            if(board[row][col] == number)
            {
                return true;
            }
        }
        //not in col
        return false;
    }

    //looks to see if number is already in row
    private boolean isInRow(int row, int number)
    {
        for(int col = 0; col < BOARD_SIZE; col++)
        {
            if(board[row][col] == number)
            {
                return true;
            }
        }
        //not in row
        return false;
    }

    //looks to see if number is already in subgrid
    private boolean isInBox(int row, int col, int number){
        int positionRow = row-row % 3;
        int positionCol = col-col% 3;
        for(int rows = positionRow; rows < positionRow + 3; rows++) //
        {
            for(int cols = positionCol; cols < positionCol + 3; cols++)
            {
                if(board[rows][cols] == number)
                {
                    return true;
                }
            }
        }
        //not in subgrid
        return false;
    }

    //checking to make sure all is smooth if above is false
    private boolean safe(int row, int col, int number)
    {
        return !(isInRow(row, number) || isInCol(col, number) || isInBox(row, col, number));
    }

    public boolean finished()
    {
        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                if(board[row][col] == 0)
                {
                    //Goes through the numbers to see which could possible go there
                    for(int number = 1; number <= BOARD_SIZE; number++)
                    {
                        if(safe(row, col, number))
                        {
                            //changes to that number if works
                            board[row][col] = number;
                            if(finished())
                            {
                                return true;
                            }
                            else
                            {
                                //backtracks and resets if it does not work and try next
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void print()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                System.out.print(" " + board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int BOARD_SIZE = 9;
        //the person playing enters numbers or zero for blank
        System.out.println("Hello and Welcome bruh! Please enter #'s 1-9 one at a time pressing enter after and 0 for blanks:");
        Scanner scan = new Scanner(System.in);
        int[][] grid = new int[BOARD_SIZE][BOARD_SIZE];
        for(int row=0;row<grid.length;row++)
            for(int col=0;col < grid [row].length; col++)
                grid[row][col]=scan.nextInt();

        Sudoku sudoku = new Sudoku(grid);

        System.out.println("board you entered:\n");
        sudoku.print();

        if(sudoku.finished())
        {
            System.out.println("Solved, congrats:\n");
            //prints if solved
            sudoku.print();
        }
        else
        {
            //board didn't work
            System.out.println("Nerp, not solveable sryyyyy");
        }
    }
}

