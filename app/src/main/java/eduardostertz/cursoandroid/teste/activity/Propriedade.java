package eduardostertz.cursoandroid.teste.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Propriedade extends AppCompatActivity {

    EditText editTextNome,editTextDescricao, editTextTipo,editTextCategoria,editTextEmail,editTextTelefone;

    TextView textView;

    Button buttonCriar;
    Button buttonLer;
    Button buttonAtualizar;
    Button buttonDeletar;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_propriedade);

        db = FirebaseFirestore.getInstance();

        editTextNome = findViewById(R.id.editTextNome);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextCategoria = findViewById(R.id.editTextCategoria);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);


        textView = findViewById(R.id.textView);

        buttonCriar = findViewById(R.id.buttonCriar);
        buttonLer = findViewById(R.id.buttonLer);
        buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonDeletar = findViewById(R.id.buttonDeletar);

    }


    public void criar(View view) {

        Map<String, Object> colecao = new HashMap<>();
        colecao.put("nome", editTextNome.getText().toString());
        colecao.put("descricao", editTextDescricao.getText().toString());
        colecao.put("tipo", editTextTipo.getText().toString());
        colecao.put("categoria", editTextCategoria.getText().toString());
        colecao.put("email", editTextEmail.getText().toString());
        colecao.put("telefone", editTextTelefone.getText().toString());

        db.collection("propriedade")
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
       /* db.collection("estabelecimento").document(editTextId.getText().toString()).update("cep", editTextCep.getText().toString(), "nome", editTextNomeEstabelecimento.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        /*db.collection("estabelecimento").document(editTextId.getText().toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        editTextId.setText("");
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
       /* editTextId.setText("Id do documento");
        editTextCep.setText("Cep");
        editTextNomeEstabelecimento.setText("Nome estabelecimento");*/
    }
}
