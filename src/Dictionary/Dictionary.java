/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dictionary;

/**
 *
 * @author kshitijkarthick
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class Dictionary {	
	Node root;
	public static int fileload;
	public static int noOfWords;
	public String[] s=new String[10000];
	public int[] rank=new int[10000];
	public Dictionary(String filename) {
		try {
				fileload=0;
				BufferedReader reader=new BufferedReader(new FileReader(filename));
				String line=null;
				root=new Node();
				while((line=reader.readLine())!= null) {
					//System.out.println(line.length());
					this.addWord(line);
				}			
				reader.close();
				fileload=1;
			}
			catch(Exception e) {
				System.err.print(e);
			}
	}

	public Dictionary(){
		System.err.print("Error No Input File");
	}
	
	public void addWord(String word) {
		char ch;
		Node temp=root;
		if(searchWord(word)==0){
			for(int i=0;i<word.length();i++){
				ch=word.charAt(i);
				ch=Character.toLowerCase(ch);
				if(temp.link[ch]==null){
					Node temp2=new Node();
					temp.link[ch]=temp2;
					temp.link[ch].rank++;
					temp=temp2;
				}
				else{
					temp.link[ch].rank++;
					temp=temp.link[ch];
				}
				//System.out.println("addWord:Rank level "+(i+1)+":"+temp.rank+":"+(char)ch);
			}
			temp.end=1;
			if(fileload==1){
				try {
						BufferedWriter f=new BufferedWriter(new FileWriter("Dictionary.txt",true));
						f.write(word+"\n");
						f.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		else
		{
			//System.out.println("AddWord:Word is already Present");
			for(int i=0;i<word.length();i++){
				ch=word.charAt(i);
				ch=Character.toLowerCase(ch);
				temp=temp.link[ch];
				temp.rank++;
				//System.out.println("addWord:Rank level "+(i+1)+":"+temp.rank+":"+(char)ch);
			}
                        if(fileload==1){
				try {
						BufferedWriter f=new BufferedWriter(new FileWriter("Dictionary.txt",true));
						f.write(word+"\n");
						f.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public int searchWord(String word) {
		char ch;
		int flag=1;
		Node temp=root;
		for(int i=0;i<word.length()&&flag==1;i++){
			ch=word.charAt(i);
			ch=Character.toLowerCase(ch);
			if(temp.link[ch]!=null){
				temp=temp.link[ch];
			}
			else
				flag=0;
		}
		if(flag==1&&temp.end==1){
			//System.out.println("Search Word:Word is Present");
			return 1;
		}
		else{
			//System.out.println("Search Word:Word is not Present");
			return 0;
		}
	}
	public String[] predict(String text)throws IOException{
		char ch;
		String word="";
		int wordCount;
		Node temp=root;
		//BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("UserInput:");
		//ch=(char)reader.read();
		for(int i=0;i<text.length();i++){
                    noOfWords=0;
                    ch=text.charAt(i);
			if(ch==' '||ch=='\n'||ch==','||ch=='.')
				break;
			if(temp.link[ch]!=null){
				temp=temp.link[ch];
				wordCount=temp.rank;
				word=word+ch;
				noOfWords=0;
                                for(int k=0;k<12;k++)
                                    s[k]="";
				//System.out.println("Word is:"+word+"Rank is:"+temp.rank);
				predictTraversal(word,wordCount,temp);
				sort();
                                /*
				for(int j=0;j<noOfWords;j++)
					System.out.println(s[j]+":"+rank[j]);
                                */
                                if(i==text.length()-1)
                                    return s;
                                
			}
                            
			//System.out.println("No Prediction");
			//BufferedReader reader1=new BufferedReader(new InputStreamReader(System.in));
			//System.out.println("UserInput:");
			//System.out.println(temp.rank);
			//ch=(char)reader1.read();
		}
		return s;
	}
	public void sort(){
		int pos,temp;
		String stemp;
		//System.out.println("No of Words:"+noOfWords);
		for(int j=0;j<noOfWords-1;j++){
			pos=j;
			for(int k=j+1;k<noOfWords;k++){
				if(rank[j]<rank[k])
					pos=k;	
			}
			temp=rank[pos];
			rank[pos]=rank[j];
			rank[j]=temp;
			stemp=s[pos];
			s[pos]=s[j];
			s[j]=stemp;
		}
	}
	public void predictTraversal(String word, int wordCount, Node temp) {
		if(temp.end==1){
			s[noOfWords]=word;
			rank[noOfWords]=temp.rank;
			//System.out.println(s[index]+":"+rank[index]+":"+index);
			noOfWords++;
		}
		if(temp!=null && noOfWords<wordCount){
			for(int j=0;j<256;j++){
				if(temp.link[j]!=null)
					predictTraversal(word+(char)j, wordCount, temp.link[j]);
			}
		}
	}

	public static void main(String[] args)throws IOException {
		Dictionary trie=new Dictionary("Dictionary.txt");
		/*
		trie.addWord("bba");
		trie.addWord("bbb");
		trie.addWord("bea");
		trie.addWord("beb");
		trie.addWord("beb");
		trie.addWord("bec");
		trie.addWord("bec");
		trie.addWord("bec");
		*/
		//trie.predict();
			}
}
class Node {
	Node[] link=new Node[256];
	int rank;
	int end;
}