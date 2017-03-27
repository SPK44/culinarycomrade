package com.uri.team21.culinarycommrade;

/**
 * Created by Shaps on 3/26/2017.
 */


import android.content.Context;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ingredientAdapter extends ArrayAdapter<ingredientView> {

    private ArrayList<ingredientView> ingredients = new ArrayList<>();
    private Context mContext;
    private ArrayList<String> ingredNames;


    public ingredientAdapter(Context context, int textViewResourceId, ArrayList<ingredientView> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        System.out.println(objects);
        ingredients = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ingredientView view;
        View v = convertView;

        System.out.println(convertView);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ingredientlayout, parent, false);
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //v = inflater.inflate(R.layout.ingredientlayout, null);
        //view.setIngredientName("hi");
        TextView ingName = (TextView) convertView.findViewById(R.id.ingName);
        Button inventBut = (Button) convertView.findViewById(R.id.inventBut);
        Button shopBut = (Button) convertView.findViewById(R.id.shopBut);
        ingName.setText(ingredients.get(position).getName());


        return convertView;
    }
}
