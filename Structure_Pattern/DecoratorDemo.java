/**
 * 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
 * 装饰（Decorator）模式的主要优点有：
 * 采用装饰模式扩展对象的功能比采用继承方式更加灵活。
 * 可以设计出多个不同的具体装饰类，创造出多个不同行为的组合。
 *
 * 其主要缺点是：装饰模式增加了许多子类，如果过度使用会使程序变得很复杂。
 *
 * 装饰模式主要包含以下角色。
 * 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
 * 具体构件（Concrete Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
 * 抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
 * 具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
 */

//抽象构件
interface Drink {
    public float cost();
    public String getDescription();
}

//具体构件
class Coffee implements Drink {
    final private String description = "coffee";

    public float cost() {
        return 10;
    }

    public String getDescription() {
        return description;
    }
}
// 抽象装饰
abstract class CondimentDecorator implements Drink {
    protected Drink decoratorDrink;

    public CondimentDecorator(Drink decoratorDrink) {
        this.decoratorDrink = decoratorDrink;
    }

    public float cost() {
        return decoratorDrink.cost();
    }

    public String getDescription() {
        return decoratorDrink.getDescription();
    }
}
// 具体装饰
class Milk extends CondimentDecorator {
    
    public Milk(Drink decoratorDrink) {
        super(decoratorDrink);
    }

    @Override
    public float cost() {
        return super.cost() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " milk";
    }
}
// 具体装饰
class Sugar extends CondimentDecorator {
    public Sugar(Drink decoratorDrink) {
        super(decoratorDrink);
    }

    @Override
    public float cost() {
        return super.cost() + 1;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " sugar";
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        //点一杯coffee
        Drink drink = new Coffee();
        System.out.println(drink.getDescription() + ":" + drink.cost());
        //加一份奶
        drink = new Milk(drink);
        System.out.println(drink.getDescription() + ":" + drink.cost());
        //加一份糖
        drink = new Sugar(drink);
        System.out.println(drink.getDescription() + ":" + drink.cost());
        //再加一份糖
        drink = new Sugar(drink);
        System.out.println(drink.getDescription() + ":" + drink.cost());
    }
}
