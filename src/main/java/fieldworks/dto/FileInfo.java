package fieldworks.dto;

public class FileInfo {
    private String fileName;
    private String contentType;
    private long size;
    private String downloadUrl; // optional, if you expose a download endpoint

    public FileInfo() {}

    public FileInfo(String fileName, String contentType, long size, String downloadUrl) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.downloadUrl = downloadUrl;
    }

    // getters & setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }
}
