package epi;

import java.io.*;
import java.util.*;

class FlattenaDictionary {

	static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
		// your code goes here
		HashMap<String, String> result = new HashMap<>();
		flatten(dict, result, "");
		return result;
	}

	static void flatten(HashMap<String, Object> dict, HashMap<String, String> result, String appendString) {
		for (String key : dict.keySet()) {
			// the value is String and not a HashMap , and its a String
			if (dict.get(key) instanceof String) {
				// its parent level key so no need to append anything
				if (appendString.equals("")) {
					result.put(key, (String) dict.get(key));
				}
				// its not parent appendString needed
				else {
					// appendString needed but if there's no key i.e. key is
					// empty , then only parentKeys needed
					if (key.equals("")) {
						result.put(appendString, (String) dict.get(key));
					}
					// both parentKeys and currentKey needed
					else {
						result.put(appendString + "." + key, (String) dict.get(key));
					}

				}

			}
			// The value is a hashmap go recursive here
			else {
				// if there are no parent keys i.e. its empty then only send the
				// currentkey for recursive calls
				if (appendString.equals("")) {
					flatten((HashMap<String, Object>) dict.get(key), result, key);
				}
				// its not parentmost , so in recursive call parent with current
				// key must go to keep track of all the parents
				else {
					flatten((HashMap<String, Object>) dict.get(key), result, appendString + "." + key);
				}
			}
		}
	}

	public static void main(String[] args) {

	}

}