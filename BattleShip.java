import java.util.ArrayList;
import java.util.Scanner;

public class BattleShip {
	static final int[] SHIP_SIZES = {2, 3, 4};
	static final int[] SHIP_COUNTS = {4, 3, 2};
	static int ROUNDS = 50;
	
    public static void initialShips(ArrayList<Ship> ships) {
        // Create ships based on the SHIP_SIZES and SHIP_COUNTS
        for (int i = 0; i < SHIP_SIZES.length; i++) {
            for (int j = 0; j < SHIP_COUNTS[i]; j++) {
                ships.add(ShipFactory.createShip(SHIP_SIZES[i])); // use static 
            }
        }
    }
    
    public static void printRemainingShips(ArrayList<Ship> ships) {
        ArrayList<String> namesPrinted = new ArrayList<>();
        StringBuilder result = new StringBuilder("Remaining ships to place: [ ");

        for (Ship ship : ships) {
            String name = ship.getName();
            if (!namesPrinted.contains(name)) {
                int count = 0;
                for (Ship s : ships) {
                    if (s.getName().equals(name)) {
                        count++;
                    }
                }
                result.append(name).append(" x").append(count).append(", ");
                namesPrinted.add(name);
            }
        }
        result.setLength(result.length() - 2); // Remove trailing comma and space
        result.append(" ]");
        System.out.println(result);
    }
    
    public static void printAvailableItems(ArrayList<Item> itemsList) {
        if (itemsList == null || itemsList.isEmpty()) {
            System.out.println("No items left!");
            return;
        }

        System.out.print("Available items: [ ");
        for (int i = 0; i < itemsList.size(); i++) {
            System.out.print(itemsList.get(i).getStrategy().getName());
            if (i != itemsList.size() - 1) {
                System.out.print(" , ");
            }
        }
        System.out.println(" ]");
    }
    
    public static boolean isValidHit(Board board, String input) {
    	// Case 1: check size
        if (input.length() != 2) return false;
    	
        // Case 2: input one alphabet one number and inside board 
        int col = input.charAt(0);
        int row = input.charAt(1);
        	
        if (!Character.isLetter(col) || (col - 'A' < 0 || col - 'A' >= 10)) return false;
        if (!Character.isDigit(row) || (Character.getNumericValue(row) < 0 || Character.getNumericValue(row) >= 10)) return false;

        // Case 3: check not already hit
        if (board.getCell(Character.getNumericValue(row), col - 'A') != " ") return false;
        
        return true;
    }

    public static boolean isValidShipPlacement(String input) {
        // Case 1: check size
        if (input.length() % 2 != 0) return false;
        
        // Case 2: input one alphabet one number and so on.. and inside board 
        for (int i = 0; i < input.length(); i += 2) {
        	int col = input.charAt(i);
        	int row = input.charAt(i+1);
        	
        	if (!Character.isLetter(col) || (col - 'A' < 0 || col - 'A' >= 10)) return false;
        	if (!Character.isDigit(row) || (Character.getNumericValue(row) < 0 || Character.getNumericValue(row) >= 10)) return false;
        }
 
        // Case 3: check valid input (horizon or vertical)
        int len = input.length() / 2;

        char[] cols = new char[len];
        int[] rows = new int[len];

        // Split into coordinates
        for (int i = 0; i < input.length(); i += 2) {
            cols[i / 2] = input.charAt(i);
            rows[i / 2] = Character.getNumericValue(input.charAt(i + 1));
        }

        boolean sameCol = true, sameRow = true;
        for (int i = 1; i < len; i++) {
            if (cols[i] != cols[i - 1]) sameCol = false;
            if (rows[i] != rows[i - 1]) sameRow = false;
        }

        // Check if either column is the same and row increases by 1
        // OR row is the same and column increases by 1
        if (sameCol) {
            for (int i = 1; i < len; i++) {
                if (rows[i] != rows[i - 1] + 1) return false;
            }
        } 
        else if (sameRow) {
            for (int i = 1; i < len; i++) {
                if (cols[i] != cols[i - 1] + 1) return false;
            }
        }
        return true;
    }
    
