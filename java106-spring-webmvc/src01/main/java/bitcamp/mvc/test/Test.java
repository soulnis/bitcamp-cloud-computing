package bitcamp.mvc.test;

public class Test extends Car {
    String a="bb";
    
    
    @Override
    public int b(int a) {
        // TODO Auto-generated method stub
        return 1;
    }


    public static void main(String[] args) {
        Car car= new Test();
        System.out.println(car.a);
        System.out.println(car.b(3));
 }

    
}
class Car {
    String a="aa";
    
    public int b(int a) {
        return 3;
    }
}
