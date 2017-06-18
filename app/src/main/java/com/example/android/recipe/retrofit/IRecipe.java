
/*
 * Copyright 2017 Nikos Vaggalis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.recipe.retrofit;

import com.example.android.recipe.pojo.Recipe;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Project "Baking App" - Created by Nikos Vaggalis as part of the Udacity Android Developer Nanodegree on 27-May-17.
 */


public interface IRecipe {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}