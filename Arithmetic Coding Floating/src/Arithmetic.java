import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arithmetic {

	char[] symbols;
	double[] probability;

	Arithmetic(char[] symbols, double[] probability) {
		this.symbols = symbols;
		this.probability = probability;
	}

	Scanner sc;

//function search for index of char  ; 
	public int GetIndexOfChar(char[] input, char c) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == c) {
				return i;
			}
		}
		return -1;
	}

	public double ArithmeticCompress(String Symbols) {
		// String data = read_file("Original Data.txt");
		int currsymbol;
		double lower = 0.0;
		double higher = 1.0;
		double range = 1.0;
		char[] input = Symbols.toCharArray(); /// I wanna get symbol and put them char array
		double lowrange[] = new double[probability.length];
		double highrange[] = new double[probability.length];

		for (int i = 0; i < lowrange.length; i++) 
		{
			lowrange[i] = 0;
			highrange[i] = probability[i];
			highrange[i] = lowrange[i] + probability[i];
			
			for (int j = 0; j < i; j++) 
			{
				lowrange[i] = lowrange[i] + probability[j];
			}

		}
		for (int i = 0; i < input.length; i++) 
		{
			currsymbol = this.GetIndexOfChar(symbols, input[i]);
			lower = lower + range * lowrange[currsymbol];
			higher = lower + range * highrange[currsymbol];
			range = higher - lower;
		}
		System.out.println("                                     ====== Arithmetic codeing Floating ========              ") ; 
		System.out.println("                                            <<<Bahaa El Deen Osama.>>>                   ");
		System.out.println("");
		System.out.println("");
		System.out.println("Range of symbol between " + lower + " and " + higher);
		return (higher + lower) / 2;
	}

	public static HashMap<Character, Double> rangeLow = new HashMap<Character, Double>();
	public static HashMap<Character, Double> rangeHigh = new HashMap<Character, Double>();
	public static char lastChar = ' ';

	public static String decompress(Double code, int numSym) {
		Double Lower = 0.0, Upper = 1.0, newCode = code;
		String temp = "";
		for (int i = 0; i < numSym; ++i) {
			for (Character c : rangeLow.keySet()) {
				if (newCode > rangeLow.get(c) && newCode < rangeHigh.get(c)) {
					temp += c;
					Lower = Lower + (Upper - Lower) * rangeLow.get(c);
					Upper = Lower + (Upper - Lower) * rangeHigh.get(c);
					newCode = (code - Lower) / (Upper - Lower);
					break;
				}
			}
		}
		return temp;
	}

	public static void main(String args[]) {
		char[] symbolchars = { 'A', 'B', 'C' };
		double[] probability = { 0.8, 0.02, 0.18 };
		Arithmetic arithmetic = new Arithmetic(symbolchars, probability);
		double compressioncode = arithmetic.ArithmeticCompress("ACBA");
		rangeLow.put('A', 0.0);
		rangeHigh.put('A', 0.8);

		rangeLow.put('B', 0.8);
		rangeHigh.put('B', 0.82);

		rangeLow.put('C', 0.82);
		rangeHigh.put('C', 1.0);
		
         
		System.out.println("==================================== Compressed ================================");
		System.out.println(compressioncode);
		System.out.println("==================================== Decompressed ==============================");
		System.out.println(decompress(compressioncode, 4));

	}
}