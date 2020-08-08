package com.numad.numadsu_alangrinberg;

import androidx.annotation.NonNull;

public class LinkItem {
    private String name;
    private String linkURL;

    public LinkItem(String name, String linkURL) {
        this.name = name;
        this.linkURL = linkURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    @NonNull
    @Override
    public String toString() {
        return linkURL;
    }
}
