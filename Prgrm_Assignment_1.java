/*
	CSCI 3320 Advanced Programming
	Aarti Munjal
		
	Prgrm Assignment #1
	Charles Bollig

*/

import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;

public class Prgrm_Assignment_1 {

	public static int findTheShortestDistance(String[] arrayOfWords, String word1, String word2){
		
		int loc1 = 50;	
		int loc2 = 50;

		List testList = Arrays.asList(arrayOfWords);

		//This will test if the words are even in the string. If not, time and space is saved
		if(testList.contains(word1) && testList.contains(word2)){

			//These are temporary variables that are stored to compare differences in relative locations
			int temp_loc1 = 0;
			int temp_loc2 =0;
			int distance = 99;
 	
			for(int i = 0; i < arrayOfWords.length; i++){
				
				//word1 in array location
				if(arrayOfWords[i].equals(word1)){
					temp_loc1 = i;
					if(Math.abs(temp_loc1 - loc2) < distance){
						loc1 = i;
						distance = Math.abs(loc1 - loc2);}
				}
				//word2 in array location
				if(arrayOfWords[i].equals(word2)){
					temp_loc2 = i;	
					if(Math.abs(temp_loc2 - loc1) < distance){
						loc2 = i;
						distance = Math.abs(loc1 - loc2);}
				}
			}
			return Math.abs(loc2 - loc1) -1;
		}
		//return the length of the array if strings are not there. No need to create new variable
		return arrayOfWords.length;		
	}
	
	public static void main(String [] args){

		String word1, word2;
		int distance;

		Scanner user_input = new Scanner(System.in);

		//This is the default string taken from Fight Club (the movie)
		String stringOfWords = "I let go. Lost in oblivion. Dark and silent and complete. I found freedom. Losing all hope was freedom.\n";
	
		System.out.println("\nThis is the String:\n\n");
		System.out.println(stringOfWords);
		System.out.println("What's the first word?");
		word1 = user_input.next();
		System.out.println("What's the second word?");
		word2 = user_input.next();
		word1 = word1.toLowerCase().replace("\n" , "");
		word2 = word2.toLowerCase().replace("\n", "");

		stringOfWords = stringOfWords.replaceAll("\\p{Punct}", "").toLowerCase().trim();
		String[] arrayOfWords = stringOfWords.split(" ");
		
		//This is the call to the function
		distance = findTheShortestDistance(arrayOfWords, word1, word2);
		
		//If word1 and word2 are not in the string, the program displays a message and exits
		if(distance != arrayOfWords.length)
			System.out.println("\nThe minimum distance between the two words is " + distance + " words.\n");
		else
			System.out.println("\nOne or more of the words was not in the string.\n");
	}
	

}


