package com.example.robotyka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Random;

//*********************************************************************************************
//
// Data: 09.12.2020
// Autor:Weronika Miksa
//
// Wydział Fizyki i Informatyki Stosowanej
// Specjalizacja A - Systemy i aplikacje mobilne
// Przedmiot: Sterowanie komputerowe i robotyka
//
// Opis projektu: Symulacja doświadczenia z krążkiem Newtona
// które polega na składaniu kilku barw prostych w jedną złożoną
//
// Opis klasy: Implementuje ekran zawierający krążek Newtona, czyli obraz i cztery przyciski:
// jeden startujący animację i dwa do modyfikacji szybkości rotacji koła (wolniej/szybciej)
// oraz ostatni w lewym górnym rogu - przenoszący do ekranu startowego aplikacji, a
// także rozwijaną listę różnych obrazów kół możliwych do animowania w prawym górnym rogu
//
//********************************************************************************************

public class Kolo extends AppCompatActivity implements Animation.AnimationListener
{
    //deklaracja zmiennych, które będą odwoływać się do widgetów użytych w layoutcie
    long ingDegrees = 0;
    boolean spoczynek = true;
    Button b_start, b_more,b_less, b_back;
    ImageView  imageKolo;
    int c1=1,c2=2,c3=3,c4=4, c5=5;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //przypisanie do klasy widoku (kolo.xml)
        setContentView(R.layout.kolo);

        //inicjalizacja zminnych (odwołanie się do widgetów z layoutu)
        b_start = (Button) findViewById(R.id.button_startKola);
        b_more = (Button) findViewById(R.id.button_szybciej);
        b_less = (Button) findViewById(R.id.button_wolniej);
        b_back = (Button) findViewById(R.id.button_wstecz);
        imageKolo = (ImageView) findViewById(R.id.image_kolo);
        spinner = (Spinner) findViewById(R.id.spinner);

