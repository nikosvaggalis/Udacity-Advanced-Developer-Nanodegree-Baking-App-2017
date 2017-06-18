
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

package com.example.android.recipe.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.example.android.recipe.R;
import java.util.List;

import static com.example.android.recipe.widget.BakingWidgetProvider.REMOTEVIEW_BUNDLE;
import static com.example.android.recipe.widget.BakingWidgetProvider.REMOTEVIEW_INGREDIENT_LIST;
import static com.example.android.recipe.widget.BakingWidgetProvider.ingredientsList;

/**
 * Project "Baking App" - Created by Nikos Vaggalis as part of the Udacity Android Developer Nanodegree on 13/6/2017.
 */

public class GridWidgetService extends RemoteViewsService {
    List<String> remoteViewingredientsList;

        @Override
        public RemoteViewsFactory onGetViewFactory(Intent intent) {
            return new GridRemoteViewsFactory(this.getApplicationContext(),intent);
        }


    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context mContext = null;

        public GridRemoteViewsFactory(Context context,Intent intent) {
            mContext = context;

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            remoteViewingredientsList = ingredientsList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {

            return remoteViewingredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_grid_view_item);

            views.setTextViewText(R.id.widget_grid_view_item, remoteViewingredientsList.get(position));

            Intent fillInIntent = new Intent();
            //fillInIntent.putExtras(extras);
            views.setOnClickFillInIntent(R.id.widget_grid_view_item, fillInIntent);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }




        }


    }

