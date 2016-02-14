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

    public ControleBanco(Context context) {
        banco = new ConexaoBD(context);
    }
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
    public Cursor carregaDadoById(String cpf) {
        Cursor cursor;
        String[] campos = {"_id","CPF","RG","Nome"};
        String where = "CPF" + "=" + cpf;
        db = banco.getReadableDatabase();
        cursor = db.query("cliente", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}

