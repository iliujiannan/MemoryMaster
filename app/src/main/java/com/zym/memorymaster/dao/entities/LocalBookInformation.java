package com.zym.memorymaster.dao.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 12390 on 2019/3/4.
 */
@Entity(nameInDb = "local_book_information")
public class LocalBookInformation {
    @Id(autoincrement = true)
    private Long LocalBookInformationId;
    private Integer userId;
    private Integer bookInformationId;
    @Property(nameInDb = "is_current")
    private Integer isCurr;
    @Generated(hash = 1741429623)
    public LocalBookInformation(Long LocalBookInformationId, Integer userId,
            Integer bookInformationId, Integer isCurr) {
        this.LocalBookInformationId = LocalBookInformationId;
        this.userId = userId;
        this.bookInformationId = bookInformationId;
        this.isCurr = isCurr;
    }
    @Generated(hash = 1660830622)
    public LocalBookInformation() {
    }
    public Long getLocalBookInformationId() {
        return this.LocalBookInformationId;
    }
    public void setLocalBookInformationId(Long LocalBookInformationId) {
        this.LocalBookInformationId = LocalBookInformationId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBookInformationId() {
        return this.bookInformationId;
    }
    public void setBookInformationId(Integer bookInformationId) {
        this.bookInformationId = bookInformationId;
    }
    public Integer getIsCurr() {
        return this.isCurr;
    }
    public void setIsCurr(Integer isCurr) {
        this.isCurr = isCurr;
    }

}
