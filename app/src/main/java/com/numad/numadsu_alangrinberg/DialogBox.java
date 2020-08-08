package com.numad.numadsu_alangrinberg;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class DialogBox extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editURL;
    private DialogBoxListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        builder.setView(view)
                .setTitle("Add URL").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }

        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                listener.onDialogPositiveClick(DialogBox.this);
                String name = editTextName.getText().toString();
                String link = editURL.getText().toString();
                listener.applyTextValues(name, link);

            }
        });

        editTextName = view.findViewById(R.id.edit_name);
        editURL = view.findViewById(R.id.edit_link);
        editURL.setText(R.string.link_starter);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogBoxListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogBoxListener");
        }
    }

    public interface DialogBoxListener {
        void applyTextValues(String name, String link);
        void onDialogPositiveClick(DialogBox dialog);
    }
}
