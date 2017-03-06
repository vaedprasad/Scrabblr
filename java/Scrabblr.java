import java.io.*;
import java.util.*; 
/**
 * Write a description of class Scrabblr here.
 * 
 * @author Vaed Prasad
 */
public class Scrabblr
{
    private static int NUM_ROWS = 15;
    private static int NUM_COLS = 15;
    private static int NUM_TILES = 7;
    private static int NUM_SHOW = 25;
    private static int[][] scoring = { // 0 = Normal, 1 = Double Letter, 2 = Triple Letter, 3 = Double Word, 4 = Triple Word
            {4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4},//7
            {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},//6
            {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},//5
            {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},//4
            {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},//3
            {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},//2
            {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},//1
            {4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 4},//***
            {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},//1
            {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},//2
            {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},//3            
            {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},//4            
            {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},//5            
            {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},//6
            {4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4},//7            
        };
    private static int[] numTiles = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    private static int[] numPoints = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};    
    //private static PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
    private static ArrayList<String> dictionary;
    public static void main(String[] args) {
        char[][] board = new char [NUM_ROWS][NUM_COLS];
        char[] tiles = new char [NUM_TILES];
        //char[] bestMoves = wordFinder(board, tiles);
        System.out.print("\f");
        dictionary = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("assets/dictionary.csv")); 
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.length() <= NUM_ROWS)
                    dictionary.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String str = "hdkamed";
        tiles = str.toCharArray();
        String[] rankWords = wordFinder(board, tiles);
        //int[] rankPoints = new int[rankWords.length];
        for(int i = 0; i < rankWords.length; i++) {
            System.out.println(rankWords[i] + ": " + pointValue(str, tiles));
        }
    }

    public static String[] wordFinder(char[][] board, char[] tiles) {
        ArrayList<String> possibleWords = dictionary;
        Collections.sort(possibleWords, (w1, w2) -> new Integer(-pointValue(w1, tiles)).compareTo(new Integer(-pointValue(w2, tiles))));
        if(possibleWords.size() > NUM_SHOW) {
            String[] rv = new String[NUM_SHOW];
            for(int i = 0; i < NUM_SHOW; i++) {
                rv[i] = possibleWords.get(i);
            }
            return rv;
        } else {
            String[] rv = new String[possibleWords.size()];
            for(int i = 0; i < possibleWords.size(); i++) {
                rv[i] = possibleWords.get(i);
            }
            return rv;
        }
    }

    public static int pointValue(String word, char[] tiles) {
        int points = 0;
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            chars.add(c);
        }

        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < chars.size(); j++) {
                if((int)tiles[i] == (int) chars.get(j)) {
                    points += numPoints[charToInt(tiles[i])];
                    chars.remove(j);
                    break;
                }
            }
        }
        return points;
    }

    public static int charToInt(char e) {
        return (int)e - 97;
    }

    //public static int wordPoint(String word)
}
