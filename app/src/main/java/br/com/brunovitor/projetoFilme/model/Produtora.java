package br.com.brunovitor.projetoFilme.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;



/**
 * Created by BrunoVitor on 19/10/2015.
 */
public class Produtora {
    @SerializedName("lancamentos")
    public List<Categoria> categorias;

}
