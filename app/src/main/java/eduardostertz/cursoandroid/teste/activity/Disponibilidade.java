package eduardostertz.cursoandroid.teste.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eduardostertz.cursoandroid.teste.R;

public class Disponibilidade extends AppCompatActivity {

    FirebaseFirestore db;

    CollectionReference hospedagemReference;

    TextView textViewResposta;

    EditText idPropriedade,idUnidade,dataEntrada,dataSaida;

    Button buttonCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_disponibilidade);

        db = FirebaseFirestore.getInstance();

        hospedagemReference = db.collection("hospedagem");

        idPropriedade = findViewById(R.id.idPropriedade);
        idUnidade = findViewById(R.id.idUnidade);
        dataEntrada = findViewById(R.id.dataEntrada);
        dataSaida = findViewById(R.id.dataSaida);

        buttonCriar = findViewById(R.id.buttonCriar);

        textViewResposta = findViewById(R.id.textViewResposta);
    }

    public void verificarButton(View view) {

        final String[] resposta = new String[1];
        final int[] achou = {0};
        hospedagemReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                   if(documentSnapshot.get("propiedade").toString().equals(idPropriedade.getText().toString()) &&
                           documentSnapshot.get("Unidade").toString().equals(idUnidade.getText().toString())){
                       achou[0] = 1;

                       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


                       Date dataInicio = null;

                       try {
                           dataInicio = format.parse(dataEntrada.getText().toString());
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                       Date dataFim = null;

                       try {
                           dataFim = format.parse(dataSaida.getText().toString());
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                       Date dataEntradaBanco = null;

                       try {
                           dataEntradaBanco = format.parse(documentSnapshot.get("dataEntrada").toString());
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                       Date dataSaidaBanco = null;

                       try {
                           dataSaidaBanco = format.parse(documentSnapshot.get("dataSaida").toString());
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }



                       //Date dataInicio = new Date(dataEntrada.getText().toString());
                       //Date dataFim = new Date((dataSaida.getText().toString()));
                       //Date dataEntradaBanco = new Date(documentSnapshot.get("dataEntrada").toString());
                       //Date dataSaidaBanco = new Date(documentSnapshot.get("dataSaida").toString());
                       if((dataInicio.after(dataEntradaBanco) && dataInicio.before(dataSaidaBanco)) ||
                               (dataFim.after(dataEntradaBanco) && dataFim.before(dataSaidaBanco))){
                           resposta[0] = "Esse período já está ocupado para a unidade dessa propriedade!";
                           buttonCriar.setVisibility(View.INVISIBLE);

                       }else{
                           resposta[0] = "Esse período está disponível para a unidade dessa propriedade!";
                           buttonCriar.setVisibility(View.VISIBLE);
                       }
                    }


                }
                if(achou[0] == 1) {
                    textViewResposta.setText(resposta[0]);
                }else{
                    resposta[0] = "Não foi possível encontrar a propriedade ou a unidade";
                    textViewResposta.setText(resposta[0]);
                    buttonCriar.setVisibility(View.INVISIBLE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "Falhou ao ler");
                Toast.makeText(getApplicationContext(), "Falha ao ler!.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void criar(View view) {
        Intent i = new Intent(getApplicationContext(), Hospedagem.class);
        startActivity(i);
    }

}