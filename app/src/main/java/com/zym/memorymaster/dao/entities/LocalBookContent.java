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
    private String lastRememberTime;

    @Property(nameInDb = "remember_amount")
    private Integer rememberAmount;

    @Property(nameInDb = "root_chapter")
    private Integer rootChapter;

    @Generated(hash = 1459004351)
    public LocalBookContent(Long bookContentId, String contentQ, String contentA,
            String contentHint, Integer rootBookId, String lastRememberTime,
            Integer rememberAmount, Integer rootChapter) {
        this.bookContentId = bookContentId;
        this.contentQ = contentQ;
        this.contentA = contentA;
        this.contentHint = contentHint;
        this.rootBookId = rootBookId;
        this.lastRememberTime = lastRememberTime;
        this.rememberAmount = rememberAmount;
        this.rootChapter = rootChapter;
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

    public String getLastRememberTime() {
        return this.lastRememberTime;
    }

    public void setLastRememberTime(String lastRememberTime) {
        this.lastRememberTime = lastRememberTime;
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
}
