package com.example.yahtzeegame.backend;

public enum ScoreCategory {
    Aces, Twos, Threes, Fours, Fives, Sixes, Three_of_a_kind, Four_of_a_kind, Full_House, SM_Straight, LG_Straight, Chance, Yahtzee;
    public static int getCategoryValue(ScoreCategory category){
        int categoryValue = 0;
        switch (category){
            case Aces:
                categoryValue = 1;
                break;
            case Twos:
                categoryValue = 2;
                break;
            case Threes:
                categoryValue = 3;
                break;
            case Fours:
                categoryValue = 4;
                break;
            case Fives:
                categoryValue = 5;
                break;
            case Sixes:
                categoryValue = 6;
                break;
            default:
                break;
        }
        return categoryValue;
    }
}
