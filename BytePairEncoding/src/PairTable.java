
public class PairTable {
	int a[][];
	public PairTable() {
		// TODO Auto-generated constructor stub
		a=new int[20][3];
		a[0][0]='a';
		a[0][1]='b';
		a[0][2]=128;
	}
	
	public int getValue(char k1,char k2) {
		for(int i=0;i<a.length;i++)
		{
			if(a[i][0]==(int)k1&&a[i][1]==(int)k2)
				return a[i][2];
		}
		return 255;
	}
}
