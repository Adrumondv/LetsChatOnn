package com.rename.rename1.letschaton.data;

import android.content.Context;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rename.rename1.letschaton.models.User;

public class UploadPhoto {

    private Context context;
    private CurrentUser currentUser = new CurrentUser();

    public UploadPhoto(Context context) {
        this.context = context;
    }

    public void toFirebase(String path){

        String folder = new EmailProcessor().sanitizedEmail(currentUser.email()) + "/";
        String photoName = "avatar.jpeg";
        String baseUrl = "gs://letschaton-6774c.appspot.com/avatars/";
        String refUrl = baseUrl + folder + photoName;
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(refUrl);
        storageReference.putFile(Uri.parse(path)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String[] fullUrl = taskSnapshot.getStorage().getDownloadUrl().toString().split("&token");
                    String url = fullUrl[0];

                    new PhotoPreference(context).photoSave(url);

                User user = new User();
                user.setName(currentUser.getCurrentUser().getDisplayName());
                user.setEmail(currentUser.email());
                user.setUid(currentUser.uid());
                user.setPhoto(url);
                String key = new EmailProcessor().sanitizedEmail(currentUser.email());
                new Nodes().user(key).setValue(user);

            }
        });

    }


}
