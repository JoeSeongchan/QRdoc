package Document;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/*pdf 파일을 읽어서 Document 객체를 만들어주는 클래스*/
public class PDFdocumentReader extends DocumentReader {

    /*PDF 파일을 읽어서 Document 객체를 만든 후 이를 리턴하는 함수*/
    public Document read(File file) throws IOException {
        Document doc = new Document();
        doc.setAttributeFromFullPath(file.getPath());
        String text;
        PDDocument pdfBoxReader = null;
        try {
            pdfBoxReader = PDDocument.load(file);
            text = new PDFTextStripper().getText(pdfBoxReader);
            doc.setText(text);
            return doc;
        } catch (IOException e) {
            throw new IOException(file.getName()+" PDF 파일이 없습니다!");
        } finally {
            if (pdfBoxReader != null) {
                pdfBoxReader.close();
            }
        }
    }
}