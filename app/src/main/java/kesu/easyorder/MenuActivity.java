package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

            Button btn1 = (Button)findViewById(R.id.add_1);
            Button btn2 = (Button)findViewById(R.id.sub_1);
            final EditText edt1 = (EditText) findViewById(R.id.sl_1);
            edt1.setText("0");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int a = Integer.parseInt(edt1.getText().toString());
                    a++;
                    edt1.setText(Integer.toString(a));
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int a = Integer.parseInt(edt1.getText().toString());
                    if (a == 0) return;
                    a--;
                    edt1.setText(Integer.toString(a));
                }
            });


    }
}

