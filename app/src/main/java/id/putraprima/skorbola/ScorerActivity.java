package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ScorerActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        editText = findViewById(R.id.editText);
    }

    public void handleButton(View view) {
        String Scorer = editText.getText().toString();
        Intent data = new Intent();
        data.putExtra("SCORER", Scorer);
        setResult(RESULT_OK, data);
        finish();
    }
}
