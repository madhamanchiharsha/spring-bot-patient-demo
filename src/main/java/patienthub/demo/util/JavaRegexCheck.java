package patienthub.demo.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class JavaRegexCheck {
    public boolean javaRegexChecking(String expression, String regex){
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(expression).matches();
    }
}
