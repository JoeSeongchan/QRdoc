package Extractor;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Document.Document;
import Information.Link;

/*문서에서 링크를 추출하는 클래스*/
public class LinkExtractor extends Extractor {
    public LinkExtractor() {       //링크 패턴 정규식
        super.p = Pattern.compile("(((http(s)?:\\/\\/)\\S+(\\.[^(\\n|\\t|\\s,)]+)+)|((http(s)?:\\/\\/)?" +
                "(([a-zA-z\\-_]+[0-9]*)|([0-9]*[a-zA-z\\-_]+)){2,}(\\.[^(\\n|\\t|\\s,)]+)+))+");
    }

    @Override
    /*링크 추출 함수*/
    public Vector<Link> extract(Document doc) {
        Vector<Link> linkArr = new Vector<Link>();
        int order = 0;
        Matcher m = p.matcher(doc.getText());
        while (m.find()) {
            linkArr.add(new Link(m.group(), m.start(), order++));
        }
        return linkArr;
    }
}