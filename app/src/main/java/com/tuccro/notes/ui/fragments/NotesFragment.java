package com.tuccro.notes.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuccro.notes.R;
import com.tuccro.notes.entity.Note;
import com.tuccro.notes.ui.adapters.NotesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotesFragment extends Fragment {

    @Bind(R.id.list)
    RecyclerView list;

    List<Note> noteList;
    NotesAdapter notesAdapter;

    public NotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);
        initNotesList();

        return view;
    }

    private void initNotesList() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList, getActivity());
        list.setAdapter(notesAdapter);
    }

    private void fillList() {

        Realm realm = Realm.getInstance(getActivity());

        noteList.clear();
        noteList.addAll(realm.where(Note.class).findAll());

        Collections.reverse(noteList);
        notesAdapter.notifyDataSetChanged();

        list.setAdapter(notesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        fillList();
    }
}
