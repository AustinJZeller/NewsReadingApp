package com.austinzeller.newsreadingapp;

class Information {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mUrl;

    Information(String title, String section, String date, String url) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
    }

    String getTitle() {
        return mTitle;
    }

    String getSection() {
        return mSection;
    }

    String getDate() {
        return mDate;
    }

    String getUrl() { return mUrl; }
}
