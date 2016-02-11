package br.ufpi.es.checar.Persistencia;
import br.ufpi.es.checar.Persistencia.ConexaoBD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by luiz henrique on 03/02/2016.
 */
public class ControleBanco {

    private SQLiteDatabase db;

    private static final String TAG = "Checar.java";

    public ControleBanco(Context context) {
        ConexaoBD banco = new ConexaoBD(context);
        db = banco.getWritableDatabase();
    }

    public void InserirDado(String cpf, String rg, String Nome) {
        ContentValues valores = new ContentValues();
        valores.put("CPF", cpf);
        valores.put("RG", rg);
        valores.put("Nome", Nome);

        db.insert("cliente", null, valores);
    }
}

