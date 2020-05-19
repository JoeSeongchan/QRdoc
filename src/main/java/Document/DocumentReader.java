package Document;

import java.io.File;
import java.io.IOException;

/*문서파일을 읽어서 Document 객체로 만들어주는 추상클래스*/
public abstract class DocumentReader {

    public abstract Document read(File file) throws IOException;

}
