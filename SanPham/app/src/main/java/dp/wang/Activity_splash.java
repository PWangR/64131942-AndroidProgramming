package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_splash extends AppCompatActivity {

    ImageView imglogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imglogo = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.my_animation);
        imglogo.setAnimation(animation);
        new Thread()
        {
            @Override
            public void run()
            {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(Activity_splash.this, MainActivity.class);
                startActivity(intent);
                Activity_splash.this.finish();
            }
        }.start();
    }
}