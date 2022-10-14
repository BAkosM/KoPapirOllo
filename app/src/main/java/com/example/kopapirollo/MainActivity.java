package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView tekpo;
    private ImageView gepkpo;
    private TextView eredmeny;
    private int tegydb;
    private int gepgydb;
    private Button btnko;
    private Button btnpapir;
    private Button btnollo;
    private Random rnd;
    private int tipp;
    private int geptipp;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListeners();
    }
    private void init(){
        btnko = findViewById(R.id.ko);
        btnpapir = findViewById(R.id.papir);
        btnollo = findViewById(R.id.ollo);
        eredmeny = findViewById(R.id.eredmeny);
        tekpo = findViewById(R.id.tekpo);
        gepkpo = findViewById(R.id.gepkpo);
        tegydb = 0;
        gepgydb = 0;
        rnd = new Random();
        builder = new AlertDialog.Builder(MainActivity.this);
    }
    private void addListeners(){
        btnko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 0;
                tekpo.setImageResource(R.drawable.rock);
                gepvalasz();
                kiert();
            }
        });
        btnpapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 1;
                tekpo.setImageResource(R.drawable.paper);
                gepvalasz();
                kiert();
            }
        });
        btnollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp = 2;
                tekpo.setImageResource(R.drawable.scissors);
                gepvalasz();
                kiert();
            }
        });
    }
    private void gepvalasz(){
        geptipp = rnd.nextInt(3);
        if (geptipp == 0){
            gepkpo.setImageResource(R.drawable.rock);
        } else if (geptipp == 1){
            gepkpo.setImageResource(R.drawable.paper);
        } else {
            gepkpo.setImageResource(R.drawable.scissors);
        }
    }
    private void kiert(){
        if (geptipp == tipp){
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        } else {
          if (tipp == 0){
              if (geptipp == 1){
                  gepgydb++;
                  Toast.makeText(this, "Gép nyert", Toast.LENGTH_SHORT).show();
              } else {
                  tegydb++;
                  Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
              }
          } else if (tipp == 1){
              if (geptipp == 2){
                  gepgydb++;
                  Toast.makeText(this, "Gép nyert", Toast.LENGTH_SHORT).show();
              } else {
                  tegydb++;
                  Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
              }
          } else {
              if (geptipp == 0){
                  gepgydb++;
                  Toast.makeText(this, "Gép nyert", Toast.LENGTH_SHORT).show();
              } else {
                  tegydb++;
                  Toast.makeText(this, "Te nyertél", Toast.LENGTH_SHORT).show();
              }
          }
        }
        eredmeny.setText("Eredmény: Ember:"+tegydb+" Computer:"+gepgydb);
        nyeresvan();
    }
    private void nyeresvan(){
        if (gepgydb >=3){
            builder.setMessage("Vereség!");
            builder.setNegativeButton("Kilépés", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("Újjáték", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newgame();
                }
            });
            builder.create().show();
        } else if (tegydb >=3){
            builder.setMessage("Győzelem!");
            builder.setNegativeButton("Kilépés", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("Újjáték", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newgame();
                }
            });
            builder.create().show();
        }
    }
    private void newgame(){
        tegydb = 0;
        gepgydb = 0;
        eredmeny.setText("Eredmény: Ember:0 Computer:0");
    }
}