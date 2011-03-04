package evaluateexpression;

import java.text.ParseException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 */

/** Given a mathematical expression, build a binary tree representation of it, and evaluate
 * 	this binary tree representation.
 * @author Rob Hussey
 * date: 1/21/2010
 *
 */
public class EvaluateExpression {

	private final static String NUMBER = "-?[0-9]+\\.?[0-9]*";
	private final static String OPAREN = "^\\(.*";
	private final static String CPAREN = ".*\\)";
	private static Map<String, Integer> operators = new HashMap<String, Integer>();
	
	/**
	 * @param args - not used
	 */
	public static void main(String[] args) {
		// the operator map - the key is the operator and the value is the precendence
		// (lower val = lower precedence)
		operators.put("-", 1);
		operators.put("+", 1);
		operators.put("*", 2);
		operators.put("/", 2);
		operators.put("^", 3);

		System.out.println("Evaluate Expression - Display the result of evaluating a given expression");
		evaluation();
	}
	
	private static void evaluation() {
		// get a line of input and split it into a string array with space separators
		Scanner scan = new Scanner(System.in);
		String[] input = getInput(scan);
		
		while(!input[0].equals("exit")) {
			// keep evaluating input expressions until exit command received
			newEvaluation(input);
			input = getInput(scan);
		}
			
		scan.close();
	}
	
	/**
	 * Return an array of strings split by the space character, ensuring that the array
	 *  is not null and has at least one element.
	 * @param scan - the input scanner
	 * @return an array of strings split by the space character
	 */
	private static String[] getInput(Scanner scan) {
		String[] input = null;
		while (input == null || input.length == 0) {
			System.out.print("\nTo exit type 'exit'\nExpression: ");
			input = scan.nextLine().split(" ");
		}
		
		return input;
	}
	
	/**
	 * Calls the helper methods which process input, create the expression tree, and display the
	 * 	result of the evaluation.
	 * @param input - the expression to evaluate
	 */
	private static void newEvaluation(String[] input) {
		// stacks for the operators and the operands
		// as operators are applied to operands, the result is placed back in the operand stack
		Deque<String> operatorStack = new LinkedList<String>();
		Deque<BinaryTree<String>> operandStack = new LinkedList<BinaryTree<String>>();
		
		try {
			processInput(input, operatorStack, operandStack);
		} catch (ParseException e) {
			System.out.println("Input at: \"" + e.getMessage() + "\" could not be processed.");
			return;
		}
		
		// processing of input can leave operators left unprocessed - such as when the input ends with
		// a number and not a closing parenthesis
		while (operatorStack.size() > 0) {
			processOperator(operatorStack, operandStack);
		}
		
		// the operand stack now should have a single element which is our full expression tree
		BinaryTree<String> expTree = operandStack.pop();
		
		// uncomment below if you want to see the binary tree that was created
		// System.out.println("Evaluating: \n" + expTree);
		System.out.print("Result of evaluation: ");
		System.out.println(evalExpression(expTree));
	}
	
