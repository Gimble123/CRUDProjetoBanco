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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Propriedade extends AppCompatActivity {

    EditText editTextNome,editTextDescricao, editTextTipo,editTextCategoria,editTextEmail,editTextTelefone, editTextIdDaPropiedade, editTextEnderecoProp;

    TextView textView, textViewLeitura;

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
        editTextIdDaPropiedade = findViewById(R.id.editTextIdDaPropiedade);
        editTextEnderecoProp = findViewById(R.id.editTextEnderecoProp);
        textViewLeitura = findViewById(R.id.textViewLeitura);

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
        colecao.put("endereco", editTextEnderecoProp.getText().toString());

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
        final DocumentReference docRef = db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString());
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

       db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString())
               .update("nome", editTextNome.getText().toString(), "descricao", editTextDescricao.getText().toString(),
                       "tipo",editTextTipo.getText().toString(), "categoria", editTextCategoria.getText().toString(),
                        "email", editTextEmail.getText().toString(), "telefone", editTextTelefone.getText().toString(),
                       "endereco", editTextEnderecoProp.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Deletado!.",
                                Toast.LENGTH_SHORT).show();
                        limparDados();
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

        editTextNome.setText("");
        editTextDescricao.setText("");
        editTextTipo.setText("");
        editTextCategoria.setText("");
        editTextEmail.setText("");
        editTextTelefone.setText("");
        editTextIdDaPropiedade.setText("");
        editTextEnderecoProp.setText("");

    }

    public void carregarDados(View view) {

        final DocumentReference docRef = db.collection("propriedade").document(editTextIdDaPropiedade.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        editTextIdDaPropiedade.setText(document.getId());
                        editTextNome.setText((document.get("nome") != null ? document.get("nome").toString() : ""));
                        editTextDescricao.setText((document.get("descricao") != null ? document.get("descricao").toString() : ""));
                        editTextTipo.setText((document.get("tipo") != null ? document.get("tipo").toString() : ""));
                        editTextCategoria.setText((document.get("categoria") != null ? document.get("categoria").toString() : ""));
                        editTextEmail.setText((document.get("email") != null ? document.get("email").toString() : ""));
                        editTextTelefone.setText((document.get("telefone") != null ? document.get("telefone").toString() : ""));
                        editTextEnderecoProp.setText((document.get("endereco") != null ? document.get("endereco").toString() : ""));
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
}
