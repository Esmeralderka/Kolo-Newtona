package com.example.robotyka;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Random;

//********************************************************************************
//
// Data: 09.12.2020
// Autor:Weronika Miksa
//
// Wydział Fizyki i Informatyki Stosowanej
// Specjalizacja A - Systemy i aplikacje mobilne
// Przedmiot: Sterowanie komputerowe i robotyka
//
// Opis projektu: Symulacja doświadczenia z krążkiem Newtona
// które polega na składaniu kilku barw prostych w barwę złożoną
//
// Opis klasy: Implementuje ekran startowy aplikacji zawierający logo, dwa
// przyciski przenoszące do odpowiednich klas oraz informacje o wersji i autorze
//
//********************************************************************************

public class MainActivity extends AppCompatActivity
{
    //deklaracja zmiennych, które będą odwoływać się do widgetów użytych w layoutcie
    Button przycisk1;
    Button przycisk2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //przypisanie do klasy widoku (activity_main.xml)
        setContentView(R.layout.activity_main);

        //inicjalizacja zminnych (odwołanie się do widgetów z activity_main.xml)
        przycisk1 = (Button) findViewById(R.id.button_start);//przycisk startu
        przycisk2 = (Button) findViewById(R.id.button_opis);//przycisk opisu

        //przypisanie przyciskom funkcji jakie mają się wykonać po naciśnięciu
        //przycisk1 ("start") ma przenosić do ekranu doświadczenia z kołem
        przycisk1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //tworzony jest nowy obiekt typu intent, którego zamiarem jest
                //wywołanie klasy Kolo.java i jej widoku
                Intent kolo = new Intent(v.getContext(), Kolo.class);
                startActivity(kolo);
            }
        });
        //przycisk2 ("opis") ma przenosić do ekranu opisu doświadczenia
        przycisk2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ponownie tworzony jest nastepny nowy obiekt typu intent, aby
                //wywołać z kolei klase Opis.java i jej widok
                Intent opis = new Intent(v.getContext(), Opis.class);
                startActivity(opis);
            }
        });
    }
}
