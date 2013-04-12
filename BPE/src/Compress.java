import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;


public class Compress {

	/**
	 * @param args
	 */
	public static String compress(String input) {
		int size=input.length();
		String sCompressed=""; 
		BufferedWriter output=null;
		File file=new File("F:/Eclipse_Workspace/BPE/output.txt");
		try {
			 output = new BufferedWriter(new FileWriter(file, true));
		
		
				output.write("File Size "+size);
				System.out.println("File Size "+size);
				
				int[] asciiArray=new int[size];
				char[] ch = input.toCharArray();
				
				  
				  
				for (int i =0;i<size;i++){
					asciiArray[i]=(int)ch[i]+30;
					//System.out.println(  Character.getNumericValue((ch[i])) );
					System.out.println(asciiArray[i]);
					output.write(asciiArray[i]);
					sCompressed+=(char)asciiArray[i];
					
				}
		  
				System.out.println(sCompressed);
				
				
			//		for (int i=0;i<size;i++) {
			//			for (int j=0;j<size;j++){
			//				
			//				 
			//			}
			//		}
					
					// TODO Auto-generated method stub
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sCompressed;
	}
	
	
	public int mostFrequent(String buff){
		
		
		return 0;
		
		
	}
   
	
}
