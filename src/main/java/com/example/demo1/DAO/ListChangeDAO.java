package com.example.demo1.DAO;

import com.example.demo1.Model.Changes;

import java.util.ArrayList;
import java.util.List;

public class ListChangeDAO {
    private List<Changes> changes;

    public ListChangeDAO(int size) {
        changes = new ArrayList<>();
    }


    public List<Changes> getAllTasks() {
        return changes;
    }


    public Changes getTaskById(int id) {
        for (Changes task : changes) {
            if (task.getId_Change() == id) {
                return task;
            }
        }
        return null;
    }


    public void addTask(Changes change) {
        this.changes.add(change);
    }

    public void updateTask(Changes change) {
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).getId_Change() == change.getId_Change()) {
                changes.set(i, change);
                return;
            }
        }
    }

    public void deleteTask(int id) {
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).getId_Change() == id) {
                changes.remove(i);
                return;
            }
        }
    }
}
