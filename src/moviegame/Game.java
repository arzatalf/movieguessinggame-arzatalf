/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author arzatalf
 */
public class Game {
    private ArrayList<String> readMovies() {
        ArrayList <String> movies = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("movies.txt"));
        
            while(scan.hasNextLine()) {
                movies.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("A movies file was not found. please put a file"
                    + " named movies.txt in your project folder");
        }
        
        return movies;
        }
        
        private String getRandomMovie() {
            ArrayList<String> movies = readMovies();
            int index = (int)(Math.random()*((movies.size()-0)+1))+0;
            return movies.get(index);
        }

        public String obscureMovie(String movieTitle, String correctLetters) {
            if (correctLetters == "") {
            return movieTitle.replaceAll("[a-zA-z]","_");
        } else {
                return movieTitle.replaceAll("[a-zA-Z&&[^"+correctLetters +"]]","_");
            }
        }
        public String inputLetter() {
            System.out.println("guess a letter:");
            //create a scanner to capture user data
            Scanner scan = new Scanner(System.in);
            //store the letter that was guessed
            String letter = scan.next().toLowerCase();

            
            if(!letter.matches("[a-z]")) {
                System.out.println("this is not a letter. Please try again");
                return inputLetter();
            }
            return letter;
        }
        /**
         * checks if the users guess is in the title
         * @param movieTitle
         * @param guess
         * @return boolean
         */
        public boolean checkGuess(String movieTitle, String guess) {
            return movieTitle.contains(guess);   
        }
        
        public void initGame() {
            boolean gameWon = false;
                //Get a movie title
                String movie = getRandomMovie();
                
                //stores incorrect letters guessed
                String wrongLetters= "";
                
                //stores correct letters guessed
                String correctLetters= "";
                
                //creates lives 
                int livesLeft =3;
                
                //string that decides when the game is over
                String GameOver= "GameOver";
                
                while(!gameWon &&(livesLeft>0)) {
                    
                //get an obscured line
                String obscure = obscureMovie (movie, correctLetters);
                System.out.println(obscure);
                
                //create the exit for the loop
                if (obscure.equals(movie)) {
                    System.out.println("You won!");
                    break;
                }
                
                // store a user's guess
                String guess = inputLetter();


                //check if the user was correct
                checkGuess(movie, guess);
                
                
                //checks if you guessed the same letter
                if ((wrongLetters.contains(guess)||(correctLetters.contains(guess)))){
                    System.out.println("you already guessed "+guess);
                   
                //checks if you got the letter right
                } else if (checkGuess(movie, guess)) {
                    correctLetters += guess;
                    System.out.println("Correct!");
                    //checks if guess is wrong
                }else {
                    System.out.println("Sorry "+guess+" is wrong, try again!");
                    livesLeft--;
                    wrongLetters += guess;
                } 
                    
                    //tells you when you are out of lives
                if (livesLeft<1) {
                    System.out.println("GameOver the movie was "+movie);
                }
                
        }        
    }
}