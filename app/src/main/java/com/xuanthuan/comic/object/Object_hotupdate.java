package com.xuanthuan.comic.object;

import java.util.ArrayList;

public class Object_hotupdate extends ArrayList<String> {
    String hinh;
    String tentruyen;

    public Object_hotupdate(String hinh, String tentruyen) {
        this.hinh = hinh;
        this.tentruyen = tentruyen;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

}
