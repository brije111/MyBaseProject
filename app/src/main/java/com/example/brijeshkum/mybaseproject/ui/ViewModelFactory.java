package com.example.brijeshkum.mybaseproject.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.NonNull;

import com.example.brijeshkum.mybaseproject.db.Repository;
import com.example.brijeshkum.mybaseproject.db.RepositoryInterface;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final RepositoryInterface repository;
    public ViewModelFactory(RepositoryInterface repositoryInterface) {
        this.repository = repositoryInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }
        return null;
    }
}
