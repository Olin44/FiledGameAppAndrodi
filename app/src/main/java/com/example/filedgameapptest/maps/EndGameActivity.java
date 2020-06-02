package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.users.UserUtils;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.data.UserDataRepository;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa odpowiadająca za obsługę funkcjonalności w widoku kończenia gry.
 */
public class EndGameActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnStartNewMap;
    /**
     * Przycisk służący do przejścia do widoku konta użytkownika.
     */
    private Button btnAccount;
    /**
     * Przycisk odpowiedzialny za wylogowywanie.
     */
    private Button btnLogOut;
    /**
     * Pole tekstowe do wyświetlania wiadomości o wynikach po zakończeniu gry.
     */
    private TextView txtEndMessage;
    private TextView txtResult;
    /**
     * Pole odpowiedzialne za przechowywanie czasu, który pozostał do zakończenia gry.
     */
    private Long mTimeLeftInMillis;
    /**
     * Repozytorium przechowujące dane na temat użytkownika, który aktualnie uczestniczy w grze.
     */
    private UserDataRepository userDataRepository = UserDataRepository.getInstance();
    /**
     * Repozytorium przechowujące dane na temat aktualnie prowadzonej rozgrywki.
     */
    private GameDataRepository gameDataRepository = GameDataRepository.getInstance();


    /**
     * Metoda wywoływana przy tworzeniu widoku.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTimeLeftInMillis = calculatePoints();
        gameDataRepository.setPoints(mTimeLeftInMillis);
        setContentView(R.layout.activity_end_game);
        initViews();
        setTextViews();

        UserUtils.endGame(gameDataRepository);
    }
    /**
     * Metoda obliczająca ilość punktów, które użytkownik otrzyma za zakończoną rozgrywkę.
     */
    private Long calculatePoints(){
        //zaokrąglenie, bo timer po zakończeniu wyrzucał czasami, ze zostało 800milisekund
        Intent intent = getIntent();
        Long timeLeftInMillis = intent.getLongExtra("timeLeft", 0);
        if(timeLeftInMillis < 1000){
            timeLeftInMillis = 0L;
        }
        if(! intent.getBooleanExtra("isGameFinished", false)){
            timeLeftInMillis = 0L;
        }
        return timeLeftInMillis;
    }
    /**
     * Metoda odpowiedzialna za wyświetlenie baneru odpowiedzialnego za poinformowanie użytkownika o zakończeniu gry i ilości punktów, które otrzymał.
     */
    private void setTextViews() {
        Intent intent = getIntent();
        if(intent.getBooleanExtra("isGameFinished", false)){
            textWinGame();
        }
        else {
            textFailedGame();
        }
    }

    private void textFailedGame(){
        String finishStringWithGameLength = "You lost the game!";
        txtResult.setText(finishStringWithGameLength);
        txtEndMessage.setText(String.format("Points: %s", mTimeLeftInMillis));
    }

    private void textWinGame(){
        SimpleDateFormat gameLength = new SimpleDateFormat("mm:ss", java.util.Locale.getDefault());
        String finishStringWithGameLength =
                        "Congrats!" + System.getProperty("line.separator")
                        + "You finished the game!" + System.getProperty("line.separator")
                        + "Game length " +
                gameLength.format(new Date(mTimeLeftInMillis));
        txtResult.setText(finishStringWithGameLength);
        txtEndMessage.setText(String.format("Points: %s", mTimeLeftInMillis));
    }
    /**
     * Metoda odpowiedzialna za zainicjowanie przycisków i innych kontrolek na widoku.
     */
    private void initViews() {
        btnStartNewMap = findViewById(R.id.btnStartNewMap);
        btnAccount = findViewById(R.id.btnAccount);
        btnLogOut = findViewById(R.id.btnLogOut);

        txtEndMessage = findViewById(R.id.txtEndMessage);
        txtResult = findViewById(R.id.txtResult);

        btnStartNewMap.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }
    /**
     * Metoda definiująca aktywności jakie zostaną wykonane po naciśnięciu poszczególnych przycisków.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogOut:
                UserUtils.logoutUser(userDataRepository);
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnAccount:
                startActivity(new Intent(this, UserAccountActivity.class));
                break;
            case R.id.btnStartNewMap:
                startActivity(new Intent(this, ScannedBarcodeActivity.class));
                break;
        }
    }
}
