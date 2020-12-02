package eduardostertz.cursoandroid.teste.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import eduardostertz.cursoandroid.teste.R;

public class Relatorio extends AppCompatActivity {


    CollectionReference usuarioReference;

    FirebaseFirestore db;

    TextView textViewLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_relatorio);

        textViewLeitura = findViewById(R.id.textViewLeitura);
        db = FirebaseFirestore.getInstance();
        usuarioReference = db.collection("usuario");
    }

    public void lerUsuarios(View view) {
        final String[] dataUsuario = {""};
        usuarioReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    //String nome = documentSnapshot.get("nome").toString();

                    dataUsuario[0] += "\n-----------------------------------------------------------" +
                            "\nNome: " + documentSnapshot.get("nome").toString() +
                            "\nSobrenome: " +  documentSnapshot.get("sobrenome").toString() +
                            "\nTipo: " + (documentSnapshot.get("tipo").toString().equals("P") ? "Proprietário" : "Geral") +
                            "\nCPF: " +  documentSnapshot.get("cpf").toString() +
                            "\nEmail: " +  documentSnapshot.get("email").toString() ;
                }
                textViewLeitura.setText(dataUsuario[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void lerPropietarios(View view) {
    }

    public void lerEnderecos(View view) {
    }

    public void lerPropiedades(View view) {
    }

    public void lerUnidadePropiedade(View view) {
    }

    public void lerRegiaoEndereco(View view) {
    }

    public void lerFaturamentoProp(View view) {
    }
}