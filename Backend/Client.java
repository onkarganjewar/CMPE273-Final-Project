package redis;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import redis.clients.jedis.Jedis;

public class Client{


public static enum Mode {
		    ALPHA, ALPHANUMERIC, NUMERIC 
		}

public static String generateRandomString(int length, Mode mode) throws Exception {

	StringBuffer buffer = new StringBuffer();
	String characters = "";

	switch(mode){
	
	case ALPHA:
		characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		break;
	
	case ALPHANUMERIC:
		characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		break;

	case NUMERIC:
		characters = "1234567890";
	    break;
	}
	
	int charactersLength = characters.length();

	for (int i = 0; i < length; i++) {
		double index = Math.random() * charactersLength;
		buffer.append(characters.charAt((int) index));
	}
	return buffer.toString();
}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 ArrayList<Jedis> al = new ArrayList<Jedis>();
			Jedis a = new Jedis("127.0.0.1",6377);
			Jedis b = new Jedis("127.0.0.1",6388);
			Jedis c = new Jedis("127.0.0.1",6389);
			Jedis d = new Jedis("127.0.0.1",6390);
			Jedis e = new Jedis("127.0.0.1",6392);

			String ared = a.toString();

			String bred = b.toString();

			String cred = c.toString();
			String dred = d.toString();
			String ered = e.toString();

			al.add(a);
	        al.add(b);
	        al.add(c);
	        al.add(d);
	        al.add(e);
		    
			
	    	ArrayList<String> data = new ArrayList<String>();
	    	
			for(int i=0;i<600;i++){
			String tempp = generateRandomString(10,Mode.ALPHANUMERIC);
		//	System.out.println(generateRandomString(10,Mode.ALPHANUMERIC));
			data.add(tempp);
			//System.out.println(generateRandomString(10,Mode.ALPHANUMERIC));
			}
			//System.out.println(data);
			System.out.println("These are the instances running right now ");
			System.out.println("1]6377 2]6388 3]6389  4]6390  5]6392");
			System.out.println("Do you want to remove any node from the system? 1.Yes 2.No");
			Scanner sc2 = new Scanner (System.in);
			String tep = sc2.nextLine();
			
			while (tep.equalsIgnoreCase("yes"))
			{
			System.out.print("Enter the port no of instance that you want to delete: ");
			Scanner sc3 = new Scanner (System.in);
				switch(sc3.nextLine())
				{
				case "6377" : al.remove(a);
								break;
								

				case "6388" : al.remove(b);
								break;
			
				case "6389" : al.remove(c);
				break;
					

				case "6390" : al.remove(d);
								break;
								

				case "6392" : al.remove(e);
								break;
							
								
				}
				tep = "no";

			}

			
					
					System.out.println("out of the loop");
					
					
					

		    
	        ArrayList<String> values = new ArrayList<String>();
	    	
	      /*  Scanner sc1 = new Scanner (System.in);
			Scanner sc2 = new Scanner (System.in);
			System.out.print("No of keys you want to enter: ");
			int no = sc1.nextInt(); 
			for(int i=0;i<no;i++){
				System.out.println("Enter Value for ["+(i+1)+"] key :");
				values.add(sc2.nextLine()); 
				System.out.println("Inserted!!\n");
			}
		*/


			String outputFile = "final.csv";
			// String outputFile = "/Applications/XAMPP/xamppfiles/htdocs/cmpe273/proj.csv";
	    	boolean alreadyExists = new File(outputFile).exists();
	    	CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
	    	CsvReader csreader = new CsvReader("final.csv");

	    //	System.out.println(csreader.readHeaders());
	    	if (!alreadyExists)
			{
	    	//	System.out.println("****************************In the loop");
				csvOutput.write("node");
				csvOutput.write("no of keys");
				csvOutput.endRecord();
			}


	        HashFunction hf = Hashing.md5();
	        int ac = 1;
	        int bc = 1;
	        int cc = 1;
	        int dc = 1;
	        int ec = 1;
	        
	        int acc = 0;
	        int bcc = 0;
	        int ccc = 0;
	        int ddd = 0;
	        int eee = 0;
	        
    ConsistentH<Jedis> consistentHash = new ConsistentH<Jedis>(hf, 100, al);
	     //   for (String val : values) {
	        	 // System.out.println("userid is "+val);
	        	for (String val : data) {
	        //	System.out.println(consistentHash.get(val).toString());
	        	String temp = consistentHash.get(val).toString();
	        	String temp1 = csreader.get("node");

	        	if(temp.equalsIgnoreCase(ared))
	            			{
	        	
	            				a.set(Integer.toString(ac), val );
	            				acc++;
	            				ac++;

	            			}
	            			else if(temp.equalsIgnoreCase(bred))
	            				{

	            					b.set(Integer.toString(bc),val);
	            					bc++;
	            					bcc++;
	            					
	            				}
	            				else if(temp.equalsIgnoreCase(cred))
	            					{

	            						c.set( Integer.toString(cc),val);
	            						cc++;
	            						ccc++;
	            					}
	            					else if(temp.equalsIgnoreCase(dred))
	            						{

	            							d.set(Integer.toString(dc),val);
	            							dc++;
	            							ddd++;
	            						}
	        	
	            					else if(temp.equalsIgnoreCase(ered))
            						{

            							e.set(Integer.toString(ec),val);
            							ec++;
            							eee++;
            						}


	        }
            System.out.println("End");

			csvOutput.write("6377");
			csvOutput.write(Integer.toString(acc));
			csvOutput.endRecord();


			csvOutput.write("6388");
			csvOutput.write(Integer.toString(bcc));
			csvOutput.endRecord();


			csvOutput.write("6389");
			csvOutput.write(Integer.toString(ccc));
			csvOutput.endRecord();


			csvOutput.write("6390");
			csvOutput.write(Integer.toString(ddd));
			csvOutput.endRecord();

			csvOutput.write("6392");
			csvOutput.write(Integer.toString(eee));
			csvOutput.endRecord();

			
	        csvOutput.close();
	}



}

	

