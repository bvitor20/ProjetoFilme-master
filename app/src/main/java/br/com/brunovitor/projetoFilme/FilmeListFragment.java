package br.com.brunovitor.projetoFilme;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.brunovitor.projetoFilme.model.Categoria;
import br.com.brunovitor.projetoFilme.model.Filme;
import br.com.brunovitor.projetoFilme.model.Produtora;
/**
 * Created by BrunoVitor on 19/10/2015.
 */

public class FilmeListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Filme> mFilmes;
    FilmesListAdapter mAdapter;
    SwipeRefreshLayout mSwipe;
    FilmesTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mFilmes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_filmes,null);
        mSwipe = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        mSwipe.setOnRefreshListener(this);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mFilmes = new ArrayList<>();
        if(mFilmes.isEmpty()){
            mAdapter =  new FilmesListAdapter(getActivity(),mFilmes);
            setListAdapter(mAdapter);
            carregarFilmes();
        }
        //carregarFilmes();
    }
    private void carregarFilmes() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            mSwipe.setRefreshing(true);
            if(mTask == null){
                mTask = new FilmesTask();
                mTask.execute();
            }else{
                mSwipe.setRefreshing(false);
            }
        }else{
            mSwipe.setRefreshing(false);
            Toast.makeText(getActivity(),R.string.msg_sem_conexao,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Filme mFilme = mFilmes.get(position);
        if(getActivity() instanceof AoClicarNoFilme  ){
            ((AoClicarNoFilme) getActivity()).clicouNoFilme(mFilme);
        }
    }
    public void onRefresh(){
        carregarFilmes();
    }

    public interface AoClicarNoFilme {
        void clicouNoFilme(Filme filme);
    }

    class FilmesTask extends AsyncTask<Void,Void,Produtora> {
        @Override
        protected Produtora doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                        .url("https://dl.dropbox.com/s/q3veujknhqrpizu/lancamentos.json")
                    .build();
            //REQUEST
            //paraAdd
            try{
                Response response = client.newCall(request).execute();
                String jsonString = response.body().string();

                Gson gson = new Gson();
                Produtora produtora = gson.fromJson(jsonString, Produtora.class);
                return produtora;
            }catch(Throwable e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Produtora produtora) {
            super.onPostExecute(produtora);
            if(produtora!= null && produtora.categorias !=null){
                mFilmes.clear();
                for (Categoria categoria:
                        produtora.categorias){
                    mFilmes.addAll(categoria.filmes);
                }
                mAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(), R.string.msg_sem_conexao, Toast.LENGTH_SHORT).show();
            }
            mSwipe.setRefreshing(false);
        }
    }
}



