import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanClass{
	
	private String input;
	private char[] tempArray;	
	private double[] charCount;
	
	private ArrayList<Character> charArray = new ArrayList<Character>();
	private ArrayList<String> huffmanCode;
	private PriorityQueue queue = new PriorityQueue();	
	
	public static Tree tree = new Tree();
	public static Node root = null;
	
	private static String[] letters;
	private static double[] frequency;
	
	public HuffmanClass(String input, String inputChoice, String filename){
		
		this.input = input;
		tempArray = input.toCharArray();
		
		storeWithNoRep();		
		
		charCount = new double[charArray.size()];
		
		if(inputChoice.toCharArray()[0] == 'a'){
			countFreq();				
			insertNodesToQueueA();
		}else {
			relateToEnglish(filename);
			insertNodesToQueueB();
		}
			
		
		root = tree.makeTree(queue);		
		huffmanCode = new ArrayList<String>(charArray.size());
		
		//get the Huffman code for each letter
		getHuffmanCode();					
	}
	
	public void displayHuffmanCode(String choice){			
		
		System.out.println("\n\t\t---------------------------------------");
		System.out.println("\t\tCharacter    Frequency     Huffman Code");		
		for(int i = 0; i < huffmanCode.size(); i++){
			if(choice.toCharArray()[0] == 'a')
				System.out.println("\t\t  " +  charArray.get(i) + "\t\t" + charCount[i] + "\t\t" + huffmanCode.get(i));
			if(choice.toCharArray()[0] == 'b')
				System.out.println("\t\t  " +  charArray.get(i) + "\t\t" + charCount[i] + "%\t\t" + huffmanCode.get(i));
		}				
				
		System.out.println("\n\t\t---------------------------------------");
		System.out.println("\t\t Input string: " + input);
		System.out.println("\n\t\tGenerated bit string:");
		System.out.print("\t\t  ");
		for(int i = 0; i < input.length(); i++){
			for(int j = 0; j < huffmanCode.size(); j++){
				if(tempArray[i] == charArray.get(j)){
					
					System.out.print(" " + huffmanCode.get(j));
					if(i == 10){
						System.out.print("\n\t\t  ");
					}						
				}
			}
		}
		System.out.println();
	}
	
	private void storeWithNoRep(){		
		
		for(int i = 0; i < tempArray.length; i++)
		{
			if(!charArray.contains(tempArray[i])){
				charArray.add(tempArray[i]);
			}
		}	
	}
	
	private void getHuffmanCode(){
		for(int i = 0; i < charArray.size(); i++){
			String code = tree.codeMsg(root, charArray.get(i));
			
			huffmanCode.add(code);
			Tree.code = "";
		}	
	}
	
	private void countFreq(){
		for(int i = 0; i < charArray.size(); i++){
			for(int j = 0; j < tempArray.length; j++){
				if(charArray.get(i) == tempArray[j]){
					charCount[i]++;
				}
			}
		}		
	}
	
	public void relateToEnglish(String filename){
		
		//input distribution file		
		File file = new File("C:/Users/msi/workspace/Programming Assignments/src/" + filename + ".txt");		
		Scanner scan = null;
		ArrayList<String> words = new ArrayList<String>();			
		
		try{
			scan = new Scanner(file);			
			while(scan.hasNext()){
				words.add(scan.next());
			}
			
			letters = new String[words.size()]; 
			frequency = new double[words.size()];
			int ctr = 0;
			
			//tokenize letter distribution
			for(int i = 0; i < words.size(); i++){
				String[] token = words.get(i).split("-");
				letters[ctr] = token[0];
				frequency[ctr++] = Double.parseDouble(token[1]);			
			}
			
			for(int i = 0; i < charArray.size(); i++){
				for(int j = 0; j < letters.length; j++){
					if(charArray.get(i) == letters[j].toCharArray()[0]){
						charCount[i] = frequency[j];
					}
				}
			}
			 
			
		}catch(Exception e){
			System.out.println("\n\t\tFile not found!");
			System.exit(0);
		}		
		
	}
	
	private void insertNodesToQueueA(){

		for(int i = 0; i < charCount.length; i++){
			Node node = new Node(""+charArray.get(i), charCount[i]);			
			queue.insert(node);			
		}
	}
	
	private void insertNodesToQueueB(){

		for(int i = 0; i < letters.length; i++){
			Node node = new Node(""+letters[i], frequency[i]);			
			queue.insert(node);			
		}
	}

	
	
}