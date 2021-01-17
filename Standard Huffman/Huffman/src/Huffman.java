import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.applet.*;
import java.awt.*;
import java.awt.im.*;
import java.awt.desktop.*;
import java.awt.print.*;


 class CompareFreq implements Comparator<HuffNode>{public int compare(HuffNode x,HuffNode y){return x.frequency - y.frequency;}}
 class Huffman {
     

    private static Scanner s;
	public static void printCode(HuffNode root, String s)
    {
        if (root.left == null && root.right == null && Character.isLetter(root.c))
        {
        	System.out.println("_________________|");
            System.out.println(root.c + "      |   " + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
        
    }
   
    public static void main(String[] args)
    {   	
        System.out.println("                                         <<< Standard Huffman Coding ALgorithm  >>>                        ");
        System.out.println("                                         <<< Bahaa El-Deen Osama CS-S1 20170078 >>>                        ");
    	System.out.println();
    	
    	System.out.print("Enter your Symbols : ");	
    	s = new Scanner(System.in);
        String str = s.nextLine();
        List<Character> charArray = new ArrayList<>();

        for(int j = 0;j<str.length();j++)
        {

            if(!(charArray.contains(str.charAt(j))))
            {
                charArray.add(str.charAt(j));
            }
        }
        int n = charArray.size();
        int[] charFreq = new int[n];
        int len=0;
        for(int j=0;j<n;j++){
            for(int k=0;k<str.length();k++){
                if(charArray.get(j)==str.charAt(k)){
                    len++;
                }
            }
            charFreq[j] = len;
            len=0;
        }
        
        PriorityQueue<HuffNode> HuffmanTree = new PriorityQueue<>(n, new CompareFreq());
        for (int i = 0; i < n; i++) 
        {
            HuffNode Node1 = new HuffNode();
            Node1.c = charArray.get(i);
            Node1.frequency = charFreq[i];
            Node1.left = null;
            Node1.right = null;
            HuffmanTree.add(Node1);
        }
        HuffNode root = null;
        while (HuffmanTree.size() > 1) 
        {
            HuffNode left = HuffmanTree.peek();
            HuffmanTree.poll();
            HuffNode right = HuffmanTree.peek();
            HuffmanTree.poll();
            HuffNode parent = new HuffNode();
            parent.frequency = left.frequency + right.frequency;
            parent.c = '-';
            parent.left = left;
            parent.right = right;
            root = parent;
            HuffmanTree.add(parent);
        }
        
        System.out.println();
        System.out.println("The output is : ");
        System.out.println("_________________");
        System.out.println("Symbol | Code ");
        printCode(root, "");
        System.out.println("_________________|");
        
    }
}
 
