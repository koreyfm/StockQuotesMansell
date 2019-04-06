package com.introtoandroid.stockquotes_mansell;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Stock stock;
    EditText symbolInput;
    TextView symbolOutput;
    TextView nameOutput;
    TextView tradePriceOutput;
    TextView tradeTimeOutput;
    TextView changeOutput;
    TextView rangeOutput;
    TextView[] textViewArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        symbolInput = (EditText) findViewById(R.id.symbolInput);
        symbolOutput = (TextView) findViewById(R.id.symbolOutput);
        nameOutput = (TextView) findViewById(R.id.nameOutput);
        tradePriceOutput = (TextView) findViewById(R.id.tradePriceOutput);
        tradeTimeOutput = (TextView) findViewById(R.id.tradeTimeOutput);
        changeOutput = (TextView) findViewById(R.id.changeOutput);
        rangeOutput = (TextView) findViewById(R.id.rangeOutput);


        textViewArray = new TextView[]{symbolOutput, tradeTimeOutput, tradePriceOutput, changeOutput, rangeOutput, nameOutput};


        symbolInput.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String tempString;

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_C)) {

                    //Toast.makeText(getApplicationContext(), "You Clicked Enter!", Toast.LENGTH_SHORT).show();

                    if (symbolInput.getText().toString() != null && symbolInput.length() == 4) {
                        //Start the Async Task Thread Here....

                        tempString = symbolInput.getText().toString();

                        Stock stock = new Stock(tempString);


                        AsyncStock asyncStock = new AsyncStock(stock, textViewArray);

                        asyncStock.execute();



                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Stock Symbol...", Toast.LENGTH_SHORT).show();
                        return false;
                    }


                    return true;
                }
                return false;
            }
        });


    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("editText",(String)symbolInput.getText().toString());
        savedInstanceState.putString("symbolOutput",(String)symbolOutput.getText().toString());
        savedInstanceState.putString("nameOutput",(String)nameOutput.getText().toString());
        savedInstanceState.putString("rangeOutput",(String)rangeOutput.getText().toString());
        savedInstanceState.putString("tradePriceOutput",(String)tradePriceOutput.getText().toString());
        savedInstanceState.putString("tradeTimeOutput",(String)tradeTimeOutput.getText().toString());
        savedInstanceState.putString("changeOutput",(String)changeOutput.getText().toString());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        symbolInput.setText(savedInstanceState.getString("editText"));
        symbolOutput.setText(savedInstanceState.getString("symbolOutput"));
        nameOutput.setText(savedInstanceState.getString("nameOutput"));
        rangeOutput.setText(savedInstanceState.getString("rangeOutput"));
        tradePriceOutput.setText(savedInstanceState.getString("tradePriceOutput"));
        changeOutput.setText(savedInstanceState.getString("changeOutput"));
        tradeTimeOutput.setText(savedInstanceState.getString("tradeTimeOutput"));



    }
}