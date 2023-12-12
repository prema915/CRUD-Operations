
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main{
	static String url="jdbc:mysql://localhost:3306/bank_management_system";
	static String user="root";
	static String pass="Nisha@123";
	static Scanner A=new Scanner(System.in);
	public static void menu1()  {
		System.out.println("\n\n->->->->->->->->->->->->->->->->->->->");
		System.out.println("/>/>/>/>/>/>ARCTIX BANK/>/>/>/>/>/>/>/");
		System.out.println("->->->->->->->->->->->->->->->->->->->");
		System.out.println("< SELECT OPTION 1. TO OPEN AN ACCOUNT>");
		System.out.println("< SELECT OPTION 2. TO DEPOSIT MONEY  >");
		System.out.println("< SELECT OPTION 3. TO WITHDRWAL MONEY>");
		System.out.println("< SELECT OPTION 4. TO VIEW BALANCE    >");
		System.out.println("< SELECT OPTION 5. TO VIEW PROFILE   >");
		System.out.println("< SELECT OPTION 6. TO VIEW ALL PROFILE >");
		System.out.println("< SELECT OPTION 7. TO CLOSE ACCOUNT  >");
		System.out.println("< SELECT OPTION 8. TO STOP THE PROGRAM>");
		System.out.println("<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-");
		System.out.println("<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-");

	}
public static void main(String[] agrs) throws ClassNotFoundException, SQLException
{
	while(true)
	{
		menu1();
		System.out.println("\nENTER YOUR CHOICE:");
		int a=A.nextInt();
		switch(a)  {
		case 1:System.out.println("\nENTER YOUR DETAILS TO OPEN AN ACCOUNT");
		System.out.println("KINDLY AVOID WHITESPACE CHARACTER WHILE INSERTING YOUR DETAILS\n");
		open_account();
		break;
		case 2:System.out.println("\n--------DEPOSIT SLIP----------");
		deposit_money();
		break;
		case 3:System.out.println("\n-------WITHDRAWAL SLIP---------");
		withdraw_money();
		break;
		case 4:System.out.println("\n-------BALANCE---------");
		balance();
		break;
		case 5:System.out.println("\n-----TO VIEW YOUR DETAILS-------");
		view();
		break;
		case 6:System.out.println("\nVIEW ALL ACCOUNT DETAILS:");
		view_all();
		break;
		case 7:System.out.println("\nCLOSE YOUR ACCOUNT");
		close_account();
		break;
		case 8:System.out.println("\nPROGRAM STOPPED");
		System.exit(a);
		break;
		default :System.out.println("\nENTER A VALID OPTION");
		break;
		          } 
	    	}
	}
public static void open_account() throws ClassNotFoundException, SQLException{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,user,pass);
	String query="insert into opening_account values(?,?,?,?,?,?)";
	PreparedStatement ps=con.prepareStatement(query);
	System.out.println("ENTER YOUR NAME IN CAPITAL LETTER:");
	String name=A.next();
	System.out.println("ENTER YOUR DATE OF BIRTH:");
	String dob=A.next();
	System.out.println("ENTER YOUR ADDRESS:");
	String address=A.next();
	System.out.println("ENTER YOUR PHONE NUMBER:");
	long phone_no=A.nextLong();
	System.out.println("ENTER YOUR ACCOUNT NUMBER:");
	int acc_no=A.nextInt();
	System.out.println("ENTER AN AMOUNT TO ADD IN YOUR ACCOUNT HAS A OPENING BALANCE:");
	int open_bal=A.nextInt();
	ps.setString(1, name);
	ps.setString(2,dob);
	ps.setString(3, address);
	ps.setLong(4, phone_no);
	ps.setInt(5,acc_no);
	ps.setInt(6, open_bal);
	ps.executeUpdate();
	System.out.println("\nACCOUNT HAS BEEN CREATED SUCCESSFULLY");
	System.out.println("%%%%%%<ENJOY WITH OUR SERVICES>%%%%%%\n");
}
public static void deposit_money()   throws ClassNotFoundException, SQLException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
		System.out.println("ENTER YOUR ACCOUNT NUMBER:");
		int ac=A.nextInt();
		System.out.println("ENTER AMOUNT TO DEPOSIT INTO YOUR ACCOUNT:");
		int amount=A.nextInt();
		String query="UPDATE opening_account SET balance=balance+"+amount+" WHERE account_number="+ac;
		PreparedStatement ps=con.prepareStatement(query);
		ps.executeUpdate(query);
		con.close();
		System.out.println("\n"+amount+":HAS BEEN DEPOSITED SUCCESSFULLY...");
}
public static void withdraw_money()  throws ClassNotFoundException, SQLException  {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,user,pass);
System.out.println("ENTER YOUR ACCOUNT NUMBER:");
int ac=A.nextInt();
System.out.println("ENTER AMOUNT TO WITHDRAW FROM YOUR ACCOUNT:");
int amount=A.nextInt();
String query="UPDATE opening_account SET balance=balance-"+amount+" WHERE account_number="+ac;
PreparedStatement ps=con.prepareStatement(query);
ps.executeUpdate(query);
System.out.println("\n"+amount+":HAS BEEN WITHDRAWNED SUCCESSFULLY...");
con.close();
}
public static void view()  throws ClassNotFoundException, SQLException{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,user,pass);
	System.out.println("ENTER YOUR ACCOUNT NUMBER TO VIEW YOUR DETAILS:");
	int ac=A.nextInt();
	String query="select * from opening_account where account_number="+ac;
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(query);
	while(rs.next()) {
		System.out.println("NAME:"+rs.getString(1)+"\n"+"DATE OF BIRTH:"+rs.getString(2)+"\n"+"ADDRESS:"+rs.getString(3)+"\n"+"PHONE NUMBER:"+rs.getInt(4)+"\n"+"ACCOUNT NUMBER:"+rs.getInt(5)+" \n"+"BALANCE:"+rs.getInt(6));}
	con.close();
	}
public static void view_all()		throws ClassNotFoundException, SQLException{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,user,pass);
	String query="select * from opening_account";
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(query);
	while(rs.next()){
		System.out.println("NAME:"+rs.getString(1)+"\n"+"DATE OF BIRTH:"+rs.getString(2)+"\n"+"ADDRESS:"+rs.getString(3)+"\n"+"PHONE NUMBER:"+rs.getInt(4)+"\n"+"ACCOUNT NUMBER:"+rs.getInt(5)+" \n"+"AVAILABLE BALANCE:"+rs.getInt(6));
	System.out.println("\n\n");	}
	con.close();
	}
public static void balance() throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			System.out.println("ENTER YOUR ACCOUNT NUMBER TO VIEW YOUR BALANCE");
			int ac=A.nextInt();
			String query="select * from opening_account where account_number="+ac;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				System.out.println("BALANCE:"+rs.getInt(6)+"\n");			}
			con.close();
	}

public static void close_account() throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,user,pass);
	String query="delete from opening_account where account_number=?";
	PreparedStatement ps=con.prepareStatement(query);
	System.out.println("ENTER YOUR ACCOUNT NUMBER:");
	int ac=A.nextInt();
	ps.setInt(1, ac);
	ps.executeUpdate();
	System.out.println("ACCOUNT HAS BEEN CLOSED SUCCESSFULLY");
	con.close();
	}
	}