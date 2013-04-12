import com.amd.aparapi.Kernel;


public class MaxFreqPair {

	/**
	 * @param args
	 */
	
	public static char[] tempfreqtable1=new char[Global.fileLength] ;
	public static char[] tempfreqtable2=new char[Global.fileLength]; 
	public static int [] tempfreqtable3=new int[Global.fileLength] ;

	public static char[] fnlFreqTable1=new char[Global.fileLength] ;
	public static char[] fnlFreqTable2=new char[Global.fileLength]; 
	public static int [] fnlFreqTable3=new int[Global.fileLength] ;
	
    public static int tempindex=0;
	
	
	public MaxFreqPair(){
		
//		System.out.println("inMaxFReqPair");
		
	}


	
	
	public static void createFinalTable()
	{
//		char[] tempFreqTable1,tempFreqTable2; 
//		int[] tempFreqTable3;
//		
		tempfreqtable1=new char[Global.position];
		tempfreqtable2=new char[Global.position];
		tempfreqtable3=new int[Global.position];
		
        tempindex=0;
		 
		 for(int j = 0 ; j < Global.position; j++ )
		   	{
			  boolean isnew=true;
			  char char1=fnlFreqTable1[j];
			  char char2=fnlFreqTable2[j];
			   
			  
			    int i=0;
		    	while(i<tempindex)
		       	{
		          if(tempfreqtable1[i]==char1 && tempfreqtable2[i]==char2)
				  	   {
		        	    isnew=false;
		        	    break;
				  	   }
		          i++;
		       	}
		    	 
			 
			   	if(isnew)
			   	{
			   		tempfreqtable1[tempindex]=fnlFreqTable1[j];
			   		tempfreqtable2[tempindex]=fnlFreqTable2[j];
			   		tempfreqtable3[tempindex++]=fnlFreqTable3[j];
			   		
			   	}
		   	}
		 Global.globalTempindex=tempindex;
	/*	
		 System.out.println("_______________Unique Tabnles_________________"+tempindex+"-"+Global.position);
		   for(int j = 0 ; j < tempindex; j++ )
		   	{ 
			   	System.out.println(tempfreqtable1[j]+""+tempfreqtable2[j]+" : "+tempfreqtable3[j]);
			   	System.out.println((int)tempfreqtable1[j]+"-"+(int)tempfreqtable2[j]+" : "+tempfreqtable3[j]);
			   
		   	}
		 System.out.println("_______________Unique Tabnles_________________");
		*/	   
	}
	  
	
	public static char[] findmax()
	{
		Global.maxFCount=0;
		char char1 = 0,char2 = 0;
		char replace[]=new char[3];
		for(int i=0;i<tempindex-1;i++)
		{
			if(Global.maxFCount<tempfreqtable3[i])
				{
				Global.maxFCount=tempfreqtable3[i];
				 	char1=tempfreqtable1[i];
				 	char2=tempfreqtable2[i];
				 	
				}
		}
		
		System.out.println(char1+""+char2+"Max Is  "+Global.maxFCount);
		replace[0]=char1;
		replace[1]=char2;
		replace[2]=(char) Global.nextAsci++;
		return replace;
	}

	
}


