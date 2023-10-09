package com.example.demo1.Model;

public enum StatusChangeEnum {
    st1( "Запрошено"),
    st2("в Процессе"),
    st3( "Завершено");
    private String text;
    private StatusChangeEnum(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    @Override
    public String toString() {
        return this.text;
    }
}
