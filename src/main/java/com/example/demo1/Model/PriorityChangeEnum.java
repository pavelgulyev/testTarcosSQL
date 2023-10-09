package com.example.demo1.Model;

public enum PriorityChangeEnum {
    high( "Высокий"),
    middle("Средний"),
    low( "Низкий");

    private String text;
    private PriorityChangeEnum(String text) {
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
