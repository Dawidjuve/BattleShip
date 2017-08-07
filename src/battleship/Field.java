package battleship;

public class Field {
	
	private int x;
	private int y;
	public enum State{
		Ship ("Ship"),
		Available("Available"),
		Unavailable("Unavailable");
		
		private String text;
		private State(String text){
			this.text=text;
		}
	}

	private State state;
	
	public Field(int x, int y){
		this.x = x;
		this.y = y;
		this.state=State.Available;
		

	}
	
	public boolean equalsState(State s){
		return this.getState()==s;
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
