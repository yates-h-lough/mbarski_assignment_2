import java.util.Arrays;

public class count_occurances {
    public static void main(String[] args) throws Exception
    {
        String word;
        String pattern;
        if(args.length == 2) {
            word = args[0];
            pattern = args[1];
            int[] newSuffix = new suffix_array(word).getSuffixArray();
            System.out.print("Number of occurances: " + findOccurrances(word ,pattern, newSuffix));
        } else {
            throw new Exception("Incorrect arguments. You put in "+  args.length + " arguments when 2 were expected.");
        }
    }
    public static int findOccurrances(String word,String pattern,int[] arr) {
        int rightI = rightIndexOf(word, pattern, arr);
        if(rightI < 0) {
            return 0;
        }
        int leftI = leftIndexOf(word, pattern, arr);
        return rightI - leftI;
    }
    public static int rightIndexOf(String word,String pattern, int[] arr) {
        int len = arr.length;
        if(arr.length < 2) {
            if(arr.length == 0) return -1;
            // Here check to see if the suffix matches the pattern, and if so, return the index.
            String suff = word.substring(arr[0]);
            for(int i = 0; i < suff.length(); i++) {
                if(i >= pattern.length()) {
                    return 0;
                } else if(i >= suff.length()) {
                    return -1;
                } else if(pattern.charAt(i) != suff.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
        int comp;
        if(word.length() < arr[len/2]+pattern.length()) {
            comp = pattern.compareTo(word.substring(arr[len/2]));
        } else {
            comp = pattern.compareTo(word.substring(arr[len/2],arr[len/2]+pattern.length()));
        }
        if(comp >= 0) {
            // take right half
            int save = rightIndexOf(word, pattern, Arrays.copyOfRange(arr,len/2,len));
            if(save == -1) {
                return -1;
            } else {
                return save + len/2;
            }
        } else if (comp < 0) {
            // take left half
            int save = rightIndexOf(word, pattern, Arrays.copyOfRange(arr, 0, len/2));
            if(save == -1) {
                return -1;
            } else {
                return save;
            }
        }
        return -1;
    }
    public static int leftIndexOf(String word, String pattern, int[] arr) {
        int len = arr.length;
        if(arr.length < 2) {
            // Need to check whether it's just the left-most index.
            if(word.length() >= arr[len/2]+pattern.length()) {
                int comp2 = pattern.compareTo(word.substring(arr[len/2],arr[len/2]+pattern.length()));
                if(comp2 == 0) {
                    return -1;
                }
            }
            return 0;
        }
        
        int comp;
        if(word.length() < arr[len/2]+pattern.length()) {
            comp = pattern.compareTo(word.substring(arr[len/2]));
        } else {
            comp = pattern.compareTo(word.substring(arr[len/2],arr[len/2]+pattern.length()));
        }
        if(comp > 0) {
            
            int save = leftIndexOf(word,pattern,Arrays.copyOfRange(arr,len/2,len));
            if(save == -1) {
                return -1;
            } else return save + len/2;
        } else if(comp <= 0) {
            int save = leftIndexOf(word,pattern,Arrays.copyOfRange(arr,0,len/2));
            if(save == -1) {
                return -1;
            } else {
                return save;
            }
        }
        return -1;

    }
    public static void printSuffixArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }    
}
