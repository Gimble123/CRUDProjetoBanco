package eduardostertz.cursoandroid.teste.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Unidade extends AppCompatActivity {


    EditText editTextNomeUnidade, editTextDescricaoUnidade, editTextTipoUnidade,
    editTextCategoriaUnidade, editTextProprietarioUnidade,
            editTextFotoUnidade , editTextVideoUnidade, editTextIDPropriedade, editTextIDUnidade;

    FirebaseFirestore db;

    TextView textView, textViewLeituraDados;

    DocumentReference propiedadeRef;

    CollectionReference propriedadeReference;

    String idocuemnto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_unidade);

        editTextNomeUnidade = findViewById(R.id.editTextNomeUnidade);
        editTextDescricaoUnidade = findViewById(R.id.editTextDescricaoUnidade);
        editTextTipoUnidade = findViewById(R.id.editTextTipoUnidade);
        editTextCategoriaUnidade = findViewById(R.id.editTextCategoriaUnidade);
        editTextProprietarioUnidade = findViewById(R.id.editTextProprietarioUnidade);
        editTextFotoUnidade = findViewById(R.id.editTextFotoUnidade);
        editTextVideoUnidade = findViewById(R.id.editTextVideoUnidade);
        editTextIDPropriedade = findViewById(R.id.editTextIDPropriedade);
        editTextIDUnidade = findViewById(R.id.editTextIDPropriedade);

        textView = findViewById(R.id.textView);

        textViewLeituraDados = findViewById(R.id.textViewLeituraDados);

        db = FirebaseFirestore.getInstance();


        propriedadeReference = db.collection("propriedade");

    }


    public void criar(View view) {

        Map<String, Object> colecao = new HashMap<>();
        colecao.put("nome", editTextNomeUnidade.getText().toString());
        colecao.put("descricao", editTextDescricaoUnidade.getText().toString());
        colecao.put("tipo", editTextTipoUnidade.getText().toString());
        colecao.put("categoria", editTextCategoriaUnidade.getText().toString());
        colecao.put("proprietario", editTextProprietarioUnidade.getText().toString());
        colecao.put("foto", editTextFotoUnidade.getText().toString());
        colecao.put("video", editTextVideoUnidade.getText().toString());

        propriedadeReference.document(editTextIDPropriedade.getText().toString()).collection("unidade").add(colecao).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Cadastrado!.",
                        Toast.LENGTH_SHORT).show();
                limparDados();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAGCadastro", "Erro ao cadastrar", e);
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar!.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void ler(View view){

    }



    public void atualizar(View view){



        db.collection("propriedade").document("LAIQTQ69WUG8UOtzooIA").collection("unidade").document(
                "R4b58bibVKzonTx2e4yu").update("nome", editTextNomeUnidade).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        });

        /*db.collection("unidade").document(editTextIDUnidade.getText().toString()).
                update("nome", editTextNomeUnidade.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                textView.setText("Atualizado!");
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
       /* db.collection("usuario").document(editTextId.getText().toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        limparDados();
                        textView.setText("Deletado!");
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
        editTextNomeUnidade.setText("");
        editTextDescricaoUnidade.setText("");
        editTextTipoUnidade.setText("");
        editTextCategoriaUnidade.setText("");
        editTextProprietarioUnidade.setText("");
        editTextFotoUnidade.setText("");
        editTextVideoUnidade.setText("");
        editTextIDPropriedade.setText("");

    }
}