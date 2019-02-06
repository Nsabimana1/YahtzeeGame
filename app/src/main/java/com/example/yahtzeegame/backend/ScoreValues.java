package com.example.yahtzeegame.backend;

import java.util.ArrayList;

public class ScoreValues {
    ScoreValues(){}
   public static int scoreOnes(ArrayList<Dice> alldices, ScoreCategory ScoreCategory){
        int countOnes = 0;
        for(Dice d: alldices){
            if(d.getValue() == 1){
                countOnes += 1;
            }
        }
        return countOnes;
   }
}
