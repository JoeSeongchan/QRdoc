package Extractor;

import Document.Document;
import Information.Link;
import java.util.Vector;
import java.util.regex.Pattern;

/*특정 패턴을 만족하는 문자열을 문서에서 추출하는 추상 클래스*/
abstract public class Extractor {
    Pattern p;      //정규식

    abstract Vector<Link> extract(Document doc);    //추출함수
}
