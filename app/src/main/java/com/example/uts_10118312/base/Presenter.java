package com.example.uts_10118312.base;
//NAMA  : LUTHFI RIFQI ZULFIQAR
//NIM   : 10118312
//KELAS : IF-8
//TGL   : 3-6 JUNI 2021

public interface Presenter<T extends View> {
    void onAttach(T view);

    void onDetach();
}