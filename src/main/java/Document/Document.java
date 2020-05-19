package Document;

public class Document {
    private String directory;       //문서의 디렉토리
    private String fileName;        //문서의 이름
    private String fileType;        //문서의 확장자 명
    private String text;            //문서의 내용

    /* 생성자 */
    Document(String fullPath, String text) {
        setAttributeFromFullPath(fullPath);
        this.text = text;
    }

    Document(String directory, String fileName, String text) {
        this(directory + "\\" + fileName, text);
    }

    Document(Document other) {
        this(other.directory, other.fileName, other.text);
    }

    public Document() {
    this.directory="";
    this.fileName="";
    this.fileType="";
    text="";
    }

    /* Setter, Getter */
    public String getDirectory() {
        return this.directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /* 문서의 확장자 명을 찾아서 리턴하는 함수 */
    private static String detectFileType(String fileName) {
        int dividePos = fileName.lastIndexOf('.');
        if (dividePos != -1) {
            return fileName.substring(dividePos + 1);
        } else {
            return "NO_TYPE";
        }
    }

    /* 문서의 전체 경로 문자열을 리턴하는 함수 */
    public String getFullPath() {
        return this.directory + "\\" + this.fileName;
    }

    /* 문서의 전체 경로를 받아서, 그걸로 문서의 디렉토리, 파일명, 확장자명을 정하는 함수 */
    public void setAttributeFromFullPath(String fullPath) {
        int dividePos = fullPath.lastIndexOf("\\");
        this.directory = fullPath.substring(0, dividePos);
        this.fileName = fullPath.substring(dividePos + 1);
        this.fileType = Document.detectFileType(this.fileName);
    }
}