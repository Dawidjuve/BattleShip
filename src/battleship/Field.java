package battleship;

public class Field {
	
	private int x;
	private int y;
	public enum State{
		Ship ("Ship"),
		Available("Available"),
		Unavailable("Unavailable"),
		PShip("PShip");
		
		private String text;
		private State(String text){
			this.text=text;
		}
		
		public String toString(){
			return text;
		}
	}

	private State state;
	
	public Field(int x, int y){
		this.x = x;
		this.y = y;
		this.state=State.Available;
		

	}
	
	public boolean equalsState(State s){
		if(this.getState()==s) return true;
		else return false;
	}
	public void setState(State s){
		this.state = s;
	}
	
	public State getState(){
		
		return this.state;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	


}
