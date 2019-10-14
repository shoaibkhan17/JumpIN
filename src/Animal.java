
public abstract class Animal extends Piece{

	
	public Animal(char type) {
		super(type, true);
		// TODO Auto-generated constructor stub
	}

	public abstract void move(int x,int y);
	
}
