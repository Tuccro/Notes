package com.tuccro.notes.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tuccro.notes.entity.Note;

import io.realm.Realm;

/**
 * Created by tuccro on 1/31/16.
 */
public class EditNoteActivity extends NoteBaseActivity {

    private long noteId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(EXTRA_NOTE_ID)) {
            noteId = getIntent().getLongExtra(EXTRA_NOTE_ID, 0);
        } else {
            finish();
        }

        Realm realm = Realm.getInstance(this);
        note = realm.where(Note.class).equalTo("createTime", noteId).findFirst();
        fillFields();
    }

    private void fillFields() {
        if (note != null) {
            editText.setText(note.getText() != null ? note.getText() : "");
            editText.setSelection(editText.getText().length());
        }
    }

    @Override
    protected long saveNote() {
        Realm realm = Realm.getInstance(this);

        realm.beginTransaction();

        note.setText(editText.getText().toString());
        realm.commitTransaction();
        realm.close();

        return noteId;
    }
}
