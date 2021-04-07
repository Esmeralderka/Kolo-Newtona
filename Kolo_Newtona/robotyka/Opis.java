package com.example.robotyka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//****************************************************************************
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
// Opis klasy: Implementuje ekran zawierający opis doświadczenia wraz z
// rysunkiem poglądowym oraz przyciskiem przenoszącym do ekranu doświadczenia
//
//****************************************************************************

public class Opis extends AppCompatActivity
{
    //deklaracja zmiennych, które będą odwoływać się do widgetów użytych w layoutcie
    Button b_start, b_back;
    TextView naglowek, opis, opis2, opis3, opis4, opis5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //przypisanie do klasy widoku (opis.xml)
        setContentView(R.layout.opis);

        //tak naprawdę można by cały tekst zawrzeć w dwóch TextView, jednak byłoby to
        //mało czytelne, więc zdecydowałam się na podział na oddzielne i wydzielone
        //tematycznie bloki tekstu z wytłuszczoną informacją pełniącą role tytułu
        naglowek=(TextView) findViewById(R.id.naglowek);
        opis=(TextView) findViewById(R.id.opis_text);
        opis2=(TextView) findViewById(R.id.opis_text2);
        opis3=(TextView) findViewById(R.id.opis_text3);
        opis4=(TextView) findViewById(R.id.opis_text4);
        opis5=(TextView) findViewById(R.id.opis_text5);
        b_back = (Button) findViewById(R.id.button_wstecz2);
        b_start = (Button) findViewById(R.id.button_start2);

        //nagłówek powinien być dobrze widoczny, więc został powiększony
        naglowek.setTextSize(36);

        //Dodaje tekst do poszczególnych TextView i
        //dla łatwiejszego zarzadzania tekstem używam formatowania z html
        naglowek.setText("Jak działa \nKoło Newtona?");

        opis.setText(Html.fromHtml("<b>" + "Krążek Newtona to koło, na którym znajdują się barwne segmenty.<br>" + "</b>"+
                " Barwy, które występują na krążku obejmują podstawowe barwy widma światła widzialnego, czyli tak zwane barwy proste:" +
                "  czerwony,  pomarańczowy, żółty, zielony, niebieski, indygo i fioletowy. W chwili," +
                " gdy obracamy krążkiem Newtona, barwy zlewają się ze sobą i widzimy „barwę” białą. " +
                " Większość barw, jakie występują w przyrodzie, to barwy złożone, które powstają na przykład " +
                "w wyniku dodania do siebie przynajmniej dwóch barw prostych. Krążek Newtona  jest zatem przykładem addytywności barw.<br>" ));

        opis2.setText(Html.fromHtml("<b>"+"Większość barw złożonych, jakie występują w przyrodzie nie powstają w wyniku dodawania barw.<br>" + "</b>"+
                " Najczęściej w przyrodzie spotykamy się z barwami złożonymi, które powstają w wyniku pochłaniania światła widzialnego." +
                " Każdy z nas, w dzieciństwie na lekcjach plastyki, dowiedział się, że na przykład zmieszanie ze sobą " +
                "farby niebieskiej i żółtej daje kolor zielony, a zmieszanie farby czerwonej i żółtej daje kolor pomarańczowy. " +
                "Jest tak, gdyż farba niebieska pochłania światło żółte i czerwone, a farba żółta pochłania światło czerwone i niebieskie. " +
                "Zatem obie te farby, żółta i niebieska, odbijają światło zielone. " +
                "Barwa czerwona natomiast pochłania światło żółte i niebieskie. Zatem farby, żółta i czerwona, odbijają kolor pomarańczowy.<br>"));

        opis3.setText(Html.fromHtml("<b>"+"Kręcąc krążkiem Newtona nie zawsze obserwujący dostrzeże barwę białą. <br>" + "</b>"+
                "Głównym powodem takiego zjawiska jest fakt, że barwy występujące na krążku mają źle dobraną intensywność" +
                " i dobrane są w złych proporcjach, co daje w wyniku barwę różną od barwy białej. " +
                "Aby otrzymać barwę białą musielibyśmy korzystać z idealnego krążka Newtona. <br>"));

        opis4.setText(Html.fromHtml("<b>"+"Doświadczenia z krążkiem Newtona, które polegają na składaniu kilku barw prostych w barwę złożoną,"+
                "pokazują zasadę działania wielu urządzeń z jakimi spotykamy się w życiu codziennym. <br>"  + "</b>"+
                "Na podobnej zasadzie działają kolorowe telewizory, monitory komputerów, czy rzutniki telewizyjne. " +
                "Technika DLP (Digital Light Processing) w rzutnikach telewizyjnych tworzy kolorowy obraz w sposób analogiczny" +
                " jak powstaje barwa biała dla krążka Newtona. Wirująca tarcza z kolorowymi filtrami (czerwonym, zielonym i niebieskim)" +
                " przed źródłem światła powoduje, że na ekran rzutowane są bardzo szybko następujące po sobie obrazy w tych kolorach," +
                " co przez ludzkie oko odbierane jest jako jeden, kolorowy obraz."));

        //ostatni TextView ma za zadanie stworzyć odstep, aby przycisk "Sprawdźmy to!" nie zakrywał tekstu
        opis5.setTextSize(36);
        opis5.setText("   \n       \n    ");

        //przypisanie przyciskom funkcji jakie mają się wykonać po naciśnięciu
        //b_back (strzałka w lewym górym rogu) ma przenosić do ekranu głownego
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tworzony jest nowy obiekt typu intent, aby
                //wywołać klase z pliku MainActivity.java
                Intent kolo = new Intent(v.getContext(), MainActivity.class);
                startActivity(kolo);
            }
        });

        //b_start ("Sprawdźmy to!") ma przenosić do ekranu doświadczenia z kołem
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //działa tak jak powyżej - intent przenosi do Kola
                Intent kolo = new Intent(v.getContext(), Kolo.class);
                startActivity(kolo);
            }
        });
    }
}