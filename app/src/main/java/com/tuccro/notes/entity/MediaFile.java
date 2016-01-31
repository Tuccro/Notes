package com.tuccro.notes.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tuccro on 1/31/16.
 */
public class MediaFile extends RealmObject {

    @PrimaryKey
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
