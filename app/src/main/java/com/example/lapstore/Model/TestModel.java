package com.example.lapstore.Model;

public class TestModel {
    String ten, matkhau;

    public TestModel(String ten, String matkhau) {
        this.ten = ten;
        this.matkhau = matkhau;
    }

    public TestModel() {
    }



    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
