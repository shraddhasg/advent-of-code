package com.aoc.day_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AllergenAssessment {
    public static HashMap<String, String> ingredientsWithallergens = new HashMap<>();
    public static ArrayList<ArrayList<String>> allergens = new ArrayList<ArrayList<String>>();

    public static ArrayList<ArrayList<String>> ingredient = new ArrayList<ArrayList<String>>();

    public static int partOne(int allIngredientSize) {
        ArrayList<String> ingredientHavingAllergens = new ArrayList<>();
        for (int i = 0; i < allergens.size(); i++) {
            ArrayList<String> ingredientAl = ingredient.get(i);
            ArrayList<String> allergenAl = allergens.get(i);
            for (int k = 0; k < allergenAl.size(); k++) {
                for (int j = 0; j < ingredientAl.size(); j++) {
                    if (ingredientsWithallergens.containsKey(ingredientAl.get(j))) continue;
                    ingredientsWithallergens.put(ingredientAl.get(j), allergenAl.get(k));
                    ingredientHavingAllergens.add(ingredientAl.get(j));
                    break;
                }
            }

        }
        return allIngredientSize - ingredientHavingAllergens.size();
    }

    public static void inputExtraction(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String[] temp = input.nextLine().split(" [\\(\\)]");
            String[] ingredients = temp[0].split(" ");
            ArrayList<String> al1 = new ArrayList<>();
            for (int i = 0; i < ingredients.length; i++) al1.add(ingredients[i]);
            ingredient.add(al1);
//            countIngredients(ingredients);
            String[] allergen = temp[1].substring(9, temp[1].length() - 1).split(", ");
            ArrayList<String> al2 = new ArrayList<>();
            for (int i = 0; i < allergen.length; i++) al2.add(allergen[i]);
            allergens.add(al2);
        }
//        System.out.println(allergens);
    }

    public static void main(String[] args) throws FileNotFoundException {
        inputExtraction(new File("resources/inputs/day_21.txt"));
        int allIngredientSize = 0;
        for (int i = 0; i < ingredient.size(); i++) {
            allIngredientSize += ingredient.get(i).size();
        }
        int partOne = partOne(allIngredientSize);
        System.out.println("Part One= " + partOne);
    }
}
