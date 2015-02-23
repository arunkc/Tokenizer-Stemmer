/**Tokenizer class has methods to tokenize data and handle the tokens.*/

import java.util.*;

public class Tokenizer {
	
	List<String> stopwords = new ArrayList<String>();
	
	public List<String> tokenize(String raw_data){
 		List<String> tokens = new ArrayList<String>();
		//Tokenize and split the words on following punctuation symbols : , ? " ( ) [ ] : ; _ !
 		StringTokenizer st = new StringTokenizer(raw_data," ,/?\"\\(\\)\\[\\]:;_!");
		initStopWords();
		while(st.hasMoreTokens()){
			String val = st.nextToken();
			if(val.matches("[a-zA-Z]+")){
				if(!checkStopWords(val))
					tokens.add(val);
			}
			else if(val.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")||val.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")||
					val.matches("[0-9]{4}-[0-9]{2}")){//Handles dates and year.
				if(val.contains("."))
					tokens.add(val.replaceAll(".","/"));
				else
					tokens.add(val);
			}
			else if(val.contains(".")){//Handles abbreviations
				val.replaceAll("(?<=(^|[.])[\\S&&\\D])[.](?=[\\S&&\\D]([.]|$))","").replace('.', ' ');
			}
			else if(val.contains("'s")&&!val.contains("let")){
				tokens.add(val+st.nextToken());//Handles possessives
			}
			else if(val.contains("'")){
				String[] words = removePunctuations(val);
				for(int i=0;i<words.length;i++){
					if(words[i]!=null&&!checkStopWords(words[i]))
						tokens.add(words[i]);
				}
			}
			else{
				if(val!=null&&!checkStopWords(val))
					tokens.add(val);
			}
		}
		return tokens;
	}
	
	/*List of stop-words that cannot be added into the dictionary. 
	 * Add or delete stop-words here to omit or allow words into the dictionary
	 * while tokenizing and stemming.*/
	public void initStopWords(){
		stopwords.addAll(Arrays.asList("a","an","and","are","as","at","be","by","for","from","has","he","in","is","it","its","of","on",
				"or","that","the","this","to","vs","was","what","when","where","who","will","with","the"));
	}
	
	/*Method checks if a particular string is in the stop-words
	 * list. Returns true if the stop-list contains the word else returns false.*/
	public boolean checkStopWords(String word){
		if(stopwords.contains(word))
			return true;
		else
			return false;
	}
	
	/*Method splits words with apostrophe and expand 
	 * the second part of the word.*/
	public String[] removePunctuations(String word){
		String[] words = new String[2];
		if(word.contains("'d")){
			words = word.split("'");
			words[1] = "would";
		}
		else if(word.contains("'ll")){
			words = word.split("'");
			words[1] = "will";
		}
		else if(word.contains("'m")){
			words = word.split("'");
			words[1] = "am";
		}
		else if(word.contains("'nt")){
			words = word.split("'");
			words[1] = "not";
		}
		else if(word.contains("'re")){
			words = word.split("'");
			words[1] = "are";
		}
		else if(word.contains("'s")){//Handles possessives
			words = word.split("'");
			if(words[0].equals("let"))
				words[1] = "us";
		}
		else if(word.contains("'ve")){
			words = word.split("'");
			words[1] = "have";
		}
		return words;
	}
	
}
