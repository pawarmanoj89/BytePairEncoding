
import java.io.*;

public class FileDivide {

	/**
	 * @param args
	 */
	
	public FileDivide(String ipfile) {
		// TODO Auto-generated constructor stub
		File f=new File(ipfile);
		int i=0;
		try {

			Global.fileLength=(int)f.length();
			//does not work for filesize > 4MB
			if(Global.fileLength>2048 && Global.fileLength<=32768)
			{
				Global.divideFact=Global.divideFact*2;
			}
			else if(Global.fileLength>32768)
			{
				Global.divideFact=Global.divideFact*4;
			}
			
			System.out.println("df "+Global.divideFact);
			
			Global.threads=(int) Math.ceil((float)Global.fileLength / Global.divideFact);
		} catch (Exception e) {
			
		}
		System.out.println("End");
		
	}

}
