package pattern.structural.adapter;
//http://userpages.umbc.edu/~tarr/dp/lectures/Adapter-2pp.pdf
 class SquarePeg1 {
	public void insert(String str) {
	System.out.println("SquarePeg insert(): " + str);
	}
	}
 class RoundPeg1 {
	 public void insertIntoHole(String msg) {
	 System.out.println("RoundPeg insertIntoHole(): " + msg);
	 }
	 }
 public class PegAdapter extends SquarePeg {
	 private RoundPeg1 roundPeg;
	 public PegAdapter(RoundPeg1 peg) {this.roundPeg = peg;}
	 public void insert(String str) {roundPeg.insertIntoHole(str);}
	 public static void main(String args[]) {
		// Create some pegs.
		RoundPeg1 roundPeg = new RoundPeg1();
		SquarePeg1 squarePeg = new SquarePeg1();
		// Do an insert using the square peg.
		squarePeg.insert("Inserting square peg...");
		// Now we'd like to do an insert using the round peg.
		// But this client only understands the insert()
		// method of pegs, not a insertIntoHole() method.
		// The solution: create an adapter that adapts
		// a square peg to a round peg!
		PegAdapter adapter = new PegAdapter(roundPeg);
		adapter.insert("Inserting round peg...");
		}
	 }