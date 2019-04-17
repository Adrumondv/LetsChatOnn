package com.rename.rename1.letschaton.views.main.drawer;

import android.content.Context;

import com.rename.rename1.letschaton.data.PhotoPreference;

public class PhotoValidation {

    private Context context;
    private PhotoCallback callback;

    public PhotoValidation(Context context, PhotoCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void validate(){

        String url = new PhotoPreference(context).getPhoto();
        if (url != null){
            callback.photoAvailable(url);
        }else {
            callback.emptyPhoto();

        }

    }


}
