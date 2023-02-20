package com.example.businessmanagement;

import static android.content.ContentValues.TAG;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.Manifest;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView lngRV;
    private EditText addEdt;
    private Button addBtn;
    private HashMap<String,String> lngList;
    private orderRVAdapter lngRVAdapter;
    private EditText idEdtAdd1;
    private Button createPdf;
    private Button createorder;

    private String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/order.pdf";
    private File file = new File(stringFilePath);
    private Uri filepath;

    private final int PICK_PDF_CODE = 2342;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_fregmentorder);
        findView();
        initView();
        lngList = new HashMap<>();
        lngRVAdapter = new orderRVAdapter(lngList,this);
        lngRV.setAdapter(lngRVAdapter);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

       // download();
        //selectFile();

    }

    private void initView() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem(addEdt.getText().toString(),idEdtAdd1.getText().toString());


            }
        });
        createorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    selectFile();

            }
        });
    }

    private void findView() {
        lngRV = findViewById(R.id.idRVItems);
        addEdt = findViewById(R.id.idEdtAdd);
        addBtn = findViewById(R.id.idBtnAdd);
        idEdtAdd1=findViewById(R.id.idEdtAdd1);
        createPdf=findViewById(R.id.createPdf);
        createorder=findViewById(R.id.createorder);
    }


    private void addItem(String item,String value) {

        if (!item.isEmpty()) {
            lngList.put(item, value);
            lngRVAdapter.notifyDataSetChanged();
        }
    }
    public void createMyPDF(View view) throws IOException, DocumentException {
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Paint paint = new Paint();
        //String stringPDF = addEdt.getText().toString();
        Document document = new Document();

        int x = 10, y = 25;
        page.getCanvas().drawText("order by : "+DataManneger.getWorker().getId(),x,y, paint);
        y+=paint.descent()-paint.ascent();


        Object[] x1=(lngList.keySet().toArray());
        for (int i = 0; i <lngList.size() ; i++) {
            String stringPDF=""+ (CharSequence) x1[i]+" "+lngList.get(x1[i]);
            page.getCanvas().drawText(stringPDF,x,y, paint);

            y+=paint.descent()-paint.ascent();
        }


        pdfDocument.finishPage(page);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));

        }
        catch (Exception e){
            e.printStackTrace();
            idEdtAdd1.setText("Error in Creating");
        }
        pdfDocument.close();

       // database.uploadPDF("/Download/ProgrammerWorld.pdf");
    }
    public void selectFile(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Document"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (data.getData() != null) {
                filepath=data.getData();
                database.UploadFile(filepath,this,DataManneger.getAmdminid());
            } else
                Toast.makeText(this, "NO FILE CHOSEN", Toast.LENGTH_SHORT).show();

        }
    }



}


