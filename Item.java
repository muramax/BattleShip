abstract class ItemStrategy {
    protected String name;
    protected String description;
    protected boolean status = true;
    protected String coordinate;
    protected Board playBoard;
    protected Board shipBoard;

    public ItemStrategy(Board playBoard, Board shipBoard) {
        this.playBoard = playBoard;
        this.shipBoard = shipBoard;
    }

    public String getName() { 
    	return name; 
    }
    
    public String getDescription() { 
    	return description; 
    }
    public void addCoordinate(String coordinate) { 
    	this.coordinate = coordinate; 
    }
    
    public boolean getStatus() { 
    	return status; 
    }

    public abstract int action();
}

class MissileBurstItem extends ItemStrategy {
    public MissileBurstItem(Board playBoard, Board shipBoard) {
        super(playBoard, shipBoard);
        this.name = "Missile Burst";
        this.description = "Launch multiple missiles";
    }

    @Override
    public int action() {
        System.out.println("Missile Burst activated at " + coordinate);
        int hit = 0;
        AirStrikeSensor sensor = new AirStrikeSensor();
        sensor.addObserver(new BoardUpdater(playBoard, shipBoard));
        for (int i = 0; i < coordinate.length(); i += 2) {
            String coor = coordinate.substring(i, i + 2);
            if (sensor.checkCoordinate(coor)) hit++;
        }
        this.status = false;
        return hit;
    }
}

class RadarItem extends ItemStrategy {
    public RadarItem(Board playBoard, Board shipBoard) {
        super(playBoard, shipBoard);
        this.name = "Radar";
        this.description = "Reveals some place of the board";
    }

    @Override
    public int action() {
        System.out.println("Radar activated!");
        int scan = 0;
        AirStrikeSensor sensor = new AirStrikeSensor();
        sensor.addObserver(new BoardUpdater(playBoard, shipBoard));
        for (int i = 0; i < coordinate.length(); i += 2) {
            int col = coordinate.charAt(i) - 'A';
            int row = Character.getNumericValue(coordinate.charAt(i + 1));
            String cell = shipBoard.getCell(row, col);
            if (cell.equals("\u001B[38;2;255;215;0m" + "S" + "\u001B[0m")) scan++;
        }
        this.status = false;
        return scan;
    }
}

public class Item {
    private ItemStrategy strategy;

    public Item(ItemStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeItem() {
        return strategy.action();
    }
    
    public ItemStrategy getStrategy() {
    	return strategy;
    }
}

