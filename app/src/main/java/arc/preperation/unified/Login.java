package arc.preperation.unified;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class Login extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    FirebaseAuth firebaseAuth;
    TextToSpeech ts;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button4);
        e1=(EditText) findViewById(R.id.editText);
        e2=(EditText) findViewById(R.id.editText1);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //e3=(EditText) findViewById(R.id.editText2);
        firebaseAuth=FirebaseAuth.getInstance();
        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(1.2f);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.isEmpty()){
                    e1.setError("Fill Email Please");
                    return;
                }
                else{
                    if(s2.isEmpty()){
                        e2.setError("Fill Password");
                        return;
                    }
                    else{
                        firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this,"Sign IN done",Toast.LENGTH_SHORT).show();
                                    ts.speak("Sign In Done",TextToSpeech.QUEUE_FLUSH,null);
                                    Intent i=new Intent(Login.this,Home.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this,"Wrong credentials",Toast.LENGTH_SHORT).show();
                                    ts.speak("Wrong Credentials",TextToSpeech.QUEUE_FLUSH,null);
                                }
                            }
                        });
                    }
                }
            }
        });


    }
}