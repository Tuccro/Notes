package com.tuccro.notes.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tuccro.notes.R;
import com.tuccro.notes.entity.Note;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    protected void onFabClick() {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivityForResult(intent, AddNoteActivity.REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NoteBaseActivity.REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:

                    final long id = data.getLongExtra(NoteBaseActivity.EXTRA_NOTE_ID, 0);

                    Snackbar.make(getCurrentFocus(), "Успех", Snackbar.LENGTH_LONG)
//                            .setAction(android.R.string.cancel, new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (id != 0) {
//
//                                        Realm realm = Realm.getInstance(MainActivity.this);
////                                        realm.cancelTransaction();
//                                    }
//                                }
//                            })
                            .show();

                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
    }
}
