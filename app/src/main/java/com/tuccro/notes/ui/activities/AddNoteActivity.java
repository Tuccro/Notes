package com.tuccro.notes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tuccro.notes.entity.Note;

import io.realm.Realm;

/**
 * Created by tuccro on 1/31/16.
 */
public class AddNoteActivity extends NoteBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            editText.setText(sharedText);
            editText.setSelection(editText.getText().length());
        }
    }

    @Override
    protected long saveNote() {
        Realm realm = Realm.getInstance(this);

        Note note = new Note();
        note.setCreateTimeInMillis(System.currentTimeMillis());

        note.setText(editText.getText().toString());

        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
        realm.close();

        return note.getCreateTimeInMillis();
    }
}
