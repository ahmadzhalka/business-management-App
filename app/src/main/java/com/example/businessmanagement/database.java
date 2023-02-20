package com.example.businessmanagement;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class database {
    private static Context context;

    public static void updateAdmin(String id) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(id).setValue(id);
    }

    public static void checkIFnoadmin() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot adminsSnapshot : dataSnapshot.getChildren()) {
                    Log.w("pttt",   "ff");
                    String value=(adminsSnapshot.getKey().toString());
                     if (value != null) {
                         DataManneger.setAmdminid(value);
                      } else {
                          DataManneger.setAmdminid(null);
                      }

                      Log.w("pttt", value + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }
        });
    }

    public static void updateWorker(Worker w,String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("workers").child("" + w.getId()).setValue(w);
    }

    public static void updatePDD(String s,String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("orders").child("" + DataManneger.getWorker().getId()).setValue(s);
    }

    public static void readPDF(String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference( "adminid");
        ArrayList<String> url = new ArrayList<>();
        ref.child(adminId).child("orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot workerSnapshot : dataSnapshot.getChildren()) {
                    String s = workerSnapshot.getValue(String.class);
                    Log.w("pttt", "Failed to read value." + s);
                    url.add(s);
                }
                DataManneger.setOrders(url);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }

        });
    }


    public static void readWorker(String id,String adminId) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("workers").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Worker c = dataSnapshot.getValue(Worker.class);
                    DataManneger.setWorker(c);
                } else {
                    Log.w("pttt", "ffff");
                    Worker c = new Worker();
                    DataManneger.setWorker(c);
                }

            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());

            }
        });
    }

    public static void updateShift(String id, Shifts shift,String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("workers").child(id).child("shifts").child("" + shift.getCount()).setValue(shift);
    }

    public static void getShift(String id, String count,String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("workers").child(id).child("shifts").child("" + count).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Shifts s = snapshot.getValue(Shifts.class);
                    DataManneger.setShif(s);
                    Log.w("pttt", "yes");

                } else {
                    Shifts c = new Shifts();
                    DataManneger.setShif(c);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void readShifts(String id,String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ArrayList<Shifts> c = new ArrayList<>();
        ref.child(adminId).child("workers").child(id).child("shifts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot shifttSnapshot : dataSnapshot.getChildren()) {
                    c.add(shifttSnapshot.getValue(Shifts.class));
                }
                DataManneger.setSh(c);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }

        });


    }

    public static void readWorkers(String adminId) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ArrayList<Integer> id = new ArrayList<>();
        ref.child(adminId).child("workers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot workerSnapshot : dataSnapshot.getChildren()) {
                    int w = Integer.parseInt(workerSnapshot.getKey());
                    id.add(w);
                }
                DataManneger.setId(id);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("pttt", "Failed to read value.", error.toException());
            }

        });
    }

    protected static void UploadFile(Uri filepath,Context context,String adminId) {
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (filepath != null) {

            Date dateObject = new Date(System.currentTimeMillis());
            String formattedDate = formatDate(dateObject);

            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference sref = storageReference.child("" + DataManneger.getWorker().getId()+ ".pdf");

            sref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            updatePDD(String.valueOf(DataManneger.getWorker().getId()),adminId);
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() )/ taskSnapshot
                                    .getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });


        }
    }
    private static String formatDate(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    public static void download(Context context,String s) {
        FirebaseStorage firebaseStorage;
        StorageReference storageReference;
        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference ref=storageReference.child(s+".pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                downloadFile(context,s,".pdf",DIRECTORY_DOWNLOADS,url);

            }
        });
    }

    public static void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }
    public static void deletePDF(String k,String adminId){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("adminid");
        ref.child(adminId).child("orders").child(k).removeValue();
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        StorageReference sref = storageReference.child(k+".pdf");
        sref.delete();

    }
}











