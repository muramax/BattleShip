interface BoardObserver {
    boolean update(String coordinate);
}

class AirStrikeSensor{
    private String coordinate;
    private BoardObserver observer;

    public boolean checkCoordinate(String coordinate) {
        this.coordinate = coordinate;
        return notifyObserver();
    }

    public void addObserver(BoardObserver observer) {
        this.observer = observer;
    }

    private boolean notifyObserver() {
        return observer.update(coordinate);
    }
}

public class BoardUpdater implements BoardObserver {
    Board playBoard;
    Board shipBoard;

    public BoardUpdater(Board playBoard, Board shipBoard) {
        this.playBoard = playBoard;
        this.shipBoard = shipBoard;
    }

    @Override
    public boolean update(String coordinate) {
        int col = coordinate.charAt(0) - 'A'; 
        int row = Character.getNumericValue(coordinate.charAt(1));
        String checkpoint = shipBoard.getCell(row, col);
        if (checkpoint.equals("\u001B[38;2;255;215;0m" + "S" + "\u001B[0m")) {
            System.out.println("Hit!");
            playBoard.setCell(row, col, "X");
            return true;
        }
        else {
            System.out.println("Miss!");
            playBoard.setCell(row , col, "—");
            return false;
        }
    }
}
