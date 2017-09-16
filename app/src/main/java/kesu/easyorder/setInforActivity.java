package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by thinhle on 9/16/17.
 */

public class setInforActivity extends AppCompatActivity {
    private Button btnTable;
    private EditText edtName;
    private EditText edttable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_infor);

        btnTable = (Button) findViewById(R.id.btn_submit_infor);
        edtName = (EditText) findViewById(R.id.edt_set_name);
        edttable = (EditText) findViewById(R.id.edt_set_table);

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setInforActivity.this, selectionActivity.class);
                if (edttable.getText().toString().isEmpty() || edtName.getText().toString().isEmpty())
                {
                    Toast.makeText(setInforActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
    }
}
