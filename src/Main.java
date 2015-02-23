/**
 * Title		:	Tokenization & Stemming of Cranfield collection 
 * Author		: 	Arun Kumar Konduru Chandra 
 * NetID		: 	axk138230 
 * Date 		: 	02/14/2015
 * Class		: 	CS6322-Information Retrieval
 * Description	: 	This program tokenizes and stems the data from the Cranfield database.
 * Requirements	:	jsoup-1.8.1 external jar.
 */

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
	
	Map<String,Token> tokens = new HashMap<String,Token>();//To store tokens and information like frequency and document id.
	Map<String,Token> stemmed_tokens = new HashMap<String,Token>();//To store stemmed_tokens and information like frequency and document id.
	List<DocumentInfo> document_info = new ArrayList<DocumentInfo>();//To store information about the document.
	
	/*Takes in the cranfield folder path as argument. 
	 * Uses Jsoup parser on sgml file.*/
	public static void main(String args[]){
		
		long startTime = System.currentTimeMillis();
		//Takes in one argument: The path of folder that containing Cranfield files
		if(args.length != 1){
			System.err.println("Usage: Tokenizer <cranfield_folder-path>"); 
			System.out.println("Possible solution: There should be no spaces in the folder_path");
			System.exit(2);
		}
		
		Main main = new Main();
		
		System.out.println("Reading Cranfield Files...");
		int no_of_files = 0;
		
		try {
			File folder = new File(args[0]);
			main.readCranfieldFiles(folder);//Read all files from the folder
			no_of_files = folder.list().length;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Token> treeMap = new TreeMap<String, Token>(main.tokens);
		main.tokens.clear();
		main.tokens = treeMap;
		
		Map<String, Token> stm_treeMap = new TreeMap<String, Token>(main.stemmed_tokens);
		main.stemmed_tokens.clear();
		main.stemmed_tokens = stm_treeMap;
		
		int no_of_tokens = 0;
		
		for(Map.Entry<String, Token> entry : main.tokens.entrySet())
			no_of_tokens = no_of_tokens + entry.getValue().getFrequency();
		
		System.out.println("Printing results...");
		
		System.out.println("***********************");
		System.out.println("Number of tokens in the Cranfield text collection : "+no_of_tokens);
		
		main.printTokenInfo(main.tokens,"tokens",no_of_files);
		
		main.printTokenInfo(main.stemmed_tokens,"stems",no_of_files);
		
		double execution_time = (double)(System.currentTimeMillis()-startTime)/1000;
		
		System.out.println("Total execution time : "+Math.round(execution_time*100)/100D+" seconds");
		
	}
	 
	/*Reads all the files from the folder specified. 
	 * Calls the readFromFile method for each file in the folder*/
	public void readCranfieldFiles(File folder){
		try {
			File[] listOfFiles = folder.listFiles();
			System.out.println("Tokenizing and Stemming now...");
			for (File file : listOfFiles) {
			    if (file.isFile()) 
			    	readFromFile(file);
			}
			System.out.println("Tokenizing and Stemming completed...");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Reads from the file specified using the Jsoup parser.
	 * Stores information like title, author, biblio in doc_info list.
	 * Calls Tokenizer and Stemmer on the text tag element.*/
	public void readFromFile(File file) throws IOException{
		Document doc = Jsoup.parse(file, "UTF-8");
		//Stores the document info into the document_info list.
		String doc_id = doc.getElementsByTag("DOCNO").text();
		String title = doc.getElementsByTag("TITLE").text();
		String author = doc.getElementsByTag("AUTHOR").text();
		String biblio = doc.getElementsByTag("BIBLIO").text();
		DocumentInfo doc_info = new DocumentInfo(doc_id,title,author,biblio);
		document_info.add(doc_info);
		 
		Tokenizer tokn = new Tokenizer();
		Stemmer stm = new Stemmer();
		
		List<String> token = tokn.tokenize(doc.getElementsByTag("TEXT").text().toLowerCase());
		
		List<String> stem_token = stm.stemTokens(token);
		
		for(String tkn:token){
			if(tokens.containsKey(tkn)){
				Token token_var = tokens.get(tkn);
				token_var.setFrequency(token_var.getFrequency()+1);
				if(!token_var.getDocuments().contains(doc_id))
					token_var.addDocument(doc_id);
			}
			else{
				tokens.put(tkn, new Token(tkn,1,doc_id));
			}
		 }
		
		for(String tkn:stem_token){
			if(stemmed_tokens.containsKey(tkn)){
				Token token_var = stemmed_tokens.get(tkn);
				token_var.setFrequency(token_var.getFrequency()+1);
				if(!token_var.getDocuments().contains(doc_id))
					token_var.addDocument(doc_id);
			}
			else{
				stemmed_tokens.put(tkn, new Token(tkn,1,doc_id));
			}
		 }
		  
	}
	
	/*Prints the following information about the tokens.
	 * 1.Number of unique tokens/stems in Cranfield collection.
	 * 2.Number of tokens/stems that occurs only once.
	 * 3.The 30 most frequent tokens/stems.
	 * 4.Average number of tokens/stems per document.
	 * Inputs:Tokens, Type = "token" or "stem", No_of_Files*/
	public void printTokenInfo(Map<String,Token> tokens, String value, int no_files){
		System.out.println("***********************");
		System.out.println("1.Number of unique "+value+" in the Cranfield text collection : "+tokens.size());
		int count = 0;
		for (Map.Entry<String, Token> entry : tokens.entrySet()) {
			if(entry.getValue().getFrequency()==1)
				count++;
		}	
		System.out.println("2.The number of "+value+" that occur only once in the Cranfield text collection : "+count);
		
		List<Token> top_30 = new ArrayList<Token>();
		
		for (Map.Entry<String, Token> entry : tokens.entrySet()) {
			if(top_30.size()<30){
				top_30.add(new Token(entry.getValue().getToken(),entry.getValue().getFrequency()));
				Collections.sort(top_30, new Comparator<Token>() {
			        @Override
			        public int compare(final Token object1, final Token object2) {
			            return object2.getFrequency()-object1.getFrequency();
			        }
				} );
			}
			else{
				if(entry.getValue().getFrequency()>top_30.get(29).getFrequency()){
					top_30.remove(29);
					top_30.add(new Token(entry.getValue().getToken(),entry.getValue().getFrequency()));
					Collections.sort(top_30, new Comparator<Token>() {
				        @Override
				        public int compare(final Token object1, final Token object2) {
				            return object2.getFrequency()-object1.getFrequency();
				        }
					} );
				}
			}
		}
		
		System.out.println("3.The 30 most frequent "+value+" in the Cranfield text collection :");
		System.out.printf("%-20s  %-20s \n","TOKEN","FREQUENCY");
		
		for(int i=0;i<30;i++)
			System.out.printf("%-20s  %-20s \n",top_30.get(i).getToken(),top_30.get(i).getFrequency());
		
		System.out.println("4.The average number of "+value+" per document : "+tokens.size()/no_files);
		System.out.println("***********************");
	}

}
