import java.util.Scanner;
import java.util.HashMap;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LZ78 {

	public static int index = 0;

	/// EX.
	/// ABAABABAA

	public static void Compression() {
		/// Take Date .
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your Data to compression: ");
		String data;
		data = input.nextLine();

		/// related in Dictionary,print them.
		Vector<Integer> idx = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		/// related in Memory
		Vector<Integer> idx_Beff = new Vector<Integer>();
		Vector<String> Symbol_Beff = new Vector<String>();

		idx_Beff.add(0);
		Symbol_Beff.add("–-----");
		int cnt = 0;

		String temp = "";

		for (int i = 0; i < data.length(); ++i) {
			temp += data.charAt(i);

			if (!Symbol_Beff.contains(temp)) {
				cnt++;
				Symbol_Beff.add(temp);
				idx_Beff.add(cnt);

				if (temp.length() == 1) {
					Symbol.add(temp);
					idx.add(0);
				} else {
					idx.add(index);
					Symbol.add(temp.substring(temp.length() - 1));
				}
				temp = "";
			} else {
				index = idx_Beff.get(Symbol_Beff.indexOf(temp));

			}
			/// Special Case
			/// To end char in string.
			if (i == data.length() - 1 && temp.length() == 1) {
				idx.add(index);
				Symbol.add("Null");
			}
		}

		for (int i = 0; i < idx.size(); ++i) {
			System.out.println("<" + idx.get(i) + " , " + Symbol.get(i) + ">");
		}

	}

	/// EX.
	/// 0 A
	/// 0 B
	// 1 A
	// 2 A
	// 4 A
	// 4 B
	// 2 B
	// 7 B
	// 8 B
	// 0 A

	public static void DeCompression() {

		int n;
		System.out.print("Enter numbers of tags: ");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();

		/// related in Dictionary,print them.
		Vector<Integer> idx = new Vector<Integer>();
		Vector<String> Symbol = new Vector<String>();

		/// related in Memory
		Vector<Integer> idx_Buff = new Vector<Integer>();
		Vector<String> Symbol_Buff = new Vector<String>();

		String str;
		int index;
		int cnt = 0;

		System.out.println("Enter < index , syembol >");
		for (int i = 0; i < n; ++i) {
			index = input.nextInt();
			str = input.nextLine();
			idx.add(index);
			Symbol.add(str);
		}

		String Data = "", temp = "";

		idx_Buff.add(0);
		Symbol_Buff.add("–-----");

		for (int i = 0; i < idx.size(); ++i) {
			if (idx.get(i) == 0) {
				++cnt;
				idx_Buff.add(cnt);
				temp = Symbol.get(i);
				Symbol_Buff.add(temp);
				Data += temp;
				temp = "";
			} else {
				temp += Symbol_Buff.get(idx.get(i));
				temp += Symbol.get(i);
				++cnt;
				idx_Buff.add(cnt);
				Symbol_Buff.add(temp);
				Data += temp;

				temp = "";

			}

		}

		System.out.println("The data after De-Compression: " + Data);
		for (int i = 0; i < idx_Buff.size(); ++i) {
			System.out.println(idx_Buff.get(i) + " | " + Symbol_Buff.get(i));
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int input;


		System.out.println();

		while (true) {

			System.out.println("==========================");
			System.out.println("To compress --> Enter(1)");
			System.out.println("To decompress --> Enter(2)");
			System.out.println("To exit --> Enter(3)");
			System.out.println("===========================");
			System.out.print("Make a choice!!");

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