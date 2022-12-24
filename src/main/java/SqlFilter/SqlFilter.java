package SqlFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlFilter {
    public static boolean Validate(String input){
        String regexBraces = "'(''|[^'])*'";
        String regexKeyWords = "\\b(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE|UNION( +ALL){0,1})\\b";

        Pattern patternBraces = Pattern.compile(regexBraces, Pattern.CASE_INSENSITIVE);
        Pattern patternKeyWords = Pattern.compile(regexKeyWords, Pattern.CASE_INSENSITIVE);

        Matcher matcher = patternBraces.matcher(input);
        if(matcher.find())
            return false;
        matcher = patternKeyWords.matcher(input);
        if(matcher.find())
            return false;

        return true;
    }
}
