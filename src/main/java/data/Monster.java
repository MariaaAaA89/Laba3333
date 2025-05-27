package data;

import java.util.List;

public class Monster {
    private String name;
    private String description;
    private List<String> habitat;
    private int firstMentioned;
    private List<String> immuneTo;
    private String height;
    private String weight;
    private String activeAt;
    private Potion potion;
    private String strength;
    private String source;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getHabitat() { return habitat; }
    public void setHabitat(List<String> habitat) { this.habitat = habitat; }
    public int getFirstMentioned() { return firstMentioned; }
    public void setFirstMentioned(int firstMentioned) { this.firstMentioned = firstMentioned; }
    public List<String> getImmuneTo() { return immuneTo; }
    public void setImmuneTo(List<String> immuneTo) { this.immuneTo = immuneTo; }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getActiveAt() { return activeAt; }
    public void setActiveAt(String activeAt) { this.activeAt = activeAt; }
    public Potion getPotion() { return potion; }
    public void setPotion(Potion potion) { this.potion = potion; }
    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    @Override
    public String toString() {
        return name;
    }

//    public static class Potion {
//        private List<Ingredient> ingredients;
//        private int brewTimeMinutes;
//
//        public List<Ingredient> getIngredients() { return ingredients; }
//        public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
//        public int getBrewTimeMinutes() { return brewTimeMinutes; }
//        public void setBrewTimeMinutes(int brewTimeMinutes) { this.brewTimeMinutes = brewTimeMinutes; }

//        public static class Ingredient {
//            private String name;
//            private String amount;
//
//            public String getName() { return name; }
//            public void setName(String name) { this.name = name; }
//            public String getAmount() { return amount; }
//            public void setAmount(String amount) { this.amount = amount; }
        }
    