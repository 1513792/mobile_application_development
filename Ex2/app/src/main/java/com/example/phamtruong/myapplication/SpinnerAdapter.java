package com.example.phamtruong.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    private Integer[] imageArray;
    private float[] rate;
    private String[] abbr;

    public SpinnerAdapter(Context context, String[] objects,
                          Integer[] imageArray, float[] rate, String[] abbr) {
        super(context, R.layout.spinner_type, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
        this.rate = rate;
        this.abbr = abbr;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
	LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View row = inflater.inflate(R.layout.spinner_type, null);
	TextView txt = (TextView) row.findViewById(R.id.spinnerTxt);
	ImageView img = (ImageView) row.findViewById(R.id.spinnerImg);
		
	if(position==0){
	    txt.setText("Please choose one");
	    txt.setTextColor(Color.parseColor("#111111"));
	    img.setVisibility(View.GONE);
    }
    else{
        txt.setText(contentArray[position]);
        img.setImageResource(imageArray[position]);
    }
	return row;
    }
}
