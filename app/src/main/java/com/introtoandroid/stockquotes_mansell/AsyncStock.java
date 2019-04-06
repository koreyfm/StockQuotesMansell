package com.introtoandroid.stockquotes_mansell;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;



public class AsyncStock extends AsyncTask<Stock, Void, Stock> {

    Stock stock;

    TextView[] info;

    public AsyncStock(Stock stock, TextView[] info) {

        this.info = info;
        this.stock = stock;
    }

    @Override
    protected Stock doInBackground(Stock... stocks) {
        //return null;

        try{
            stock.load ( );
            return stock;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(Stock s){
        super.onPostExecute (s);


        info[0].setText (s.getSymbol ());
        info[1].setText (s.getLastTradeTime ());
        info[2].setText (s.getLastTradePrice ());
        info[3].setText (s.getChange ());
        info[4].setText (s.getRange ());
        info[5].setText (s.getName ());


    }


}
