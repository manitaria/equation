import java.io.IOException;

/**
 * Created by kirill on 22.05.2015.
 */
public class main {
    public static void main(String[] args) throws IOException {
        Equation equation = new Equation();
        equation.getSolve(1,2,3);
        equation.getSolve("input.txt");
    }
}
