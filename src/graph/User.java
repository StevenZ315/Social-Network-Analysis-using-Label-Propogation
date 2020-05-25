package graph;

public class User implements Comparable<User> 
{
	private int id;
	private String label;
	
	public User(int id) {
		this.id = id;
		this.label = String.valueOf(id);
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int compareTo(User other) {
		return Integer.compare(this.id, other.id);
	}
	
	public String toString() {
		return String.valueOf(id);
	}

}
