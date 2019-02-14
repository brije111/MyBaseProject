/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.brijeshkum.mybaseproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.brijeshkum.mybaseproject.db.dao.CountryDao;
import com.example.brijeshkum.mybaseproject.db.model.Country;


/**
 * The Room database that contains the Users table
 */
@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //private static volatile AppDatabase INSTANCE;
    public abstract CountryDao countryDao();

}
