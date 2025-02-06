import java.util.Stack;


/*
This class implements a two-stack algorithm for evaluating arithmetic expressions.

Key components:
1. Two stacks: 
   - ops: for storing operators
   - vals: for storing numeric values

2. The evaluate method:
   - Takes a string input representing an arithmetic expression
   - Returns the result as a double

Algorithm steps:
1. Split the input string into tokens (numbers, operators, parentheses)
2. Iterate through each token:
   - If it's an opening parenthesis '(', ignore it
   - If it's an operator (+, -, *, /), push onto ops stack
   - If it's a closing parenthesis ')', perform the calculation:
     a. Pop the top operator from ops
     b. Pop the required number of values from vals
     c. Perform the operation
     d. Push the result back onto vals
   - If it's a number, parse it to double and push onto vals stack

3. After processing all tokens, the final result will be the only item left on vals stack

Important considerations:
- Use .equals() for string comparisons, not ==
- Be careful with the order of operands when popping for binary operations
- Consider adding checks for empty stacks to prevent errors

This implementation provides O(n) time complexity and effectively handles 
operator precedence through the use of parentheses in the input expression.
*/

import java.util.Stack;

public class TwoStack {
    Stack<String> ops  = new Stack<>();
    Stack<Double> vals = new Stack<>();

    public double evaluate(String s) {
        String[] tokens = s.split(" ");
       
        // Loop over the tokens until you reach the end of the expression
        for (String token : tokens) {
            if (token.equals("(")) {
                // Ignore opening parentheses
                continue;
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                // Push operator onto the ops stack
                ops.push(token);
            } else if (token.equals(")")) {
                // Perform calculation when encountering a closing parenthesis
                String op = ops.pop();
                double val = vals.pop();

                if (op.equals("+")) {
                    val = vals.pop() + val;
                } else if (op.equals("-")) {
                    val = vals.pop() - val;
                } else if (op.equals("*")) {
                    val = vals.pop() * val;
                } else if (op.equals("/")) {
                    val = vals.pop() / val;
                }

                // Push result back onto the vals stack
                vals.push(val);
            } else {
                // It's a number, parse and push onto vals stack
                vals.push(Double.parseDouble(token));
            }
        }

        // The final result should be the only element left in vals stack
        return vals.pop();
    }

    public static void main(String[] args) {
        TwoStack calculator = new TwoStack();
        
        // Example expressions
        String expr1 = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        String expr2 = "( ( 10 + 2 ) * ( 3 / ( 6 - 4 ) ) )";
        
        System.out.println("Result 1: " + calculator.evaluate(expr1)); // Expected output: 101.0
        System.out.println("Result 2: " + calculator.evaluate(expr2)); // Expected output: 18.0
        

    }
}