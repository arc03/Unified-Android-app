package arc.preperation.unified;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calc extends AppCompatActivity {

    private EditText display;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        display=(EditText) findViewById(R.id.editText);
        display.setShowSoftInputOnFocus(false);
        b= (Button) findViewById(R.id.button3);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Calc.this,Home.class);
                startActivity(i);
                finish();

            }
        });
    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            display.setSelection(cursorPos+1);
        }
    }

    public void clear(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
    public void bracket(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar += 1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar += 1;
            }
        }

        if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");

        }
        else if(openPar > closedPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");

        }
        display.setSelection(cursorPos+1);
    }
    public void exp(View view){
        updateText("^");

    }
    public void div(View view){

        updateText("/");
    }
    public void seven(View view){

        updateText("7");
    }
    public void eight(View view){

        updateText("8");
    }
    public void nine(View view){

        updateText("9");
    }
    public void multy(View view){

        updateText("*");
    }
    public void four(View view){

        updateText("4");
    }
    public void five(View view){

        updateText("5");
    }
    public void six(View view){

        updateText("6");
    }
    public void plus(View view){

        updateText("+");
    }
    public void one(View view){

        updateText("1");
    }
    public void two(View view){

        updateText("2");
    }
    public void three(View view){

        updateText("3");
    }
    public void minus(View view){

        updateText("-");
    }
    public void allclear(View view){
        display.setText("");
    }
    public void zero(View view){

        updateText("0");
    }
    public void dot(View view){

        updateText(".");
    }
    public void equal(View view){
        String userExp = display.getText().toString();
        Expression exp = new Expression(userExp);

        String result= String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
}