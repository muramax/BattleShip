public class Board {
	private String[][] grid;
	
    public Board() {
        grid = new String[10][10]; // 10x10 board
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col] = " ";
            }
        }
    }
    
    public void setCell(int row, int col , String value) {
    	if( value == "S") {
    		grid[row][col] = "\u001B[38;2;255;215;0m" + "S" + "\u001B[0m";
        }
        else if(value == "X") {
            grid[row][col] = "\u001B[38;2;255;0;0m" + "X" + "\u001B[0m";
        }
        else if(value == "—") {
            grid[row][col] = "—";
        }
    }
    
    public String getCell(int row, int col) {
        return grid[row][col];
    }
    
    public void printBoard() {
    	String[] col_alphabet = {
    		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
    		"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
    		"U", "V", "W", "X", "Y", "Z"
    	};
    	
        System.out.print("      "); 
        for (int col = 0; col < 10; col++) {
        	System.out.print(col_alphabet[col] + "   ");
        }
        System.out.println();  // New line

        for (int row = 0; row < 10; row++) {
        	System.out.println();  // New line
        	System.out.print(" " + (row) + "   ");
            for (int col = 0; col < 10; col++) {
                System.out.print("[" + grid[row][col] + "]" + " ");
            }
            System.out.println();  // New line
        }
    }
}
	
	
	

