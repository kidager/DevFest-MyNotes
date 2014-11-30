package tn.devfest.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity {

  // Splash screen timer
  private static int SPLASH_TIME_OUT = 1500;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_splash);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
      }
    }, SPLASH_TIME_OUT);
  }

}