	/**
	 * Process an array of operands and operators and build the operator and operand stacks in the process.
	 * @param input - array of input strings to process (should have numbers, operators, etc.)
	 * @param operatorStack - stack of operators waiting to be applied to operands
	 * @param operandStack - stack of binary tree operands
	 * @throws ParseException - if the input string couldn't be matched to being an operand, operator, or parenthesis
	 */
	private static void processInput(String[] input, Deque<String> operatorStack, 
										Deque<BinaryTree<String>> operandStack) throws ParseException {
		// to make sure correct formatting, keep track of number occurrences and ensure next is not a number as well
		boolean prevNumber = false;
		for (String s : input) {
			if (!prevNumber && s.matches(NUMBER)) {
				operandStack.push(new BinaryTree<String>(s));
				prevNumber = true;
			}
			else if (s.matches(OPAREN)) {
				// pull the operand from the parentheses (if it is attached to it without spaces)
				String operand = "";
				
				if (s.matches("^\\(.+")) {
					if (prevNumber)
						throw new ParseException(s, 0); // can't have 2 numbers consecutively
					
					operand = s.replace("(", "");
					operandStack.push(new BinaryTree<String>(operand));
					
					prevNumber = true;
				}
				
				s = s.replace(operand, ""); // if there was an operand, remove it now from the string
				while (s.matches(OPAREN)) {
					operatorStack.push("(");
					s = s.substring(1);
				}
			}
			else if (s.matches(CPAREN)) {
				// pull the operand from the parentheses (if it is attached to it without spaces)
				String operand = "";
				
				if (s.matches(".+\\)")) {
					if (prevNumber)
						throw new ParseException(s, 0); // can't have 2 numbers consecutively
					
					operand = s.replace(")", "");
					operandStack.push(new BinaryTree<String>(operand));
					
					prevNumber = true;
				}
				
				s = s.replace(operand, ""); // if there was an operand, remove it now from the string
				while (s.matches(CPAREN)) {
					while (operatorStack.peek() != "(") { // don't pop it here since we still need it
						processOperator(operatorStack, operandStack);
					}
					operatorStack.pop(); // pop the opening parenthesis
					s = s.substring(0, s.length() - 1);
				}
			}
			else if (operators.containsKey(s)) {
				// while the operatorStack has a valid operator and it is of greater precedence to that
				// of the current operator in the string, process the operator from the stack
				while (operators.containsKey(operatorStack.peek()) && 
						operators.get(s) <= operators.get(operatorStack.peek())) {
					processOperator(operatorStack, operandStack);
				}
				operatorStack.push(s);
				prevNumber = false;
			}
			else {
				throw new ParseException(s, 0);
			}
		}
	}
	
	/**
	 * Pop the top two operands and the top operator, make a new binary tree with the operator as the root 
	 * and the operands as the children and push it to the operand stack
	 * @param operatorStack - stack of operators waiting to be applied
	 * @param operandStack - stack of operands as binary trees
	 */
	private static void processOperator(Deque<String> operatorStack, Deque<BinaryTree<String>> operandStack) {
		BinaryTree<String> rightOperand = null;
		BinaryTree<String> leftOperand = null;
		String root = "";
		
		try {
			rightOperand = operandStack.pop(); // right will be top of stack
			leftOperand = operandStack.pop(); // left is after right (somewhat counter-intuitive)
			root = operatorStack.pop();
		} catch (NoSuchElementException e) {
			System.out.println("Error processing input. Ensure correct number of operands.");
			evaluation();
		}
		
		// 2 old operands removed and new operand with operator as root is pushed to operand stack
		operandStack.push(new BinaryTree<String>(root, leftOperand, rightOperand));
	}
	
	/**
	 * Evaluate a given binary tree as an expression using in-order traversal. 
	 * @param exp - the expression tree (binary tree) to evaluate
	 * @return the result of evaluating the tree
	 */
	private static double evalExpression(BinaryTree<String> exp) {
		if (exp.isLeaf())
			return Double.parseDouble(exp.getData()); // get leaf node data
		
		// get the operation to be applied (non-leaf nodes should have operations)
		String operation = exp.getData();
		
		// recurse on the left subtree and the right subtree afterwards
		double operand1 = evalExpression(exp.getLeftSubtree());
		double operand2 = evalExpression(exp.getRightSubtree());
		
		// apply the operation to the operands
		return applyOp(operand1, operand2, operation);
	}
	
	/**
	 * Apply a given operation to given operands
	 * @param operand1 - the first (left) operand
	 * @param operand2 - the second (right) operand
	 * @param operation - the operation to be applied
	 * @return - the result of applying the operation
	 */
	private static double applyOp(double operand1, double operand2, String operation) {
		if (operation.equals("^"))
			return Math.pow(operand1, operand2);
		if (operation.equals("*"))
			return operand1 * operand2;
		if (operation.equals("/"))
			return operand1 / operand2;
		if (operation.equals("-"))
			return operand1 - operand2;
		if (operation.equals("+"))
			return operand1 + operand2;
		
		throw new IllegalArgumentException();
	}
}
