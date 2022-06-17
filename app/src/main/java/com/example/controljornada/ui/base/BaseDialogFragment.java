package com.example.controljornada.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Esta clase es la encargada de hacer que salga el cuadro de dialogo a la hora de eliminar en los listados
 * @author pablo
 *
 */
public class BaseDialogFragment extends DialogFragment {

    public static final String REQUEST ="requestDialog";
    public static final String KEY_BUNDLE ="result";
    public static final String TITLE ="title";
    public static final String MESSAGE ="message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            //Patron builder
            String title = getArguments().getString(TITLE);
            String message = getArguments().getString(MESSAGE);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(KEY_BUNDLE,true);
                 getActivity().getSupportFragmentManager().setFragmentResult(REQUEST,bundle);
                }
            });


            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });
            return builder.create();
        }
        return null;
    }
}
