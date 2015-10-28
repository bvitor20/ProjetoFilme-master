package br.com.brunovitor.projetoFilme;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.brunovitor.projetoFilme.FilmeListFragment.AoClicarNoFilme;
import br.com.brunovitor.projetoFilme.model.Filme;

public class FilmeActivity extends AppCompatActivity
                implements AoClicarNoFilme {

//tela principal que esta exibindo o fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new PaginasAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

       }

    public void clicouNoFilme(Filme filme) {

        if(getResources().getBoolean(R.bool.smartphone)){
            Intent it = new Intent(this, FilmeDelatlheActivity.class);
            it.putExtra(FilmeDelatlheActivity.EXTRA_FILME, filme);
            startActivity(it);
        }else {

            FilmeDetalheFragment fragment = FilmeDetalheFragment.novaInstancia(filme);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detalhe, fragment, FilmeDetalheFragment.TAG_DETALHE);
            ft.commit();
        }
    }

    private class PaginasAdapter extends FragmentPagerAdapter {


        public PaginasAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new FilmeListFragment();
            } else {
                return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? "Web" : "Favoritos";
        }
    }
}
