import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {

	private Map<WordGram, ArrayList<String>> myMap;
	//public String[] myText;

	
	
	//public EfficientWordMarkov(String[] words, int index, int size) {
	public EfficientWordMarkov(int order) {	
	//super(words, index, size);
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public EfficientWordMarkov() {
		this(3);
	}
	//creating the key, words[] is myText in this case
	//need a loop, add mySize amount of terms, but its already
	//in the constructor
	//start at the correct index
	
	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap.clear();
		WordGram key;

		for(int k = 0; k <= myWords.length - myOrder; k++) {
			key = new WordGram(myWords, k, myOrder);
			//key = new WordGram(myOrder);
			String next = "";
			if (k + myOrder == myWords.length) {
				next = PSEUDO_EOS;
			} else {
			next = myWords[k + myOrder];
			}
			
			ArrayList<String> value = new ArrayList<String>();
			
			if (! myMap.containsKey(key)) {
				value.add(next);
				myMap.put(key, value);
			} else {
				value = myMap.get(key);
				value.add(next);
				myMap.put(key, value);
			} 
			 
		}
	}

	
	public ArrayList<String> getFollows(WordGram key)  {
		ArrayList<String> value = new ArrayList<>();
		try {
			if (myMap.containsKey(key)) {
				value = myMap.get(key);
			} 
		}
		catch(Exception e) {
			System.out.println("test");
			throw new NoSuchElementException("This element is not present" + e);
		}
		return value;
	}
}	
