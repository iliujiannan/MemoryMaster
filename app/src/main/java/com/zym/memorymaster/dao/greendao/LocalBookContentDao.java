package com.zym.memorymaster.dao.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zym.memorymaster.dao.entities.LocalBookContent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "book_content".
*/
public class LocalBookContentDao extends AbstractDao<LocalBookContent, Long> {

    public static final String TABLENAME = "book_content";

    /**
     * Properties of entity LocalBookContent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookContentId = new Property(0, Long.class, "bookContentId", true, "_id");
        public final static Property ContentQ = new Property(1, String.class, "contentQ", false, "content_q");
        public final static Property ContentA = new Property(2, String.class, "contentA", false, "content_a");
        public final static Property ContentHint = new Property(3, String.class, "contentHint", false, "content_hint");
        public final static Property RootBookId = new Property(4, Integer.class, "rootBookId", false, "ROOT_BOOK_ID");
        public final static Property StartRememberTime = new Property(5, String.class, "startRememberTime", false, "last_remember_time");
        public final static Property RememberAmount = new Property(6, Integer.class, "rememberAmount", false, "remember_amount");
        public final static Property RootChapter = new Property(7, Integer.class, "rootChapter", false, "root_chapter");
        public final static Property IsOkOnMorning = new Property(8, Integer.class, "isOkOnMorning", false, "is_ok_on_morning");
        public final static Property IsOkOnEve = new Property(9, Integer.class, "isOkOnEve", false, "is_ok_on_eve");
    }


    public LocalBookContentDao(DaoConfig config) {
        super(config);
    }
    
    public LocalBookContentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"book_content\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: bookContentId
                "\"content_q\" TEXT," + // 1: contentQ
                "\"content_a\" TEXT," + // 2: contentA
                "\"content_hint\" TEXT," + // 3: contentHint
                "\"ROOT_BOOK_ID\" INTEGER," + // 4: rootBookId
                "\"last_remember_time\" TEXT," + // 5: startRememberTime
                "\"remember_amount\" INTEGER," + // 6: rememberAmount
                "\"root_chapter\" INTEGER," + // 7: rootChapter
                "\"is_ok_on_morning\" INTEGER," + // 8: isOkOnMorning
                "\"is_ok_on_eve\" INTEGER);"); // 9: isOkOnEve
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"book_content\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LocalBookContent entity) {
        stmt.clearBindings();
 
        Long bookContentId = entity.getBookContentId();
        if (bookContentId != null) {
            stmt.bindLong(1, bookContentId);
        }
 
        String contentQ = entity.getContentQ();
        if (contentQ != null) {
            stmt.bindString(2, contentQ);
        }
 
        String contentA = entity.getContentA();
        if (contentA != null) {
            stmt.bindString(3, contentA);
        }
 
        String contentHint = entity.getContentHint();
        if (contentHint != null) {
            stmt.bindString(4, contentHint);
        }
 
        Integer rootBookId = entity.getRootBookId();
        if (rootBookId != null) {
            stmt.bindLong(5, rootBookId);
        }
 
        String startRememberTime = entity.getStartRememberTime();
        if (startRememberTime != null) {
            stmt.bindString(6, startRememberTime);
        }
 
        Integer rememberAmount = entity.getRememberAmount();
        if (rememberAmount != null) {
            stmt.bindLong(7, rememberAmount);
        }
 
        Integer rootChapter = entity.getRootChapter();
        if (rootChapter != null) {
            stmt.bindLong(8, rootChapter);
        }
 
        Integer isOkOnMorning = entity.getIsOkOnMorning();
        if (isOkOnMorning != null) {
            stmt.bindLong(9, isOkOnMorning);
        }
 
        Integer isOkOnEve = entity.getIsOkOnEve();
        if (isOkOnEve != null) {
            stmt.bindLong(10, isOkOnEve);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LocalBookContent entity) {
        stmt.clearBindings();
 
        Long bookContentId = entity.getBookContentId();
        if (bookContentId != null) {
            stmt.bindLong(1, bookContentId);
        }
 
        String contentQ = entity.getContentQ();
        if (contentQ != null) {
            stmt.bindString(2, contentQ);
        }
 
        String contentA = entity.getContentA();
        if (contentA != null) {
            stmt.bindString(3, contentA);
        }
 
        String contentHint = entity.getContentHint();
        if (contentHint != null) {
            stmt.bindString(4, contentHint);
        }
 
        Integer rootBookId = entity.getRootBookId();
        if (rootBookId != null) {
            stmt.bindLong(5, rootBookId);
        }
 
        String startRememberTime = entity.getStartRememberTime();
        if (startRememberTime != null) {
            stmt.bindString(6, startRememberTime);
        }
 
        Integer rememberAmount = entity.getRememberAmount();
        if (rememberAmount != null) {
            stmt.bindLong(7, rememberAmount);
        }
 
        Integer rootChapter = entity.getRootChapter();
        if (rootChapter != null) {
            stmt.bindLong(8, rootChapter);
        }
 
        Integer isOkOnMorning = entity.getIsOkOnMorning();
        if (isOkOnMorning != null) {
            stmt.bindLong(9, isOkOnMorning);
        }
 
        Integer isOkOnEve = entity.getIsOkOnEve();
        if (isOkOnEve != null) {
            stmt.bindLong(10, isOkOnEve);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LocalBookContent readEntity(Cursor cursor, int offset) {
        LocalBookContent entity = new LocalBookContent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // bookContentId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // contentQ
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // contentA
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // contentHint
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // rootBookId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // startRememberTime
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // rememberAmount
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // rootChapter
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // isOkOnMorning
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9) // isOkOnEve
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LocalBookContent entity, int offset) {
        entity.setBookContentId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContentQ(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContentA(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContentHint(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRootBookId(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setStartRememberTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRememberAmount(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setRootChapter(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setIsOkOnMorning(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setIsOkOnEve(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LocalBookContent entity, long rowId) {
        entity.setBookContentId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LocalBookContent entity) {
        if(entity != null) {
            return entity.getBookContentId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LocalBookContent entity) {
        return entity.getBookContentId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
