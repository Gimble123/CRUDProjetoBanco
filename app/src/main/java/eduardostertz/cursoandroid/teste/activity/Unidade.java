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

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Unidade extends AppCompatActivity {


    EditText editTextNomeUnidade, editTextDescricaoUnidade, editTextTipoUnidade,
    editTextCategoriaUnidade, editTextPropriedadeUnidade, editTextProprietarioUnidade,
            editTextFotoUnidade , editTextVideoUnidade;

    FirebaseFirestore db;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_unidade);

        editTextNomeUnidade = findViewById(R.id.editTextNomeUnidade);
        editTextDescricaoUnidade = findViewById(R.id.editTextDescricaoUnidade);
        editTextTipoUnidade = findViewById(R.id.editTextTipoUnidade);
        editTextCategoriaUnidade = findViewById(R.id.editTextCategoriaUnidade);
        editTextPropriedadeUnidade = findViewById(R.id.editTextPropriedadeUnidade);
        editTextProprietarioUnidade = findViewById(R.id.editTextProprietarioUnidade);
        editTextFotoUnidade = findViewById(R.id.editTextFotoUnidade);
        editTextVideoUnidade = findViewById(R.id.editTextVideoUnidade);


        textView = findViewById(R.id.textView);

        db = FirebaseFirestore.getInstance();
    }


    public void criar(View view) {

       Map<String, Object> colecao = new HashMap<>();
        colecao.put("nome", editTextNomeUnidade.getText().toString());
        colecao.put("descricao", editTextDescricaoUnidade.getText().toString());
        colecao.put("tipo", editTextTipoUnidade.getText().toString());
        colecao.put("categoria", editTextCategoriaUnidade.getText().toString());
        colecao.put("propriedade", editTextPropriedadeUnidade.getText().toString());
        colecao.put("proprietario", editTextProprietarioUnidade.getText().toString());
        colecao.put("foto", editTextFotoUnidade.getText().toString());
        colecao.put("video", editTextVideoUnidade.getText().toString());



        db.collection("unidade")
                .add(colecao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // editTextId.setText(documentReference.getId());
                        // textView.setText("Cadastrado!");
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
       /* DocumentReference docRef = db.collection("usuario").document(editTextId.getText().toString());
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
                    Toast.makeText(getApplicationContext(), "Falha ao ler!.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }



    public void atualizar(View view){

        /*db.collection("usuario").document(editTextId.getText().toString()).
                update("nome", editTextNomeUsuario.getText().toString(), "cpf", editTextCpf.getText().toString()
                ,"dataNascimento",editTextDataNascimento.getText().toString(),
                       "cidade",editTextCidadeNatal.getText().toString(),
                       "estado", editTextEstado.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
       /* editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");*/

    }
}