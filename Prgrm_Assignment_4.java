/*
	CSCI 3320 Advanced Programming
		
	Prgrm Assignment #4
	Charles Bollig

*/
import java.util.*;
public class Prgrm_Assignment_4{
	
	//binary Search to locate the position of the item in array
	public static int binarySearch(int[] array, int low, int high, int item){
	
		if(high < low)
			return -1;
		int middle = (low + high) /2;
		if(item == array[middle])
			return middle;
		if(item < array[middle])
			return binarySearch(array,low,(middle-1),item);
		else
			return binarySearch(array,(middle+1),high,item);
	}


	//find the pivot point function
	public static int findThePivot(int[] array, int start, int end){

		//safety for small array
		if(array.length == 1)
			return start;
		//base case for recursion
		if(start == end)
			return start;
		//pivot not in array
		if(end < start)
			return -1;

		int middle = (start + end)/2;
		//pivot point location
		if(middle < end && array[middle] > array[middle +1])
			return middle;
		if(middle > start && array[middle] < array[middle -1])
			return middle -1;
		//recursive pivot point locator
		if(array[start] >= array[middle])
			return findThePivot(array, start, middle -1);
		return findThePivot(array, middle +1, end);
	}


	//main program
	public static void main(String [] args){
		
		//Get input from the user
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nAn array of sorted ascending integers looks like this:\n\n" 
		+ "| 1 | 2 | 3 | 4 | 5 | 6 |\n");
		System.out.println("\nAn array that has been rotated looks like this:\n\n" 
		+ "| 3 | 4 | 5 | 6 | 1 | 2 |\n");
		System.out.println("\nPlease enter a rotated array of sorted ascending integers (Enter -1 to complete input): ");
		
		try{
			//User ArrayList to get user input
			ArrayList<Integer> tempList = new ArrayList<Integer>();
		
			int user_input = keyboard.nextInt();
			while(user_input != -1){
			tempList.add(user_input);
			user_input = keyboard.nextInt();
			}
		
			//Create array from ArrayList
			int i = 0;
			int[] prgrmArray = new int[tempList.size()];
			for(Integer n: tempList)
				prgrmArray[i++] = n;

			//Error check for legitimate array values
			for(int r = 0; r < prgrmArray.length ; r++){
				for(int s = 0; s < prgrmArray.length ; s++){
					if(s != r){
						//System.out.println("prgrmArray["+r+"]: prgrmArray["+s+"]");
						if(prgrmArray[r] == prgrmArray[s]){
							System.out.println("\nIncorrect input. Exiting program.\n");
							return;
							}
					}
				}
			}
			//Check to make sure array is rotated
			if(prgrmArray[0] < prgrmArray[prgrmArray.length -1]){
				System.out.println("\nArray is not constructed properly. Exiting.\n");
				return;
				
			}

			System.out.println("\nEnter an item to search for in the array.");
			user_input = keyboard.nextInt();
			
			//Display the array
			System.out.print("The array: ");		
			for(int n: prgrmArray)
				System.out.print(" " + n + ",");

			//Error checking array
			if(prgrmArray.length == 0){
				System.out.println("\nArray is empty. Exiting.\n");
				return;
			}
			//Have to do error checking for arrays of length=2. If not, findThePivot() gets messed up
			if(prgrmArray.length == 2){
				if(prgrmArray[0] == user_input){
					System.out.println("\n\nThe pivot element AND item is at: array[0]\n");
					return;
				}
				else if(prgrmArray[1] == user_input){
					System.out.println("\n\nThe pivot element AND item is at: array[1]\n");
					return;
				}
				else{
					System.out.println("\nThe item to be searched is not in the array. Exiting.\n");
					return;
				}
			}
			int pivot = findThePivot(prgrmArray, 0, prgrmArray.length);
			if(prgrmArray[pivot] < prgrmArray[0]){
				System.out.println("\nArray is not constructed properly. Exiting.\n");
				return;
			}
			if(prgrmArray[pivot] == user_input){
				System.out.println("\n\nThe pivot element AND item is at: array[" + pivot + "]\n");
			}
			else if(pivot != -1){		
				System.out.println("\nThe pivot element is: array[" + pivot + "] = " + prgrmArray[pivot]);	
				if(prgrmArray.length == 1)
					//Error checking user input 
					if(prgrmArray[0] != user_input){
						System.out.println("\nThe item to be searched is not in the array. Exiting.\n");
						return;
					}
					else
						System.out.println("\n\nThe pivot element AND item is at: array[0]\n");
						//Left side of array traversal
						if(prgrmArray[0] <= user_input){
							int index = binarySearch(prgrmArray, 0, pivot -1, user_input);
							if(index != -1)
								System.out.println("\nThe item is located at array[" + index + "] in the left subarray.\n");
							else
								System.out.println("\nThe item to be searched is not in the array. Exiting.\n");
					}
						//Right side of array traversal					
						else{
							int index = binarySearch(prgrmArray, pivot, prgrmArray.length-1, user_input);						if(index != -1)
								System.out.println("\nThe item is located at array[" + index + "] in the right subarray.\n");
							else
								System.out.println("\nThe item to be searched is not in the array. Exiting.\n");
						}
						
			}	
		//Error checking for bad input
		}catch(InputMismatchException e){
			System.out.println("\nIncorrect input. Exiting program.\n");
			return;		
		}
	}
	
}	

