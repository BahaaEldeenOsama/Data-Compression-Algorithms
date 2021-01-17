import java.util.*;
import javax.swing.*;
import java.awt.*;

public class LZ77 {

	public static int index = 0;

	/// idx no change.
	public static void Compression() {

		/// Data input process
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the Data to compression: ");
		String data;
		data = input.nextLine();

		
		Vector<Integer> idx = new Vector<Integer>();
		Vector<Integer> len = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		
		Vector<Integer> idx_Buff = new Vector<Integer>();
		Vector<Integer> len_Buff = new Vector<Integer>();
		Vector<String> Symbol_Buff = new Vector<String>();

		int cnt = 0;

		String temp = "";
		String temp_2 = "";
		String last_substr = "";

		for (int k = 0; k < data.length(); ++k) {
			temp += data.charAt(k);
			if (!temp2.contains(temp)) {
				++cnt;
				Symbol_Buff.add(temp);
				len_Buff.add(temp.length());
				idx_Buff.add(cnt);

				/// Base case
				if (temp.length() == 1) {
					Symbol.add(temp);
					idx.add(0);
					len.add(temp.length() - 1);
				} else {
					Symbol.add(temp.substring(temp.length() - 1));
					len.add(temp.length() - 1);
					idx.add(index);
				}
				temp_2 += temp;
				temp = "";
			}
			 else 
			{
				// index =
			}

		}

		/* to solve last char problem */

		last_substr = data.substring(temp_2.length(), data.length());
		Symbol.add(last_substr.substring(last_substr.length() - 1));
		len.add(last_substr.length() - 1);
		idx.add(0);

		for (int i = 0; i < Symbol.size(); ++i) {
			System.out.println("<" + idx.get(i) + " , " + len.get(i) + " , " + Symbol.get(i) + ">");
		}
	}

	

	public static void DeCompression() {
		int n;
		System.out.print("Enter the number of tags: ");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();

		
		Vector<Integer> idx = new Vector<Integer>();
		Vector<Integer> len = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		String str;
		int index;
		int length;
		int cnt = 0;
		System.out.println("Enter < Index , Length  , Syembol >");
		for (int j = 0; j < n; ++j) {
			index = input.nextInt();
			length = input.nextInt();
			str = input.nextLine();

			idx.add(index);
			len.add(length);
			Symbol.add(str);
		}

		String Data = "", temp = "";

		for (int i = 0; i < idx.size(); ++i) 
	     {
			/// New Symbol.
			if (idx.get(i) == 0) {
				temp = Symbol.get(i);
				Data += temp;
				temp = "";
			} 
			else 
			{
  			         {

					for (int k = 0; k < len.get(i); ++k) 
					{
						temp += Symbol.get(i);
						Data += temp;
						temp = "";
					}
				}
			}
		}

		System.out.println("The data after DeCompression process is: " + Data);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int input;

		while (true) {

			System.out.println("==========================");
			System.out.println("To compress --> Enter(1)");
			System.out.println("To decompress --> Enter(2)");
			System.out.println("To exit --> Enter(3)");
			System.out.println("===========================");
			System.out.print("Make a choice!!");
			input = in.nextInt();

			if (input == 1) {
				Compression();
			} else if (input == 2) {
				DeCompression();
			} else if (input == 3) {
				System.out.println("Exit");
				break;
			} else {
				System.out.println("Wrong input !) ");
			}

		}

	}

}