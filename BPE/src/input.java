import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class input {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream fstream;
		String strLine="", inputString="";
		try {
					 fstream = new FileInputStream("F:/Eclipse_Workspace/BPE/data.txt");
					 DataInputStream in = new DataInputStream(fstream);
					 BufferedReader br = new BufferedReader(new InputStreamReader(in));
					  
					  //Read File Line By Line
					  while ((strLine = br.readLine()) != null) 
					  {
						  		// 	Print the content on the console
					           // System.out.println (strLine);
						        inputString+=strLine;
			          }
		    } 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file NOt FOund");
			e.printStackTrace();
		     }
		  // Get the object of DataInputStream
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println(Compress.compress(inputString));
		

	}

}
