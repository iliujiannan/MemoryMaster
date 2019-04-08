package com.zym.memorymaster.util;


import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.util.Log;
import com.zym.memorymaster.base.MyApplication;
import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.dao.greendao.DaoSession;
import com.zym.memorymaster.dao.greendao.LocalBookContentDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by 12390 on 2019/3/4.
 */
public class EbbinghausUtil {

    public static List<LocalBookContent> getTodayWordsList() {
        List<LocalBookContent> todayWordsList = new Vector<>();
        DaoSession session = MyApplication.getDaoSession();
        QueryBuilder<LocalBookContent> queryBuilder = session.
                queryBuilder(LocalBookContent.class);
        List<LocalBookContent> sourceList = queryBuilder.list();
        for (LocalBookContent bookContent : sourceList) {
            if (isTodayWord(bookContent)) {
                todayWordsList.add(bookContent);
            }
        }
        return todayWordsList;

    }


    private static boolean isTodayWord(LocalBookContent bookContent) {
        int baseTime = 0;
        switch (bookContent.getRememberAmount()) {
            case 0:
                baseTime = 0;
                break;
            case 1:
                baseTime = 12;
                break;
            case 2:
                baseTime = 36;
                break;
            case 3:
                baseTime = 84;
                break;
            case 4:
                baseTime = 148;
                break;
            case 5:
                baseTime = 296;
                break;
            case 6:
                baseTime = 592;
                break;
            case 7:
                return false;
        }
        int distance = MyDateUtil
                .getDistanceTime(bookContent.getStartRememberTime()
                        , MyDateUtil.dateToString(new Date()));
        return distance <= (12*60 - baseTime * 60);

    }

    public static void updateDBAfterRemembered(LocalBookContent bookContent) {
        DaoSession daoSession = MyApplication.getDaoSession();
        bookContent.setRememberAmount(bookContent.getRememberAmount()+1);
        daoSession.update(bookContent);

    }

    public static void insertWordsToDB(List<LocalBookContent> bookContentList) {
        DaoSession daoSession = MyApplication.getDaoSession();
        for (LocalBookContent content : bookContentList) {
            content.setStartRememberTime(MyDateUtil.getNextDay(content.getRootChapter()-1));
            content.setBookContentId(null);
            daoSession.getLocalBookContentDao().insertOrReplace(content);
        }
    }
}
