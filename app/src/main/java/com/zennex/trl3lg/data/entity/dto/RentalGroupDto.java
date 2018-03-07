package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 26.07.17.
 */

public class RentalGroupDto {

    @SerializedName("id")
    private String mId;
    @SerializedName("parent_id")
    private String mParentId;
    @SerializedName("active")
    private String mActive;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("path")
    private String mPath;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String parentId) {
        mParentId = parentId;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

}
