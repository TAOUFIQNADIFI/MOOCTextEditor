package textgen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
		
	}
	
	
	public List<ListNode> getTokens( String text)
	{
		//getTokens("[a-zA-Z]+|");
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[a-zA-Z']+");
		Matcher m = tokSplitter.matcher(text);
		
			while (m.find()) {
				String b=m.group();
				
				tokens.add(b);	
								}
			
			ListNode actNode ;
			ListNode prevNode;
			ArrayList<ListNode> result = new ArrayList<ListNode>();
			
			if(tokens.size()>0){ 
				
				starter= tokens.get(0);
				ListNode firstNode = new ListNode(starter);
			    result.add(firstNode);
			   // System.out.println(tokens.size());
			    
			    
			for(int i=1; i<tokens.size();i++){
					
					String a = tokens.get(i);
					String c = tokens.get(i-1);
					
					//System.out.println(a);
					
					 actNode = new ListNode(a);
					 prevNode = new ListNode(c);
					
					if(result.contains(actNode)){ 
						
					int n =	result.indexOf(prevNode);
						result.get(n).addNextWord(a);
				//	System.out.println(" the    prev  "+ result.get(n).getWord());
				//	System.out.println(" the    prev  next word :  " +result.get(n).getNextWord()  );	
					  }
					else{
						
						result.add(actNode);
						int n =	result.indexOf(prevNode);
						result.get(n).addNextWord(a);
						//System.out.println(" the    prev  "+ result.get(n).getWord());
						//System.out.println(" the    prev  next word :  " +result.get(n).getNextWord()  );
					}
					
			}
				String z =tokens.get(0);
				String g = tokens.get(tokens.size()-1);
				ListNode l = new ListNode(g);
				
						if(result.contains(l)){
							int v = result.indexOf(l);
							ListNode p =result.get(v);
							p.addNextWord(z);
						    }
						else{
							result.add(l);
							l.addNextWord(z);
						}
			
			}
			
			//System.out.println(" next word  "+result.get(0).getNextWord().size());

			
		return result;
	}
	
	/** Train the generator by adding the sourceText */
	@Override
	public  void train(String sourceText)
	{		// TODO: Implement this method
		
		 this.wordList = getTokens(sourceText);
			/*		
		for(ListNode n : wordList)	{
		//	System.out.print(n.toString());
		} 
		*/
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		
		String currentW =starter;
		String output ="";
		output=output+currentW;
		int i =1;
		while(i<numWords)
			
				   {
				ListNode a = new ListNode(currentW);
				int b = wordList.indexOf(a);
				ListNode c = wordList.get(b);
				List<String> d = c.getNextWord();
				int index = rnGenerator.nextInt(d.size());
				String nextWord= d.get(index);
				output=output+ " "+nextWord;
		        currentW=nextWord;
		        i++;
		        }
       // System.out.println("this is the out : "+output);
        return output;
		
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		 this.wordList = getTokens(sourceText);
			/*
			for(ListNode n : wordList)	{
			//	System.out.print(n.toString());
			} */
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		//String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//String textString ="  hello hello hello hello bonjour bonjour   hello hello ";
		String textString ="hi leo hi there";
		//System.out.println(textString);
		gen.train(textString);
		//gen.generateText(4);
		System.out.println(gen.generateText(4));
		//System.out.println(gen);
		//System.out.println(gen.generateText(20));
		/*
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		
		
		System.out.println(textString2);
		gen.train(textString2);
		*/
		
		/*
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
		
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode   
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public List<String> getNextWord()
	{
		return nextWords;
	}
	
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return null;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	@Override
	public boolean equals(Object o){
		ListNode O = (ListNode)o;
		return this.getWord().equals(O.getWord()); }
		
		
	
	}



