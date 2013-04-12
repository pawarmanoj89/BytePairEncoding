
import java.io.*;

public class FileDivide {

	/**
	 * @param args
	 */
	int len=0;
	int df=16;
	public FileDivide(String ipfile) {
		// TODO Auto-generated constructor stub
		File f=new File(ipfile);
		int i=0;
		try {

			len=(int)f.length();
			//does not work for filesize > 4MB
			if(len>2048 && len<=32768)
			{
				df=df*2;
			}
			else if(len>32768)
			{
				df=df*4;
			}
			
		} catch (Exception e) {
			
		}
		System.out.println("End");
		
	}

}
