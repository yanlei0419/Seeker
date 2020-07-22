package org.seeker.file.entity;

/**
 * 文件分割属性类
 */
public class CutPoint {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件头位置
     */
    private long head;
    /**
     * 文件结束位置
     */
    private long tail;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getHead() {
        return head;
    }

    public void setHead(long head) {
        this.head = head;
    }

    public long getTail() {
        return tail;
    }

    public void setTail(long tail) {
        this.tail = tail;
    }
}
