import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        Func f = new Func();
        Object[] option1 = {"NOK", "YEN", "Quit"};
        Object[] option2 = {"Try again", "Quit"};



        while(true) {
            String input = JOptionPane.showInputDialog(null,  "Enter value: ");
            System.out.println(input);

            if (f.check(input)) {
                double Minput = Double.parseDouble(input);

                int choice1 = JOptionPane.showOptionDialog(null, "Choose currency",
                        "Currency Converter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        option1, option1[2]);

                if(choice1 == 0){
                    f.dollarToNok(Minput);
                    break;
                } else if(choice1 == 1){
                    f.dollarToJpy(Minput);
                    break;
                } else {
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid value, try again!");

                int choice2 = JOptionPane.showOptionDialog(null, "What do you wish to do?", "Currency Converter",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        option2, option2[1]);

                if(choice2 == 0){

                } else{
                    break;
                }
            }

        }
    }
}
