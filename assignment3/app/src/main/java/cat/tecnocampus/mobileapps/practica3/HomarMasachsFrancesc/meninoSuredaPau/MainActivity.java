package cat.tecnocampus.mobileapps.practica3.HomarMasachsFrancesc.meninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView ivIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivIMG = findViewById(R.id.ivImg);
        Glide.with(this).load("https://imgur.com/aUBispH").into(ivIMG);
    }
}