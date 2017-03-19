import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class fallbackDFA {
	public static ArrayList<Integer> states = new ArrayList<Integer>();
	public static ArrayList<String> alphabet = new ArrayList<String>();
	private static ArrayList<String[]> transitions = new ArrayList<String[]>();
	public static ArrayList<Integer> acceptStates = new ArrayList<Integer>();
	public static int startstate;
	
	public static HashMap<Integer, String> label = new HashMap<Integer, String>();
	public static HashMap<String, String> regularDefinition = new HashMap<String, String>();

	//private static ArrayList<String[]> label = new ArrayList<String[]>();
	//private static ArrayList<String[]> regularDefinition = new ArrayList<String[]>();

	// private static final String FILENAME =
	// "C:/Users/FRRR/Desktop/GUC/Semester 10/Advanced Computer Lab/Task2/fallbackdfa.in";

	public fallbackDFA() {
		try {
			BufferedReader buf = new BufferedReader(
					new FileReader(
							"C:/Users/FRRR/Desktop/GUC/Semester 10/Advanced Computer Lab/Task2/fallbackdfa.in"));
			ArrayList<String> words = new ArrayList<>();
			String lineJustFetched = null;
			String[] wordsArray;
			int lines = 0;

			while (true) {
				lineJustFetched = buf.readLine();
				// lines++;
				if (lineJustFetched == null) {
					break;
				} else {
					lines++;
					wordsArray = lineJustFetched.split("\t");
					for (String each : wordsArray) {
						if (!"".equals(each)) {
							words.add(each);
						}
					}
				}
			}

			// get all states
			System.out.println("The states are: ");

			String allstates = words.get(1);
			String[] st = allstates.split(",");
			for (int i = 0; i < st.length; i++) {
				states.add(Integer.parseInt(st[i]));

			}
			System.out.println(states);
			System.out.println();

			System.out.println("The alphabets are: ");

			// all alphabets
			String allAlpha = words.get(3);
			String[] alpha = allAlpha.split(",");
			for (int i = 0; i < alpha.length; i++) {
				alphabet.add((alpha[i]));
			}
			System.out.println(alphabet);

			// all transitions
			int index = words.indexOf("#start state");
			// System.out.println(index);

			for (int i = 5; i < index; i++) {

				String[] a = words.get(i).split(",");
				getTransitions().add(a);

			}
			System.out.println();

			System.out.println("The transitions are: ");

			for (int i = 0; i < getTransitions().size(); i++) {
				String[] temp = getTransitions().get(i);
				for (int j = 0; j < temp.length; j++) {

					System.out.print(temp[j] + ",");

				}
				System.out.println();
			}
			// start state
			System.out.println();
			System.out.println("The initial state is: ");

			startstate = Integer.parseInt(words.get(index + 1));
			System.out.println(startstate);

			// accept states
			System.out.println();
			System.out.println("The accept states are: ");

			String allAcceptStates = words.get(index + 3);
			String[] accept = allAcceptStates.split(",");
			for (int i = 0; i < accept.length; i++) {
				acceptStates.add(Integer.parseInt(accept[i]));
			}
			System.out.println(acceptStates);

			// labels

			int indexLabel = words.indexOf("#label");
			int indexRegularDefinition = words.indexOf("#regulardefinition");

			for (int i = indexLabel + 1; i < indexRegularDefinition; i++) {

				String[] lb = words.get(i).split(",");
					label.put(Integer.parseInt(lb[0]), lb[1]);

				
			}
			System.out.println();

			System.out.println("The labels are: ");
			
			Map<Integer, String> reversedMap = new TreeMap<Integer, String>(label);

			//then you just access the reversedMap however you like...
			for (Map.Entry entry : reversedMap.entrySet()) {
			    System.out.println(entry.getKey() + ", " + entry.getValue());
			}
		

		/*	for (int i = 0; i < label.size(); i++) {
				String[] temp = label.get(i);
				for (int j = 0; j < temp.length; j++) {

					System.out.print(temp[j] + ",");

				}
				System.out.println();
			}*/
			  
			// regular definitions

			for (int i = indexRegularDefinition + 1; i < lines; i++) {

				String[] reg = words.get(i).split(",");
				regularDefinition.put(reg[0], reg[1]);

			}
			System.out.println();

			System.out.println("The regular definitions are: ");

		/*	for (int i = 0; i < regularDefinition.size(); i++) {
				String[] temp = regularDefinition.get(i);
				for (int j = 0; j < temp.length; j++) {

					System.out.print(temp[j] + ",");

				}
				System.out.println();
			}*/
			
			Map<String, String> reversedMapregularDef = new TreeMap<String, String>(regularDefinition);

			//then you just access the reversedMap however you like...
			for (Map.Entry entry : reversedMapregularDef.entrySet()) {
			    System.out.println(entry.getKey() + ", " + entry.getValue());
			}
		

			buf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * BufferedReader br = null; FileReader fr = null;
		 * 
		 * try {
		 * 
		 * fr = new FileReader(
		 * "C:/Users/FRRR/Desktop/GUC/Semester 10/Advanced Computer Lab/Task2/fallbackdfa.in"
		 * ); br = new BufferedReader(fr);
		 * 
		 * br = new BufferedReader(new FileReader(
		 * "C:/Users/FRRR/Desktop/GUC/Semester 10/Advanced Computer Lab/Task2/fallbackdfa.in"
		 * ));
		 * 
		 * ArrayList<String> words = new ArrayList<>(); String lineJustFetched =
		 * null; String[] wordsArray;
		 * 
		 * int lines = 0; while (br.readLine() != null) lines++;
		 * 
		 * while (true) { lineJustFetched = br.readLine(); if (lineJustFetched
		 * == null) { break; } else { wordsArray = lineJustFetched.split("\t");
		 * for (String each : wordsArray) { if (!"".equals(each)) {
		 * words.add(each); } } } }
		 * 
		 * 
		 * br.close();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * } finally {
		 * 
		 * try {
		 * 
		 * if (br != null) br.close();
		 * 
		 * if (fr != null) fr.close();
		 * 
		 * } catch (IOException ex) {
		 * 
		 * ex.printStackTrace();
		 * 
		 * }
		 * 
		 * }
		 */

	}

	public static ArrayList<String[]> getTransitions() {
			
		return transitions;
	}

	public static void setTransitions(ArrayList<String[]> transitions) {
		fallbackDFA.transitions = transitions;
	}

	
}
