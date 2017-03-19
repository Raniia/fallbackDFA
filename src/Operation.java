import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.naming.InitialContext;

public class Operation {
	private static String inputString;
	private static Stack<Integer> stackOfStates = new Stack<Integer>();
	private static fallbackDFA dfa = new fallbackDFA();

	private static int initialstate;
	// pointer R
	private static int R = 0;
	// pointer L
	private static int L = 0;
	private static int newState = -1;
	private static String finalOutput = "";
	private static ArrayList<String> finalArrayList = new ArrayList<String>();

	public static final String filename ="C:/Users/FRRR/Desktop/GUC/Semester 10/Advanced Computer Lab/Task2/Lab2.in";
	
	public static void firstOperation() {

		for (int j = L; j < inputString.length(); j++) {
			String s = String.valueOf(inputString.charAt(j));
			newState = -1;
			if (dfa.alphabet.contains(s)) {
				for (int i = 0; i < dfa.getTransitions().size(); i++) {
					int topOfStack = stackOfStates.peek();
					String[] temp = dfa.getTransitions().get(i);
					if (Integer.parseInt(temp[0]) == topOfStack) {
						if (temp[1].equals(s)) {
							// I saved 3rd character
							newState = Integer.parseInt(temp[2]);

							stackOfStates.push(newState);
							L++;

							//System.out.println("L is: " + L);

							//System.out.println("The new state is: " + newState);

							// System.out.println(stackOfStates);
							break;
						}
					}
					// else {
					// newState = -1;
					// }

				}
			}
			if (newState == -1) {
				stackOfStates.push(-1);
				L++;
				break;

			}

		}

		//System.out.println("The final stack is: " + stackOfStates);
	}

	public static void secondOperation() {

		if (L == inputString.length()) {
			// L--;
			while (!stackOfStates.isEmpty()) {
				int statepopped = stackOfStates.pop();
			//	System.out.println("The popped state is: " + statepopped);
				L--;
				// if state popped is an accept state
				if (!(dfa.acceptStates.contains(statepopped))) {
					// L--;
				//	System.out.println("L is nowwwwwwwwww: " + L);
				} else {
					stackOfStates.clear();
					stackOfStates.add(initialstate);
					//System.out.println("The L counter is: " + L);
					//System.out.println("The stack now is: " + stackOfStates);
					String lexem = inputString.substring(R, L + 1);
					//System.out.println(lexem);

					// get corresponding label and regular
					// definition

					if (dfa.label.containsKey(statepopped)) {
						String reg = fallbackDFA.label.get(statepopped);

						for (Entry<String, String> e : fallbackDFA.regularDefinition
								.entrySet()) {
							if (e.getValue().equals(reg)) {

								String key = e.getKey();
								finalOutput += key + "," + lexem + " ";
//								System.out.println(key + "," + lexem);
							}
						}
					}
					break;

				}

			}
		} else if ((!stackOfStates.isEmpty()) && stackOfStates.peek() == -1) {
			boolean flagError = true;
			while (!stackOfStates.isEmpty()) {
				L--;
				int statepopped = stackOfStates.pop();
	//			System.out.println("The popped state is: " + statepopped);
				// if state popped is an accept state
				if (!(dfa.acceptStates.contains(statepopped))) {
					// L--;
				} else {
					stackOfStates.clear();
					stackOfStates.add(initialstate);
					flagError = false;
		//			System.out.println("The L counter is: " + L);
			//		System.out.println("The stack now is: " + stackOfStates);
					String lexem = inputString.substring(R, L + 1);
				//	System.out.println(lexem);

					// get corresponding label and regular
					// definition

					if (dfa.label.containsKey(statepopped)) {
						String reg = fallbackDFA.label.get(statepopped);

						for (Entry<String, String> e : fallbackDFA.regularDefinition
								.entrySet()) {
							if (e.getValue().equals(reg)) {

								String key = e.getKey();
								finalOutput += key + "," + lexem + " ";
					//			System.out.println(key + "," + lexem);
							}
						}
					}
					break;

				}

			}
			if(flagError){
				//System.out.println("Error!");
				L = inputString.length();
				R=L;
				finalOutput += "Error!";
				return;
			}
		}

	}

	public static String doOperation() {
		

		initialstate = fallbackDFA.startstate;
//		System.out.println("The initial state is: " + initialstate);
		// Push the initial state to the stack.

		stackOfStates.push(initialstate);

		// b

		firstOperation();

		secondOperation();
		while (R <= inputString.length()) {
			L++;

			R = L;
	//		System.out.println("Value of L is: " + L);
		//	System.out.println("Value of R is: " + R);
			firstOperation();
			secondOperation();


		}

		//System.out.println("The final Output is " + finalOutput);
//System.out.println("Theeee final output is: " +finalOutput);
		return finalOutput;

	}

	public static void main(String[] args) {
		
		BufferedReader br = null;
		FileReader fr = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try{
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			//1- push initial state into stack
			File file = new File("Lab2.out");
			
			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename ));
			
			
			 fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			

			while ((sCurrentLine = br.readLine()) != null){
				if (!(sCurrentLine.contains("input"))) {
				inputString = sCurrentLine;	
				//System.out.println("Now input is " + inputString);

				
				doOperation();
				L=0;
				R=0;
				bw.write(finalOutput);
				bw.write("\n");

			System.out.println(finalOutput);	
			finalOutput = "";
			System.out.println();
				}
				else {
					bw.write("output"+ sCurrentLine.substring(5,6) + ": ");
					bw.write("\n");

					System.out.println("output"+ sCurrentLine.substring(5,6) + ": ");
				}
			//System.out.println(sCurrentLine);
					
					
				
				
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}


	}
	

	

}
}
