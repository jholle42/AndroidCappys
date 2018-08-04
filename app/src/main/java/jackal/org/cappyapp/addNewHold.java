package jackal.org.cappyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addNewHold extends AppCompatActivity {

    EditText item, amount, number;
    Button create;
    TextView rules;


    String i,a,n;
    final String RULES = "Put in SOME GENERIC HOLD RULES FOR USERS\n\t1) No BLANTONS EVER\n\n\t2) No more than 2 on rare beers per person\n\n\n\nYellow tag = awaiting fulfillment\nRed tag = denied hold\nGreen tag = Approved and waiting pickup";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_new_hold);

        item = (EditText) findViewById(R.id.itemName);
        amount = (EditText) findViewById(R.id.itemQuantity);
        number = (EditText) findViewById(R.id.phoneNumber);
        rules = (TextView) findViewById(R.id.rules);
        rules.setText(RULES);

        create = (Button) findViewById(R.id.createButton);


        item.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                i = item.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }



        });

        amount.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                a = amount.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }



        });

        number.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                n = number.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }



        });

        Button.OnClickListener mAddListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                save();
            }
        };

        create.setOnClickListener(mAddListener);



    }


    public void save(){
        Intent returnIntent = new Intent();
        if(item.getText().toString().isEmpty()){
            Toast.makeText(this,"Item Cannot Be Blank!",Toast.LENGTH_SHORT).show();
        }else if(amount.getText().toString().isEmpty()){
            Toast.makeText(this,"Amount Cannot Be Blank!",Toast.LENGTH_SHORT).show();
        }else if(number.getText().toString().isEmpty()){
            Toast.makeText(this,"Phone Number Required!",Toast.LENGTH_SHORT).show();
        }else {
            returnIntent.putExtra("i", i);
            returnIntent.putExtra("a", a);
            returnIntent.putExtra("n", n);
            //Toast.makeText(this,i.toString()+a.toString()+n.toString(),Toast.LENGTH_SHORT).show();
            setResult(102, returnIntent);
            finish();
        }
    }


}
