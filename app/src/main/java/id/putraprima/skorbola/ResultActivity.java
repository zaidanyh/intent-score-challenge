package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private String message;
    private ArrayList<String> ListPemain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            message = bundle.getString("RESULT_KEY");
            if (message.equals("DRAW")) {
                result.setText(message);
            } else {
                ListPemain = getIntent().getStringArrayListExtra("PEMAIN_KEY");
                String result = message+"\n";
                for (int i = 0; i< ListPemain.size(); i++) {
                    result+=ListPemain.get(i)+"\n";
                }
                this.result.setText(result);
            }
        }
    }
}
