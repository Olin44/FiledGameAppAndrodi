package com.example.filedgameapptest.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filedgameapptest.MainActivity;
import com.example.filedgameapptest.R;
import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.UserService;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.users.account.UserAccountActivity;
import com.example.filedgameapptest.users.data.LogoutUserDTO;
import com.example.filedgameapptest.users.data.UserDataRepository;
import com.example.filedgameapptest.users.login.LoginActivity;
import com.example.filedgameapptest.users.register.RegisterActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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

        endGame();
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
        return timeLeftInMillis;
    }
    /**
     * Metoda odpowiedzialna za wyświetlenie baneru odpowiedzialnego za poinformowanie użytkownika o zakończeniu gry i ilości punktów, które otrzymał.
     */
    private void setTextViews() {
        SimpleDateFormat gameLength = new SimpleDateFormat("mm:ss", java.util.Locale.getDefault());
        String finishStringWithGameLength = "Congrats! You finished the game! Game length " +
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
                LogOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnAccount:
                startActivity(new Intent(this, UserAccountActivity.class));
                break;
            case R.id.btnStartNewMap:
                startActivity(new Intent(this, StartMapActivity.class));
                break;
        }
    }
    /**
     * Metoda odpowiedzialna za wylogowanie użytkownika.
     */
    private void LogOut() {
        LogoutUserDTO logoutUserDTO  = new LogoutUserDTO(userDataRepository.getEmail());
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        UserService userService = retrofit.create(UserService.class);
        Call<LogoutUserDTO> call = userService.logout(logoutUserDTO);
        call.enqueue(new Callback<LogoutUserDTO>() {
            @Override
            public void onResponse(Call<LogoutUserDTO> call, Response<LogoutUserDTO> response) {
                if (response.isSuccessful()) {
                    userDataRepository.deleteAllData();
                    //TODO: Monika implement activity on success
                } else {
                    //TODO: Monika implement activity on failure

                }
            }
            @Override
            public void onFailure(Call<LogoutUserDTO> call, Throwable t) {
                //TODO: Monika implement activity on failure
            }
        });

    }
    /**
     * Metoda odpowiedzialna za wysłanie danych o zakończonej rozgrywce na serwer.
     */
    private void endGame(){
        GameUserDTO gameUserDTO = new GameUserDTO();
        gameUserDTO.setActive(gameDataRepository.getActive());
        gameUserDTO.setUserId(gameDataRepository.getUserId());
        gameUserDTO.setId(gameDataRepository.getId());
        gameUserDTO.setMapId(gameDataRepository.getMapId());
        gameUserDTO.setPoints(gameDataRepository.getPoints());
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        GameService gameService = retrofit.create(GameService.class);
        Call<GameUserDTO> call = gameService.saveResults(gameUserDTO);
        call.enqueue(new Callback<GameUserDTO>() {
            @Override
            public void onResponse(Call<GameUserDTO> call, Response<GameUserDTO> response) {
                if(response.isSuccessful()) {
                    System.out.println(response.body().toString());
                }else{
                    //TODO: Monika implement activity on failure

                }
            }
            @Override
            public void onFailure(Call<GameUserDTO> call, Throwable t) {
                //TODO: Monika implement activity on failure
            }
        });

    }
}
