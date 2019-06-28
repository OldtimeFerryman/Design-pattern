/* 抽象工厂模式是工厂方法模式的升级版本，他用来创建一组相关或者相互依赖的对象。他与工厂方法模式的区别就在于，工厂方法模式针对
 * 的是一个产品等级结构；而抽象工厂模式则是针对的多个产品等级结构。在编程中，通常一个产品结构，表现为一个接口或者抽象类，也就
 * 是说，工厂方法模式提供的所有产品都是衍生自同一个接口或抽象类，而抽象工厂模式所提供的产品则是衍生自不同的接口或抽象类。
 *
 * 在抽象工厂模式中，有一个产品族的概念：所谓的产品族，是指位于不同产品等级结构中功能相关联的产品组成的家族。抽象工厂模式所提供
 * 的一系列产品就组成一个产品族；而工厂方法提供的一系列产品称为一个等级结构。
 *
 * (1) 产品等级结构：产品等级结构即产品的继承结构，如一个抽象类是电视机，其子类有海尔电视机、海信电视机、TCL电视机，则抽象电视
 * 机与具体品牌的电视机之间构成了一个产品等级结构，抽象电视机是父类，而具体品牌的电视机是其子类。
 *
 * (2) 产品族：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品，如海尔电器工厂生产的海尔电视机、
 * 海尔电冰箱，海尔电视机位于电视机产品等级结构中，海尔电冰箱位于电冰箱产品等级结构中，海尔电视机、海尔电冰箱构成了一个产品族。
 *
 * 抽象工厂模式与工厂方法模式最大的区别在于，工厂方法模式针对的是一个产品等级结构，而抽象工厂模式需要面对多个产品等级结构，一个工厂
 * 等级结构可以负责多个不同产品等级结构中的产品对象的创建。当一个工厂等级结构可以创建出分属于不同产品等级结构的一个产品族中的所有对
 * 象时，抽象工厂模式比工厂方法模式更为简单、更有效率。
 *
 * 以上资料整理得来，非原创，若有侵权联系删除。
 */

interface IProduct1 {
    void show();
}

interface IProduct2 {
    void show();
}

class Product1 implements IProduct1 {
    public void show() {
        System.out.println("this is product 1.");
    }
}

class Product2 implements IProduct2 {
    public void show() {
        System.out.println("this is product 2");
    }
}

interface IFactory {
    IProduct1 createProduct1();
    IProduct2 createProduct2();
}

class Factory implements IFactory{
    public IProduct1 createProduct1() {
        return new Product1();
    }
    public IProduct2 createProduct2() {
        return new Product2();
    }
}


public class absfactory {
    public static void main(String[] args){
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}

// out:
// this is product 1.
// this is product 2
