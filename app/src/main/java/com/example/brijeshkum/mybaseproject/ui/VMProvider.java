package com.example.brijeshkum.mybaseproject.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.brijeshkum.mybaseproject.db.Repository;

import javax.inject.Inject;

public class VMProvider implements ViewModelProvider.Factory {

    Repository repository;
    public VMProvider(Repository repository) {
        this.repository=repository;
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
