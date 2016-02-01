package com.tuccro.notes.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuccro.notes.R;
import com.tuccro.notes.entity.Note;
import com.tuccro.notes.ui.activities.EditNoteActivity;
import com.tuccro.notes.util.DateFormatter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tuccro on 1/31/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    List<Note> noteList;
    Activity activity;

    public NotesAdapter(List<Note> noteList, Activity activity) {
        this.noteList = noteList;
        this.activity = activity;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_entry, parent, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {

        final Note note = noteList.get(position);

        String text = note.getTitle() != null ? note.getTitle() : note.getText() != null ? note.getText() : "";

        holder.title.setText(text);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditNoteActivity.class);
                intent.putExtra(EditNoteActivity.EXTRA_NOTE_ID, note.getCreateTime());
                activity.startActivity(intent);
            }
        });

        DateFormatter dateFormatter = new DateFormatter(note.getCreateTime(), activity);
        holder.date.setText(dateFormatter.getDateOrDay());
    }

    @Override
    public int getItemCount() {
        return noteList != null ? noteList.size() : 0;
    }

    protected class NoteHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cardView)
        CardView cardView;

        @Bind(R.id.title)
        TextView title;

        @Bind(R.id.textDate)
        TextView date;

        @Bind(R.id.textLabelAdded)
        TextView labelAdded;

        @Bind(R.id.textLabelEdited)
        TextView labelEdited;

        public NoteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
