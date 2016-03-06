package br.ufpi.es.checar.Persistencia;
import br.ufpi.es.checar.Persistencia.ConexaoBD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class ControleBanco {
    private SQLiteDatabase db;
    private ConexaoBD banco;

    /**
     * Cria uma nova conexão com o banco de dados, gerando uma nova tabela com os respectivos dados.
     *
     * @author Luis Henrique
     * @param context
     * @return ControleBanco
     */
    public ControleBanco(Context context) {
        banco = new ConexaoBD(context);
    }

    /**
     * Insere no banco os dados CPF, RG e Nome associado ao novo Cliente.
     * @author Luis Henrique
     * @param cpf
     * @param rg
     * @param Nome
     * @return - Retorna uma string com o valor de resultado para verificar se o dado foi inserido ou não.
     */
    public String InserirDado(String cpf, String rg, String Nome) {
        ContentValues valores = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();
        valores.put("CPF", cpf);
        valores.put("RG", rg);
        valores.put("Nome", Nome);
        resultado = db.insert("cliente", null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }
    /**
     * Insere no banco dos dados dos veículos através da função getWritableDatabase(). É importante lembrar que
     * o atributo db deve receber o resultado do método getWritableDatabase, que diz ao Android que o banco será
     * utilizado para leitura e escrita de dados.
     * @author Luis Henrique
     * @param placa - Parâmetro
     * @param fab - Fabricante
     * @param ver - Versão
     * @param mod - Modelo
     * @param ano - Ano de fabricação
     * @param cor - Cor do veículo
     * @param motor - Motor do veículo
     * @return - retorna uma string informando se o dado foi inserido ou não.
     */
    public String InserirPlaca(String placa, String fab, String ver, String mod, String ano, String cor, String motor) {
        ContentValues valores = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();
        valores.put("Placa", placa);
        valores.put("Fabricante", fab);
        valores.put("Modelo", mod);
        valores.put("Versao", ver);
        valores.put("Ano", ano);
        valores.put("Cor", cor);
        valores.put("Motor", motor);
        resultado = db.insert("carro", null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    /**
     * Lista todos os clientes cadastrados no banco de dados através da função getReadableDatabase().
     * @author Luis Henrique
     * @return - retorna um cursor uma classe do Android que salva as informações que são retornadas do banco de dados.
     */
    public Cursor carregaDados(){
        Cursor c;
        String[] campos = {"_id","CPF","RG","Nome"};
        db = banco.getReadableDatabase();
        c = db.query("cliente", campos, null, null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        db.close();
        return c;
    }

    /**
     * Busca o cliente através do Cpf no banco de dados.
     * @author Luis Henrique
     * @param cpf
     * @return - retorna um cursor uma classe do Android que salva as informações que são retornadas do banco de dados.
     */
    public Cursor carregaDadoById(String cpf) {
        Cursor cursor;
        //O problema é o _id
        String[] campos = {"CPF","_id","RG","Nome"};
        String where = "CPF" + "=" + cpf;
        db = banco.getReadableDatabase();
        cursor = db.query("cliente", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /**
     * Busca uma placa a partir da seu ID no banco de dados.
     * @param p - String placa, a placa do carro que vc deseja ver.
     * @return - retorna um cursor uma classe do Android que salva as informações que são retornadas do banco de dados.
     */
    public Cursor BuscaPlaca(String p) {
        Cursor cursor;
        String[] campos = {"Placa","_id","Fabricante","Modelo","Versao","Ano","Cor","Motor"};
        String where = "Fabricante" + "=" + p;
        db = banco.getReadableDatabase();
        cursor = db.query("carro", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /**
     * Lista todos os veículos cadastrados no banco de dados.
     * @author Luis Henrique
     * @return - retorna um cursor uma classe do Android que salva as informações que são retornadas do banco de dados.
     */
    public Cursor ListarCarros() {
        Cursor cursor;
        String[] campos = {"_id","Placa","Fabricante","Modelo","Versao","Ano","Cor","Motor"};
        db = banco.getReadableDatabase();
        cursor = db.query("carro", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
   }
}
//

