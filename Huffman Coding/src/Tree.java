public class Tree{
	
	private static Node node;
	public static Node newRoot;
	public static String code = "";

	public Tree(){
	}
	
	public Node makeTree(PriorityQueue queue)
	{
		
		if(!queue.isEmpty())			
		{
			Node nodeLeft = queue.remove();
			Node nodeRight = queue.remove();
			
			if(nodeRight != null){	
				
				node = new Node(nodeLeft.value + nodeRight.value, nodeLeft.frequency + nodeRight.frequency);
				node.left = nodeLeft;
				node.right = nodeRight;
				queue.insert(node);						
				
				makeTree(queue);	
				
			}else{
				newRoot = nodeLeft;		// element in queue is now a root of the tree					
			}
		}		
		return newRoot;		
	}
	
	public void printBinaryTree(Node root, int level){
				
	    if(root==null) return;
	    	    
	    printBinaryTree(root.right, level+1);
	    
	    if(level!=0){
	        for(int i = 0; i < level-1; i++)
	            System.out.print("|\t");
	        	
	        	if(root.left == null && root.right == null)
	        		System.out.println("|------[" + root.value + ":" + (int)root.frequency + "]");
	        	else
	        		System.out.println("|------[" + (int)root.frequency + "]");
	    }
	    else
	        System.out.println("["+(int)root.frequency + "]");
	    
	    printBinaryTree(root.left, level+1);
	}
	
	
	//recursive method
	public String codeMsg(Node currentNode, char character)
	{		
		Node current = currentNode;				

		if(current.left != null || current.right != null){
			
			if(current.left.value.equals(character)){
				code += "0";				
				return code;
			}
			else if(stringContainsChar(current.left.value, character)){
				code += "0";
				current = current.left;				
			}
			else if(current.right.value.equals(character)){
				code += "1";
				return code;
			}
			else if(stringContainsChar(current.right.value, character)){
				code += "1";
				current = current.right;
			}else{				
				return null;
			}
						
			codeMsg(current, character);
		}
		
		return code;
	}
	
	private boolean stringContainsChar(String string, char character){
		
		char[] charArray = string.toCharArray();	

		for(int i = 0; i < charArray.length; i++){			
			if(charArray[i] == character){
				return true;
			}
		}
		
		return false;		 
	}
	
} 