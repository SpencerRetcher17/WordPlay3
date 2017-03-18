package com.example.sretcher.wordplay3;

//Import Statements

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by sretcher on 3/1/2017.
 */


public class Anagram {

    //Contains all five letter words

    private ArrayList<String> myWords = new ArrayList<>();

    //Contains the anagrams for a word

    private ArrayList<String> combinations = new ArrayList<>();

    //Default Constructor- I plan on using methods such as isAnagram that doesn't require reading a file

    public Anagram() {


    }

    //Main Constructor- Calls ReadWords and gets the words from a file

    public Anagram(Context myContext, String fileName) {

        readWords(myContext, fileName);

    }

    //Reading in the words

    public void readWords(Context myContext, String fileName) {

        BufferedReader myReader;

        try {

            //Opens the file up and prepares for reading the words

            InputStream fin = myContext.getAssets().open(fileName);

            myReader = new BufferedReader(new InputStreamReader(fin));



            //Read a single five letter word on each line, adds word to myWords till there are no more lines

            String line = myReader.readLine();
            while (line != null) {

                //Prints out word

                Log.i("Anagram", line);

                //Replaces all non characters with spaces, Note I did the following in case I wanted
                //to read say a passage from a book instead of a list of words

                String line1 = line.replaceAll("\\W", " ");

                //Breaks up words on lines based on spaces for passages

                String[] words = line1.split("\\s");

                //Adds word to myWords, exclude spaces

                for (int i = 0; i < words.length; i++) {
                    if (!words[i].equals(""))
                        myWords.add(words[i]);
                }

                //Gets next line for iteration

                line = myReader.readLine();
            }

            //Prints info if file is not found

        } catch (IOException e) {

            Log.i("Anagram", "No file found");

        }
    }

    //Getter-returns ArrayList of Words

    public ArrayList<String> getWords() {

        return myWords;
    }

//Getter-returns ArrayList of anagrams

    public ArrayList<String> getCombinations() {

        return combinations;
    }


    //Didn't use this method but maybe in the future?
    //Checks if two strings are anagrams. They must have the same letters but form a diffrent word
    //If letters in string1 are contained in string2 and they are the same length-Anagram

    public boolean isAnagram(String word1, String word2) {
        int numLetters = 0;

        for (int i = 0; i < word1.length() - 1; i++) {

            if (word1.length() != word2.length()) {

                return false;

            } else if (word1.contains(word2.charAt(i) + "")) {

                numLetters++;

            }


        }

        return numLetters == (word1.length() - 1);

    }

//Returns a random word in arraylist

    public String getRandomWord() {
        return getWords().get((int) (Math.random() * myWords.size()));
    }

    //Convert a string to character array. Loop through word and swap the character at i with
    //a random character in the word. Returns a scrambled string

    public String scramble(String letters) {


        char[] letterArray = letters.toCharArray();


        for (int i = 0; i < letterArray.length; i++) {


            int random = (int) (Math.random() * letterArray.length);

            char temp = letterArray[i];

            letterArray[i] = letterArray[random];

            letterArray[random] = temp;

        }

        //Adds characters up into a string to return a string

        String word = "";

        for (char l : letterArray) {

            word += l;

        }

        return word;
    }


    //Ughh Recursion. This was the hardest part of the program. This finds all anagrams for
    // a word. If I work on this more, I would make it detect valid words, not made up words
    // String Permututation takes in a string (input) and an empty string "" (permutation) for paramters.
    //Its loops through the word and at every iteration it finds all permutations for the word that
    //start with the character at i. Every diffrent anagram is eventually added to permutation
    //which leaves input empty. When input.length is empty, it ends that specific iteration and adds
    //the specific word to the ArrayList Combinations

//TODO
    public void printStringPermutation(String input) {
        StringPermutation("", input);
    }
    //nnn

    private void StringPermutation(String permutation, String input) {

        //Base Case-Ends Recursive calls

        if (input.length() == 0) {
            Log.i("Permutation", permutation);
            getCombinations().add(permutation);
        } else {
            for (int i = 0; i < input.length(); i++) {
                StringPermutation(permutation + input.charAt(i), input.substring(0, i) + input.substring(i + 1, input.length()));
            }
        }

    }


}









