package com.zym.memorymaster.dao.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 12390 on 2019/3/4.
 */
@Entity(nameInDb = "book_content")
public class LocalBookContent {
    @Id(autoincrement = true)
    private Long bookContentId;

    @Property(nameInDb = "content_q")
    private String contentQ;

    @Property(nameInDb = "content_a")
    private String contentA;

    @Property(nameInDb = "content_hint")
    private String contentHint;

    private Integer rootBookId;

    @Property(nameInDb = "last_remember_time")
    private String startRememberTime;

    @Property(nameInDb = "remember_amount")
    private Integer rememberAmount;

    @Property(nameInDb = "root_chapter")
    private Integer rootChapter;

    @Property(nameInDb = "is_ok_on_morning")
    private Integer isOkOnMorning;
    @Property(nameInDb = "is_ok_on_eve")
    private Integer isOkOnEve;
    @Generated(hash = 1624109771)
    public LocalBookContent(Long bookContentId, String contentQ, String contentA,
            String contentHint, Integer rootBookId, String startRememberTime,
            Integer rememberAmount, Integer rootChapter, Integer isOkOnMorning,
            Integer isOkOnEve) {
        this.bookContentId = bookContentId;
        this.contentQ = contentQ;
        this.contentA = contentA;
        this.contentHint = contentHint;
        this.rootBookId = rootBookId;
        this.startRememberTime = startRememberTime;
        this.rememberAmount = rememberAmount;
        this.rootChapter = rootChapter;
        this.isOkOnMorning = isOkOnMorning;
        this.isOkOnEve = isOkOnEve;
    }
    @Generated(hash = 1362155483)
    public LocalBookContent() {
    }
    public Long getBookContentId() {
        return this.bookContentId;
    }
    public void setBookContentId(Long bookContentId) {
        this.bookContentId = bookContentId;
    }
    public String getContentQ() {
        return this.contentQ;
    }
    public void setContentQ(String contentQ) {
        this.contentQ = contentQ;
    }
    public String getContentA() {
        return this.contentA;
    }
    public void setContentA(String contentA) {
        this.contentA = contentA;
    }
    public String getContentHint() {
        return this.contentHint;
    }
    public void setContentHint(String contentHint) {
        this.contentHint = contentHint;
    }
    public Integer getRootBookId() {
        return this.rootBookId;
    }
    public void setRootBookId(Integer rootBookId) {
        this.rootBookId = rootBookId;
    }
    public String getStartRememberTime() {
        return this.startRememberTime;
    }
    public void setStartRememberTime(String startRememberTime) {
        this.startRememberTime = startRememberTime;
    }
    public Integer getRememberAmount() {
        return this.rememberAmount;
    }
    public void setRememberAmount(Integer rememberAmount) {
        this.rememberAmount = rememberAmount;
    }
    public Integer getRootChapter() {
        return this.rootChapter;
    }
    public void setRootChapter(Integer rootChapter) {
        this.rootChapter = rootChapter;
    }
    public Integer getIsOkOnMorning() {
        return this.isOkOnMorning;
    }
    public void setIsOkOnMorning(Integer isOkOnMorning) {
        this.isOkOnMorning = isOkOnMorning;
    }
    public Integer getIsOkOnEve() {
        return this.isOkOnEve;
    }
    public void setIsOkOnEve(Integer isOkOnEve) {
        this.isOkOnEve = isOkOnEve;
    }

}
