package com.example.jejavi.myapplication;

import android.view.View;

/**
 * Created by jejavi on 13/11/17.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
