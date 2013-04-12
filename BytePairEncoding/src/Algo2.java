import java.beans.FeatureDescriptor;
import java.io.FileReader;
import java.io.Reader;

import com.bytepair.freqtable;


public class Algo2 {

	/**
	 * @param args
	 */
	public Algo2() {
		// TODO Auto-generated constructor stub
		int c;
		char c1,c2;
		String ipstr="";
		int unused[]=new int[127];
		FreqTable ft=new FreqTable();
		try
		{
			Reader in = new FileReader("d:\\ipfile.txt");
			c1=(char) in.read();
			while((c=in.read())!=-1)
			{
				//System.out.print((char)c);
				ipstr+=(char)c;
				c2=c1;
				c1=(char)c;
				unused[c]=1;
				String temp=c1+"";
				temp+=c2;
				ft.add(temp);
				ft.print();
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		System.out.println(ipstr);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Algo2 aa=new Algo2();
	}

}