    public static boolean isValidUseOfItem(Board board, String input) {
        // Case 1: check size == 10
        if (input.length() != 10) return false;
        
        // Case 2: input one alphabet one number and so on.. and inside board 
        for (int i = 0; i < input.length(); i += 2) {
        	int col = input.charAt(i);
        	int row = input.charAt(i+1);
        	
        	if (!Character.isLetter(col) || (col - 'A' < 0 || col - 'A' >= 10)) return false;
        	if (!Character.isDigit(row) || (Character.getNumericValue(row) < 0 || Character.getNumericValue(row) >= 10)) return false;
        	
        	// Case 3: check not already hit
        	if (board.getCell(Character.getNumericValue(row), col - 'A') != " ") return false;
        }
        return true;
    }

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		
		// Main menu page
		System.out.println("""
		=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
				
				
		   ██████╗  █████╗ ████████╗████████╗██╗     ███████╗     ███████╗██╗  ██╗██╗██████╗ 
		   ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝     ██╔════╝██║  ██║██║██╔══██╗
		   ██████╔╝███████║   ██║      ██║   ██║     █████╗       ███████╗███████║██║██████╔╝
		   ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝       ╚════██║██╔══██║██║██╔═══╝ 
		   ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗     ███████║██║  ██║██║██║     
		   ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝     ╚══════╝╚═╝  ╚═╝╚═╝╚═╝   
					
					
			 		
			   	    >>> Enter any keys to start the battle! <<<
							
							
							
		=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		""");
		scanner.nextLine();
        // Print blank page to separate between main menu and pre-round
        for (int i = 0; i < 100; i++) System.out.println();
		
		// IN-GAME
		int totalSegment  = 0;
        for (int i = 0; i < SHIP_SIZES.length; i++) {
        	totalSegment  += SHIP_SIZES[i] * SHIP_COUNTS[i];
        }
        int remainingSegment = totalSegment;

        Board shipBoard = new Board();
        Board playBoard = new Board();
        
        Item radarItem = new Item(new RadarItem(playBoard, shipBoard));
        Item missileBurstItem = new Item(new MissileBurstItem(playBoard, shipBoard));

        ArrayList<Item> items = new ArrayList<>();
        items.add(missileBurstItem);
        items.add(radarItem);
        
		ArrayList<Ship> ships = new ArrayList<>();
        
        // Create the ships
        initialShips(ships);
        
        while (!ships.isEmpty()) {
        	System.out.println("\n=-=-=-=-=-=-=-= Turn: Player 1 =-=-=-=-=-=-=-=\n");
	        System.out.println("-------------( Place the ships )-------------\n");
	        shipBoard.printBoard();
	        System.out.println("\n-------------( Place the ships )-------------\n");
	        printRemainingShips(ships);
	        
	        System.out.print("\nEnter the coordinates for your ship (ex. A0A1A2): ");
	        String playerInput = scanner.nextLine();
	        // Remove all whitespace and convert to upper case
	        playerInput = playerInput.replaceAll("\\s+", "").toUpperCase();

	        if (!isValidShipPlacement(playerInput)) {
	        	System.out.println("\n❌ Invalid input. Please try again\n");
	        	continue; //skip to next loop
	        } 
	        
	        // Check ship available or not and not in already exist place         
	        Ship selectedShip = null;
	        for (Ship ship : ships) {
	            if (ship.getSize() == playerInput.length() / 2) {
	                selectedShip = ship;
	                break;
	            }
	        }
	        if (selectedShip != null) {
	        	boolean alreadyHave = false;
	            // Place the ship
	            for (int i = 0; i < playerInput.length(); i += 2) {   
	            	int col = playerInput.charAt(i) - 'A';
		        	int row = Character.getNumericValue(playerInput.charAt(i+1));
		        	
		        	if (shipBoard.getCell(row, col) == "\u001B[38;2;255;215;0m" + "S" + "\u001B[0m") {
		        		alreadyHave = true;
		        		System.out.println("\n❌ Already have ship at that position\n");              
		        		break;
		        	}                                                       
		        	shipBoard.setCell(row, col, "S");
	            }
	            // Remove that ship from the list and proceed
	            if (!alreadyHave) ships.remove(selectedShip);
	            else if (alreadyHave) continue; //skip to next loop
	        } 
	        else {
	            System.out.println("\n❌ No ship size " + playerInput.length() / 2 + " available. Please try again with a valid ship.\n");
	            continue; //skip to next loop
	        }
	        System.out.println();
		}
        System.out.println("\n=-=-=-=-=-= Board Setup Complete -==-=-=-=-=\n");
        shipBoard.printBoard();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.println("Player 2, prepare for battle...\n\n");
        Thread.sleep(3000); // delay 3 seconds
        
