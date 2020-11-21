import java.util.*;
import javax.swing.*;
import java.awt.*;

public class LZ77 {

	public static int index = 0;

	/// idx no change.
	public static void Compression() {

		/// Take Date .
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your Data to compression: ");
		String data;
		data = input.nextLine();

		/// related in Dictionary,print them.
		Vector<Integer> idx = new Vector<Integer>();
		Vector<Integer> len = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		/// related in Memory
		Vector<Integer> idx_Buff = new Vector<Integer>();
		Vector<Integer> len_Buff = new Vector<Integer>();
		Vector<String> Symbol_Buff = new Vector<String>();

		int cnt = 0;

		String temp = "";
		String temp2 = "";
		String last_substr = "";

		for (int i = 0; i < data.length(); ++i) {
			temp += data.charAt(i);
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
				temp2 += temp;
				temp = "";
			} else {
				// index =
			}

		}

		/* handling last char */

		last_substr = data.substring(temp2.length(), data.length());
		Symbol.add(last_substr.substring(last_substr.length() - 1));
		len.add(last_substr.length() - 1);
		idx.add(0);

		for (int i = 0; i < Symbol.size(); ++i) {
			System.out.println("<" + idx.get(i) + " , " + len.get(i) + " , " + Symbol.get(i) + ">");
		}
	}

	// EX.
	// 2
	// 4
	// 0 0 A
	/// 0 0 B
	/// 1 3 A
	/// 2 3 B

	public static void DeCompression() {
		int n;
		System.out.print("Enter numbers of tags: ");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();

		/// related in Dictionary,print them.
		Vector<Integer> idx = new Vector<Integer>();
		Vector<Integer> len = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		String str;
		int index;
		int length;
		int cnt = 0;
		System.out.println("Enter < Index , Length  , Syembol >");
		for (int i = 0; i < n; ++i) {
			index = input.nextInt();
			length = input.nextInt();
			str = input.nextLine();

			idx.add(index);
			len.add(length);
			Symbol.add(str);
		}

		String Data = "", temp = "";

		for (int i = 0; i < idx.size(); ++i) {
			/// New Symbol.
			if (idx.get(i) == 0) {
				temp = Symbol.get(i);
				Data += temp;
				temp = "";
			} else {
				{
					for (int k = 0; k < len.get(i); ++k) {
						temp += Symbol.get(i);
						Data += temp;
						temp = "";
					}
				}
			}
		}

		System.out.println("The data after De-Compression: " + Data);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int input;

		System.out.println();
		System.out.println(
				"                                                         <<<<  Algorithm:LZ77  >>>                                ");
		System.out.println();

		while (true) {

			System.out.println("==========================");
			System.out.println("To compression..Enter(1)");
			System.out.println("To decompression..Enter(2)");
			System.out.println("To exit..Enter(3)");
			System.out.println("===========================");
			System.out.print("Make a choice...");
			input = in.nextInt();

			if (input == 1) {
				Compression();
			} else if (input == 2) {
				DeCompression();
			} else if (input == 3) {
				System.out.println("Exit");
				break;
			} else {
				System.out.println("Wrong input ya m3lem :) ");
			}

		}

	}

}
