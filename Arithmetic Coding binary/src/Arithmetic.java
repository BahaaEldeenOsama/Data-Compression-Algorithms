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
	
	 static String decimalToBinary(double num, int k_prec) 
	    { 
	        String binary = ""; 
	  
	        // Fetch the integral part of decimal number 
	        int Integral = (int) num; 
	  
	        // Fetch the fractional part decimal number 
	        double fractional = num - Integral; 
	  
	        // Conversion of integral part to 
	        // binary equivalent 
	        while (Integral > 0)  
	        { 
	            int rem = Integral % 2; 
	  
	            // Append 0 in binary 
	            binary += ((char)(rem + '0')); 
	  
	            Integral /= 2; 
	        } 
	  
	        // Reverse string to get original binary 
	        // equivalent 
	        binary = reverse(binary); 
	  
	        // Append point before conversion of 
	        // fractional part 
	        ///  binary += ('.'); 
	  
	        // Conversion of fractional part to 
	        // binary equivalent 
	        while (k_prec-- > 0) 
	        { 
	            // Find next bit in fraction 
	            fractional *= 2; 
	            int fract_bit = (int) fractional; 
	  
	            if (fract_bit == 1) 
	            { 
	                fractional -= fract_bit; 
	                binary += (char)(1 + '0'); 
	            } 
	            else
	            { 
	                binary += (char)(0 + '0'); 
	            } 
	        } 
	  
	        return binary; 
	    } 
	  
	    static String reverse(String input)  
	    { 
	        char[] temparray = input.toCharArray(); 
	        int left, right = 0; 
	        right = temparray.length - 1; 
	  
	        for (left = 0; left < right; left++, right--)  
	        { 
	            // Swap values of left and right  
	            char temp = temparray[left]; 
	            temparray[left] = temparray[right]; 
	            temparray[right] = temp; 
	        } 
	        return String.valueOf(temparray); 
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
	public  String ArithmeticCompress(String Symbols) {
    //    String data = read_file("Original Data.txt");
		String BinaryCode= "";
		int currsymbol;
		double lower = 0.0;
		double higher = 1.0;
		double range = 1.0;  
		char[] input = Symbols.toCharArray(); /// I wanna get symbol and put them char array
		double lowrange[] = new double[probability.length];
		double highrange[] = new double[probability.length];
		
		
		
		double Temp_Last_upper = 0;
		
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
			range = higher - lower;
			currsymbol = this.GetIndexOfChar(symbols, input[i]);
			lower = lower + range * lowrange[currsymbol];
			higher = lower + range * highrange[currsymbol];
			
			Temp_Last_upper = higher;   /// will exit form this loop = 0.54112000000001.
			
			
			while(true)
            {             
            	if(lower>=0.5 && higher<1)  /// E2 Scaling (Upper - 0.5) * 2   and (Lower - 0.5 ) * 2 ... add 1 bit .
            	{
            	   lower = (lower - 0.5 ) * 2 ;
            	   higher = (higher - 0.5) * 2;
            	   BinaryCode+='1';
            	}
            	else if(lower>=0 && higher<0.5) /// E1 Scaling (Upper * 2)   and (Lower * 2) ... add 0 bit .
            	{
            		lower = (lower) * 2 ;
            		higher = (higher) * 2;
              	    BinaryCode+='0';	
            	}
            	else 
            	{
            		break;
            	}
            
         	   /*System.out.println("Lower is " + lower);
         	   System.out.println("Upper is " + higher);
         	   System.out.println("");*/
            }
			
		}
		
		
		String Binary = decimalToBinary( Temp_Last_upper, 6);  /// 6 the " K " bits.
		BinaryCode+=Binary;
		
		
		return BinaryCode;
	}
	
	public static HashMap<Character, Double> rangeLow  = new HashMap<Character, Double>();
    public static HashMap<Character, Double> rangeHigh = new HashMap<Character, Double>();
    public static char lastChar = ' ';
	
    

    
	public static void main(String args[]) {
		char[] symbolchars = { 'A', 'B', 'C' };
		double[] probability = { 0.8, 0.02, 0.18 };
		Arithmetic arithmetic = new Arithmetic(symbolchars, probability);
		String compressioncode = arithmetic.ArithmeticCompress("ACBA");
		rangeLow.put('A',0.0);
        rangeHigh.put('A',0.8);
        
        rangeLow.put('B',0.8);
        rangeHigh.put('B',0.82 );
        
        rangeLow.put('C',0.82);
        rangeHigh.put('C',1.0);
        
        System.out.println("                                     ====== Arithmetic codeing binary ========              ") ; 
    	System.out.println("                                            <<<Bahaa El Deen Osama.>>>                   ");
		System.out.println("");
		System.out.println("");
        System.out.println("<<<Compressed>>>") ; 
        System.out.println(compressioncode); 
       
	}
	
}