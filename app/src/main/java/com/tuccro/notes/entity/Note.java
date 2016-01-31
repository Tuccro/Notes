package com.tuccro.notes.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by tuccro on 1/30/16.
 */
public class Note extends RealmObject {

    @PrimaryKey
    private long createTime;

    private long lastEditTime;

    private String title;

    @Required
    private String text;

    /**
     * list of links to images and audio
     */
    private RealmList<MediaFile> images;
    private RealmList<MediaFile> audio;

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public RealmList<MediaFile> getImages() {
        return images;
    }

    public void setImages(RealmList<MediaFile> images) {
        this.images = images;
    }

    public RealmList<MediaFile> getAudio() {
        return audio;
    }

    public void setAudio(RealmList<MediaFile> audio) {
        this.audio = audio;
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
