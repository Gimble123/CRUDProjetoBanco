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

public class Endereco extends AppCompatActivity {

    EditText editTextRua, editTextNumero, editTextComplemento, editTextCep, editTextCidadeEndereco,
            editTextEstado, editTextPais, editTextRegiao, editTextContinente, editTextCoordenadas;


    TextView textView;

    Button buttonCriar, buttonLer, buttonAtualizar, buttonDeletar;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_endereco);

        editTextRua = findViewById(R.id.editTextRua);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextCep = findViewById(R.id.editTextCep);
        editTextCidadeEndereco = findViewById(R.id.editTextCidadeEndereco);
        editTextEstado = findViewById(R.id.editTextEstado);
        editTextPais = findViewById(R.id.editTextPais);
        editTextRegiao = findViewById(R.id.editTextRegiao);
        editTextContinente = findViewById(R.id.editTextContinente);
        editTextCoordenadas = findViewById(R.id.editTextCoordenadas);

        textView = findViewById(R.id.textView);

        buttonCriar = findViewById(R.id.buttonCriar);
        buttonLer = findViewById(R.id.buttonLer);
        buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonDeletar = findViewById(R.id.buttonDeletar);

        db = FirebaseFirestore.getInstance();
    }

    public void criar(View view) {

        Map<String, Object> colecao = new HashMap<>();
        colecao.put("rua", editTextRua.getText().toString());
        colecao.put("numero", editTextNumero.getText().toString());
        colecao.put("complemento", editTextComplemento.getText().toString());
        colecao.put("cep", editTextCep.getText().toString());
        colecao.put("cidade", editTextCidadeEndereco.getText().toString());
        colecao.put("estado", editTextEstado.getText().toString());
        colecao.put("pais", editTextPais.getText().toString());
        colecao.put("regiao", editTextRegiao.getText().toString());
        colecao.put("continente", editTextContinente.getText().toString());
        colecao.put("coordenadas", editTextCoordenadas.getText().toString());

        db.collection("endereco")
                .add(colecao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
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


    public void ler(View view) {
       /* DocumentReference docRef = db.collection("endereco").document(editTextId.getText().toString());
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


    public void atualizar(View view) {

       /*db.collection("endereco").document(editTextId.getText().toString()).
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


    public void deletar(View view) {
        /*db.collection("endereco").document(editTextId.getText().toString())
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
    public void limparDados() {
        editTextRua.setText("");
        editTextNumero.setText("");
        editTextComplemento.setText("");
        editTextCep.setText("");
        editTextCidadeEndereco.setText("");
        editTextEstado.setText("");
        editTextPais.setText("");
        editTextRegiao.setText("");
        editTextContinente.setText("");
        editTextCoordenadas.setText("");
    }
}