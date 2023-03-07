import java.util.Arrays;

public class pattern_search {
    public static void main(String[] args) throws Exception {
        String word;
        String pattern;
        if(args.length == 2) {
            word = args[0];
            pattern = args[1];
        } else {
            throw new Exception("Incorrect arguments. You put in "+  args.length + " arguments when 2 were expected.");
        }
        int[] newSuffix = new suffix_array(word).getSuffixArray();
        System.out.print("Pattern found at index " + findPattern(newSuffix,pattern,word));
    }
    public static char getCharfromIndex(int[] arr,int index,String word) {
        return word.charAt(arr[index]);
    }
    public static int findPattern(int[] suffixArr, String pattern,String word) {
        int res = 0;
        int len = suffixArr.length;
        if(len == 0) {
            return -1;
        }
        int middleIndex = suffixArr[len/2];
        for(int i = 0; i < pattern.length(); i++) {
            if(middleIndex+i < word.length()) {
                if(word.charAt(middleIndex+i) == pattern.charAt(i)) {
                    continue;
                } else if(word.charAt(middleIndex+i) < pattern.charAt(i)) {
                    res = 1;
                    break;
                } else {
                    res = -1;
                    break;
                }
            } else {
                res = 1;
            }
            if(res != 0) break;
        }
        if(res < 0) {
            return findPattern(Arrays.copyOfRange(suffixArr, 0, len/2), pattern, word);
        } else if(res > 0) {
            return findPattern(Arrays.copyOfRange(suffixArr, len/2, len-1), pattern, word);
        } else {
            return middleIndex;
        }
        
    }
    public static void printSortedString(int[] arr,String word) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(getCharfromIndex(arr,i,word));
        }
    }
    public static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }    
}
