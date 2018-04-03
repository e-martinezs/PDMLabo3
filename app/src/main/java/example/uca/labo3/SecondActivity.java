package example.uca.labo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = findViewById(R.id.messageTextView);
        button = findViewById(R.id.returnButton);

        Intent callingIntent = getIntent();
        String intentAction = callingIntent.getAction();
        String intentType = callingIntent.getType();

        if (Intent.ACTION_SEND.equals(intentAction) && intentType != null){
            if (intentType.equals("text/plain")){
                handleReceivedText(callingIntent);
            }
        }

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnIntent);
            }
        });
    }

    private void handleReceivedText(Intent intent){
        String intentText = intent.getStringExtra(Intent.EXTRA_TEXT);
        text.setText(intentText);
    }
}
