//importing the required packages
import java.util.ArrayList;
import java.util.Arrays;

class suffix_array {
    static String word = "banana";
	public suffix_array(String string) {
        word = string;
    }

    public suffix_array() {
        if(word=="") {
            word = "banana";
        }
    }

    public int[] getSuffixArray() throws Exception
	{
		String arr1[] = new String[word.length()];
		String arr2[] = new String[word.length()];
		ArrayList<Integer> suffix_index
			= new ArrayList<Integer>();
		int suffix_array[] = new int[word.length()];
		for (int i = 0; i < word.length(); i++) {
			arr1[i] = word.substring(i);
		}
		arr2 = arr1.clone();
		Arrays.sort(arr1);
		for (String i : arr1) {
			String piece = i;
			int index
				= new suffix_array().index(arr2, piece);
			suffix_index.add(index);
		}
		for (int i = 0; i < suffix_array.length; i++) {
			suffix_array[i] = suffix_index.get(i);
		}
        return suffix_array;
	}
	
	//simple function to return the index of item from array arr[]
	int index(String arr[], String item)
	{
		for (int i = 0; i < arr.length; i++) {
			if (item == arr[i])
				return i;
		}
		return -1;
	}
}
