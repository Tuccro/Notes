package com.tuccro.notes.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.tuccro.notes.R;
import com.tuccro.notes.entity.Note;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tuccro on 1/31/16.
 */
public abstract class NoteBaseActivity extends AppCompatActivity {


    public static final int REQUEST_CODE = 1;
    public static final String EXTRA_NOTE_ID = "note_id";

    protected Note note;

    @Bind(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_note);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {

        if (!editText.getText().toString().isEmpty()) {
            returnResult(saveNote());
        }

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract long saveNote();

    protected void returnResult(long id) {

        if (id != 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_NOTE_ID, id);
            setResult(RESULT_OK, intent);
        } else {
            setResult(RESULT_CANCELED);
        }

        finish();
    }
}
