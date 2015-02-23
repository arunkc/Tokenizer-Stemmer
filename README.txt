PROJECT NAME		:	Tokenizer&Stemmer

AUTHOR			:	Arun Kumar Konduru Chandra

REQUIREMENTS		:	External Library - JSOUP
				Can be downloaded from : http://jsoup.org/packages/jsoup-1.8.1.jar
				I have attached jsoup-1.8.1.jar along with the project

COMPILING INSTRUCTION 	:	TO COMPILE ON UNIX/LINUX
				1.Download the java files and copy them into some folder.
				2.Copy jsoup-1.8.1.jar external library into same folder.
				3.Navigate to the folder where the files were copied.
				4.Use the following code to compile the project:
					javac <Include external lib> <ClassName>
					javac -cp .:jsoup-1.8.1.jar Main.java

RUNNING INSTRUCTION	:	TO RUN ON UNIX/LINUX
				1.After successful compilation, to run the project:
					java <Include external lib> <ClassName> <CranfieldFolderPath>
					java -cp .:jsoup-1.8.1.jar Main /people/cs/s/sanda/cs6322/Cranfield

RUNNING IN ECLIPSE	:	1.Import the project into Eclipse.
				2.Download jsoup-1.8.1.jar.
				3.Right Click on project --> Build Path --> Configure Build Path --> Add External JARs.. --> 
					Click jsoup-1.8.1.jar
				4.To run Click Run --> Run Configurations --> Arguments --> <Add Cranfield Folder Path> --> 
					Click Apply --> Run
