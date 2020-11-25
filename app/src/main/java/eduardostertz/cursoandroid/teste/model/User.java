package eduardostertz.cursoandroid.teste.model;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import eduardostertz.cursoandroid.teste.config.ConfiguracaoFirebase;

public class User {

    private String UserName;
    private String UserFullName;
    private String UserDocument;
    private String Country;
    private String State;
    private String City;
    private String Address;


    public User() {
    }

    public User(String UserName, String UserFullName, String UserDocument, String Country, String State, String City, String Address) {
        this.UserName = UserName;
        this.UserFullName = UserFullName;
        this.UserDocument = UserDocument;
        this.Country = Country;
        this.State = State;
        this.City = City;
        this.Address = Address;
    }

    public void save(User userDTO){
        FirebaseFirestore firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("UserName", userDTO.getUserName());
        objectMap.put("UserFullName", userDTO.getUserFullName());
        objectMap.put("UserDocument", userDTO.getUserDocument());
        objectMap.put("Country", userDTO.getCountry());
        objectMap.put("State", userDTO.getState());
        objectMap.put("City", userDTO.getCity());
        objectMap.put("Address", userDTO.getAddress());

        firebaseRef.collection("User").add(objectMap);
    }

    /*public void atualizar(User estabelecimento) {
        FirebaseFirestore firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DocumentReference objectDocumentReference = firebaseRef.collection("estabelecimentos").document("");

        objectDocumentReference.update("cep", estabelecimento.getCep());

    }
    */

    /*public void deletar() {
        FirebaseFirestore firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DocumentReference estabelecimentoRef = firebaseRef.collection("estabelecimentos").document(cep);
        estabelecimentoRef.delete();

    }*/

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public void setUserFullName(String userFullName) {
        UserFullName = userFullName;
    }

    public String getUserDocument() {
        return UserDocument;
    }

    public void setUserDocument(String userDocument) {
        UserDocument = userDocument;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