        //przypisanie przyciskom funkcji jakie mają się wykonać po naciśnięciu
        //b_start ("Start") rozpoczyna animację kręcenia kołem
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stworzyłam nowy watek aby koło chodziło płynniej
                //jednak nadal widoczny jest efekt "klatkowania"
                //tzn koło nie obraca się płynnie
                new Thread(new Runnable() {
                    public void run()
                    {
                        OnClickButtonRotation(v);
                    }
                }).start();
            }
        });

        //b_more ("+") ma za zadanie przyśpieszyć animacje
        b_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wywołanie metody przyśpieszającej rotacje
                OnClickButtonFaster();
            }
        });

        //b_less ("-") ma za zadanie zwolnić animacje
        b_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wywołanie metody zwalniającej rotacje
                OnClickButtonSlower();
            }
        });

        //b_back (strzałka w lewym górym rogu) ma przenosić do ekranu startowego
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent przenosi do MainActivity
                Intent kolo = new Intent(v.getContext(), MainActivity.class);
                startActivity(kolo);
            }
        });

        //tablica stringów, które wyswietlane będa po rozwinięciu widgeta typu spinner
        String [] values = {"     ","Koło 1","Koło 2","Koło 3","Koło 4","Koło 5",};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //ustawianie odpowiednich akcji jakie mają się wywołać po wybraniu elementu z listy
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(c1==position)
                {
                    //wybranie pliku
                    int resID = getResources().getIdentifier("kolo1", "drawable", getPackageName());
                    //zmiana obrazu
                    imageKolo.setImageResource(resID);
                }
                if(c2==position)
                {
                    //wybranie pliku
                    int resID = getResources().getIdentifier("kolo2", "drawable", getPackageName());
                    //zmiana obrazu
                    imageKolo.setImageResource(resID);
                }
                if(c3==position)
                {
                    //wybranie pliku
                    int resID = getResources().getIdentifier("kolo3", "drawable", getPackageName());
                    //zmiana obrazu
                    imageKolo.setImageResource(resID);
                }
                if(c4==position)
                {
                    //wybranie pliku
                    int resID = getResources().getIdentifier("kolo4", "drawable", getPackageName());
                    //zmiana obrazu
                    imageKolo.setImageResource(resID);
                }
                if(c5==position)
                {
                    //wybranie pliku
                    int resID = getResources().getIdentifier("kolo5", "drawable", getPackageName());
                    //zmiana obrazu
                    imageKolo.setImageResource(resID);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {
        //zmienna pomocnicza sprawdzająca czy koło jest w stanie spoczynku
        //tutaj jest w ruchu więc jest false
        this.spoczynek = false;
        //po rozpoczęciu animacji zmieniamy tekst przycisku z "START" na "STOP"
        b_start.setText("STOP");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //zmienna pomocnicza sprawdzająca czy koło jest w stanie spoczynku
        //animacja zakończyła się więc koło jest nieruchome (stąd true)
        this.spoczynek = true;
        //po zakończeniu animacji zmieniamy tekst przycisku z "STOP" na "START"
        b_start.setText("START");
        //wywołujemy tu przycisk aby po zakończeniu animacji mozna było ją powtórzyć po naciśnięciu przycisku "START"
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wywołanie funkcji rotującej
                OnClickButtonRotation(v);
            }
        });
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //funkcja obsługująca rotacje koła
    public void OnClickButtonRotation(View view) {
        if (this.spoczynek) {
            //długość trwania obrotu (droga)
            int ran = new Random().nextInt(360) + 7880;
            //link do dokumentacji klasy RotateAnimation, gdzie została obszernie opisana:
            //https://developer.android.com/reference/android/view/animation/RotateAnimation
            RotateAnimation rotateAnimation = new RotateAnimation((float) this.ingDegrees, (float)
                    (this.ingDegrees + ((long) ran)), 1, 0.5f, 1, 0.5f);
            this.ingDegrees = (this.ingDegrees + ((long) ran)) % 360;
            rotateAnimation.setDuration((long) ran); //czas w którym animacja będzie się wykonywała
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageKolo.setAnimation(rotateAnimation);
            imageKolo.startAnimation(rotateAnimation);

            //wywołujemy tu przycisk aby mozna było po naciśnięciu przycisku "STOP" faktycznie zastopować animację
            b_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //zmiana czasu trwania aplikacji na 0, aby ją zatrzymać
                    rotateAnimation.setDuration(0);
                }
            });
        }
    }
    //funkcja przyśpieszająca rotacje koła
    public void OnClickButtonFaster() {
        //prędkość koła jest zdefiniowana czasem w jakim koło musi przebyć drogę
        //toteż przyśpieszenie rotacji to nic innego jak zmniejszenie czasu

        //długość trwania obrotu (droga) wszędzie taka sama
        int ran = new Random().nextInt(360) + 7880;
        //link do dokumentacji klasy RotateAnimation, gdzie została obszernie opisana:
        //https://developer.android.com/reference/android/view/animation/RotateAnimation
        RotateAnimation rotateAnimation = new RotateAnimation((float) this.ingDegrees, (float)
                (this.ingDegrees + ((long) ran )), 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration((long) ran - 1000); //krótszy czas w którym animacja będzie się wykonywała
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(this);
        imageKolo.setAnimation(rotateAnimation);

        //wywołujemy tu przycisk aby mozna było po naciśnięciu przycisku "STOP" faktycznie zastopować animację
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zmiana czasu trwania aplikacji na 0, aby ją zatrzymać
                rotateAnimation.setDuration(0);
                //aby ponownie była możliwość zakręcenia kołem
                OnClickButtonRotation(v);
            }
        });
    }

    //funkcja zwalniająca rotacje koła
    public void OnClickButtonSlower() {
        //prędkość koła jest zdefiniowana czasem w jakim koło musi przebyć drogę
        //toteż zwolnienie rotacji to nic innego niż zwiększenie czasu

        //długość trwania obrotu (droga) wszędzie taka sama
        int ran = new Random().nextInt(360) + 7880;
        //link do dokumentacji klasy RotateAnimation, gdzie została obszernie opisana:
        //https://developer.android.com/reference/android/view/animation/RotateAnimation
        RotateAnimation rotateAnimation = new RotateAnimation((float) this.ingDegrees, (float)
                (this.ingDegrees + ((long) ran )), 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration((long) ran + 10000);//dłuższy czas w którym animacja będzie się wykonywała
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(this);
        imageKolo.setAnimation(rotateAnimation);

        //by mozna było po naciśnięciu przycisku "STOP" faktycznie zastopować animację
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zmiana czasu trwania aplikacji na 0, aby ją zatrzymać
                rotateAnimation.setDuration(0);
                //by ponownie była możliwość zakręcenia kołem
                OnClickButtonRotation(v);
            }
        });
    }
}
