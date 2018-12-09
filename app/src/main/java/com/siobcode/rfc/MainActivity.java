package com.siobcode.rfc;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtApellidoPaterno;

    private EditText RFC;
    private EditText apPaterno;
    private EditText apMaterno;
    private EditText Nombre;
    private EditText Date;
    private TextView hermo;
    private Button Limpiar;

    char[] elementos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


    Calendar mycalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            mycalendar.set(Calendar.YEAR, year);
            mycalendar.set(Calendar.MONTH, monthOfYear);
            mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabels();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RFC = (EditText) findViewById(R.id.edtRFC);
        apPaterno = (EditText) findViewById(R.id.edtApellidoPaterno);
        apMaterno = (EditText) findViewById(R.id.edtApellidomaterno);
        Nombre = (EditText) findViewById(R.id.edtname);
        Date = (EditText) findViewById(R.id.edtFechaNac);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, mycalendar
                        .get(Calendar.YEAR), mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void Limpiar(View v) {
                Nombre.setText(null);
                apMaterno.setText(null);
                apPaterno.setText(null);
                Date.setText(null);
                hermo.setText(null);
                RFC.setText(null);
    }
    public void generarRFC(View v) {

        hermo = (TextView) findViewById(R.id.tvHermonimia);
        char [] conjunto = new char[3];
        String homo="";

        //Tomar la primer letra y primer vocal del apellido paterno
        String inputText = apPaterno.getText().toString();
        String cadena = inputText.substring(0, 1);

        String inputText2 = apMaterno.getText().toString();
        String cadena2 = inputText2.substring(0, 1);

        String inputText3 = Nombre.getText().toString();
        String cadena3 = inputText3.substring(0, 1);

        String inputText4 = Date.getText().toString();
        String cadena4 = inputText4.substring(2, 4);

        String inputText5 = Date.getText().toString();
        String cadena5 = inputText5.substring(5, 7);

        String inputText6 = Date.getText().toString();
        String cadena6 = inputText6.substring(8, 10);


        for(int i=0;i<=2;i++){
            conjunto[i] = elementos[(int)(Math.random()*37)];
            homo=homo+=conjunto[i];
            hermo.setText(homo);
            homo = homo.toUpperCase();
        }

        boolean vocal = false;
        int i = 0;
        while (i < inputText.length() & !vocal) {
            if ((inputText.charAt(i) == 'a') ||
                    (inputText.charAt(i) == 'á') ||
                    (inputText.charAt(i) == 'e') ||
                    (inputText.charAt(i) == 'é') ||
                    (inputText.charAt(i) == 'i') ||
                    (inputText.charAt(i) == 'í') ||
                    (inputText.charAt(i) == 'o') ||
                    (inputText.charAt(i) == 'ó') ||
                    (inputText.charAt(i) == 'u') ||
                    (inputText.charAt(i) == 'ú')) {

                cadena = cadena + inputText.charAt(i);
                cadena = cadena.toUpperCase();
                vocal = true;
            }
            i++;
            cadena2 = cadena2.toUpperCase();
            cadena3 = cadena3.toUpperCase();
            RFC.setText(cadena + cadena2 + cadena3 + cadena4 + cadena5 + cadena6 + homo);
        }
    }//oncreate

    public void updateLabels() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.ENGLISH);
        Date.setText(sdf.format(mycalendar.getTime()));
    }


}

