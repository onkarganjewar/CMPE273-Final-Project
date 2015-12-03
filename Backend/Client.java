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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 ArrayList<Jedis> al = new ArrayList<Jedis>();
			Jedis a = new Jedis("127.0.0.1",6377);
			Jedis b = new Jedis("127.0.0.1",6388);
			Jedis c = new Jedis("127.0.0.1",6389);
			Jedis d = new Jedis("127.0.0.1",6390);

			String ared = a.toString();

			String bred = b.toString();

			String cred = c.toString();

			String dred = d.toString();





			al.add(a);
	        al.add(b);
	        al.add(c);
	        al.add(d);

	        ArrayList<String> values = new ArrayList<String>();

			Scanner sc1 = new Scanner (System.in);
			Scanner sc2 = new Scanner (System.in);
			System.out.print("No of keys you want to enter: ");
			int no = sc1.nextInt();
			for(int i=0;i<no;i++){
				System.out.println("Enter Value for ["+(i+1)+"] key :");
				values.add(sc2.nextLine());
				System.out.println("Inserted!!\n");
			}



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
	        int acc = 0;
	        int bcc = 0;
	        int ccc = 0;
	        int ddd = 0;
    ConsistentH<Jedis> consistentHash = new ConsistentH<Jedis>(hf, 100, al);
	        for (String val : values) {
	        	 // System.out.println("userid is "+val);

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


	        csvOutput.close();
	}
}
