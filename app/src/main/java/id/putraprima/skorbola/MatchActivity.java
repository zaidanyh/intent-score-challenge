package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText, awayText, scoreHome, scoreAway;
    private int scoreH, scoreA;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;
    private String teamHome, teamAway, Result;
    private ArrayList<String> ScorerH = new ArrayList<>();
    private ArrayList<String> ScorerA = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        teamHome = homeText.getText().toString();
        teamAway = awayText.getText().toString();

        Bundle bundle = getIntent().getExtras();
        Challenge challenge = bundle.getParcelable("CHALLENGE_KEY");

        if (!bundle.isEmpty()) {
            teamHome = challenge.getHometext();
            teamAway = challenge.getAwaytext();
            homeText.setText(challenge.getHometext());
            awayText.setText(challenge.getAwaytext());
            homeUri = challenge.getHomeUri();
            awayUri = challenge.getAwayUri();
            scoreHome.setText("0");
            scoreAway.setText("0");
            scoreH = Integer.parseInt(scoreHome.getText().toString());
            scoreA = Integer.parseInt(scoreAway.getText().toString());

            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);
        }
    }

    public void handleHomeScore(View view) {
        Intent in = new Intent(this, ScorerActivity.class);
        startActivityForResult(in, 1);
    }

    public void handleAwayScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String Message = data.getStringExtra("SCORER");
            ScorerH.add(Message);
            scoreH += 1;
            scoreHome.setText(Integer.toString(scoreH));

        } else if (requestCode == 2) {
            String Message = data.getStringExtra("SCORER");
            ScorerA.add(Message);
            scoreA += 1;
            scoreAway.setText(Integer.toString(scoreA));
        }
    }
    public void handleResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        if (scoreH > scoreA) {
            Result = teamHome+ " WIN\n";
            intent.putExtra("RESULT_KEY", Result);
            intent.putExtra("PEMAIN_KEY", ScorerH);
        } else if (scoreA > scoreH) {
            Result = teamAway+" WIN\n";
            intent.putExtra("RESULT_KEY", Result);
            intent.putExtra("PEMAIN_KEY", Result);
        } else {
            Result ="DRAW";
            intent.putExtra("RESULT_KEY", Result);
        }
        startActivity(intent);
    }
}
