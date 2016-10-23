package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList = new ArrayList<>();


    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);


        }
    }

    public boolean isGoodWord(String word, String base) {
        boolean good = false;
        if(wordList.contains(word)) {
            if (word.contains(base)) {
                return false;
            }
            else {
                good = true;
            }

        }
        return good;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        String orderedWord = sortLetters(targetWord);
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).length() == orderedWord.length() && sortLetters(wordList.get(i)).equals(orderedWord)){
                result.add(wordList.get(i));
            }
        }

        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String oneMoreLetter = word;
        for(int i = 97; i < 123; i++){
            oneMoreLetter += (char)i;
            result = getAnagrams(oneMoreLetter);
            

        }

        return result;
    }

    public String sortLetters(String word){

        String sortedWord;
        char[] arrayWord = new char[word.length()];
        for(int i = 0; i < word.length(); i++){
            arrayWord[i] = word.charAt(i);
        }
        Arrays.sort(arrayWord);
        sortedWord = new String(arrayWord);
        return sortedWord;

    }

    public String pickGoodStarterWord() {
            int index = random.nextInt(wordList.size());
                return wordList.get(index);
    }


}
