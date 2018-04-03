package example.uca.labo3;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText messageEdit;
    EditText nameEdit;
    EditText emailEdit;
    Button sendButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEdit = findViewById(R.id.messageEditText);
        nameEdit = findViewById(R.id.nameEditText);
        emailEdit = findViewById(R.id.emailEditText);
        sendButton = findViewById(R.id.sendButton);
        addButton = findViewById(R.id.addButton);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, messageEdit.getText().toString());

                if (sendIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(sendIntent);
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addContact(nameEdit.getText().toString(), emailEdit.getText().toString());
            }
        });
    }

    public void addContact(String name, String email){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
