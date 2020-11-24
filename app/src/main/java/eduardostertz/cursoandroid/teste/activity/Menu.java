package eduardostertz.cursoandroid.teste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import eduardostertz.cursoandroid.teste.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btn_cad_user;
        btn_cad_user = (Button) findViewById(R.id.btn_cad_user);
        btn_cad_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), User.class);
                startActivity(i);
            }
        });

        Button btn_cad_estabelecimentos;
        btn_cad_estabelecimentos = (Button) findViewById(R.id.btn_cad_estabelecimentos);
        btn_cad_estabelecimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}