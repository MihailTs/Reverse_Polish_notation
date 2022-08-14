public class ReversePolishTest {

	public static void main(String[] args) {

		RPN rpn = new RPN("((2/8)*(3*5)+7)");
		System.out.println(rpn.toString());
		rpn.divide(3);
		System.out.println(rpn.toString());

	}
}

class RPN {

	private String expr;

	RPN(String s) {
		expr = toRPN(s);
	}

	private String toRPN(String s) {

		// System.out.println(s);
		if (s.length() < 3)
			return s;

		if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
			int op = 0;
			for (int i = 1; i < s.length() - 1; i++) {
				if (s.charAt(i) == '(') {
					op++;
					break;
				}
				if (s.charAt(i) == ')') {
					op--;
					break;
				}
			}

			if (op > -1)
				s = s.substring(1, s.length() - 1);
		}
		System.out.println(s);

		String exp = "";
		int place = -1;
		int prior = 3;
		int bracket = 0;

		for (int i = 0; i < s.length(); i++) {
			if (prior(s.charAt(i)) == -1)
				continue;

			if (s.charAt(i) == '(') {
				bracket++;
				continue;
			} else if (s.charAt(i) == ')') {
				bracket--;
				continue;
			}

			if (bracket != 0)
				continue;

			if (prior(s.charAt(i)) < prior) {
				prior = prior(s.charAt(i));
				place = i;
			}
		}

		System.out.println(s.charAt(place));
		exp = toRPN(s.substring(0, place)) + toRPN(s.substring(place + 1, s.length())) + s.charAt(place);
		return exp;
	}

	public void add(int a) {
		expr = expr + a + '+';
	}

	public void substract(int a) {
		expr = expr + a + '-';
	}

	public void mult(int a) {
		expr = expr + a + '*';
	}

	public void divide(int a) {
		expr = expr + a + '/';
	}

	private int prior(char ch) {

		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '/':
		case '*':
			return 2;
		case '(':
		case ')':
			return 0;
		}

		return -1;
	}

	public String toString() {
		return expr;
	}

}
