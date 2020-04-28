package clg;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class UserInfo
{
//stores username and password
static Map<String,String> hm = new HashMap<String,String>();
//array store selected seats
static boolean arr[][]=new boolean[30][30];
//user info
static String username,password,movie;
static int price=0,number_of_seats=0;
static int seatNumbers[];
}
public class Project_1 extends UserInfo
{
static boolean login()
{
if(hm.containsKey(username))
{
	String val=hm.get(username);
	if(val.equals(password))
	{
	System.out.println("\nsuccessfully login");
	return true;
	}
	else 
	{
	System.out.println("\n1login failed");
	return false;
	}
}
	System.out.println("\n2login failed");
	return false;
}
static void register()
{
hm.put(username,password);
}
static void showSeating()
{
System.out.println("\nTheater seat arrangement is as follows\n");
for(int i=1;i<=15;i++)
{
if(i==8 || i==9)
continue;
if(i<=7)
System.out.print("\t\t");
else
System.out.print("\t ");
for(int j=1;j<=30;j++)
{
if(j==6 || j==26)
{
if(i<=7)
System.out.print("\t\t"+(i*10+j)+" ");
else
System.out.print("\t"+(i*10+j)+" ");
}
else
System.out.print(i*10+j+" ");
}
System.out.println();
}
}
static void calculatePrice()
{
int total=0;
for(int i=0;i<number_of_seats;i++)
{
if(seatNumbers[i]<=60 || seatNumbers[i]>=101)
total+=100;
else
total+=150;
}
price=total;
}
static void clearScreen()
{
System.out.println("\n\n\n");
}
static void printSummary()
{
System.out.println("\n\nSummary of Ticket is as follows :");
calculatePrice();
System.out.println("--------------------------------------------------------");
System.out.println("Name\t\t |\t"+username);
System.out.println("movie\t\t |\t"+movie);
System.out.print("seat numbers\t |\t");
for(int i=0;i<number_of_seats;i++)
System.out.print(seatNumbers[i]+" ");
System.out.println("\ntotal pay\t |\t"+price+"rupees");
System.out.println("--------------------------------------------------------");
}
public static void main(String []args)
{
hm.put("admin", "admin");//default login of admin
hm.put("a", "a");//default login
//options to move to pages
int op1=0;
Scanner sc=new Scanner(System.in);
while(true)
{
System.out.println("\n\n\nPlease select one of the option");
System.out.println("\n1.Login\n2.Register\n");
op1=sc.nextInt();
System.out.print("Enter username : ");
sc.nextLine();
username=sc.nextLine();
System.out.print("Enter password : ");
password=sc.nextLine();
if(op1==1)
{
if(!login())
op1=2;
break;
}
else
register();
}
if(op1==1 && !username.equals("admin"))
{
//movies are shown
clearScreen();
System.out.println("\nEnter name of movies to Watch from list");
try {
int i=1;
File myObj = new File("src/movies.txt");
Scanner myReader = new Scanner(myObj);
while (myReader.hasNextLine()) {
String data = myReader.nextLine();
System.out.println(i+". "+data);
i++;
}
myReader.close();
}
catch (FileNotFoundException e) {
System.out.println("An error occurred.");
e.printStackTrace();
}
movie=sc.nextLine();
System.out.println("\nSelected movie is"+movie);
//seating is shown
clearScreen();
showSeating();
System.out.println("\nPlease Enter number of Seats you want to book\t");
number_of_seats=sc.nextInt();
seatNumbers=new int[number_of_seats];
System.out.println("\nPlease Enter seat numbers\t");
for(int i=0;i<number_of_seats;i++)
{
int t=sc.nextInt();
if(arr[t/10][t%10])
{
System.out.println("please enter another seat it is selected");
t=sc.nextInt();
}
arr[t/10][t%10]=true;
seatNumbers[i]=t;
}
//summary of ticket
clearScreen();
printSummary();
}
if(op1==1 &&username.equals("admin"))
{
System.out.println("\n\nWelcome admin");
System.out.println("Enter number of movies you want to enter");
int num=sc.nextInt();
try {
System.out.println("Enter movies :\n");
FileWriter myWriter = new FileWriter("src/movies.txt");
sc.nextLine();
for(int i=0;i<num;i++)
{
String str=sc.nextLine();
str+="\n";
myWriter.write(str);
}
myWriter.close();
}
catch (IOException e) {
System.out.println("An error occurred.");
e.printStackTrace();
}
System.out.println("\n\nMovies successfully written in file");
}
sc.close();
}
}
