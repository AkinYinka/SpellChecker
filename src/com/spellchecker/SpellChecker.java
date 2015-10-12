package com.spellchecker;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
	 
	public class SpellChecker {
		public static void main(String[] args) throws IOException{
			
			String file_name = args[0];
			String dictionary = args[1];
			readFile file = new readFile(file_name);
			readFile dict = new readFile(dictionary);
			WriteFile wfile = new WriteFile("document-corrected.txt", true);

			BinarySearch binsearch = new BinarySearch();
			String[] array = file.openFile();
			String[] dictArray = dict.openFile();
			String[] words;

			
			for(String a : array){
				words = a.split(" ");
				//System.out.println(Arrays.toString(words));
				
				for(String x : words){
					wfile.writeFile(binsearch.search(dictArray, x));

				}

			}
			System.out.println("file created");
		}
		public class BinarySearch {

			private static final int ELEMENT_NOT_FOUND = -1;

			private static String search(String[] array, String element, int first, int last) {
				//System.out.println(first);
				//System.out.println(last);
				
				
				if (first > last) {
					return element;
				}

				int mid = (first + last) / 2;
				String midElement = array[mid];
				//System.out.println("--- " +midElement);
				int compare = midElement.compareToIgnoreCase(element);
				
				
				if (midElement.equals(element)) {
					return "-----";
				} else if (compare > 0) {
					return search(array, element, first, mid - 1);
				} else {
					return search(array, element, mid + 1, last);
				}
			}

			public static String search(String[] array, String element) {
				return search(array, element, 0, array.length - 1);
			}
		}
		public class WriteFile {
			private String file_path;
			private boolean append_to_file = false;
			
			public WriteFile(String file_path) {
				this.file_path = file_path;
			}
			
			public WriteFile(String file_path, boolean append_to_file) {
				this.file_path = file_path;
				this.append_to_file = append_to_file;
			}
			
			public void writeFile(String textLine) throws IOException{
				FileWriter fw = new FileWriter(file_path, append_to_file);
				PrintWriter pw = new PrintWriter(fw);
				
				pw.printf("%s" + "%n", textLine);
				pw.close();
			}
			
//			public static void main(String[] args) throws IOException {
//				WriteFile rite = new WriteFile("document-corrected.txt");
//				rite.writeFile("thid id not eworking why?");
//			}
		}
		public class readFile {
			private String file_path;
			
			public readFile(String file_path){
				this.file_path = file_path;
			}
			
			public String[] openFile() throws IOException {
				FileReader fr = new FileReader(file_path);
				BufferedReader br = new BufferedReader(fr);
				
				int numberOfLines = readLines();
				String[] words = new String[numberOfLines];
				
				for(int i=0; i<numberOfLines; i++) {
					words[i] = br.readLine();
				}
				
				br.close();
				return words;
			}
			
			int readLines() throws IOException {
				FileReader fr = new FileReader(file_path);
				BufferedReader br = new BufferedReader(fr);
				
				String aLine;
				int numberOfLines = 0;
				
				while((aLine = br.readLine()) != null) {
					numberOfLines++;
				}
				
				br.close();
				return numberOfLines;
				
			}
			
		}

	}