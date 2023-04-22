package com.TicketManagementSystem;

import java.util.*;
public class ticketMain {

	public static void main(String[] args)throws Exception {
		
		Scanner sc=new Scanner(System.in);
		ticketDAO dao=new ticketDAO();
		passenger p1=new passenger();
		
		//reading data  from user
		System.out.println("***********Welcome to Ticket Management*************");
		System.out.println("Press 1 for Registration \n Press 2 for Login");
		int op=sc.nextInt();
		switch(op) {
		
		case 1->{
        System.out.println("Enter Passenger Id");
        int pid=sc.nextInt();
	    
	    System.out.println("Enter Passenger Pin");
	    int ppin=sc.nextInt();
	    
	    System.out.println("Enter Passenger Name");
	    sc.nextLine();
	    String pname=sc.nextLine();
	   
	    System.out.println("Enter Passenger Place");
	    String pplace=sc.nextLine();
	    
	  
	    p1.passId=pid;
	    p1.passPin=ppin;
	    p1.passName=pname;
	    p1.passPlace=pplace;
	   
	    
	   
	  
	    //getting connection to db
	    dao.dbconnection();
	    int res=dao.registerpassenger(p1);
	    
	    //if register is success response=1 otherwise 0
	    if(res==1)
	    {
	    System.out.println(" passenger Account creation is successfull\n Thank You for Registering!");
	    }
	    else {
	    	System.out.println("Something went wrong");
	    }
		}
		case 2->{
			System.out.println("*******welcome to Login page********");
			
			//reading username and password for login
			System.out.println("Enter Username");
			sc.nextLine();
			String uname=sc.nextLine();
			System.out.println("Enter password");
			int pwd=sc.nextInt();
			
			//connection to db
			dao.dbconnection();
			
			//login method
			int res=dao.login(uname, pwd);
			
			//handling the response
			if(res==0) {
				System.out.println("Username or Password is Invalid");
			}
			else if(res==-1) {
				System.out.println("Unable to find details");
			}
			else {
				System.out.println("Login Successfull");
				System.out.println("Press 1 for select destination\n Press 2 for no. of tickets \n Press 3 for cancel tickets \n Press 4 for change pin\n Press 5 for delete account");
			    int ops=sc.nextInt();
			    sc.nextLine();
			
			switch(ops) {
			 
			      case 1->{
				         // selecting destination from user
				         System.out.println("Please select your destination from the following location \n Press 1 to  Delhi \n Press 2 for Kashmir\n Press 3 for  Vizag \n Press 4 for  Goa\n Press 5 for Chennai");
				         int loc=sc.nextInt();
				         int ticketprice;
				         String destiny;
			    
				switch(loc) {
	            case 1->{
	            	
	            	ticketprice=600;
	            	destiny="Delhi";
	            	String resl=dao.destination(destiny,ticketprice,res);
	            	System.out.println("Destination selection successfull\n"+resl);
			    	
	            }
                case 2->{
	            	
                	ticketprice=800;
	            	destiny="Kashmir";
	                String resl=dao.destination(destiny,ticketprice,res);
	                System.out.println("Destination selection successfull\n"+resl);
	                
	            }
	            case 3->{
	            	
	            	ticketprice=500;
	               	destiny="Vizag";
	            	String resl=dao.destination(destiny,ticketprice,res);
	            	System.out.println("Destination selection successfull\n"+resl);
	                
	            }
	            case 4->{
	            	
	            	ticketprice=800;
	                destiny="Goa";
			    	String resl=dao.destination(destiny,ticketprice,res);
			    	System.out.println("Destination selection successfull\n"+resl);
	               
	            }
	            case 5->{
	            	
	            	ticketprice=700;
	            	destiny="Chennai";
	            	String resl=dao.destination(destiny,ticketprice,res);
	            	System.out.println("Destination selection successfull\n"+resl);
		            
	            }
	            
	            default->{
	                
	            	System.out.println("Invalid destination...! Select between 1 and 5");
	                
	                    }
				
			          }
			        }
			
			      //ticket booking for no of person
			case 2->{
				System.out.println("Number of tickets you are booking");
		    	int noftickets=sc.nextInt();
		    String status=dao.tickets(noftickets,res);
		    	System.out.println("updated successful\n"+status);
		    	}
			
			
			
			
		
		 //canceling the ticket using pwd
         case 3->{
  		
       	  System.out.println("Enter Password to cancel");
				int cnfmpwd=sc.nextInt();
				int status=dao.canceltickets(cnfmpwd,res);
			if(status==1) {					
				System.out.println("Your ticket is cancelled");
			       }
			
			else {
				System.out.println("Something went wrong");				
				}
	         }
		
		
		//changing the password
		 case 4->{
				
				System.out.println("Enter current password");
				int currentpwd=sc.nextInt();
				System.out.println("Enter new password");
				int newpwd=sc.nextInt();
				
				int status=dao.changepwd(currentpwd, newpwd, res);
				if(status==1) {
					System.out.println(" Your Password is Changed...");
				}
				else {
					System.out.println("Something went wrong");
				}
				
		        }
		 //deleting the account using pwd
		    case 5->{
		    	
				System.out.println("Enter Password to delete");
				int pass=sc.nextInt();
				int status=dao.deleteAccount(pass, res);
				if(status==1) {
					System.out.println("Your account is deleted\n Good Bye!....");
				}
				else {
					System.out.println("Something went wrong");
				}
				}
		    
		}
	
		}
	}
}

	    sc.close();
	

	}
}
	    
	    
	    
	    
		
		

