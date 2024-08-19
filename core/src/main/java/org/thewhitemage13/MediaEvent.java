package org.thewhitemage13;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MediaEvent implements Serializable {
    private Long mediaId;
    private Long userId;
    private String url;
    private String fileName;
    private Double fileSize;
    private String fileType;
    private LocalDateTime uploadDate;

    public MediaEvent() {
    }

    public MediaEvent(Long mediaId, Long userId, String url, String fileName, Double fileSize, String fileType, LocalDateTime uploadDate) {
        this.mediaId = mediaId;
        this.userId = userId;
        this.url = url;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "MediaEvent{" +
                "mediaId=" + mediaId +
                ", userId=" + userId +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
