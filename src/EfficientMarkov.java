import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	
	Map<String, ArrayList<String>> myMap;
	
	public EfficientMarkov (int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	//default constructor has order 3
	public EfficientMarkov() {
		this(3);
	}
	
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		String key;
		for(int k = 0; k <= myText.length() - myOrder; k++) {
			key = myText.substring(k, k + myOrder);
			String next = "";
			if (k + myOrder == myText.length()) {
				next = "EOS";
			} else {
			next = myText.substring(k + myOrder, k + myOrder + 1);
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
	
	@Override
	public ArrayList<String> getFollows(String key)  {
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