import static java.lang.Character.isLetter;

public class Parsing {

    public int getPriority(char ch) {
        switch(ch) {
            case '!':
                return 3;
            case '*':
                return 2;
            case '+':
                return 1;
            default:
                return -1;
        }
    }

    public boolean leftToRight(char ch) {
        return (ch == '!' || ch == '*' || ch == '+');
    }

    public String convert(String expression) {
        String output = "";
        Stack parsingStack = new Stack();
        for (int i = 0; i < expression.length(); ++i) {
            char ch = expression.charAt(i);
            if (isLetter(ch)) {
                output += ch;
            }
            else if (ch == '(') {
                parsingStack.push(ch);
            }
            else if (ch == ')') {
                while (!parsingStack.isEmpty() && parsingStack.peek() != '('){
                    output += parsingStack.pop();
                }
                parsingStack.pop();
            }
            else {
                while (!parsingStack.isEmpty() && getPriority(ch) <= getPriority(parsingStack.peek()) && leftToRight(ch)) {
                    output += parsingStack.pop();
                }
                parsingStack.push(ch);
            }
        }
        while (!parsingStack.isEmpty()) {
            if (parsingStack.peek() == '(')
                return "This expression is invalid";
            output += parsingStack.pop();
        }
        return output;
    }
}
