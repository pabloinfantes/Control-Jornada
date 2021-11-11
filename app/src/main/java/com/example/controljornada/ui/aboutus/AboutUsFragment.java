package com.example.controljornada.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.controljornada.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Element version = new Element();
        version.setTitle("1.0");

        return new AboutPage(getContext())
                .isRTL(false)
                .addGroup("Empresa de construcción")
                .setDescription("Aplicación para las empresas que se dedican a la construcción")
                .addGroup("Redes Sociales")
                .addEmail("pabloinfantes@gmail.com", "Email")
                .addInstagram("pabloinfantes00")
                .addGitHub("pabloinfantes")
                .addTwitter("pabloinfantes1")
                .addPlayStore("pabloinfantes")
                .addYoutube("pabloinfantes")
                .setImage(R.drawable.ic_tiluser_person)
                .addItem(version)
                .create();
    }

}