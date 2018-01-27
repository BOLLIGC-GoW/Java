/*
	CSCI 3320 Advanced Programming
		
	Prgrm Assignment #5
	Charles Bollig

*/

//Timer help: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java
//manageResources() function -Professor Bob's code
//Randon numbers http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java

import java.util.*;
import java.lang.Math;

public class Prgrm_Assignment_5 {

	//Variables for the timer		
	public static long start = -1;
	public static long end = -1;
	public final static int TOTALTIME = 60000;

	//static var to keep track of # of Philosophers
	public static int totalNumOfPhilosophers = 1;

	//Request Resources | Free Resources | Max # of Philosophers | lock
	public final static int REQUEST = 1;	
	public final static int FREE = 2;
	public final static int MAXPHILOSOPHERS = 5;
	public static int lock = 0;

	//Resources (5 Chopsticks)
	public final static int CHOPSTICK1 = 0b1;
	public final static int CHOPSTICK1FREE = 0xff - CHOPSTICK1;
	public final static int CHOPSTICK2 = 0b10;
	public final static int CHOPSTICK2FREE = 0xff - CHOPSTICK2;
	public final static int CHOPSTICK3 = 0b100;
	public final static int CHOPSTICK3FREE = 0xff - CHOPSTICK3;
	public final static int CHOPSTICK4 = 0b1000;
	public final static int CHOPSTICK4FREE = 0xff - CHOPSTICK4;
	public final static int CHOPSTICK5 = 0b10000;
	public final static int CHOPSTICK5FREE = 0xff - CHOPSTICK5;

	//Manage the chopstick use (synchronized)
	public static synchronized boolean manageResources( int query, long resource ){

		//If ret is returned true, the operation was successful
		boolean ret = true;
		switch(query){
			case REQUEST:
				//Checking CHOPSTICK1
				if((resource & CHOPSTICK1) != 0){
					if((lock & CHOPSTICK1) != 0)
						ret =false;
				}
				//Checking CHOPSTICK2
				if((resource & CHOPSTICK2) != 0){
					if((lock & CHOPSTICK2) != 0)
						ret =false;
				}
				//Checking CHOPSTICK3
				if((resource & CHOPSTICK3) != 0){
					if((lock & CHOPSTICK3) != 0)
						ret =false;
				}
				//Checking CHOPSTICK4
				if((resource & CHOPSTICK4) != 0){
					if((lock & CHOPSTICK4) != 0)
						ret =false;
				}
				//Checking CHOPSTICK5
				if((resource & CHOPSTICK5) != 0){
					if((lock & CHOPSTICK5) != 0)
						ret =false;
				}
				//If passed all of the checks, places writes that resource into lock var
				if(ret == true){
					if((resource & CHOPSTICK1) != 0)
						lock |= CHOPSTICK1;
					if((resource & CHOPSTICK2) != 0)
						lock |= CHOPSTICK2;
					if((resource & CHOPSTICK3) != 0)
						lock |= CHOPSTICK3;
					if((resource & CHOPSTICK4) != 0)
						lock |= CHOPSTICK4;
					if((resource & CHOPSTICK5) != 0)
						lock |= CHOPSTICK5;
				}
				break;				
			case FREE:
				if((resource & CHOPSTICK1) != 0)
					lock &= CHOPSTICK1FREE;
				if((resource & CHOPSTICK2) != 0)
					lock &= CHOPSTICK2FREE;	
				if((resource & CHOPSTICK3) != 0)
					lock &= CHOPSTICK3FREE;	
				if((resource & CHOPSTICK4) != 0)
					lock &= CHOPSTICK4FREE;	
				if((resource & CHOPSTICK5) != 0)
					lock &= CHOPSTICK5FREE;
				break;	
		}
		return ret;
	}
	//Thread extended Philosopher class
	public static class Philosopher extends Thread{
		//Keeps a count of how many philosophers and which one each is
		private int philNum = -1;
		//Chopstick resouces
		private long rightChopStick = -1;
		private long leftChopStick = -1;
		//Track of how many times the Philosopher has eaten
		public int eaten = 0;

