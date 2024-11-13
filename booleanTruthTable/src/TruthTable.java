import static java.lang.Character.isLetter;

public class TruthTable {
    private int variableAmount;

    public boolean charToBoolean(char ch) {
        boolean charBoolean;
        if (ch == 'T') {
            charBoolean = true;
        }
        else {
            charBoolean  = false;
        }
        return charBoolean;
    }

    public char booleanToChar(boolean bool) {
        if (bool) {
            return 'T';
        }
        else {
            return 'F';
        }
    }

    public String header (String postfix, String expression) {
        String output = "";
        for (int i = 0; i < postfix.length(); ++i) {
            char ch = postfix.charAt(i);
            if (isLetter(ch)) {
                output += ch + " | ";
                variableAmount++;
            }
        }
        output += expression;
        return output;
    }

    public void generation(String postfix, String expression) {
        String output = "";
        variableAmount = 0;
        output += header(postfix,expression);
        System.out.println(output);
        int rows = (int) Math.pow(2, variableAmount);
        //Generates the values for the columns of the variables
        for (int i = 0; i < rows; i++) {
            char[] variablesBoolean = new char[variableAmount];
            for (int j = 0; j < variableAmount; j++) {
                int temp = (i / (int) Math.pow(2, variableAmount - 1 - j)) % 2;;
                if (temp == 1) {
                    variablesBoolean[j] = 'T';
                }
                else {
                    variablesBoolean[j] = 'F';
                }
                System.out.print(variablesBoolean[j] + " | ");
            }
            char result = evaluation(postfix, variablesBoolean);
            System.out.print(result);
            System.out.println(" ");
        }
    }

    public char evaluation(String postfix, char[] variablesBool) {
        //Keeps track of the amount of variables in the expression
        int variableCount = 0;
        int index = 0;
        Stack evaluationStack = new Stack();
        for (int i = 0; i < postfix.length(); i++) {
            //Reads each character of the postfix expression
            char ch = postfix.charAt(i);
            //If it is a letter, then it pushes the boolean value as a char from the array variablesBool to the evaluation stack, either T or F
            if (isLetter(ch)) {
                //Index of the array increments in order to get the boolean value of the next variable from variablesBool.
                //If there are 2 variables (A, B), the index of 1 would be the boolean value of B
                evaluationStack.push(variablesBool[index++]);
                variableCount++;
            }
            //Once an operator is encountered, it converts the char boolean values of the operands in the stack to boolean and then performs the operation.
            //It pushes the result of the operation to the evaluation stack after it converts it from boolean to char T or F.
            else {
                if (ch == '!' && !evaluationStack.isEmpty()) {
                    char operand = evaluationStack.pop();
                    boolean boolOperand = charToBoolean(operand);
                    boolean notOp = !boolOperand;
                    operand = booleanToChar(notOp);
                    evaluationStack.push(operand);
                }
                //It won't perform these operations unless there are two or more variables in the expression
                else if ((ch == '*' || ch == '+') && variableCount >= 2) {
                    char operand2 = evaluationStack.pop();
                    boolean boolOperand2 = charToBoolean(operand2);
                    char operand1 = evaluationStack.pop();
                    boolean boolOperand1 = charToBoolean(operand1);
                    if (ch == '*') {
                        boolean andBoolean = (boolOperand1 && boolOperand2);
                        evaluationStack.push(booleanToChar(andBoolean));
                    }
                    else if (ch == '+') {
                        boolean orBoolean = (boolOperand1 || boolOperand2);
                        evaluationStack.push(booleanToChar(orBoolean));
                    }
                }
            }
        }
        //Returns the top data of the stack which is the result
        return evaluationStack.peek();
    }
}
