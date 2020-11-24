package eduardostertz.cursoandroid.teste.model;

public class Estabelecimento {

    String id;
    private String cep;
    private String nomeEstabelecimento;

    public Estabelecimento(String cep, String nomeEstabelecimento) {
        this.cep = cep;
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public Estabelecimento(String id, String cep, String nomeEstabelecimento) {
        this.id = id;
        this.cep = cep;
        this.nomeEstabelecimento = nomeEstabelecimento;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getIdEstabelecimento() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
