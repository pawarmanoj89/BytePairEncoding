import com.amd.aparapi.Kernel;


public class FreqCountKernel extends Kernel {



	char[] input,freqtable1,freqtable2 ; 
	int length;
	int[]  freqtable3;
 
    @Override

    public void run() {
    	
    	
    	int index=0;
        int gid = getGlobalId();
        
        char char1 = input[gid] ;
		char char2 = input[gid+1];
		int count=0;
		
		for(int i=0;i<length-1;i++)
		   	if(input[i]==char1 && input[i+1]==char2)
		         count++;
		
		  freqtable1[gid]=char1;
		  freqtable2[gid]=char2;
		  freqtable3[gid]=count;
 	}

    
    public FreqCountKernel()

    {
    	length=Global.position;
    	freqtable1=new char[length];
    	freqtable2=new char[length];
        freqtable3=new int[length];
        
        
        // System.out.println(input);
        
    }

    public void setCharArray(char iparray[]){
		this.input = iparray;
		length=Global.position;
		
	}
 

   public void printResults()
   {
	   for(int j = 0 ; j < length-1; j++ )
		  	System.out.println(freqtable1[j]+""+freqtable2[j]+" : "+freqtable3[j]);
   }
 
   
   public void copytoglobal()
   {
	   for(int j = 0 ; j < length-1; j++ )
	   	{ 
		   MaxFreqPair.fnlFreqTable1[j]=freqtable1[j];
          MaxFreqPair.fnlFreqTable2[j]=freqtable2[j];
          MaxFreqPair.fnlFreqTable3[j]=freqtable3[j];
    		               
	   	}
	      
   }
  

}
