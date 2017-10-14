package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


/**
 * Created by thinhle on 9/16/17.
 */

public class SetInforActivity extends AppCompatActivity {
    private Button btnTable;
    private EditText edtName;
    private EditText edtTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_infor);

        btnTable = (Button) findViewById(R.id.btn_submit_infor);
        edtName = (EditText) findViewById(R.id.edt_set_name);
        edtTable = (EditText) findViewById(R.id.edt_set_table);

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetInforActivity.this, SelectionActivity.class);
                String regExName = "^([a-zA-Z]+\\s)+[a-zA-Z]+\\s*$";

                String regExTable = "^[1-9]|[1-9][0-9]$";

                Pattern pName = Pattern.compile(regExName);
                Pattern pTable = Pattern.compile(regExTable);
                if (edtTable.getText().toString().isEmpty() || edtName.getText().toString().isEmpty())
                {
                    Toast.makeText(SetInforActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if (pName.matches(regExName, edtName.getText().toString())
                        && pTable.matches(regExTable, edtTable.getText().toString()))
                {
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SetInforActivity.this, "Vui lòng nhập thông tin chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
