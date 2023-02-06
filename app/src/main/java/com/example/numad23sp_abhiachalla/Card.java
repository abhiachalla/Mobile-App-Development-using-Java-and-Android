package com.example.numad23sp_abhiachalla;

public class Card implements LinkListener {

    private final String url;

    public Card(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onItemClick(int position) {

    }
}
