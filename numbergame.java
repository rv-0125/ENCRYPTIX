import java.util.Random;
import java.util.Scanner;

public class numbergame{
    public static void game(int num,int limit){
        Scanner s= new Scanner(System.in);
        System.out.println("Guess a number: ");
        for(int i=0;i<limit;i++){
            int a=s.nextInt();
            if(a==num){
                System.out.println("Well done!! You got it right");
                System.out.println("Now to exit enter 0 or to play again enter 1");
                int b=s.nextInt();
                if(b==0) return;
                else game(num,limit);
            }else if(a>num && a-num<a/2){
                System.out.println("OOPS!! You got it wrong..Guess a bit smaller number");
            }else if(a>num && a-num>=a/2){
                System.out.println("OOPS!! You got it wrong..Guess a much smaller number");
            }else if(a<num && num-a<num/2){
                System.out.println("OOPS!! You got it wrong..Guess a bit bigger number");
            }else if(a<num && num-a>=num/2){
                System.out.println("OOPS!! You got it wrong..Guess a much bigger number");
            }
            if(i<limit-1) System.out.println("Try Again: ");
        }

    }
    public static void main(String[] args) {
        Random r= new Random();
        int num= r.nextInt(1,101);
        // System.out.println(num);
        int limit=10;
        game(num,limit);
    }
}