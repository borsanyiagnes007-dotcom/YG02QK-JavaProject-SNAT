import java.util.ArrayList;
import java.util.Scanner;
public class Input {
    private Scanner input = new Scanner(System.in);

    //többféleképpen adom meg a beolvasást
    public String getInput() {
        return this.input.nextLine();
    }
    public String getInput(String prompt) {
        System.out.println(prompt);
        return this.input.nextLine();
    }
    public String getInput(String prompt, ArrayList<String> options) {      //csak a megadott ertekeket adhatja meg inputnak
        String choice = null;
        do {
            System.out.print(prompt + " (");
            for (String option : options) {
                System.out.print(option + " ");
            }
            System.out.print(")");
            choice = this.input.nextLine();

        }   while (!options.contains(choice));
        return choice;
    }
}
