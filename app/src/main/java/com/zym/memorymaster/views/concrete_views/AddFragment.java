package com.zym.memorymaster.views.concrete_views;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.zym.memorymaster.R;
import com.zym.memorymaster.base.BaseFragment;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.util.DataBaseUtil;
import com.zym.memorymaster.views.adapters.FragmentAddRVAdapter;

import java.util.*;

import static android.app.Activity.RESULT_OK;
import static com.zym.memorymaster.views.adapters.FragmentAddRVAdapter.CHOOSE_PHOTO;

/**
 * Created by 12390 on 2019/2/27.
 */
public class AddFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView vRv;
    private FragmentAddRVAdapter vRvAdapter;
    private TextView btVAdd;
    private TextView btVDelete;
    private TextView btCompleted;
    public static Integer tempPosition = -1;
    public static Map<Integer, List<String>> paths = new HashMap<Integer, List<String>>();
    @Override
    public int getContentViewId() {
        return R.layout.fragment_add;
    }

    @Override
    protected void initAllMembersView(View rootView) {
        vRv = (RecyclerView) rootView.findViewById(R.id.fragment_add_rv);
        vRvAdapter = new FragmentAddRVAdapter(this);
        vRv.setAdapter(vRvAdapter);
        vRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        vRvAdapter.addItem();


        btVAdd = (TextView) rootView.findViewById(R.id.fragment_add_bt_v_add);
        btVAdd.setOnClickListener(this);

        btVDelete = (TextView) rootView.findViewById(R.id.fragment_add_bt_v_delete);
        btVDelete.setOnClickListener(this);

        btCompleted = (TextView) rootView.findViewById(R.id.fragment_add_bt_completed);
        btCompleted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_add_bt_v_add:
                addItemInRv();
                break;
            case R.id.fragment_add_bt_v_delete:
                deleteItemInRv();
                break;
            case R.id.fragment_add_bt_completed:
                updateToDB(paths);
                showMessageWithoutLooper("新建成功");
                break;
        }
    }

    private void updateToDB(Map<Integer, List<String>> paths) {
        List<LocalBookContent> contents = new Vector<>();
        Set<Integer> set = paths.keySet();
        for (Integer key: set) {
            List<String> pathList = paths.get(key);
            for(String path: pathList){
                LocalBookContent bookContent = new LocalBookContent();
                bookContent.setContentQ(path);
                bookContent.setRememberAmount(0);
                bookContent.setRootChapter(key+1);
                bookContent.setContentHint("img");
                contents.add(bookContent);
            }
        }
        DataBaseUtil.insertWordsToDB(contents);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CHOOSE_PHOTO&&resultCode==RESULT_OK) {
            handleImageOnKitKat(data);
        }
    }

    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getActivity(), uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri, null);
        }
        displayImage(imagePath);

    }

    private void displayImage(String imagePath) {
        if(imagePath!=null){
            if(paths.get(tempPosition)==null){
                paths.put(tempPosition, new Vector<String>());
            }
            paths.get(tempPosition).add(imagePath);

            vRvAdapter.onPhotoSelected(imagePath);

        }else{
            showMessage("图片加载失败");
        }
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        Cursor cursor = getActivity()
                .getContentResolver()
                .query(externalContentUri, null, selection, null, null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void deleteItemInRv() {
        vRvAdapter.deleteItem();
    }

    private void addItemInRv() {
        vRvAdapter.addItem();
    }
}