        // Print blank page to separate between pre-round and game-round
        for (int i = 0; i < 100; i++) System.out.println();
        
        AirStrikeSensor airStrikeSensor = new AirStrikeSensor();
        BoardUpdater boardUpdater = new BoardUpdater(playBoard, shipBoard);
        airStrikeSensor.addObserver(boardUpdater);

        for (int rounds = ROUNDS; rounds > 0; rounds--){
            System.out.println("\n=-=-=-=-=-=-=-= Turn: Player 2 =-=-=-=-=-=-=-=\n");
	        System.out.println("----------------( Hit a ship )----------------\n");
	        playBoard.printBoard();
	        System.out.println("\n-------------( " + rounds + " Missiles left )-------------\n");
	        System.out.printf("Total damage: %.2f%% of all ships%n\n", (double) (totalSegment - remainingSegment) / totalSegment * 100); 
	        printAvailableItems(items);

            System.out.print("\nEnter 'item' to use an item / coordinates to HIT! (ex. A0): ");
            String playerInput = scanner.nextLine();
            // Remove all whitespace and convert to upper case
	        playerInput = playerInput.replaceAll("\\s+", "").toUpperCase();
	        
	        // Enter item
	        if (playerInput.equals("ITEM")) {
	            if (!items.isEmpty()) {
	                System.out.println("\nChoose an item:");
	                System.out.printf("%d) %s : %s%n", 1, missileBurstItem.getStrategy().getName(), missileBurstItem.getStrategy().getDescription());
	                System.out.printf("%d) %s : %s%n", 2, radarItem.getStrategy().getName(), radarItem.getStrategy().getDescription());
	            	System.out.println("\nEnter number: ");
	                String itemInput = scanner.nextLine();
	                // Remove all whitespace and convert to upper case
	    	        playerInput = playerInput.replaceAll("\\s+", "").toUpperCase();
	    	        
	                if (itemInput.equals("1") && missileBurstItem.getStrategy().getStatus()) {
	                	System.out.print("\nEnter 5 coordinates you want to Hit!! (ex. A1A2A3A4A5): ");
	                	String input = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();
	                	if (isValidUseOfItem(playBoard, input)) {
	                		missileBurstItem.getStrategy().addCoordinate(input);
	                	    int hit = missileBurstItem.executeItem();
		                    remainingSegment -= hit;  
		                    items.remove(missileBurstItem);
	                	}
	                	else {
	                		System.out.println("\n❌ Invalid input\n");
	                		rounds++;
	                		continue;
	                	}
	                }
	                else if (itemInput.equals("2") && radarItem.getStrategy().getStatus()){
	                    System.out.print("\nEnter 5 coordinates you want to Scan!! (ex. A1A2A3A4A5): ");
	                    String input = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();
	                    rounds++;
	                	if (isValidUseOfItem(playBoard, input)) {
	                		radarItem.getStrategy().addCoordinate(input);
	                		int scan = radarItem.executeItem();
	                		System.out.print(scan + " coordinates found a ship!!\n");
	                		items.remove(radarItem);
	                	}
	                	else {
	                		System.out.println("\n❌ Invalid input\n");
	                		continue;
	                	}
	                }
	                else {
	                	rounds++;
	                    System.out.println("Item not available! Please try again.");
	                }
	            }
	            else {
	            	System.out.println("No items left!");
	            	rounds++;
	            }
	        }
	        // Enter coordinates
	        else if (isValidHit(playBoard, playerInput)) {
	        	if (airStrikeSensor.checkCoordinate(playerInput)) {
	        		remainingSegment--;
	        	}
	        }
	        else {
	        	System.out.println("\n❌ Invalid input\n"); 
	        	rounds++;
	        }
	        
	        // Check win game
	        if (remainingSegment <= 0) {
	            System.out.println("\n=-=-=-=-=-=-=-=-= YOU WIN! =-=-=-=-=-=-=-=-=\n");
	            playBoard.printBoard();
	            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
	            scanner.close();
	        	return;
	        }
        }
        System.out.println("\n=-=-=-=-=-=-=-=-= YOU LOSE =-=-=-=-=-=-=-=-=\n");
        playBoard.printBoard();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        scanner.close();
    }
}
