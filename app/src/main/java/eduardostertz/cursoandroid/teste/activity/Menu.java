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
        setContentView(R.layout.tela_menu);

        Button btn_cad_user;
        btn_cad_user = (Button) findViewById(R.id.btn_cad_user);
        btn_cad_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Usuario.class);
                startActivity(i);
            }
        });

        Button btn_cad_estabelecimentos;
        btn_cad_estabelecimentos = (Button) findViewById(R.id.btn_cad_estabelecimentos);
        btn_cad_estabelecimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Propriedade.class);
                startActivity(i);
            }
        });


        Button btn_cad_enderecos;
        btn_cad_enderecos = (Button) findViewById(R.id.btn_cad_enderecos);
        btn_cad_enderecos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Endereco.class);
                startActivity(i);
            }
        });

        Button btn_cad_unidade;
        btn_cad_unidade = (Button) findViewById(R.id.btn_cad_unidade);
        btn_cad_unidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Unidade.class);
                startActivity(i);
            }
        });

        Button btn_cad_hospedagem;
        btn_cad_hospedagem = (Button) findViewById(R.id.btn_cad_hospedagem);
        btn_cad_hospedagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Hospedagem.class);
                startActivity(i);
            }
        });

        Button btn_cad_Gerar_Relatório;
        btn_cad_Gerar_Relatório = (Button) findViewById(R.id.btn_cad_Gerar_Relatório);
        btn_cad_Gerar_Relatório.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Relatorio.class);
                startActivity(i);
            }
        });
    }
}