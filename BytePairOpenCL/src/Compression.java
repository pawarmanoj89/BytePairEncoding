import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Compression {

	static int length;
	FileDivide fd;
	String dict="";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String ipfile = "f:\\ipfile.txt";
		 Compression compress=new Compression(ipfile);
		 compress.fileWrite();
	}
	
	
	
	public  Compression(String ipfile) {
		fd = new FileDivide(ipfile);
		 Global.position=Global.fileLength;
		length=Global.fileLength;
		final char []string=new char[length];
    	System.out.println("length of FILE "+length);
        char replace[] =new char[2];
        Global.inputString=new char[Global.fileLength];
        
    	MaxFreqPair mfp = new MaxFreqPair();
        FreqCountKernel fck = new FreqCountKernel();
        UnusedPairKernel upk=new UnusedPairKernel(Global.divideFact);
        ReplaceKernel rk = new ReplaceKernel(Global.divideFact);
		
		  try 
	        {
		        FileInputStream fstream = new FileInputStream(ipfile);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				int ch;
				int k=0;
				
			    while ((ch = fstream.read()) != -1)
				{
			    	Global.inputString[k]=(char) ch;
			    	//string[k]=(char) ch;
					k++;
				}
			    in.close();
			    
//			    long  time1 = System.currentTimeMillis();
//			    long  time2 = System.currentTimeMillis();
		
	        }
	        
	        catch(Exception ne)
	        {
	            ne.printStackTrace();
			}
		
		  upk.setCharArray(Global.inputString);
	      upk.execute(Global.threads);
	      upk.printArray();
	      upk.dispose();
	      Global.position=Global.fileLength;
	      System.out.println("GLobPosi "+Global.position);
	      Global.globalTempindex=Global.fileLength;
	      
	     while(Global.nextAsci<255)
	    	 
	      {
	      fck.setCharArray(Global.inputString);
		  fck.execute(Global.position-1);
		  fck.copytoglobal();
	      fck.dispose();
          MaxFreqPair.createFinalTable();
	      replace=MaxFreqPair.findmax();
	      if(Global.maxFCount<=1)
			  break;
	      dict=dict+replace[0]+""+replace[1]+""+replace[2];
	      rk.setCharArray(Global.inputString,replace);
		  rk.execute(Global.threads);
		  rk.printArray();
		  rk.dispose();
		  
	      }
	      dict+="%%%";
	      System.out.println("dict="+dict);
	     
		  //while(){
		  //fck.execute(length-1);
		  // call to replacekernel
		  //}
	}

	public void fileWrite(){
		try {
			
		FileOutputStream fos = new FileOutputStream("F:\\opfile.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		System.out.println("----------------------ascii");
		for(int k=0;k<dict.length();k++)
		{
			bos.write(dict.charAt(k));
			//System.out.println((int)dict.charAt(k));
		}
		System.out.println("----------------------");
		for (int k = 0; k < Global.position; k++) {
			System.out.print((int) Global.inputString[k] + "-");
			bos.write((int) Global.inputString[k]);
		}

		bos.close();
		fos.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	} 
}
