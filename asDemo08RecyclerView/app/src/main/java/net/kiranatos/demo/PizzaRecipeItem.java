package net.kiranatos.demo;

public class PizzaRecipeItem {
    private int imageResource; // int тому-що доступ до зображень в папці drawable виконується по цілочисленому коді
    private String title;
    private String description;
    private String recipe;

    public PizzaRecipeItem(int imageResource, String title, String description, String recipe) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.recipe = recipe;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRecipe() {
        return recipe;
    }
}
