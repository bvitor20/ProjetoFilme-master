package br.com.brunovitor.projetoFilme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.brunovitor.projetoFilme.model.Filme;


/**
 * Created by Bruno Vitor Pires on 07/10/2015.
 */
public class FilmeDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    public static final String EXTRA_FILME = "filme";
    
    TextView mTextNome;
    TextView mTextElenco;
    TextView mAno;
    RatingBar mRatingEstrelas;
    TextView mTextResumo;
    Filme mFilme;
    ImageView imgCapaFilme;
    
    public static FilmeDetalheFragment novaInstancia(Filme filme){
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_FILME, filme);
        
        FilmeDetalheFragment fragment = new FilmeDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mFilme = (Filme)getArguments().getSerializable(EXTRA_FILME);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_detalhe_filme,
                container,false);
        mTextNome = (TextView)layout.findViewById(R.id.txtNome);
        mTextElenco = (TextView)layout.findViewById(R.id.txtElenco);
        mAno = (TextView)layout.findViewById(R.id.txtAnoLancamento);
        mRatingEstrelas = (RatingBar)layout.findViewById(R.id.rtbEstrelas);
        mTextResumo = (TextView)layout.findViewById(R.id.txtResumo);
        imgCapaFilme = (ImageView)layout.findViewById(R.id.img_capa_Detalhe);

        if (mFilme != null){
            mTextNome.setText(mFilme.titulo);
            mTextElenco.setText(mFilme.direcao);
            mAno.setText(mFilme.lancamento);
            mRatingEstrelas.setRating(mFilme.classificacao);
            mTextResumo.setText(mFilme.sinopse);
            Picasso.with(getContext()).load(mFilme.capa).into(imgCapaFilme);
        }
        return layout;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_filme_delatlhe, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //implementar o favoritos
        if (item.getItemId() == R.id.acao_favorito){
            Toast.makeText(getActivity(), "Clicou em favorito!",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
