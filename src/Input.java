import java.util.Scanner;
public class Input {
    private Scanner input = new Scanner(System.in);

    //kétféleképpen adom meg a beolvasást
    public String getInput() {
        return this.input.nextLine();
    }
    public String getInput(String prompt) {
        System.out.println(prompt);
        return this.input.nextLine();
    }
}
