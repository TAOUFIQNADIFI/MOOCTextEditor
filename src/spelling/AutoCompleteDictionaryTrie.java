package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
	
		/** 
		 * An trie data structure that implements the Dictionary and the AutoComplete ADT
		 * @author You
		 *
		 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word1)
	{
	    //TODO: Implement this method.
		String word=word1.toLowerCase();
		if(this.isWord(word)){return false;}
		TrieNode act = root;
		for(int i=0; i<word.length();i++){
			char a = word.charAt(i);
				if(act.getChild(a)!=null)
				    {
						act =act.getChild(a);
						if(i==word.length()-1){ 
							act.setEndsWord(true); 
							this.size++;  
							return true ;}
							
						}
				else{
				act.insert(a);
				act =act.getChild(a);
			    
						if(i==word.length()-1){ 
							act.setEndsWord(true); 
							this.size++;  
							return true;}
						}
		}
		//printNode(root);
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode act = root;
		s=s.toLowerCase();
		for(int i=0; i<s.length();i++){
			char a = s.charAt(i);
			act =act.getChild(a);
			if(act==null){return false;}			
			if((i==s.length()-1)&&(act.endsWord())){ 
				return true;
				  }  
			}
		
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     * 
     */
	public List<String> getWords(TrieNode start){
		List<String> result =new ArrayList<String>() ;
		HashMap<Character, TrieNode> children = start.getChildren();
		if(children==null){return null; }
		else {
			for (TrieNode v : children.values()) {
			    // process each value in turn 
				String s = v.getText();
				if(v.endsWord()){result.add(s);}
				result.addAll(getWords(v));
				//Collections.sort(result);
			}
		}
		Collections.sort(result,new LenghtCompararer());
		System.out.println(result);
		
		return result;
	}
	
	@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
		if(numCompletions<0){return null;}
		List<String> result =new ArrayList<String>() ;
		List<String> result1 =new ArrayList<String>() ;
		List<String> result2 =new ArrayList<String>() ;
		
		if(numCompletions<=0){return result;}
		
    	 TrieNode act = root;
    	 TrieNode pref =null;
    	 
    	 if(prefix.isEmpty()){ pref=root; }
    	 
    	 else {
	    		 for(int i=0; i<prefix.length();i++){
	    	 
	 			char a = prefix.charAt(i);
	 			act =act.getChild(a);
	 			if(act==null){return result2;}
	 			else if(i==prefix.length()-1 && act!=null){
	 				pref=act;
	 							}
 				  		} 
	    		 }
 		
 		if(pref==null){return result2;}
 		else { result1 = getWords(pref);
 		Collections.sort(result1,new LenghtCompararer());
 		}
 			if(result1.size()<=numCompletions){result=result1;if(isWord(prefix)){
 										result.add(prefix);}
 								}
 		  
 			else if (result1.size()>numCompletions){
 				if(isWord(prefix)){
 							result.add(prefix);
			 				for(int i =0; i<numCompletions-1;i++){
			 			 	result.add(result1.get(i));	
			 			 		}
	 			   }
	 		
		 			else{
		 				for(int i =0; i<numCompletions;i++){
					 	result.add(result1.get(i));	
			 		 }
		 		}
 			}
	
		 		//System.out.println(result);
		 		Collections.sort(result,new LenghtCompararer());
		 		//System.out.println(result);
		 		//System.out.println(result.size());
		         return result;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 }

class LenghtCompararer implements Comparator<String>{
	
	@Override
	public int compare(String x, String y){
		
		int result= x.length()- y.length();
		if(result==0){result =x.compareTo(y);}
		return result;
	}
	
}
