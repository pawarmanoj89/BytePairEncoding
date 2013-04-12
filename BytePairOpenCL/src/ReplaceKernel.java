import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.amd.aparapi.Kernel;

class ReplaceKernel extends Kernel {
	int df, ipLength; // previous length of iparray
	char replace[];
	char iparray[];
	char nullchar[] = { (char) 255 };

	// freqtable ft=new freqtable();
	public ReplaceKernel(int df) {
		// TODO Auto-generated constructor stub
		this.df = df;
		Global.replacedIndex = new int[Global.threads];
		// For setting array randomly

	}

	public void setCharArray(char iparray[], char[] replace2) {
		this.iparray = iparray;
		this.replace = replace2;
		ipLength = Global.position;
	}

	public char[] printArray() {
//		System.out.println("df=" + df);
//		System.out.println("Replaced array");
//
//		System.out.println("");

		Global.position = 0;

		for (int k = 0; k < ipLength; k++) {
			// System.out.print(iparray[k]);
			if (iparray[k] != nullchar[0])
				Global.inputString[Global.position++] = iparray[k];
			// if((k+1)%16==0)
			// System.out.println("");
		}
	
	/*	System.out.println("--------------------------------pos"+ Global.position);
		for (int k = 0; k < Global.position; k++) {
			System.out.print(iparray[k]);
			// if((k+1)%16==0)
			// System.out.println("");
		}
		System.out.println("");
*/
		return iparray;
	}

	@Override
	public void run() {
		int i = getGlobalId();
		int start = i * df;
		int end = i * df + df;
		int l;
		int tempstart = start;
		int charcnt = 0;// for same character count in previous kernel
		if (end > iparray.length)
			end = iparray.length;
		while (tempstart > 0) {
			if (iparray[tempstart] != iparray[tempstart - 1]) {
				tempstart = 0;// break is not working
				charcnt--;
				// break;
			}
			tempstart = tempstart - 1;
			charcnt++;
		}
		if (charcnt % 2 != 0)
			start++;
		for (int k = start; k < end; k++) {
			if (iparray[k + 1] == nullchar[0]) {
				if (start + 16 < iparray.length)
					if (iparray[k] == replace[0]
							&& iparray[i * df + 16] == replace[1]) {
						iparray[k] = replace[2];
						iparray[i * df + 16] = nullchar[0];
					}
			} else if (iparray[k] == replace[0] && iparray[k + 1] == replace[1]) {
				iparray[k] = replace[2];
				// for shifting remaining element to left
				/*
				 * for (l = k + 1; l < end-1; l++) iparray[l] = iparray[l + 1];
				 */
				iparray[k + 1] = nullchar[0];// this is somewhat like empty
												// string
			}
		}
	}
}