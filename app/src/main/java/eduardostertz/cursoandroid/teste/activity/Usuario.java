package eduardostertz.cursoandroid.teste.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Usuario extends AppCompatActivity {


    EditText editTextNomeUsuario, editTextCpf, editTextSobrenome, editTextEmail, editTextTipo, editTextIDdoUsuario, editTextEnderecoUsuario;


    TextView textView, textViewLeitura;

    Button buttonCriar, buttonLer, buttonAtualizar, buttonDeletar;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_usuario);

        editTextNomeUsuario = findViewById(R.id.editTextNomeUsuario);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTipo = findViewById(R.id.editTextTipo);
        editTextIDdoUsuario = findViewById(R.id.editTextIDdoUsuario);
        editTextEnderecoUsuario = findViewById(R.id.editTextEnderecoUsuario);

        textView = findViewById(R.id.textView);
        textViewLeitura = findViewById(R.id.textViewLeitura);

        buttonCriar = findViewById(R.id.buttonCriar);
        buttonLer = findViewById(R.id.buttonLer);
        buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonDeletar = findViewById(R.id.buttonDeletar);

        db = FirebaseFirestore.getInstance();


    }


    public void criar(View view) {

        Map<String, Object> colecao = new HashMap<>();
        colecao.put("nome", editTextNomeUsuario.getText().toString());
        colecao.put("cpf", editTextCpf.getText().toString());
        colecao.put("sobrenome", editTextSobrenome.getText().toString());
        colecao.put("email", editTextEmail.getText().toString());
        colecao.put("tipo", editTextTipo.getText().toString());
        colecao.put("endereco", editTextEnderecoUsuario.getText().toString());

        db.collection("usuario")
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


    public void ler(View view) {
        final DocumentReference docRef = db.collection("usuario").document(editTextIDdoUsuario.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        textViewLeitura.setText("ID: " + document.getId() + "\nNome: " + (document.get("nome") != null ? document.get("nome").toString() : "") +
                                "\nSobrenome: " + (document.get("sobrenome") != null ? document.get("sobrenome").toString() : "") +
                                "\nCpf: " + (document.get("cpf") != null ? document.get("cpf").toString() : "") +
                                "\nEmail: " + (document.get("email") != null ? document.get("email").toString() : "") +
                                "\nTipo: " + (document.get("tipo").equals("P") ? "Proprietário" : "Geral") +
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


    public void atualizar(View view) {


        db.collection("usuario").document(editTextIDdoUsuario.getText().toString()).
                update("nome", editTextNomeUsuario.getText().toString(), "cpf", editTextCpf.getText().toString()
                        , "sobrenome", editTextSobrenome.getText().toString(),
                        "email", editTextEmail.getText().toString(),
                        "tipo", editTextTipo.getText().toString(),
                        "endereco", editTextEnderecoUsuario.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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


    public void deletar(View view) {
        db.collection("usuario").document(editTextIDdoUsuario.getText().toString())
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
    public void limparDados() {
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextEnderecoUsuario.setText("");
    }

    public void carregarDados(View view) {

        final DocumentReference docRef = db.collection("usuario").document(editTextIDdoUsuario.getText().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        editTextIDdoUsuario.setText(document.getId());
                        editTextNomeUsuario.setText((document.get("nome") != null ? document.get("nome").toString() : ""));
                        editTextSobrenome.setText((document.get("sobrenome") != null ? document.get("sobrenome").toString() : ""));
                        editTextCpf.setText((document.get("cpf") != null ? document.get("cpf").toString() : ""));
                        editTextEmail.setText((document.get("email") != null ? document.get("email").toString() : ""));
                        editTextTipo.setText((document.get("tipo") != null ? document.get("tipo").toString() : ""));
                        editTextEnderecoUsuario.setText((document.get("endereco") != null ? document.get("endereco").toString() : ""));
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