package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btn_login);
        editText = (EditText)findViewById(R.id.edt_code);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetInforActivity.class);
                if (editText.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Code", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(editText.getText().toString()) > 0) // Sửa lại điều kiện
                {
                    startActivity(intent);
                }
            }
        });
    }

}
