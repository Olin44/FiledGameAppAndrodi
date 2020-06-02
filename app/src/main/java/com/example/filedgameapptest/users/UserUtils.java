package com.example.filedgameapptest.users;

import com.example.filedgameapptest.apiconnections.RetrofitClientInstance;
import com.example.filedgameapptest.apiconnections.UserService;
import com.example.filedgameapptest.apiconnections.models.GameService;
import com.example.filedgameapptest.apiconnections.models.GameUserDTO;
import com.example.filedgameapptest.maps.data.GameDataRepository;
import com.example.filedgameapptest.users.data.LogoutUserDTO;
import com.example.filedgameapptest.users.data.UserDataRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/**
 * Klasa z przydatnymi metodami, używanami do zarządzania użytkownikiem.
 */
public class UserUtils {
    /**
     * Metoda odpowiedzialna za wylogowanie użytkownika.
     */
    public static void logoutUser(UserDataRepository userDataRepository) {
        LogoutUserDTO logoutUserDTO = new LogoutUserDTO(userDataRepository.getEmail());
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
    public static void endGame(GameDataRepository gameDataRepository){
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
