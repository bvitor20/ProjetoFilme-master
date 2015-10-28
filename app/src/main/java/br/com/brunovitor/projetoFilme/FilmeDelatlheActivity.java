package br.com.brunovitor.projetoFilme;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.brunovitor.projetoFilme.model.Filme;


public class FilmeDelatlheActivity extends AppCompatActivity {

    public static final String EXTRA_FILME = "filme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_delatlhe);

        //Recebendo o objeto Filme que vem da outra tela
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            Filme filme = (Filme) intent.getSerializableExtra(EXTRA_FILME);

            //Instanciando fragment de detalhe
            FilmeDetalheFragment fragment = FilmeDetalheFragment.novaInstancia(filme);
            FragmentManager fm = getSupportFragmentManager();//Para adicionar o fragment dinamicamente
            FragmentTransaction ft = fm.beginTransaction();//iniciando transação
            ft.replace(R.id.detalhe, fragment, FilmeDetalheFragment.TAG_DETALHE);//Adicionando o fragmento ao frameLayout
            ft.commit();

        }
    }
}
