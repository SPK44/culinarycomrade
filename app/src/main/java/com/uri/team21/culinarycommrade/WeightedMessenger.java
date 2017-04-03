package com.uri.team21.culinarycommrade;

import java.util.Arrays;
import java.util.Comparator;


public class WeightedMessenger {
    public String[] recipes;
    public double[] weights;
    public int index = 0;

    WeightedMessenger(int length) {
        recipes = new String[length];
        weights = new double[length];
    }

    public void append(String recipe, double weight) {
        recipes[index] = recipe;
        weights[index] = weight;
        index++;
    }

    public void sort() {
        NameNumber [] zip = new NameNumber[Math.min(recipes.length,weights.length)];
        for(int i = 0; i < zip.length; i++)
        {
            zip[i] = new NameNumber(recipes[i],weights[i]);
        }

        Arrays.sort(zip, new Comparator<NameNumber>() {

            @Override
            public int compare(NameNumber o1, NameNumber o2) {
                return Double.compare(o2.number, o1.number);
            }
        });

        for(int i = 0; i < zip.length; i++) {
            recipes[i] = zip[i].name;
            weights[i] = zip[i].number;
        }

    }
}

class NameNumber
{

    public NameNumber(String name, double n) {
        this.name = name;
        this.number = n;
    }

    public String name;
    public double number;
}