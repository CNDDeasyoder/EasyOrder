package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by thinhle on 9/16/17.
 */

public class InformationActivity extends AppCompatActivity{

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        btnBack = (ImageButton) findViewById(R.id.btn_nav_ttin_thinh);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });
    }

}
