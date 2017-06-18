
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

package com.example.android.recipe.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.recipe.R;
import com.example.android.recipe.adapters.RecipeDetailAdapter;
import com.example.android.recipe.pojo.Recipe;
import com.example.android.recipe.pojo.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Project "Baking App" - Created by Nikos Vaggalis as part of the Udacity Android Developer Nanodegree on 13/6/2017.
 */

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailAdapter.ListItemClickListener,RecipeStepDetailFragment.ListItemClickListener{

    static String ALL_RECIPES="All_Recipes";
    static String SELECTED_RECIPES="Selected_Recipes";
    static String SELECTED_STEPS="Selected_Steps";
    static String SELECTED_INDEX="Selected_Index";
    static String STACK_RECIPE_DETAIL="STACK_RECIPE_DETAIL";
    static String STACK_RECIPE_STEP_DETAIL="STACK_RECIPE_STEP_DETAIL";


    private   ArrayList<Recipe> recipe;
    String recipeName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_recipe_detail);

        if (savedInstanceState == null) {

            Bundle selectedRecipeBundle = getIntent().getExtras();

            recipe = new ArrayList<>();
            recipe = selectedRecipeBundle.getParcelableArrayList(SELECTED_RECIPES);
            recipeName = recipe.get(0).getName();

     final RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(STACK_RECIPE_DETAIL)
                    .commit();

            if (findViewById(R.id.recipe_linear_layout).getTag()!=null && findViewById(R.id.recipe_linear_layout).getTag().equals("tablet-land")) {

                final RecipeStepDetailFragment fragment2 = new RecipeStepDetailFragment();
                fragment2.setArguments(selectedRecipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, fragment2).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                        .commit();

            }



    } else {
        recipeName= savedInstanceState.getString("Title");
    }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(recipeName);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                if (findViewById(R.id.fragment_container2)==null) {
                    if (fm.getBackStackEntryCount() > 1) {
                        //go back to "Recipe Detail" screen
                        fm.popBackStack(STACK_RECIPE_DETAIL, 0);
                    } else if (fm.getBackStackEntryCount() > 0) {
                        //go back to "Recipe" screen
                        finish();

                    }


                }
                else {

                    //go back to "Recipe" screen
                    finish();

                }

            }
        });
    }





    @Override
    public void onListItemClick(List<Step> stepsOut, int selectedItemIndex,String recipeName) {


        final RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        getSupportActionBar().setTitle(recipeName);

        Bundle stepBundle = new Bundle();
        stepBundle.putParcelableArrayList(SELECTED_STEPS,(ArrayList<Step>) stepsOut);
        stepBundle.putInt(SELECTED_INDEX,selectedItemIndex);
        stepBundle.putString("Title",recipeName);
        fragment.setArguments(stepBundle);

        if (findViewById(R.id.recipe_linear_layout).getTag()!=null && findViewById(R.id.recipe_linear_layout).getTag().equals("tablet-land")) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container2, fragment).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                    .commit();

        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(STACK_RECIPE_STEP_DETAIL)
                    .commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
        outState.putString("Title",recipeName);
    }





}
