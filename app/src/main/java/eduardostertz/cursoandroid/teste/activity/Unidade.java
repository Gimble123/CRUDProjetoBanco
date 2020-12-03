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

    TextView textView, textViewLeitura;

    CollectionReference propriedadeReference;


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

        textViewLeitura = findViewById(R.id.textViewLeitura);

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
                //limparDados();
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
        final DocumentReference docRef = db.collection("propriedade").document(editTextIDPropriedade.getText().toString()).collection("unidade").
                document(editTextIDUnidade.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        textViewLeitura.setText("ID: " + document.getId()
                                + "\nNome: " + (document.get("nome") != null ? document.get("nome").toString() : "") +
                                "\nDescricao: " + (document.get("descricao") != null ? document.get("descricao").toString() : "") +
                                "\nTipo: " + (document.get("tipo") != null ? document.get("tipo").toString() : "") +
                                "\nCategoria: " + (document.get("categoria") != null ? document.get("categoria").toString() : "") +
                                "\nEmail: " + (document.get("email") != null ? document.get("email").toString() : "") +
                                "\nTelefone: " + (document.get("telefone") != null ? document.get("telefone").toString() : "") +
                                "\nId endereco: " + (document.get("endereco") != null ? document.get("endereco").toString() : ""));
                        Toast.makeText(getApplicationContext(), "Documento encontrado!.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("TAG", "Documento não encontrado");
                        Toast.makeText(getApplicationContext(), "Documento não encontrado!.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("TAG", "Falhou em ", task.getException());
                    Toast.makeText(getApplicationContext(), "Falha ao ler!.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void atualizar(View view){
        db.collection("propriedade").document(editTextIDPropriedade.getText().toString()).collection("unidade").document(
                editTextIDUnidade.getText().toString()).update("nome", editTextNomeUnidade,
                "descricao", editTextDescricaoUnidade,
                "nome", editTextNomeUnidade,
                "tipo", editTextTipoUnidade,
                "categoria", editTextCategoriaUnidade,
                "proprietario", editTextProprietarioUnidade,
                "foto", editTextFotoUnidade,
                "video", editTextVideoUnidade).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    }



    public void deletar(View view){
       db.collection("unidade").document(editTextIDUnidade.getText().toString())
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
                });
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
        editTextIDUnidade.setText("");

    }
}