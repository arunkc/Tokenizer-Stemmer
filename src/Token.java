/**
 * Token class is used to store basic info on a token.
 * It stores token name, frequency and the list of documents the token occurs.
 * Appropriate methods are used to set and get those information.*/

import java.util.*;

public class Token {

	String token;
	int frequency = 0;
	List<String> documents = new ArrayList<String>();
	
	public Token(String token){
		this.token = token;
	}
	
	public Token(String token, int frequency){
		this.token = token;
		this.frequency = frequency;
	}
	
	public Token(String token, int frequency, String new_doc){
		this.token = token;
		this.frequency = frequency;
		if(!documents.contains(new_doc))
			documents.add(new_doc);
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}

	public void addDocument(String new_doc){
		if(!documents.contains(new_doc))
			documents.add(new_doc);
	}
	
	public String getToken(){
		return token;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public List<String> getDocuments(){
		return documents;
	}
	
}
