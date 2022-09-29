import javax.swing.*;

public class Func {

    public boolean check(String input){
        try{
            double x = Double.parseDouble(input);
            if(x >= 0 || x < 0){
                return true;
            }
        } catch (NumberFormatException e){
            return false;
        }
        return false;
    }

    public void dollarToNok(double input){
        double nok = input * 8.2;
        JOptionPane.showMessageDialog(null, "Amount NOK: " +nok);
    }

    public void dollarToJpy(double input){
        double jpy = input * 112.7;
        JOptionPane.showMessageDialog(null, "Amount JPY: " +jpy);
    }
}
