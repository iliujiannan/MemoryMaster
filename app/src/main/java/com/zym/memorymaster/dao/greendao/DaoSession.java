package com.zym.memorymaster.dao.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zym.memorymaster.dao.entities.LocalBookContent;
import com.zym.memorymaster.dao.entities.LocalBookInformation;

import com.zym.memorymaster.dao.greendao.LocalBookContentDao;
import com.zym.memorymaster.dao.greendao.LocalBookInformationDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig localBookContentDaoConfig;
    private final DaoConfig localBookInformationDaoConfig;

    private final LocalBookContentDao localBookContentDao;
    private final LocalBookInformationDao localBookInformationDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        localBookContentDaoConfig = daoConfigMap.get(LocalBookContentDao.class).clone();
        localBookContentDaoConfig.initIdentityScope(type);

        localBookInformationDaoConfig = daoConfigMap.get(LocalBookInformationDao.class).clone();
        localBookInformationDaoConfig.initIdentityScope(type);

        localBookContentDao = new LocalBookContentDao(localBookContentDaoConfig, this);
        localBookInformationDao = new LocalBookInformationDao(localBookInformationDaoConfig, this);

        registerDao(LocalBookContent.class, localBookContentDao);
        registerDao(LocalBookInformation.class, localBookInformationDao);
    }
    
    public void clear() {
        localBookContentDaoConfig.clearIdentityScope();
        localBookInformationDaoConfig.clearIdentityScope();
    }

    public LocalBookContentDao getLocalBookContentDao() {
        return localBookContentDao;
    }

    public LocalBookInformationDao getLocalBookInformationDao() {
        return localBookInformationDao;
    }

}
