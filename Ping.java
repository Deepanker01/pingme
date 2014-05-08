//Deepanker Saxena

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.io.BufferedWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ping
{


public static void main(String[] args) throws FileNotFoundException, IOException,UnknownHostException
{

FileInputStream f1 = new FileInputStream ("ips.txt");
BufferedInputStream bis = new BufferedInputStream(f1);

int c;
String d="";
char a;
while((c=f1.read())!= -1)
{
a=(char)c;
d= d+ a;
}
bis.close();

System.out.println("Welcome " + " Let's Ping"+"\n");

FileWriter fw = new FileWriter("pingresult.xls");
BufferedWriter bw = new BufferedWriter(fw);
Date date1 = new Date();
	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateFormatted = dateFormat.format(date1);
    
bw.write("Date" + "Ip" +"\t" + "Status"+"\n");

String sarr[] = d.split(";");

for(int z=0;z<sarr.length;z++)
{
//System.out.println(sarr[z] + "\n");

try {

InetAddress inet = InetAddress.getByName(sarr[z]);
          System.out.println("Sending Ping Request to " + sarr[z]+"\n");

          System.out.println("Host is reachable: "+inet.isReachable(5000) +"\n");
		  
		  if(inet.isReachable(5000))
		  {
		  String temp1=sarr[z]+ "\t"+"Alive"+ "\n";
		  bw.write(dateFormatted+"\t");
		  bw.write(temp1);
		  System.out.println(temp1);
		  }
		  else
		  {
		  String temp2 = sarr[z] +"\t"+"Dead"+"\n";
		  bw.write(dateFormatted+"\t");
	
		  System.out.println(temp2);
		  bw.write(temp2);
		  }
     }
	 catch (UnknownHostException e)
{
     System.err.println("Host does not exists"+"\n");
bw.write(sarr[z] + "\t"+"Dead ( Host Doesn't Exist )"+ "\n");
		  
	 }
catch (IOException e)
{
     System.err.println("Error in reaching the Host"+"\n");
	 bw.write(sarr[z] + "\t"+ "Dead ( Error Reaching Host ) "+"\n");
		  
}

}
bw.close();
}
}
