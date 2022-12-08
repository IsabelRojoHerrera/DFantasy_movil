package com.example.tab_fragmentos;

import com.google.gson.annotations.SerializedName;

public class ConfirmResponse {

    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
