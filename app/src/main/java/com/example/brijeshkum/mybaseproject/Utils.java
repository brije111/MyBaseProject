package com.example.brijeshkum.mybaseproject;

import com.example.brijeshkum.mybaseproject.db.model.Country;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Utils {

    public static Country[] toArray(List<Country> list) {
        Country[] countries = new Country[list.size()];
        for (int i = 0; i < list.size(); i++) {
            countries[i] = list.get(i);
        }
        return countries;
    }
}
