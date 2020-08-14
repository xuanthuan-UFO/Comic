package com.xuanthuan.comic.object;

public class Object_Library {
    String ten, urrlimg, idtruyen;
    int id;

    public Object_Library(String ten, String urrlimg, String idtruyen, int id) {
        this.ten = ten;
        this.urrlimg = urrlimg;
        this.idtruyen = idtruyen;
        this.id = id;
    }

    public String getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(String idtruyen) {
        this.idtruyen = idtruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUrrlimg() {
        return urrlimg;
    }

    public void setUrrlimg(String urrlimg) {
        this.urrlimg = urrlimg;
    }
}
