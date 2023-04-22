package com.TicketManagementSystem;

//controller which is communicating with db
import java.sql.*;

public class ticketDAO {

	Connection con=null;
	
	//method to get connection to db
	public void dbconnection()throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketSystempro","root","72041999");
		
	}
	
	//method to register passenger to db
	public int registerpassenger(passenger p1)throws Exception{
		String query="insert into passenger values(?,?,?,?,?,?,?,?)";
				
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,p1.passId);
		pst.setString(2,p1.passName);
		pst.setInt(3,p1.passPin);
		pst.setString(4, p1.passPlace);
		pst.setString(5,p1.passDest);
		pst.setInt(6,p1.passnoofTickets);
		pst.setInt(7,p1.passPrice);
		pst.setInt(8,p1.passtotalPrice);
		
		int res=pst.executeUpdate();
		return res;
		
	}
	
	public int login(String uname,int pwd)throws Exception {
		
		//fetching the user details based on username
		String query="select * from passenger where passName='"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		//checking whether we use user details or not
		if(rs.next()) {
			
			//fetching the password from db
			int password=rs.getInt(3);
			
			//matching the password
			if(password==pwd) {
				//login success
				return rs.getInt(1);
			}
			else {
				//bad password
				return 0;
			}
		}
		else {
			//unable to fetch user details
			return -1;
		}
	}
	
	public String destination(String destiny, int ticketprice,int pid) throws Exception {
		
		
		//storing the updated destination

		 String query="update passenger set passDest='"+destiny+"', passPrice="+ticketprice+" where passId="+pid;
		
		 PreparedStatement pst=con.prepareStatement(query);
		
		 int up=pst.executeUpdate();
		
		//returning updated destination
		if(up==1) {
			String res="Your destination is "+destiny+ " and per person ticket price is "+ticketprice;
		    return res;
		}
		else {
			return "sorry";
		}
			
		
	}
	public String tickets(int noftickets,int passengerid) throws Exception {
		
		//fetching user details based on passenger id
				
		String query2="select * from passenger where passId="+passengerid;
				
		Statement st=con.createStatement();
				
		ResultSet rs=st.executeQuery(query2);
		        
		rs.next();
		        //fetching available per person ticket price from db
		        int ticketprice=rs.getInt(7);
		        int totalamt;
		    	
		        //calculating total amount
		        totalamt=ticketprice*noftickets;
		     
		       
		        //storing the updated tickets and its price
				String query="update passenger set passnoofTickets ="+noftickets+" , passtotalPrice ="+totalamt+" where passId="+passengerid;
				PreparedStatement pst=con.prepareStatement(query);
				
				//returning the updated Price and no.of tickets
				int op=pst.executeUpdate();
				
				if(op==1) {
				String res="Num of tickets are "+noftickets+" and its price is "+totalamt;
				return res;
				}
				else {
					return "Something went wrong";
				}
			}
	public int canceltickets(int pwd,int passengerid)throws Exception {
		
		String 	query2="select * from passenger where passId="+passengerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		rs.next();
	   
         //checking if pwd is match from password in db
		if(pwd==rs.getInt(3))
		{
			int value=0;
			
			//if password is match ticket is cancelled
			String query="update passenger set passnoofTickets ='"+value+"', passtotalPrice ='"+value+"',passDest='"+value+"',passPrice='"+value+"' where passId="+passengerid;
			PreparedStatement pst=con.prepareStatement(query);

			pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}
	public int changepwd(int currentpwd,int newpwd, int passengerid)throws Exception{
		
		//fetching user details based on password id
		String 	query3="select * from passenger where passId="+passengerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query3);
		rs.next();
		
		//confirming existing password
		if(currentpwd==rs.getInt(3)) {
			
			//update new pwd in db
			String query="update passenger set passPin="+newpwd+" where passId="+passengerid;
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public int deleteAccount(int pwd, int passengerid)throws Exception{
		
		//fetching user details based on passenger id
		String 	query4="select * from passenger where passId="+passengerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query4);
		rs.next();
		
		//confirming pwd
		if(pwd==rs.getInt(3)) {
		
			//delete the account
			String query="delete from passenger where passId="+passengerid;
			PreparedStatement pst=con.prepareStatement(query);
		    pst.executeUpdate();
			return 1;
		}
		else {
			return 0;
		}
	}









}



			








	
	



