package com.zym.memorymaster.models;

/**
 * Created by 12390 on 2019/3/2.
 */
public class BookInformation {
    private Integer bookInformationId;
    private String bookName;
    private Integer bookType;
    private Integer bookRecordsNum;
    private String bookAuthor;
    private String bookPath;
    private String bookImgSrc;
    private Integer bookPrice;
    private Integer bookLoadingNum;
    private Integer bookChapterNum;
    private String bookDescription;

    public Integer getBookInformationId() {
        return bookInformationId;
    }

    public void setBookInformationId(Integer bookInformationId) {
        this.bookInformationId = bookInformationId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookRecordsNum() {
        return bookRecordsNum;
    }

    public void setBookRecordsNum(Integer bookRecordsNum) {
        this.bookRecordsNum = bookRecordsNum;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getBookImgSrc() {
        return bookImgSrc;
    }

    public void setBookImgSrc(String bookImgSrc) {
        this.bookImgSrc = bookImgSrc;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookLoadingNum() {
        return bookLoadingNum;
    }

    public void setBookLoadingNum(Integer bookLoadingNum) {
        this.bookLoadingNum = bookLoadingNum;
    }

    public Integer getBookChapterNum() {
        return bookChapterNum;
    }

    public void setBookChapterNum(Integer bookChapterNum) {
        this.bookChapterNum = bookChapterNum;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
