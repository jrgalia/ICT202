import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class GrayCode {

	
	public static String grayCodeToBinary(String grayCode){
		int len = grayCode.length();
		int decimal = Integer.parseInt(grayCode, 2);	//decimal
		int bin = decimal; 
		while (decimal != 0) {
			decimal >>>= 1;
			bin ^= decimal;
		}
		String strBin = Integer.toBinaryString(bin);
		
		//adding leading zeros
		if (strBin.length() < len)
			strBin = String.format("%0" + (len - strBin.length()) + "d", 0) + strBin;

		return strBin;  
	}
	
	
	public static String binaryToGrayCode(String bin){
		int len = bin.length();
		int decimal = Integer.parseInt(bin, 2);			//decimal
		int grayCode = decimal >>> 1 ^ decimal;			//gray code in decimal
		String strGrayCode = Integer.toBinaryString(grayCode);
		
		//adding leading zeros
		if (strGrayCode.length() < len)
			strGrayCode = String.format("%0" + (len - strGrayCode.length()) + "d", 0) + strGrayCode;
			
		return strGrayCode;
	}
	
	
	public static void conversionTable(int n, String fileName) throws IOException {
		double max = Math.pow(2, n);
		
		String binary = null;
		String pad = null;
		StringBuffer buffer = new StringBuffer("DECIMAL\tBINARY\tGRAY CODE\n");
		
		for (int i=0; i<max; i++) {
			binary = Integer.toBinaryString(i);
			pad = (n==binary.length())? "" : String.format("%0" + (n-binary.length()) + "d", 0);
			String s = binaryToGrayCode(pad + binary);
			buffer.append(i).append("\t\t").append(pad).append(binary);
			buffer.append("\t\t").append(s).append("\n");
		}
		System.out.println(buffer);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(buffer.toString());
		writer.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int menu = -1;
		String grayCode = null;
		String bin = null;
		do {
			System.out.print("\n\nMENU:\t\n\t1-BINARY TO GRAY CODE\n\t2-GRAY CODE TO BINARY\n\t3-TABLE\n\t0-EXIT\nmenu:_");
			menu = input.nextInt();
			switch(menu){
			case 1:
				System.out.print("ENTER BINARY NUMBER: ");
				bin = input.next();
				grayCode = binaryToGrayCode(bin);
				System.out.println("BINARY\t\t:" + bin + "\nGRAY CODE\t:" + grayCode);
				break;
			case 2:
				System.out.print("ENTER GRAY CODE: ");
				grayCode = input.next();
				bin = grayCodeToBinary(grayCode);
				System.out.println("GRAY CODE\t:" + grayCode + "\nBINARY\t\t:" + bin);
				break;
			case 3:
				System.out.print("HOW MANY BITS:");
				int n = input.nextInt();
				System.out.print("ENTER FILE PATH & NAME:");
				String fileName = input.next();
				conversionTable(n, fileName);
				break;
			case 0:
				System.out.println("thank you");
				break;
			default:
				System.out.println("please enter correct menu");
			}
			
			
		} while (menu != 0);
		
		input.close();
	}

}

