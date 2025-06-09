interface Ship {
	String getName();
    int getSize();
//  void hit();
//  boolean isSunk();
}

// Small Ship
class SmallShip implements Ship {
	private String name = "Small Ship";
    private int size = 2;
//  private int hits = 0; // Tracks the number of hits
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getSize() {
        return size;
    }

//  @Override
//  public void hit() {
//      hits++;
//      System.out.println("Small ship hit!");
//  }
//
//  @Override
//  public boolean isSunk() {
//      return hits == size; // The ship sinks when all parts are hit
//  }
}

// Medium Ship
class MediumShip implements Ship {
	private String name = "Medium Ship";
    private int size = 3;
//  private int hits = 0;

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getSize() {
        return size;
    }

//  @Override
//  public void hit() {
//      hits++;
//      System.out.println("Medium ship hit!");
//  }
//
//  @Override
//  public boolean isSunk() {
//      return hits == size;
//  }
}

// Large Ship
class LargeShip implements Ship {
	private String name = "Large Ship";
    private int size = 4;
//  private int hits = 0;
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getSize() {
        return size;
    }

//  @Override
//  public void hit() {
//      hits++;
//      System.out.println("Large ship hit!");
//  }
//
//  @Override
//  public boolean isSunk() {
//      return hits == size;
//  }
}

//Ship Factory
public class ShipFactory {
	 public static Ship createShip(int size) {
	     switch (size) {
	         case 2: return new SmallShip();
	         case 3: return new MediumShip();
	         case 4: return new LargeShip();
	         default: throw new IllegalArgumentException("Unknown ship size: " + size);
	     }
	 }
}
