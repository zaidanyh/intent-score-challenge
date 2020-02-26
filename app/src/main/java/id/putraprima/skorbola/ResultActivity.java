package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            message = bundle.getString("RESULT_KEY");
            result.setText(message);
        }
    }
}
