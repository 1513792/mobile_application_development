package com.example.phamtruong.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import org.w3c.dom.Text;
import android.view.LayoutInflater;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Spinner above,below;
	SpinnerAdapter adapter;
	String[] content = {"Please choose one","Great Britain Pound", "United State Dollar", "Vietnam Dong", "Euro"};
	Integer[] img = {R.drawable.britain,R.drawable.britain, R.drawable.usa, R.drawable.vietnam, R.drawable.euro};
	float[] ratio = {0, 32150, 22810, 1, 28130};
	String[] abbr ={"", "GBP", "USD", "VND", "EUR"};
    EditText  in;
    int inselected, outselected;
    String intxt;
    TextView result, inlabel, outlabel;
    Button submit;
    float init, inRate, outRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        above = (Spinner) findViewById(R.id.above);
        below = (Spinner) findViewById(R.id.below);
        in = (EditText) findViewById(R.id.intxt);
        result = (TextView) findViewById(R.id.result);
        submit = (Button) findViewById(R.id.exchange_btn);
        inlabel = (TextView) findViewById(R.id.inlabel);
        outlabel = (TextView) findViewById(R.id.outlabel);

	    adapter = new SpinnerAdapter(this, content, img, ratio, abbr);
        above.setAdapter(adapter);
        below.setAdapter(adapter);
        //event handling setting
	    above.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //get position
                inselected = i;
                inRate = ratio[i];
                //ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) in.getLayoutParams();
                if(i == 0){
                    inlabel.setVisibility(View.GONE);
                }
                else{
                    inlabel.setVisibility(View.VISIBLE);
                    inlabel.setText(abbr[i]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });
        below.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //get position
                outselected = i;
                outRate = ratio[i];
                if(i == 0){
                    outlabel.setVisibility(View.GONE);
                }
                else{
                    outlabel.setVisibility(View.VISIBLE);
                    outlabel.setText(abbr[i]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });

        new Thread(){
            @Override
            public void run() {
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intxt = in.getText().toString();
                        if(intxt.length() == 0)
                            in.setError("This attribute is compulsory");
                        else{
                            init = Float.parseFloat(intxt);
                            exchange(inselected, outselected, inRate, outRate, init);
                        }
                    }
                });
            }
        }.start();
    }
    //exchange function
    public void exchange(int input, int output, float inRate, float outRate, float init){
        //Invalid or same category
        if(input == 0 || output == 0){
            Toast.makeText(this, "Can not exchange", Toast.LENGTH_SHORT).show();
            result.setText("");
            return;
        }
        //Differ
        else{
            float ret = init * inRate / outRate;
            String x = Float.toString(ret);
            result.setText(x);
        }
    }
}
