import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanMain{
	
	private static String userInput;
	private static final String letterString = "abcdefghijklmnopqrstuvwxyz";
	private static final ArrayList<Character> lettersFromAtoZ = new ArrayList<Character>();

	public static void main(String[] args){
		
		HuffmanClass huffman = null;
		Scanner input = new Scanner(System.in);
		String stringInput = "";
		String inputChoice;
		
		
		for(int i = 0;  i < letterString.length(); i++){
			lettersFromAtoZ.add(letterString.toCharArray()[i]);			
		}		
		
		while(true){

			System.out.println("\t\t---------------------------------------");
			System.out.println("\t\t\tHuffman Coding\n");			
			
			System.out.println("\n\t\tChoose Mode:");
			System.out.println("\t\t[a] string");
			System.out.println("\t\t[b] statistical distribution");
			System.out.print("\n\t\tEnter Choice: ");

			inputChoice = input.nextLine();		
				
			if(inputChoice.length() > 2){
				System.out.println("Invalid Input! Please try again.");
			}						

			if(inputChoice.toCharArray()[0] != 'a' && inputChoice.toCharArray()[0] != 'b'){
				System.out.println("\n\t\tInvalid input! Please try again.");				
				continue;
			}
			
			String choice;
			do{				
				System.out.print("\t\tEnter String: ");
				stringInput = input.nextLine();								
				userInput = stringInput.toLowerCase();				
				
				// throws error if input string contains characters that are not letters
				for(int i = 0; i < userInput.length(); i++){
					if(!lettersFromAtoZ.contains(userInput.toCharArray()[i])){					
						//throw new java.lang.Error("String contains character(s) not on English alphabet.");
					}
				}
								
				if(inputChoice.toCharArray()[0] == 'a'){					
					huffman = new HuffmanClass(stringInput, inputChoice,"");
					
					System.out.println("\nHuffman Tree:\n");
					HuffmanClass.tree.printBinaryTree(HuffmanClass.root, 0);
					System.out.println();
					
					huffman.displayHuffmanCode(inputChoice);
				}
				if(inputChoice.toCharArray()[0] == 'b'){
					System.out.print("\t\tEnter distribution file: ");
					String distFileName = input.nextLine();	
					
					try{
						Scanner scan = new Scanner("C:/Users/msi/workspace/Programming Assignments/src/" + distFileName + ".txt");
						huffman = new HuffmanClass(stringInput, inputChoice, distFileName);
						
						System.out.println("\nHuffman Tree:\n");
						HuffmanClass.tree.printBinaryTree(HuffmanClass.root, 0);
						System.out.println();
						
						huffman.displayHuffmanCode(inputChoice);
						
					}catch(Exception e){
						System.out.println("\t\tFile not found!");
						System.exit(0);
					}
					
					
				}
				
				
				
				do{					
					System.out.println("\n\t\tChoose:");
					System.out.println("\t\t[a] input new string");
					System.out.println("\t\t[b] return to main menu.");
					System.out.print("\n\t\tEnter Choice: ");
					
					choice = input.nextLine();		
						
					if(choice.length() > 2)
						System.out.println("\t\tInvalid Input! Please try again.");
					
					if(choice.toCharArray()[0] != 'a' && choice.toCharArray()[0] != 'b')
						System.out.println("\n\t\tInvalid input! Please try again.");									
					
				}while(choice.length() > 2 || (choice.toCharArray()[0] != 'a' && choice.toCharArray()[0] != 'b'));
				
			}while(choice.toCharArray()[0] != 'b');
			
			
			
		}
		
	}

}