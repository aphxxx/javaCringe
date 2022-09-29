import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        Func f = new Func();



        while(true) {
            String input = JOptionPane.showInputDialog(null,  "Enter value: ");
            if (f.check(input)) {
//            f.dollarToNok(input);
//            f.dollarToJpy(input);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid value, try again!");

            }

        }
    }
}