		//Constructor sets and increments the philosopher's number and total# philosophers | sets chopsticks
		public Philosopher(){
			philNum = totalNumOfPhilosophers;
			totalNumOfPhilosophers++;
			switch(philNum){
				case 1:
					leftChopStick = CHOPSTICK5;
					rightChopStick = CHOPSTICK1;
					break;
				case 2:
					leftChopStick = CHOPSTICK1;
					rightChopStick = CHOPSTICK2;
					break;
				case 3:
					leftChopStick = CHOPSTICK2;
					rightChopStick = CHOPSTICK3;
					break;
				case 4:
					leftChopStick = CHOPSTICK3;
					rightChopStick = CHOPSTICK4;
					break;
				case 5:
					leftChopStick = CHOPSTICK4;
					rightChopStick = CHOPSTICK5;
					break;
			}
		}
		//functions to manage request resources and releasing resources 
		public void releaseChopsticks(){
			long resource = this.leftChopStick | this.rightChopStick;
			manageResources(FREE, resource);	
		}
		public void eat(){
			long resource = this.leftChopStick | this.rightChopStick;
			while(!manageResources(REQUEST, resource)){
				//System.out.println("Philosopher#" +this.philNum + " was unable to eat");
				try{
					this.sleep(13);
				}catch(InterruptedException e){
					System.out.println("Got an error, buddy!");
					System.exit(0);
				}
			}
			System.out.println("Philosopher #" + this.philNum + " is now eating");
			this.eaten++;
			//simulate eat time (random time .1[s] - .2[s])
			try{			
				this.sleep(100 + (int)(Math.random() * ((200 - 100) + 1)));
			}catch(InterruptedException e){
					System.out.println("\nGot an error, buddy!\n");
					System.exit(0);
			}
			
			this.releaseChopsticks();
			System.out.println("Philosopher #" + this.philNum + " has finished eating");
			//simulate not eat time (random time .1[s] - .2[s])
			try{			
				this.sleep(100 + (int)(Math.random() * ((200 - 100) + 1)));
			}catch(InterruptedException e){
					System.out.println("\nGot an error, buddy!\n");
					System.exit(0);
			}
			
			
		}

		//implementing run
		public void run(){
			while(System.currentTimeMillis()%1000000 < end){
				this.eat();
			}
			try{
				this.sleep(250);
			}catch(InterruptedException e){
				System.out.println("Got an error, buddy!");
				System.exit(0);
			}
			System.out.println("Philosopher#" + this.philNum + " has eaten " + this.eaten + " times");		
		}
	}

	public static class DiningPhilosophers{

		static Philosopher[] diningPhilosophers = new Philosopher[MAXPHILOSOPHERS];

		//function that is called in constructor to start time
		public void startTime(){
			//Setting a Timer
			start = System.currentTimeMillis()%1000000;
			System.out.println( "\nStart time:\t" +start+"\n");
			end = (start + TOTALTIME);
			System.out.println( "\nEnd time:\t" + end+"\n");	
		}
		public DiningPhilosophers(){

			//Philosopher[] diningPhilosophers = new Philosopher[MAXPHILOSOPHERS];

			for(int i = 0;	i < MAXPHILOSOPHERS; i++)
				this.diningPhilosophers[i] = new Philosopher();
			//Starting the philosoper threads			
			this.startTime();
			diningPhilosophers[0].start();
			diningPhilosophers[1].start();
			diningPhilosophers[2].start();
			diningPhilosophers[3].start();
			diningPhilosophers[4].start();	

		}
			
	}

	//Main program
	public static void main( String [] args ){

		DiningPhilosophers hungryGuys = new DiningPhilosophers();
	}

}
