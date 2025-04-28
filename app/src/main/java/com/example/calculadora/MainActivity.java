package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double pn;
    String operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Encontrar variables*/

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button borrarop = findViewById(R.id.erase);
        Button borrar = findViewById(R.id.eraseop);
        Button cambiarop = findViewById(R.id.swop);

        /*OPERADORES*/

        Button division = findViewById(R.id.div);
        Button suma = findViewById(R.id.sum);
        Button resta = findViewById(R.id.rest);
        Button mult = findViewById(R.id.mult);

        Button resultado = findViewById(R.id.resp);
        Button punto = findViewById(R.id.punto);

        TextView pantalla = findViewById(R.id.screen);

        borrarop.setOnClickListener(view -> {
            pn = 0;
            pantalla.setText("0");
        });

        pantalla.setVisibility(View.VISIBLE);
        pantalla.setText("0");

        ArrayList<Button> numeros = new ArrayList<>();
        numeros.add(num0);
        numeros.add(num1);
        numeros.add(num2);
        numeros.add(num3);
        numeros.add(num4);
        numeros.add(num5);
        numeros.add(num6);
        numeros.add(num7);
        numeros.add(num8);
        numeros.add(num9);

        ArrayList<Button> op = new ArrayList<>();
        op.add(division);
        op.add(mult);
        op.add(suma);
        op.add(resta);

        for (Button b : numeros){
            b.setOnClickListener(view -> {
                if (!pantalla.getText().toString().equals("0")){
                    pantalla.setText(pantalla.getText().toString() + b.getText().toString());
                } else {
                    pantalla.setText(b.getText().toString());
                }
            });
        }



        for (Button b : op) {
           b.setOnClickListener(view -> {
               pn = Double.parseDouble(pantalla.getText().toString());
               operacion = b.getText().toString();
               pantalla.setText("0");
           });
        }

        /*Boton de borrar uno por uno*/

        borrar.setOnClickListener(view -> {
            String num = pantalla.getText().toString();
            if (num.length()>1) {
                pantalla.setText(num.substring(0,num.length()-1));
            } else if (num.length() == 1 && !num.equals("0")) {
                pantalla.setText("0");
            }
        });

        /*Cambio de signo*/

        cambiarop.setOnClickListener(view -> {
            String result = pantalla.getText().toString();

            try {
                double numero = Double.parseDouble(result);
                double resulta = -numero;

                pantalla.setText(String.valueOf(resulta));
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            }
        });

        /*Punto de decimal*/

        punto.setOnClickListener(view -> {
            if (!pantalla.getText().toString().equals(".")) {
                pantalla.setText(pantalla.getText().toString() + ".");
            }
        });

        /*Boton de resultado*/

        resultado.setOnClickListener(view -> {
                double sn = Double.parseDouble(pantalla.getText().toString());
                double result;
                switch (operacion) {
                    case "/":
                        result = pn / sn;
                        break;
                    case "X":
                        result = pn * sn;
                        break;
                    case "+":
                        result = pn + sn;
                        break;
                    case "-":
                        result = pn - sn;
                        break;
                    default:
                        result = pn + sn;
                }
                        pantalla.setText(String.valueOf(result));
                        pn = result;
        });



    }
}