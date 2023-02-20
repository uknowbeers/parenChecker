package com.acme;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String input1 = "(()){}[]";
        String input2 = "((({[[]]})))";
        String input3 = "([))";
        String input4 = " (defun csg-intersection-intersect-all (obj-a obj-b)\n" +
                "   (lambda (ray)\n" +
                "     (flet ((inside-p (obj) (lambda (d) (inside-p obj (ray-point ray d)))))\n" +
                "       (merge 'fvector\n" +
                "              (remove-if-not (inside-p obj-b) (intersect-all obj-a ray))\n" +
                "              (remove-if-not (inside-p obj-a) (intersect-all obj-b ray))\n" +
                "              #'<))))";
        String input5 = " (defclass rewindable ()\n" +
                "   ((rewind-store :reader rewind-store\n" +
                "                  :initform (make-array 12 :fill-pointer 0 :adjustable t))\n" +
                "    ;; Index is the number of rewinds we've done.\n" +
                "    (rewind-index :accessor rewind-index\n" +
                "                  :initform 0)))\n" +
                "\n" +
                "\n" +
                " (defun rewind-count (rewindable)\n" +
                "   (fill-pointer (rewind-store rewindable)))\n" +
                "\n" +
                "\n" +
                " (defun last-state (rewindable)\n" +
                "   (let ((size (rewind-count rewindable)))\n" +
                "     (if (zerop size)\n" +
                "         (values nil nil)\n" +
                "         (values (aref (rewind-store rewindable) (1- size)) t)))"; //paren missing at end

        String input6 = "\n" +
                " (defun save-rewindable-state (rewindable object)\n" +
                "   (let ((index (rewind-index rewindable))\n" +
                "         (store (rewind-store rewindable))))\n" +
                "     (unless (zerop index)\n" +
                "       ;; Reverse the tail of pool, since we've\n" +
                "       ;; gotten to the middle by rewinding.\n" +
                "       (setf (subseq store index) (nreverse (subseq store index))))\n" +
                "     (vector-push-extend object store)))"; //extra paren closing in the middle

        System.out.println("input 1 should be true and is: " + isValidParentheses(input1)); // true
        System.out.println("input 2 should be true and is: " + isValidParentheses(input2)); // true
        System.out.println("input 3 should be false and is: " + isValidParentheses(input3)); // false
        System.out.println("input 4 should be true and is: " + isValidParentheses(input4)); // true
        System.out.println("input 5 should be false and is: " + isValidParentheses(input5)); // false
        System.out.println("input 6 should be false and is: " + isValidParentheses(input6)); // false

    }


    /**
     * The algorithm uses a stack data structure to keep track of opening parentheses that have not yet been closed.
     * When the algorithm encounters an opening parenthesis, it pushes it onto the stack.
     * When it encounters a closing parenthesis, it pops the top element from the stack and checks if it matches the closing parenthesis.
     * If the parentheses do not match or the stack is empty when encountering a closing parenthesis, the algorithm returns false.
     * Finally, if the stack is empty after processing the entire string, the algorithm returns true.
     *
     * @param stringToTest String of code
     * @return boolean for validity
     */
    public static boolean isValidParentheses(String stringToTest) {
        Stack<Character> stack = new Stack<>();
        char[] arr = stringToTest.toCharArray();
        for (int i = 0; i < stringToTest.length(); i++) {
            char c = arr[i];
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


}
