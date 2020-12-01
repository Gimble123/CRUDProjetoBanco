package eduardostertz.cursoandroid.teste.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Hospedagem extends AppCompatActivity {


    EditText editTextIDPropriedadeHosp, editTextIDunidadeHosp, editTextDataEntrada, editTextDataSaida, editTextIDUsuarioHosp, editTextIDHospedagem, editTextValorHosp;

    TextView textView;

    FirebaseFirestore db;

    long days;

    String dayDifference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_hospedagem);

        editTextIDPropriedadeHosp = findViewById(R.id.editTextIDPropriedadeHosp);
        editTextIDunidadeHosp = findViewById(R.id.editTextIDunidadeHosp);
        editTextDataEntrada = findViewById(R.id.editTextDataEntrada);
        editTextDataSaida = findViewById(R.id.editTextDataSaida);
        editTextIDUsuarioHosp = findViewById(R.id.editTextIDUsuarioHosp);
        editTextIDHospedagem = findViewById(R.id.editTextIDHospedagem);
        editTextValorHosp = findViewById(R.id.editTextValorHosp);

        textView = findViewById(R.id.textView);


        db = FirebaseFirestore.getInstance();

    }

    public void criar(View view) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        Date startDateValue = null;

        try {
            startDateValue = format.parse(editTextDataEntrada.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDateValue = null;

        try {
            endDateValue = format.parse(editTextDataSaida.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long difference = Math.abs(endDateValue.getTime() - startDateValue.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);
        dayDifference = Long.toString(differenceDates);

        float total = Float.parseFloat(editTextValorHosp.getText().toString()) * Float.parseFloat(dayDifference);



        Map<String, Object> colecao = new HashMap<>();
        colecao.put("propiedade", editTextIDPropriedadeHosp.getText().toString());
        colecao.put("Unidade", editTextIDunidadeHosp.getText().toString());
        colecao.put("dataEntrada", editTextDataEntrada.getText().toString());
        colecao.put("dataSaida", editTextDataSaida.getText().toString());
        colecao.put("usuario", editTextIDUsuarioHosp.getText().toString());
        colecao.put("valorDiaria", editTextValorHosp.getText().toString());
        colecao.put("valorTotal", total);



        db.collection("hospedagem")
                .add(colecao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //editTextId.setText(documentReference.getId());
                        //textView.setText("Cadastrado!");
                        Toast.makeText(getApplicationContext(), "Criado!.",
                                Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAGCadastro", "Erro ao cadastrar", e);
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar!.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }



    public void ler(View view){
      /*  DocumentReference docRef = db.collection("estabelecimento").document(editTextId.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        textView.setText(editTextId.getText().toString());
                        Toast.makeText(getApplicationContext(), "Documento encontrado!.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("TAG", "Documento não encontrado");
                        Toast.makeText(getApplicationContext(), "Documento não encontrado!.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("TAG", "Falhou em ", task.getException());
                    Toast.makeText(getApplicationContext(), "Falha!.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }



    public void atualizar(View view){

        /*db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString())
                .update("nome", editTextNome.getText().toString(), "descricao", editTextDescricao.getText().toString(),
                        "tipo",editTextTipo.getText().toString(), "categoria", editTextCategoria.getText().toString(),
                        "email", editTextEmail.getText().toString(), "telefone", editTextTelefone.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Atualizado!.",
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "Falhou ao atualizar");
                Toast.makeText(getApplicationContext(), "Falha ao atualizar!.",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
    }



    public void deletar(View view){
        /*db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Deletado!.",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Erro ao deletar!", e);
                        Toast.makeText(getApplicationContext(), "Erro ao deletar!.",
                                Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    //após deletar deve limpar os campos
    public void limparDados(){
       /* editTextId.setText("Id do documento");
        editTextCep.setText("Cep");
        editTextNomeEstabelecimento.setText("Nome estabelecimento");*/
    }
}