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

    ArrayList<ingredientView> ingredients = new ArrayList<>();
    private Context mContext;
    private ArrayList<String> ingredNames;


    public ingredientAdapter(Context context, int textViewResourceId, ArrayList<ingredientView> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        System.out.println(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ingredientView view;
        View v = convertView;

        if(convertView == null){
            view = new ingredientView(mContext);
        } else {
            view = (ingredientView) convertView;
        }

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //v = inflater.inflate(R.layout.ingredientlayout, null);
        view.setIngredientName("hi");
        //TextView textView = (TextView) view.findViewById(R.id.textView);
        //Button shop = (Button) v.findViewById(R.id.button);
        //textView.setText(ingredients.get(position).getName());
        return view;
    }
}
