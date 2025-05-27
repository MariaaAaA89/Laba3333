/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.List;

/**
 *
 * @author Мария
 */


public class Potion {
    private List<Ingredient> ingredients;
    private int brewTimeMinutes;

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
    public int getBrewTimeMinutes() { return brewTimeMinutes; }
    public void setBrewTimeMinutes(int brewTimeMinutes) { this.brewTimeMinutes = brewTimeMinutes; }
}
