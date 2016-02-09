package com.tuccro.notes.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.tuccro.notes.R;
import com.tuccro.notes.entity.Note;
import com.tuccro.notes.util.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tuccro on 1/31/16.
 */
public abstract class NoteBaseActivity extends AppCompatActivity {

    public static final int REQUEST_GET_IMAGE = 123;

    public static final int REQUEST_CODE = 1;
    public static final String EXTRA_NOTE_ID = "note_id";

    protected Note note;

    private Uri selectedImageUri;

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
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_add_image:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), REQUEST_GET_IMAGE);
                break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // TODO: 2/9/16 uncomment when photo function will be finished
//        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GET_IMAGE && resultCode == Activity.RESULT_OK) {

            selectedImageUri = data.getData();

            Bitmap bitmap = null;
            try {
                InputStream is = getContentResolver().openInputStream(selectedImageUri);
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bitmap != null) {
                String copyPhotoPath = FileUtils.addNewImageToAppDir(this, bitmap);
            }
        }
    }
}
