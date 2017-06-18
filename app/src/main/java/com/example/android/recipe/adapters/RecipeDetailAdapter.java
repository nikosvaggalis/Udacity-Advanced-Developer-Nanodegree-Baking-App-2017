
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

package com.example.android.recipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.recipe.R;
import com.example.android.recipe.pojo.Recipe;
import com.example.android.recipe.pojo.Step;
import java.util.List;

/**
 * Project "Baking App" - Created by Nikos Vaggalis as part of the Udacity Android Developer Nanodegree on 13/6/2017.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecyclerViewHolder> {

    List<Step> lSteps;
    private  String recipeName;

    final private ListItemClickListener lOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(List<Step> stepsOut,int clickedItemIndex,String recipeName);
    }

    public RecipeDetailAdapter(ListItemClickListener listener) {
        lOnClickListener =listener;
    }


    public void setMasterRecipeData(List<Recipe> recipesIn, Context context) {
        //lSteps = recipesIn;
        lSteps= recipesIn.get(0).getSteps();
        recipeName=recipesIn.get(0).getName();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();

        int layoutIdForListItem = R.layout.recipe_detail_cardview_items;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
       holder.textRecyclerView.setText(lSteps.get(position).getId()+". "+ lSteps.get(position).getShortDescription());

    }

    @Override
    public int getItemCount() {

        return lSteps !=null ? lSteps.size():0 ;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textRecyclerView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            textRecyclerView = (TextView) itemView.findViewById(R.id.shortDescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(lSteps,clickedPosition,recipeName);
        }

    }
}
