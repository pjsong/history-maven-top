package pattern.structural.adapter;

 interface IRoundPeg {
	public void insertIntoHole(String msg);
	}

interface ISquarePeg {
	public void insert(String str);
	}
//The RoundPeg class.
 class RoundPeg implements IRoundPeg {
public void insertIntoHole(String msg) {
System.out.println("RoundPeg insertIntoHole(): " + msg);
}
}
// The SquarePeg class.
 class SquarePeg implements ISquarePeg {
public void insert(String str) {
System.out.println("SquarePeg insert(): " + str);
}
}
public class PegAdapter1 implements ISquarePeg, IRoundPeg {
	 private RoundPeg roundPeg;
	 private SquarePeg squarePeg;
	 public PegAdapter1(RoundPeg peg) {this.roundPeg = peg;}
	 public PegAdapter1(SquarePeg peg) {this.squarePeg = peg;}
	 public void insert(String str) {roundPeg.insertIntoHole(str);}
	 public void insertIntoHole(String msg){squarePeg.insert(msg);}
	 public static void main(String args[]) {
		// Create some pegs.
		RoundPeg roundPeg = new RoundPeg();
		SquarePeg squarePeg = new SquarePeg();
		// Do an insert using the square peg.
		squarePeg.insert("Inserting square peg...");
		// Create a two-way adapter and do an insert with it.
		ISquarePeg roundToSquare = new PegAdapter1(roundPeg);
		roundToSquare.insert("Inserting round peg...");
		// Do an insert using the round peg.
		roundPeg.insertIntoHole("Inserting round peg...");
		// Create a two-way adapter and do an insert with it.
		IRoundPeg squareToRound = new PegAdapter1(squarePeg);
		squareToRound.insertIntoHole("Inserting square peg...");
		}
	 }