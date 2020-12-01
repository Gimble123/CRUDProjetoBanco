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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.R;

public class Usuario extends AppCompatActivity {



    EditText editTextNomeUsuario, editTextCpf, editTextSobrenome,editTextEmail,editTextTipo, editTextIDdoUsuario, editTextEnderecoUsuario;



    TextView textView;

    Button buttonCriar, buttonLer,buttonAtualizar,buttonDeletar;

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


        db.collection("usuario").document(editTextIDdoUsuario.getText().toString()).
                update("nome", editTextNomeUsuario.getText().toString(), "cpf", editTextCpf.getText().toString()
                ,"sobrenome",editTextSobrenome.getText().toString(),
                       "email",editTextEmail.getText().toString(),
                       "tipo", editTextTipo.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    public void limparDados(){
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
        editTextNomeUsuario.setText("");
    }
}