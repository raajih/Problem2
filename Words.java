import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;
/**
 * Complete the methods below. You may add helper methods
 * as you require.
 */
public class Words
{  
    /**
     * 
     * @param word the word we are testing to see if all letters are unique.
     * @return true if no repeat letters in word, false if not.
     */
    public static boolean allLettersUnique(String word)
    {
        Set<Character> seen = new HashSet<>();

        for (char c: word.toCharArray())
        {
            if(!seen.add(c))
            {
                return false; //If we can't add the character to set, then word must have duplicate letters so return false.
            }
        }
        return true; //If there were no duplicate characters in word return true.
    }    
   /**
    * The noLetterRepeated method reads all of the words 
    * in filename and returns a list of all words with 
    * length of at least ten, in which no letter is repeated.
    * You may assume that filename has one word per line.
    */
   public static List<String> noLetterRepeated(String filename)
   {
      List<String> result;
      //TODO: Your work goes here

      try (Stream<String> lines = Files.lines(Paths.get(filename)))
      {
        result = lines
            .filter(w -> !w.endsWith("'s")) //Make sure stream doesn't include letters that end with "'s".
            .filter(w -> w.length() >= 10) //Stream only includes words with 10 letters or more.
            .filter(w -> allLettersUnique(w)) //Stream only includes words with no duplicate letters.
            .collect(Collectors.toList());

        
      }
      catch (IOException e)
      {
        result = List.of();
      }

      
      return result;
   }
   
   /**
    * The longestWord method takes a Stream<String> and
    * returns the longest word in the stream.
    */
   public static String longestWord(Stream<String> stream)
   {
      String result;
      //TODO: Your work goes here

      //Create map where key is the length of each word and value is a List of all strings of that length.
      Map<Integer, List<String>> wordMap = stream.collect(
        Collectors.groupingBy(
            w -> w.length()
        )
      );

      //Find the max length.
      int maxLength = wordMap
        .keySet()
        .stream()
        .max((x, y) -> Integer.valueOf(x).compareTo(y))
        .orElse(0);

        //Create list of words with the longest length.
        List<String> longestWords = wordMap.get(maxLength);

        if (longestWords != null && !longestWords.isEmpty())
        {
            result = longestWords.get(0);//Return first word in list.
        }
        else
        {
            result = "none";
        }
      return result;
   }
   
   /**
    * Given a Stream<String> and a length, 
    * returns the number of words of that length in the stream.
    */
   public static long wordCount(Stream<String> stream, int len)
   {
      long result;
      
      result = stream
        .filter(w -> w.length() == len)
        .count();
      return result;
   }
}
