package br.com.brunovitor.projetoFilme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno Vitor Pires on 28/10/2015.
 */
public class FilmesSQLHelper extends SQLiteOpenHelper {

    private final String NOME_BANCO = "dbFilme";
    private final int VERSAO_BANCO = 1;
    public static final String TABELA_FILME = "filme";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "titulo";
    public static final String COLUNA_DIRECAO = "direcao";
    public static final String COLUNA_LANCAMENTO = "lancamento";
    public static final String COLUNA_CLASSIFICACAO = "classificacao";
    public static final String COLUNA_SINOPSE = "sinopse";
    public static final String COLUNA_CAPA = "capa";

    public FilmesSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(
            "CREATE TABLE "+ TABELA_FILME +" (" +
                    COLUNA_ID      +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUNA_NOME  +" TEXT NOT NULL UNIQUE," +
                    COLUNA_DIRECAO     +" TEXT," +
                    COLUNA_LANCAMENTO     +" TEXT," +
                    COLUNA_CLASSIFICACAO     +" REAL," +
                    COLUNA_SINOPSE     +" TEXT," +
                    COLUNA_CAPA     +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
