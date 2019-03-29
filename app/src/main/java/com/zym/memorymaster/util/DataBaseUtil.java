package com.zym.memorymaster.util;


import android.util.Log;
import com.zym.memorymaster.base.MyApplication;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.dao.greendao.DaoSession;
import com.zym.memorymaster.dao.greendao.LocalBookContentDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/3/4.
 */
public class DataBaseUtil {

    public static List<LocalBookContent> getTodayWordsList(){
        List<LocalBookContent> todayWordsList = new Vector<>();
        DaoSession session = MyApplication.getDaoSession();
        QueryBuilder<LocalBookContent> queryBuilder = session.
                queryBuilder(LocalBookContent.class)
                .where(LocalBookContentDao.Properties.RootChapter.eq(1));
        List<LocalBookContent> sourceList = queryBuilder.list();
        for (LocalBookContent bookContent:sourceList){
            if(isTodayWord(bookContent)){
                todayWordsList.add(bookContent);
            }
        }
        return todayWordsList;

    }


    private static boolean isTodayWord(LocalBookContent bookContent){
        return true;
    }
    public static void insertWordsToDB(List<LocalBookContent> bookContentList){
        DaoSession daoSession = MyApplication.getDaoSession();
        for (LocalBookContent content: bookContentList) {
            content.setBookContentId(null);
            daoSession.getLocalBookContentDao().insert(content);
        }
    }
}
