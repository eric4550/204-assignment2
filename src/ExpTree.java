import java.util.Iterator;
import java.util.Stack;

public abstract class ExpTree {

//    protected ExpTree lChild, rChild;
//
//    public ExpTree leftChild(){
//        return lChild;
//    }
//
//    public ExpTree rightChild(){
//        return rChild;
//    }
//
//    public abstract String toString();

    private int kind;
    private Object value;
    private ExpTree lChild, rChild;

    public  static final int numNode = 0, idNode = 1, opNode = 2;

    public ExpTree(int knd, Object val, ExpTree l, ExpTree r){
        kind = knd;
        value = val;
        lChild = l;
        rChild = r;
    }

    //methods to examine the node

    //I am not sure why I like this more than just writing .charValue()?
    public char toChar(Character Char){
        return Char.charValue();
    }

    int preced(char ch) {
        if(ch == '+' || ch == '-') {
            return 1;              //Precedence of + or - is 1
        }else if(ch == '*' || ch == '/') {
            return 2;            //Precedence of * or / is 2
        }else if(ch == '^') {
            return 3;            //Precedence of ^ is 3
        }else {
            return 0;
        }
    }

    public String postOrder(String in) {
        //takes in the in-order equation and returns the post-order equation
        Stack<Character> stk = new Stack<>();
        stk.push('#');
        String postfix = "";
        char[] charArr = in.toCharArray();

        for(char c : charArr) {
            if (Character.isDigit(c)) {
                postfix += c;
            } else if (c == '(') {
                stk.push('(');
            } else if (c == '^') {
                stk.push('^');
            } else if (c == ')') {
                while (stk.peek() != '#' && stk.peek() != '(') {
                    postfix += stk.peek();
                    stk.pop();
                }
                stk.pop();
            } else {
                if (preced(c) > preced(stk.peek())) {
                    stk.push(c);
                } else {
                    while (stk.peek() != '#' && preced(c) <= preced(stk.peek())) {
                        postfix += stk.peek();
                        stk.pop();
                    }
                    stk.push(c);
                }
            }
        }
        while(stk.peek() != '#') {
            postfix += stk.peek();
            stk.pop();
        }
        return postfix;
    }

}
