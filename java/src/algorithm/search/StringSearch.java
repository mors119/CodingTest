package algorithm.search;
// javac algorithm/search/StringSearch.java
// java algorithm/search/StringSearch

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class StringSearch {
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);

        String[] x = {
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "try",
            "void",
            "volatile",
            "while"
        };

        System.out.println("키워드 검색: ");
        String key = stdIn.next();

        int idx = Arrays.binarySearch(x, key);

        if(idx < 0) {
            System.out.println("해당 키워드 없음.");
        }
        if(idx >= 0) {
            System.out.println(key + "는 x[" + idx + "]에 있습니다.");
        }
    }
}
