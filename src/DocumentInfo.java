/**
 * DocumentInfo class is used to store basic info about a document.
 * It stores document_id, title, author and biblio.
 * Appropriate methods are used to set and get those information.*/

public class DocumentInfo {

	String doc_ID;
	String title;
	String author;
	String biblio;
	
	public DocumentInfo(String doc_ID){
		this.doc_ID = doc_ID;
	}
	
	public DocumentInfo(String doc_ID, String title){
		this.doc_ID = doc_ID;
		this.title = title;
	}
	
	public DocumentInfo(String doc_ID, String title, String author){
		this.doc_ID = doc_ID;
		this.title = title;
		this.author = author;
	}
	
	public DocumentInfo(String doc_ID, String title, String author, String biblio){
		this.doc_ID = doc_ID;
		this.title = title;
		this.author = author;
		this.biblio = biblio;
	}

	public void setDocID(String doc_ID){
		this.doc_ID = doc_ID;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setBiblio(String biblio){
		this.biblio = biblio;
	}

	public String getDocID(){
		return doc_ID;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getAuthor(){
		return author;
	}

	public String getBiblio(){
		return biblio;
	}
	
}
