package com.tuccro.notes.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by tuccro on 1/30/16.
 */
public class Note extends RealmObject {

    @PrimaryKey
    private long createTimeInMillis;

    private String title;

    @Required
    private String text;

    public long getCreateTimeInMillis() {
        return createTimeInMillis;
    }

    public void setCreateTimeInMillis(long createTimeInMillis) {
        this.createTimeInMillis = createTimeInMillis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
