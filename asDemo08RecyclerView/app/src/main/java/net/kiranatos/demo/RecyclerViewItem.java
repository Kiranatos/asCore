package net.kiranatos.demo;

public class RecyclerViewItem {
    private int imageResource; // int тому-що доступ до зображень в папці drawable виконується по цілочисленому коді
    private String text1;
    private String text2;

    public RecyclerViewItem(int imageResource, String text1, String text2) {
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
