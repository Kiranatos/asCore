package net.kiranatos.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* GPT { Цей клас успадковує RecyclerView.Adapter і працює з власним внутрішнім класом RecyclerViewViewHolder.
Він зв'язує дані (модель RecyclerViewItem) з візуальним відображенням (recycler_view_item.xml). }*/
public class PizzaRecipeAdapter extends RecyclerView.Adapter<PizzaRecipeAdapter.PizzaRecipeViewHolder> {

    private ArrayList<PizzaRecipeItem> arrayList;
    Context context;

    /* Autor of video deleted in class here public and static
    https://stackoverflow.com/questions/31302341/what-difference-between-static-and-non-static-viewholder-in-recyclerview-adapter    */
    class PizzaRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        /* GPT {Контейнер для одного елемента списку. Дозволяє ефективно перевикористовувати UI-елементи (для продуктивності).}*/
        public PizzaRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            PizzaRecipeItem pizzaRecipeItem = arrayList.get(position);

            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("imageResource", pizzaRecipeItem.getImageResource());
            intent.putExtra("title", pizzaRecipeItem.getTitle());
            intent.putExtra("description", pizzaRecipeItem.getDescription());
            intent.putExtra("recipe", pizzaRecipeItem.getRecipe());
            context.startActivity(intent);
        }
    } // end of class RecyclerViewViewHolder

    public PizzaRecipeAdapter(ArrayList<PizzaRecipeItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // Метод створює новий RecyclerViewViewHolder, передаючи розмітку recycler_view_item в View
    @NonNull
    @Override
    public PizzaRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item,
                viewGroup, false);
        PizzaRecipeViewHolder recyclerViewViewHolder = new PizzaRecipeViewHolder(view);
        return recyclerViewViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PizzaRecipeViewHolder recyclerViewViewHolder, int i) {
        PizzaRecipeItem recyclerViewItem = arrayList.get(i);

        recyclerViewViewHolder.imageView.setImageResource(recyclerViewItem.getImageResource());
        recyclerViewViewHolder.textView1.setText(recyclerViewItem.getTitle());
        recyclerViewViewHolder.textView2.setText(recyclerViewItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}