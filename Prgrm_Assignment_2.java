/*
	CSCI 3320 Advanced Programming
	Aarti Munjal
		
	Prgrm Assignment #2
	Charles Bollig

Assistance with algorithm from http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/13/hanoi.html

*/
import java.util.*;
import java.lang.Exception.*;

public class Prgrm_Assignment_2{

	public static class TowerClass{
	
		//Variables for the Stack (Tower) and the height, defaulted to -1 because 0 is the top index
		public Stack<Integer> tower;
		public int towerHeight;
		
		//This is for assigning each tower an index, after it is created, that can be printed
		public static int totalTowers = 0;
		private int towerNumber = totalTowers;

		//Constructor - just sets the tower index (for printing)
		public TowerClass(){

			tower = new Stack<Integer>();
			towerHeight = 0;
			totalTowers++;
			towerNumber = totalTowers;
			
		}
		
		//Returns the tower index (for easy printing)
		public int getTowerNumber(){
			
			return towerNumber;
		}

		//Setting a base height for a (the first) tower
		public void setTower(int inputHeight){

			for(int i = inputHeight-1; i >= 0; i--)
				tower.push(i);
			towerHeight = inputHeight;
			
		}

		//Returns top of the stack without modifying stack
		public Integer peekDisk(){
			if(tower.empty())
				return -1;
			else
				return tower.peek();		
		}
		
		//Simple .pop function that maintains that height of the Tower
		public Integer popDisk(){

			towerHeight--;
			return tower.pop(); 
		}

		//Simple .push function that maintains that height of the Tower
		public void pushDisk(Integer inputDisk){

			tower.push(inputDisk);
			towerHeight++;
		}

		

		//Recursive function that will keep going until the disks have been moved
		public void moveDisk(int height, TowerClass startTower,  TowerClass helpTower,  TowerClass endTower){
			
			if(height == 1){
				endTower.pushDisk(startTower.popDisk());
				System.out.println("\t" + startTower.getTowerNumber() + " -> " + endTower.getTowerNumber());
			}
			else{
				
				moveDisk(height -1, startTower, endTower, helpTower);
				endTower.pushDisk(startTower.popDisk());
				System.out.println("\t" + startTower.getTowerNumber() + " -> " + endTower.getTowerNumber());
				moveDisk(height -1, helpTower, startTower, endTower);

			}
			return;

		}
	
		//Sets tower height based on user input and calls the recursive function to move the disks
		public void moveDisksAmongTowers(int number, TowerClass finalTower, TowerClass intermediateTower){
	
			this.setTower(number);
			System.out.println("Tower[" + this.getTowerNumber() + "] Height: " + this.towerHeight + "\nTower[" + finalTower.getTowerNumber() + "] Height: " + finalTower.towerHeight + "\n");
			moveDisk(number, this, intermediateTower, finalTower);
		}
	
	}


	//This is the main program
	public static void main(String[] args){

		//Getting user's input for height of the tower
		Scanner reader = new Scanner(System.in);
		System.out.print("\nEnter the height for the tower: ");
		try{
		int numberOfDisks = reader.nextInt();

		//Making the towers
		TowerClass[] arrayOfTowers = {new TowerClass(), new TowerClass(), new TowerClass()};	

		//Note to user
		System.out.println("\nThe following steps will be used to transfer the disks from Tower[" + arrayOfTowers[0].getTowerNumber() + "] -> Tower[" + arrayOfTowers[2].getTowerNumber() + "]\n");

		//Moving disks
		arrayOfTowers[0].moveDisksAmongTowers(numberOfDisks, arrayOfTowers[2], arrayOfTowers[1]);

		//A little extra...
		System.out.println("\nTo prove that we are not just printing random information...\n");
		System.out.println("Tower[" + arrayOfTowers[0].getTowerNumber() + "] Height: " + arrayOfTowers[0].towerHeight + "\nTower[" + arrayOfTowers[2].getTowerNumber() + "] Height: " + arrayOfTowers[2].towerHeight + "\n");
		
		}catch(InputMismatchException e){
			
			System.out.println("\n糟糕啦!你必须输入数字! You must enter a number! " + e);
			System.out.println("\n请重新启动再试一次！ Please restart the program and try again!\n");
		
		}catch(OutOfMemoryError e){

			System.out.println("Your system doesn't have enough memory to complete this program. Goodbye.\n");
		}

	}



}
