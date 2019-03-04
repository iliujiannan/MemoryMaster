package com.zym.memorymaster.dao.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zym.memorymaster.dao.entities.LocalBookInformation;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "local_book_information".
*/
public class LocalBookInformationDao extends AbstractDao<LocalBookInformation, Long> {

    public static final String TABLENAME = "local_book_information";

    /**
     * Properties of entity LocalBookInformation.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property LocalBookInformationId = new Property(0, Long.class, "LocalBookInformationId", true, "_id");
        public final static Property UserId = new Property(1, Integer.class, "userId", false, "USER_ID");
        public final static Property BookInformationId = new Property(2, Integer.class, "bookInformationId", false, "BOOK_INFORMATION_ID");
        public final static Property IsCurr = new Property(3, Integer.class, "isCurr", false, "is_current");
    }


    public LocalBookInformationDao(DaoConfig config) {
        super(config);
    }
    
    public LocalBookInformationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"local_book_information\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: LocalBookInformationId
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"BOOK_INFORMATION_ID\" INTEGER," + // 2: bookInformationId
                "\"is_current\" INTEGER);"); // 3: isCurr
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"local_book_information\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LocalBookInformation entity) {
        stmt.clearBindings();
 
        Long LocalBookInformationId = entity.getLocalBookInformationId();
        if (LocalBookInformationId != null) {
            stmt.bindLong(1, LocalBookInformationId);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Integer bookInformationId = entity.getBookInformationId();
        if (bookInformationId != null) {
            stmt.bindLong(3, bookInformationId);
        }
 
        Integer isCurr = entity.getIsCurr();
        if (isCurr != null) {
            stmt.bindLong(4, isCurr);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LocalBookInformation entity) {
        stmt.clearBindings();
 
        Long LocalBookInformationId = entity.getLocalBookInformationId();
        if (LocalBookInformationId != null) {
            stmt.bindLong(1, LocalBookInformationId);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Integer bookInformationId = entity.getBookInformationId();
        if (bookInformationId != null) {
            stmt.bindLong(3, bookInformationId);
        }
 
        Integer isCurr = entity.getIsCurr();
        if (isCurr != null) {
            stmt.bindLong(4, isCurr);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LocalBookInformation readEntity(Cursor cursor, int offset) {
        LocalBookInformation entity = new LocalBookInformation( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // LocalBookInformationId
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // bookInformationId
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // isCurr
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LocalBookInformation entity, int offset) {
        entity.setLocalBookInformationId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setBookInformationId(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setIsCurr(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LocalBookInformation entity, long rowId) {
        entity.setLocalBookInformationId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LocalBookInformation entity) {
        if(entity != null) {
            return entity.getLocalBookInformationId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LocalBookInformation entity) {
        return entity.getLocalBookInformationId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}