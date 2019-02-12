package com.example.yahtzeegame.backend;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ScoreValues {
    HashMap<Integer, Integer> dicetoScoreCount;
    HashMap<ScoreCategory, Integer> categoryToValue;
    ArrayList<ScoreButton> allScoreButtons;

    public ScoreValues(){
        this.dicetoScoreCount = new HashMap<>();
        this.categoryToValue = new HashMap<>();
    }

    public void setAllScoreButtons(ArrayList<ScoreButton> scoreButtons){
        this.allScoreButtons = scoreButtons;
    }

    public int scoreDices(ArrayList<Dice> alldices, ScoreCategory scoreCategory){
        int score = 0;
        for(Dice d: alldices){
            if(d.getValue() == ScoreCategory.getCategoryValue(scoreCategory)){
                score += 1;
            }
        }
        return score * ScoreCategory.getCategoryValue(scoreCategory) ;
   }


   public void calculateScore(ArrayList<Dice> alldices){
        for(int i = 1; i <= 6; i++){
            this.dicetoScoreCount.put(i, ScoreValues.getScore(alldices, i));
        }
   }

   public static int getScore(ArrayList<Dice> alldices, int num ){
       int score = 0;
       for(Dice d: alldices){
           if(d.getValue() == num){
               score += 1;
           }
       }
       return score ;
   }

   public int getScoreFor(ScoreCategory sc) {
        return categoryToValue.get(sc);
   }

   public ScoreButton getButtoFromScoreButton(ScoreCategory category){
        ScoreButton toReturn = null;
        for(ScoreButton b: this.allScoreButtons){
            if(b.getCategory().equals(category)){
                toReturn = b;
                break;
            }
        }
        return toReturn;
   }

   public void assignCategoryValues(){
        for(Integer e: this.dicetoScoreCount.keySet()){
            if(e == 1 && !this.getButtoFromScoreButton(ScoreCategory.Aces).isScored()){
                this.categoryToValue.put(ScoreCategory.Aces, this.dicetoScoreCount.get(e) * e);
            }else if(e == 2 && !this.getButtoFromScoreButton(ScoreCategory.Twos).isScored()){
                this.categoryToValue.put(ScoreCategory.Twos, this.dicetoScoreCount.get(e) * e);
            }
            else if(e == 3 && !this.getButtoFromScoreButton(ScoreCategory.Threes).isScored()){
                this.categoryToValue.put(ScoreCategory.Threes, this.dicetoScoreCount.get(e) * e);
            }
            else if(e == 4 && !this.getButtoFromScoreButton(ScoreCategory.Fours).isScored()){
                this.categoryToValue.put(ScoreCategory.Fours, this.dicetoScoreCount.get(e) * e);
            }
            else if(e == 5 && !this.getButtoFromScoreButton(ScoreCategory.Fives).isScored()){
                this.categoryToValue.put(ScoreCategory.Fives, this.dicetoScoreCount.get(e) * e);
            }
            else if(e == 6 && !this.getButtoFromScoreButton(ScoreCategory.Sixes).isScored()){
                this.categoryToValue.put(ScoreCategory.Sixes, this.dicetoScoreCount.get(e) * e);
            }
        }
        if(!this.getButtoFromScoreButton(ScoreCategory.Three_of_a_kind).isScored()){
            this.categoryToValue.put(ScoreCategory.Three_of_a_kind, this.getNumoffaKind(3) * 3);
        }

       if(!this.getButtoFromScoreButton(ScoreCategory.Four_of_a_kind).isScored()){
           this.categoryToValue.put(ScoreCategory.Four_of_a_kind, this.getNumoffaKind(4) * 4);
       }

       if(!this.getButtoFromScoreButton(ScoreCategory.Yahtzee).isScored()){
           this.categoryToValue.put(ScoreCategory.Yahtzee, this.getNumoffaKind(5) > 0 ? 50:0);
       }
       if(!this.getButtoFromScoreButton(ScoreCategory.Chance).isScored()){
           this.categoryToValue.put(ScoreCategory.Chance, this.ScoreAChance());
       }
       if(!this.getButtoFromScoreButton(ScoreCategory.SM_Straight).isScored()){
           ArrayList<Integer> dices  = new ArrayList<>(this.dicetoScoreCount.keySet());
           this.categoryToValue.put(ScoreCategory.SM_Straight, this.scoreStraight(1) ? 30:0);
       }

       if(!this.getButtoFromScoreButton(ScoreCategory.LG_Straight).isScored()){
           ArrayList<Integer> dices  = new ArrayList<>(this.dicetoScoreCount.keySet());
           this.categoryToValue.put(ScoreCategory.LG_Straight, this.scoreStraight(2) ? 40:0);
       }

       if(!this.getButtoFromScoreButton(ScoreCategory.Full_House).isScored()){
           if(this.getNumoffaKind(3) != 0 && this.getNumoffaKind(2) != 0 && this.getNumoffaKind(3) != this.getNumoffaKind(2)){
               this.categoryToValue.put(ScoreCategory.Full_House, 25);
           }else {
               this.categoryToValue.put(ScoreCategory.Full_House, 0);
           }
       }
   }


   public Integer getNumoffaKind(int num){
        for(Integer e: this.dicetoScoreCount.keySet()){
            if (this.dicetoScoreCount.get(e) == num){
                return e;
            }
        }
        return 0;
   }

   public Integer ScoreAChance(){
        int tatal = 0;
       for(Integer e: this.dicetoScoreCount.keySet()){
           tatal += e * this.dicetoScoreCount.get(e);
           }
       return tatal;
   }

   public boolean scoreStraight(int min){
        int count = 0;
        ArrayList<Integer> dices  = new ArrayList<>(this.dicetoScoreCount.keySet());
        Collections.sort(dices);
        for(int i = 0; i < dices.size() - 1; i ++){
            if(dicetoScoreCount.get(dices.get(i)) > 0 && dices.get(i) + 1 == (dices.get(i + 1))){
                count += 1;
            }
        }

        if (count == dices.size() - 1 && dices.get(0) == min){
            return true;
        }else {
            return false;
        }
   }


   public Integer getInitialSum(){
        int initialSum = 0;
        for (ScoreCategory sc: ScoreCategory.values()) {
            if (ScoreCategory.getCategoryValue(sc) > 0) {
                initialSum += this.categoryToValue.get(sc);
            }
        }
        return initialSum;
   }

   public Integer getBonusScore(){
        return this.getInitialSum() >= 63 ? 35 : 0;
   }

   public Integer getTotalScore(){
        this.assignCategoryValues();
        int totalSum = 0;
        for(ScoreCategory c: this.categoryToValue.keySet()){
            totalSum += this.categoryToValue.get(c);
        }
        return totalSum;
   }

   public HashMap<ScoreCategory, Integer> getCategoryToValueMap(){
        return this.categoryToValue;
   }
}
