package id.vigyan.rumahjurnal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import id.vigyan.rumahjurnal.Auth.Auth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long status_login = Auth.getInstance().getPreferenceCurentUser(SplashScreen.this);
                if (status_login != 0){
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 3000L);
    }
}