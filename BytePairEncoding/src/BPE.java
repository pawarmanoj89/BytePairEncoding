import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.amd.aparapi.Kernel;

public class BPE {
	static int len;
	static int df;
	String ipfile = "d:\\ipfile.txt";
	FileDivide fd;
	int m1 = 0;
	char c[];

	BPE() {
		fd = new FileDivide(ipfile);

		len = fd.len;
		df = fd.df;
		BPEKernel bk = new BPEKernel(df);

		try {
			long time1 = System.currentTimeMillis();
			// ap.setExecutionMode(Kernel.EXECUTION_MODE.JTP);
			// ap.setExecutionMode(Kernel.EXECUTION_MODE.GPU);
			// ap.setExecutionMode(Kernel.EXECUTION_MODE.CPU);
			int z = (int) Math.ceil(len / df);
			c = new char[len];
			int k = 0;
			try {
				FileInputStream fstream = new FileInputStream(ipfile);
				DataInputStream in = new DataInputStream(fstream);
				//BufferedReader br = new BufferedReader(new InputStreamReader(in));
				// does not work for filesize > 4MB
				int ch;

				while ((ch = fstream.read()) != -1) {
					c[k] = (char) ch;
					k++;
				}
				bk.setCharArray(c);
				in.close();

			} catch (Exception e) {
			}
			for (int j = 0; j < c.length; j++)
				System.out.print(c[j]);
			System.out.println("m1-" + m1);
			bk.execute(z + 1);

			ReplaceKernel rk = new ReplaceKernel(df);
			rk.setCharArray(c);
			rk.execute(z + 1);
			rk.printArray();
			rk.dispose();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		long time1 = System.currentTimeMillis();
		// bk.printArray();
		System.out
				.println("Time taken for kenel execution in Sequential CPU mode is :"
						+ (System.currentTimeMillis() - time1));
		bk.dispose();

	}

	public static void main(String[] args) throws Exception {
		BPE b = new BPE();
	}
}

class BPEKernel extends Kernel {
	int df;
	char iparray[];
	int unused[];

	// freqtable ft=new freqtable();
	public BPEKernel(int df) {
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
			unused[(int) iparray[i * df + k]] = 1;
			// iparray[i*df+k]='a';
		}
	}
}

class ReplaceKernel extends Kernel {
	int df;
	char a[], b[], c[];
	int arraylen = 2;
	char iparray[];

	// freqtable ft=new freqtable();
	public ReplaceKernel(int df) {
		// TODO Auto-generated constructor stub
		this.df = df;
		a = new char[arraylen];
		b = new char[arraylen];
		c = new char[arraylen];
		// For setting array randomly
		a[0] = 'k';
		b[0] = 'a';
		c[0] = (char) 130;

		a[1] = 'i';
		b[1] = 'l';
		c[1] = (char) 135;
		/*
		 * for(int i=0;i<20;i++) { a[i]=(char)k++; b[i]=(char)k++;
		 * c[i]=(char)l++; }
		 */

	}

	public void setCharArray(char c[]) {
		iparray = c;
	}

	public void printArray() {
		try {
			System.out.println("Replaced array");
			FileOutputStream fos = new FileOutputStream("D:\\temp\\opfile.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			for (int k = 0; k < iparray.length; k++)
			{
				System.out.print((int) iparray[k] + "-");
				bos.write((int) iparray[k]);
			}
			System.out.println("");

			for (int k = 0; k < iparray.length; k++)
				System.out.print(iparray[k]);
			System.out.println("");

			bos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int i = getGlobalId();
		int start = i * df;
		int end = i * df + df;
		int l;
		for (int j = 0; j < arraylen; j++) {
			for (int k = start; k < end; k++) {
				if (iparray[k] == a[j] && iparray[k + 1] == b[j]) {
					iparray[k] = c[j];
					// for shifting remaining element to left
					for (l = k + 1; l < end - 1; l++)
						iparray[l] = iparray[l + 1];
					iparray[l] = '$';// this is somewhat like empty string
				}
			}
		}
	}
}