package eduardostertz.cursoandroid.teste.config;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfiguracaoFirebase {

    private static FirebaseFirestore database;
    private static DocumentReference documentReference;

    //retorna uma instancia do FirebaseFirestore
    public static FirebaseFirestore getFirebaseDatabase(){

        if (database == null) {
            database = FirebaseFirestore.getInstance();
        }
        return database;

    }

}
