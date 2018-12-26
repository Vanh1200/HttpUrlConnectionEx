package com.example.vanh1200.httpurlconnectionex;

public class Repo {
    private int mId;
    private String mName;
    private String mFullName;

    public Repo(int id, String name, String fullName) {
        mId = id;
        mName = name;
        mFullName = fullName;
    }

    public Repo() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }
}
