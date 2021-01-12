package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.animation.BidirectionalTypeConverter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Vvod;
    private EditText Rezultat;
    private TextView VvodText;
    private TextView RezultatText;
    private Spinner SpinnerIn;
    private Spinner SpinnerOut;
    private Button Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Vvod = (EditText) findViewById(R.id.Vvod);
        VvodText = (TextView) findViewById(R.id.VvodText);
        Rezultat = (EditText) findViewById(R.id.Rezultat);
        RezultatText = (TextView) findViewById(R.id.RezultatText);
        SpinnerIn = (Spinner) findViewById(R.id.SpinnerIn);
        SpinnerOut = (Spinner) findViewById(R.id.SpinnerOut);
        Count = (Button) findViewById(R.id.Count);
        Count.setOnClickListener(this);
    }

    private static String Convert2to8(String _num) {
        return Convert10to8(Convert2to10(_num));
    }

    private static String Convert2to10(String _num) {
        int osnov = 2;
        String mod = "";
        String div = "";
        if (_num.indexOf('.') != -1){
            mod = _num.substring(0, _num.indexOf('.'));
            div = _num.substring(_num.indexOf('.')+1);
        }
        else mod = _num;
        int result = 0;
        double revers = 0;
        for(int i = 0; i < mod.length(); i++){
            result += Integer.parseInt(mod.substring(i, i+1)) * Math.pow(osnov, mod.length()-i-1);
        }
        if (_num.indexOf('.') != -1){
            for(int i = 0; i < div.length(); i++){
                double Pow = Math.pow(osnov, -(i+1));
                revers += Integer.parseInt(div.substring(i, i+1)) * Pow;
            }
        }
        revers += result;

        return String.valueOf(revers) ;
    }

    private static String Convert2to16(String _num) {
        return Convert10to16(Convert2to10(_num));
    }

    private static String Convert8to2(String _num) {
        return Convert10to2(Convert8to10(_num));
    }

    private static String Convert8to10(String _num) {
        int osnov = 8;
        String mod = "";
        String div = "";
        if (_num.indexOf('.') != -1){
            mod = _num.substring(0, _num.indexOf('.'));
            div = _num.substring(_num.indexOf('.')+1);
        }
        else mod = _num;
        int result = 0;
        double revers = 0;
        for(int i = 0; i < mod.length(); i++){
            result += Integer.parseInt(mod.substring(i, i+1)) * Math.pow(osnov, mod.length()-i-1);
        }
        if (_num.indexOf('.') != -1) {
            for (int i = 0; i < div.length(); i++) {
                revers += Integer.parseInt(div.substring(i, i + 1)) * Math.pow(osnov, -(i + 1));
            }
        }
        revers += result;

        return String.valueOf(revers);
    }

    private static String Convert8to16(String _num) {
        return Convert10to16(Convert8to10(_num));
    }

    private static String Convert10to2(String _num) {
        int mod;
        double div = 0;
        if (_num.indexOf('.') != -1) {
            mod = Integer.parseInt(_num.substring(0, _num.indexOf('.')));
            div = Double.parseDouble(_num.substring(_num.indexOf('.')));
        } else mod = Integer.parseInt(_num);
        String result = "";
        String revers = "";
        while (mod != 0 && mod != 1) {
            result += String.valueOf(mod % 2);
            mod /= 2;
        }
        result += String.valueOf(mod);
        for (int i = 0; i < result.length(); i++) {
            revers = result.charAt(i) + revers;
        }
        if (_num.indexOf('.') != -1) {
            result = "";
            int Iterator = 3;
            for (int i = 0; i < Iterator; i++) {
                div *= 2;
                result += Integer.toString((int) div);
                div -= (int) div;
            }
            revers += "." + result;
        }
        return revers;
    }

    private static String Convert10to8(String _num) {
        int mod;
        double div = 0;
        if (_num.indexOf('.') != -1) {
            mod = Integer.parseInt(_num.substring(0, _num.indexOf('.')));
            div = Double.parseDouble(_num.substring(_num.indexOf('.')));
        } else mod = Integer.parseInt(_num);
        String result = "";
        String revers = "";
        while (mod > 8) {
            result += String.valueOf(mod % 8);
            mod /= 8;
        }
        result += String.valueOf(mod);
        for (int i = 0; i < result.length(); i++) {
            revers = result.charAt(i) + revers;
        }
        if (_num.indexOf('.') != -1) {
            result = "";
            int Iterator = 3;
            for (int i = 0; i < Iterator; i++) {
                div *= 8;
                result += Integer.toString((int) div);
                div -= (int) div;
            }
            revers += "." + result;
        }
        return revers;
    }

    private static String Convert10to16(String _num) {
        int def = 55;
        int mod;
        double div = 0;
        if (_num.indexOf('.')!= -1){
            mod = Integer.parseInt(_num.substring(0, _num.indexOf('.')));
            div = Double.parseDouble(_num.substring(_num.indexOf('.')));
        }
        else mod = Integer.parseInt(_num);
        String result = "";
        String revers = "";
        while(mod > 16){
            result += mod % 16 < 10 ? String.valueOf(mod % 16) : String.valueOf((char)(def + mod % 16));
            mod /= 16;
        }
        result += mod % 16 < 10 ? String.valueOf(mod % 16) : String.valueOf((char)(def + mod % 16));
        if(mod == 16) result+= "1";
        for (int i = 0; i < result.length(); i++){
            revers = result.charAt(i) + revers;
        }
        if (_num.indexOf('.')!= -1){
            result = "";
            int Iterator = 3;
            for (int i = 0; i< Iterator; i++){
                div *= 16;
                result += div % 16 < 10 ? String.valueOf((int)(div % 16)) : String.valueOf((char)((int)(def + div % 16)));
                div -= (int) div;
            }
            revers += "." + result;
        }
        return revers;
    }

    private static  String Convert16to2(String _num){
        return Convert10to2(Convert16to10(_num));
    }

    private static  String Convert16to8(String _num){
        return Convert10to8(Convert16to10(_num));
    }

    private static  String Convert16to10(String _num){
        int osnov = 16;
        String mod = "";
        String div = "";
        if (_num.indexOf('.') != -1){
            mod = _num.substring(0, _num.indexOf('.'));
            div = _num.substring(_num.indexOf('.')+1);
        }
        else mod = _num;
        int result = 0;
        int def = 55;
        double revers = 0;
        for(int i = 0; i < mod.length(); i++){
            if (mod.substring(i,i+1).charAt(0) > 57){
                result+=(mod.substring(i,i+1).charAt(0) - def) *  Math.pow(osnov, mod.length()-i-1);
            }
            else
            result += Integer.parseInt(mod.substring(i, i+1)) * Math.pow(osnov, mod.length()-i-1);
        }
        if (_num.indexOf('.') != -1) {
            for (int i = 0; i < div.length(); i++) {
                if (div.substring(i,i+1).charAt(0) > 57){
                    revers+=(div.substring(i,i+1).charAt(0)-def) * Math.pow(osnov,-(i+1));
                }
                else
                revers += Integer.parseInt(div.substring(i, i + 1)) * Math.pow(osnov, -(i + 1));
            }
        }
        revers += result;

        return String.valueOf(revers);
    }

        @Override
    public void onClick(View v) {
    if (v.getId() == R.id.Count){
        String selector = SpinnerIn.getSelectedItem().toString()+"_"+ SpinnerOut.getSelectedItem().toString();
        String ConvertResult = "";
        String Number = Vvod.getText().toString();
    switch (selector) {
        case "2_8":
            ConvertResult = Convert2to8(Number);
            break;
        case "2_10":
            ConvertResult = Convert2to10(Number);
            break;
        case "2_16":
            ConvertResult = Convert2to16(Number);
            break;
        case "8_2":
            ConvertResult = Convert8to2(Number);
            break;
        case "8_10":
            ConvertResult = Convert8to10(Number);
            break;
        case "8_16":
            ConvertResult = Convert8to16(Number);
            break;
        case "10_2":
            ConvertResult = Convert10to2(Number);
            break;
        case "10_8":
            ConvertResult = Convert10to8(Number);
            break;
        case "10_16":
           ConvertResult = Convert10to16(Number);
            break;
        case "16_2":
            ConvertResult = Convert16to2(Number);
            break;
        case "16_8":
            ConvertResult = Convert16to8(Number);
            break;
        case "16_10":
            ConvertResult = Convert16to10(Number);
            break;
        default: ConvertResult = String.valueOf(Number);
            break;
    }
        Rezultat.setText(ConvertResult);

    }
    }
}//fds