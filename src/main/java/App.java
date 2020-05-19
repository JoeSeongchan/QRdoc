import Document.Document;
import Extractor.LinkExtractor;
import Information.Link;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import Exception.WidthHeightNegativeException;
import Document.PDFdocumentReader;
import QRgenerator.DefaultQRgenerator;
import com.google.zxing.WriterException;


/*프로그램 잘 돌아가는 지 체크!!*/
public class App {

    public static void main(String[] args) {
        File file = new File(".\\test.pdf");
        //
        //  pdf 문서 읽어서, 그 안에 적힌 모든 문자열을 Document 객체에 저장!
        //
        PDFdocumentReader docReader = new PDFdocumentReader();
        Document doc = new Document();
        try {
            doc = docReader.read(file);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException--> " + e.getMessage());
        }
        System.out.println(doc.getText());

        //
        //  Document 객체 안에 저장된 문자열을 일정한 패턴 하에 분리한 후, 이를 배열로 저장!!
        //
        LinkExtractor linkExtractor = new LinkExtractor();
        Vector<Link> linkArr = linkExtractor.extract(doc);
        Link link;
        for (int i = 0; i < linkArr.size(); i++) {
            link = linkArr.elementAt(i);
            System.out.println("link 주소 문자열: " + link.getLink());
            System.out.println("link 순서값: " + link.getLinkOrder());
            System.out.println("link 위치: " + link.getLinkPos());
        }

        //
        //  QR-code를 만들어서 이미지 파일로 저장!!
        //

        /*QR-code를 저장할 폴더가 존재하는지 확인한다.
        * pdf 파일 이름이 "test"면 test안에 들어갈 QR-code 이미지 파일을 모아놓은 폴더의 이름은 "test~QR"이다. */
        File qrCodeImageDirectory=
                new File(".\\" +doc.getFileName().substring
                (0,doc.getFileName().length()-doc.getFileType().length()-1)+"~QR");
        if(!qrCodeImageDirectory.exists()){
            qrCodeImageDirectory.mkdir();
        }

        DefaultQRgenerator qrGenerator = new DefaultQRgenerator(qrCodeImageDirectory.getPath());
        for (int i = 0; i < linkArr.size(); i++) {
            try {
                qrGenerator.generate
                        (linkArr.elementAt(i), 200, 200, "test" + i + ".png");
            } catch (WriterException e) {
                System.out.println("WriterException--> " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException--> " + e.getMessage());
            } catch (WidthHeightNegativeException e) {
                System.out.println("WidthHeightNegativeException--> " + e.getMessage());
            }
        }
        System.out.println("Program is over!");
        //
        //  프로그램 종료!!
        //
    }
}

