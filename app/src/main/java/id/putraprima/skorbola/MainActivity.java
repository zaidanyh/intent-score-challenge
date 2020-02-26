package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private ImageView home, away;
    private EditText homeText, awayText;
    private Uri homeUri, awayUri;
    private Bitmap homeBitmap, awayBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = findViewById(R.id.home_logo);
        away = findViewById(R.id.away_logo);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    homeUri = data.getData();
                    homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    home.setImageBitmap(homeBitmap);
                } catch(IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == 2) {
            if (data != null) {
                try {
                    awayUri = data.getData();
                    awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    away.setImageBitmap(awayBitmap);
                } catch(IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public void handleImageHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleImageAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    public void handleNextButton(View view) {
        String HOME = homeText.getText().toString();
        String AWAY = awayText.getText().toString();

        Challenge challenge = new Challenge(HOME, AWAY, homeUri, awayUri);
        Intent intent = new Intent(this, MatchActivity.class);
        if (!HOME.isEmpty() && !AWAY.isEmpty()) {
            if (homeBitmap != null && awayBitmap != null) {
                intent.putExtra("CHALLENGE_KEY", challenge);
                startActivity(intent);
            } else if (homeBitmap == null) {
                Toast.makeText(this, "Please, Choose Image of Team Home!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please, Choose Image of Team Away!", Toast.LENGTH_SHORT).show();
            }
        } else if (HOME.isEmpty()){
            Toast.makeText(this, "Team Home Name is Empty!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Team Away Name is Empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
