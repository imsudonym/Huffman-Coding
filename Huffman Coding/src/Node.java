public class Node{
	
	public String value;
	public double frequency;
	public Node left, right, next;
	
	public Node(String value, double frequency){
		this.value = value;
		this.frequency = frequency;
		left = null; right = null; next = null;
	}
	
	
}