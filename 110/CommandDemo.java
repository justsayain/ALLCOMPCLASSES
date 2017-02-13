import java.util.*;
import java.io.*;

public class CommandDemo{

	public static void commandline(){
		Scanner in= new Scanner(System.in);

		while(true){
			System.out.print(">");
			String s= in.nextLine();

			if(s.equals("set"))
				System.out.println("set is TBD.");
			else if(s.equals("show"))
				System.out.println("show is TBD.");
			else if (s.equals("quit")){
				System.out.println("bye!");
				break;
			}
			else{
				System.out.println("Command not found. Try set, show, or quit.");
			}
		}
	}

	public static void main(String[]args){
		commandline();
	}
}
