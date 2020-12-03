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

import eduardostertz.cursoandroid.teste.R;

public class Relatorio extends AppCompatActivity {


    CollectionReference usuarioReference;
    CollectionReference enderecoReference;
    CollectionReference propriedadeReference;
    CollectionReference hospedagemReference;

    EditText editTextIdpropiedadeConsulta, editTextEnderecoRegiao, editTextLerPropiedadeConsulta;

    FirebaseFirestore db;

    TextView textViewLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_relatorio);

        editTextEnderecoRegiao = findViewById(R.id.editTextEnderecoRegiao);
        editTextLerPropiedadeConsulta = findViewById(R.id.editTextLerPropiedadeConsulta);

        textViewLeitura = findViewById(R.id.textViewLeitura);

        db = FirebaseFirestore.getInstance();
        usuarioReference = db.collection("usuario");
        enderecoReference = db.collection("endereco");
        propriedadeReference = db.collection("propriedade");
        hospedagemReference = db.collection("hospedagem");
    }

    public void lerUsuarios(View view) {
        final String[] dataUsuario = {""};
        usuarioReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    //String nome = documentSnapshot.get("nome").toString();

                    dataUsuario[0] += "\n-----------------------------------------------------------" +
                            "\nNome: " + documentSnapshot.get("nome").toString() +
                            "\nSobrenome: " + documentSnapshot.get("sobrenome").toString() +
                            "\nTipo: " + (documentSnapshot.get("tipo").toString().equals("P") ? "Proprietário" : "Geral") +
                            "\nCPF: " + documentSnapshot.get("cpf").toString() +
                            "\nEmail: " + documentSnapshot.get("email").toString() +
                            "\nId endereco: " + (documentSnapshot.get("endereco") != null ? documentSnapshot.get("endereco").toString() : "");
                }
                textViewLeitura.setText(dataUsuario[0]);
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

    public void lerPropietarios(View view) {

        final String[] dataUsuario = {""};
        usuarioReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    //String nome = documentSnapshot.get("nome").toString();
                    if (documentSnapshot.get("tipo").toString().equals("P")) {
                        dataUsuario[0] += "\n-----------------------------------------------------------" +
                                "\nNome: " + documentSnapshot.get("nome").toString() +
                                "\nSobrenome: " + documentSnapshot.get("sobrenome").toString() +
                                "\nTipo: " + (documentSnapshot.get("tipo").toString().equals("P") ? "Proprietário" : "Geral") +
                                "\nCPF: " + documentSnapshot.get("cpf").toString() +
                                "\nEmail: " + documentSnapshot.get("email").toString() +
                                "\nId endereco: " + (documentSnapshot.get("endereco") != null ? documentSnapshot.get("endereco").toString() : "-");
                    }
                }
                textViewLeitura.setText(dataUsuario[0]);
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

    public void lerEnderecos(View view) {

        final String[] dataEndereco = {""};
        enderecoReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    dataEndereco[0] += "\n-----------------------------------------------------------" +
                            "\nRua: " + documentSnapshot.get("rua").toString() +
                            "\nNúmero: " + documentSnapshot.get("numero").toString() +
                            "\nComplemento: " + (documentSnapshot.get("complemento") != null ? documentSnapshot.get("complemento").toString() : "-") +
                            "\nCep: " + documentSnapshot.get("cep").toString() +
                            "\nCidade: " + documentSnapshot.get("cidade").toString() +
                            "\nEstado: " + documentSnapshot.get("estado").toString() +
                            "\nPaís: " + documentSnapshot.get("pais").toString() +
                            "\nRegião: " + documentSnapshot.get("regiao").toString() +
                            "\nContinente: " + documentSnapshot.get("continente").toString() +
                            "\nLatitude: " + documentSnapshot.get("latitude").toString() +
                            "\nLongitude: " + documentSnapshot.get("longitude").toString();
                }

                textViewLeitura.setText(dataEndereco[0]);
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

    public void lerPropiedades(View view) {

        final String[] dataPropriedade = {""};
        propriedadeReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    dataPropriedade[0] += "\n-----------------------------------------------------------" +
                            "\nNome: " + documentSnapshot.get("nome").toString() +
                            "\nDescrição: " + documentSnapshot.get("descricao").toString() +
                            "\nTipo: " + (documentSnapshot.get("tipo").toString().equals("H") ? "Hotel" : "Resort") +
                            "\nCategoria: " + documentSnapshot.get("categoria").toString() +
                            "\nEmail: " + documentSnapshot.get("email").toString() +
                            "\nId endereço: " + (documentSnapshot.get("endereco") != null ? documentSnapshot.get("endereco").toString() : "-");
                }
                textViewLeitura.setText(dataPropriedade[0]);
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

    public void lerUnidadePropiedade(View view) {


    }

    public void lerRegiaoEndereco(View view) {

        final String[] dataEndereco = {""};
        enderecoReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (editTextEnderecoRegiao.getText().toString().equals(documentSnapshot.get("regiao").toString())) {
                        dataEndereco[0] += "\n-----------------------------------------------------------" +
                                "\nRua: " + documentSnapshot.get("rua").toString() +
                                "\nNúmero: " + documentSnapshot.get("numero").toString() +
                                "\nComplemento: " + (documentSnapshot.get("complemento") != null ? documentSnapshot.get("complemento").toString() : "-") +
                                "\nCep: " + documentSnapshot.get("cep").toString() +
                                "\nCidade: " + documentSnapshot.get("cidade").toString() +
                                "\nEstado: " + documentSnapshot.get("estado").toString() +
                                "\nPaís: " + documentSnapshot.get("pais").toString() +
                                "\nRegião: " + documentSnapshot.get("regiao").toString() +
                                "\nContinente: " + documentSnapshot.get("continente").toString() +
                                "\nLatitude: " + documentSnapshot.get("latitude").toString() +
                                "\nLongitude: " + documentSnapshot.get("longitude").toString();
                    }
                }
                textViewLeitura.setText(dataEndereco[0]);
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

    public void lerFaturamentoProp(View view) {
        final double[] total = {0};
        final String[] nome = {""};
        hospedagemReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if(editTextLerPropiedadeConsulta.getText().toString().equals(documentSnapshot.get("propiedade").toString())){
                        total[0] += Float.parseFloat(documentSnapshot.get("valorTotal").toString());
                    }
                }

                final DocumentReference docRef = db.collection("propriedade").document(editTextLerPropiedadeConsulta.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                nome[0] = (String) document.get("nome");
                                Toast.makeText(getApplicationContext(), "Documento encontrado!.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                nome[0] = "---";
                                Log.d("TAG", "Documento não encontrado");
                                Toast.makeText(getApplicationContext(), "Documento não encontrado!.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            nome[0] = "---";
                            Log.d("TAG", "Falhou em ", task.getException());
                            Toast.makeText(getApplicationContext(), "Falha ao ler!.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                textViewLeitura.setText("Total de Faturamento da Propiedade " + nome[0] + " foi de R$" + total[0]);
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
}