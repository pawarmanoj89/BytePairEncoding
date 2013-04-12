import com.amd.aparapi.Kernel;


public class UnusedPairKernel  extends Kernel {// kernel for unused
	int df;
	char iparray[];
	int unused[];

	public UnusedPairKernel(int df) {
		// TODO Auto-generated constructor stub
		this.df = df;
		unused = new int[128];
	}

	public void printArray() {
		for (int k = 0; k < iparray.length; k++)
			System.out.print(iparray[k]);
		System.out.println("");
		for (int k = 0; k < 128; k++)
			if (unused[k] != 1)
				System.out.print(k + "-");
		System.out.println("");
	}

	public void setCharArray(char c[]) {
		iparray = c;
	}

	@Override
	public void run() {
		int i = getGlobalId();
		int start = i * df;
		int end = i * (df + 1);
		for (int k = 0; k < df; k++) {
			unused[(int) iparray[start + k]] = 1;
			//iparray[i*df+k]='a';
		}
	}
}