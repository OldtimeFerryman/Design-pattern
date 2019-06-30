package Structure;

/**
 * 桥接（Bridge）模式的定义如下：将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现
 * ，从而降低了抽象和实现这两个可变维度的耦合度。
 *
 * 桥接（Bridge）模式的优点是：
 * 由于抽象与实现分离，所以扩展能力强；
 * 其实现细节对客户透明。
 *
 * 缺点是：由于聚合关系建立在抽象层，要求开发者针对抽象化进行设计与编程，这增加了系统的理解与设计难度。
 *
 * 桥接（Bridge）模式包含以下主要角色。
 * 1. 抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
 * 2. 扩展抽象化（Refined Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，
 *      并通过组合关系调用实现化角色中的业务方法。
 * 3. 实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 4. 具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
 */

//实现化（Implementor）角色
interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}

//具体实现化（Concrete Implementor）角色
class RedCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

//具体实现化（Concrete Implementor）角色
class GreenCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}

//抽象化（Abstraction）角色
abstract class Shape {
    protected DrawAPI drawAPI;
    
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
//扩展抽象化（Refined Abstraction）角色
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
