//http://en.wikipedia.org/wiki/Interpreter_pattern

//The following Reverse Polish notation example illustrates the interpreter pattern. The grammar
//expression ::= plus | minus | variable
//plus ::= expression expression '+'
//minus ::= expression expression '-'
//variable  ::= 'a' | 'b' | 'c' | ... | 'z'
//
//defines a language which contains reverse polish expressions like:
//a b +
//a b c + -
//a b + c a - -

package pattern.behavior;

import java.util.*;

interface Expression {
    public int interpret(HashMap<String,Integer> variables);
}
 
class Number implements Expression {
    private int number;
    public Number(int number)       { this.number = number; }
    public int interpret(HashMap<String,Integer> variables)  { return number; }
}
 
class Plus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Plus(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public int interpret(HashMap<String,Integer> variables)  { 
        return leftOperand.interpret(variables) + rightOperand.interpret(variables);
    }
}
 
class Minus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Minus(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public int interpret(HashMap<String,Integer> variables)  { 
        return leftOperand.interpret(variables) - rightOperand.interpret(variables);
    }
}
 
class Variable implements Expression {
    private String name;
    public Variable(String name)       { this.name = name; }
    public int interpret(HashMap<String,Integer> variables)  { 
        return variables.get(name); 
    }
}

class Evaluator {
    private Expression syntaxTree;
 
    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            if  (token.equals("+")) {
                Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else if (token.equals("-")) {
                Expression subExpression = new Minus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else                        
                expressionStack.push( new Variable(token) );
        }
        syntaxTree = expressionStack.pop();
    }
 
    public int evaluate(HashMap<String,Integer> context) {
        return syntaxTree.interpret(context);
    }
}
public class InterpreterExample {
    public static void main(String[] args) {
        String expression = "w x z - +";
        Evaluator sentence = new Evaluator(expression);
        HashMap<String,Integer> variables = new HashMap<String,Integer>();
        variables.put("w", 5);
        variables.put("x", 10);
        variables.put("z", 42);
        int result = sentence.evaluate(variables);
        System.out.println(result);
    }
}


