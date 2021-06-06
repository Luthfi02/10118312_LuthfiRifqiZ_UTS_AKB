package com.example.uts_10118312.main.Presenters;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

import com.example.uts_10118312.base.Presenter;
import com.example.uts_10118312.main.Models.Data;
import com.example.uts_10118312.main.Views.MainView;

public class MainPresenter implements Presenter<MainView> {
    private MainView mView;

    @Override
    public void onAttach(final MainView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public void showFragment() {
        // Set Data
        final Data data = new Data();
        mView.onShowFragment(data);
    }

}
