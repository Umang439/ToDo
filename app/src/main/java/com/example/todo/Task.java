package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.todo.databinding.ActivityTaskBinding;

public class Task extends AppCompatActivity {
    private ActivityTaskBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
}