/*工厂模式 
定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行。
主要解决：主要解决接口选择的问题。
何时使用：我们明确地计划不同条件下创建不同实例时。
如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品。
关键代码：创建过程在其子类执行。
*/

interface Cat {
    void Meow();
}
class AmericanSharthaie implements Cat {

    @Override
    public void Meow() {
        // TODO Auto-generated method stub
        System.out.println("AmericanSharthaie meows!");
    }

}
class Ragdoll implements Cat {

    @Override
    public void Meow() {
        // TODO Auto-generated method stub
        System.out.println("Ragdoll meows!");
    }

}
class Munchkin implements Cat {

    @Override
    public void Meow() {
        // TODO Auto-generated method stub
        System.out.println("Munchkin meows!");
    }

}
class CatFactory {

    public static Cat getCat(String cat){
        if(null == cat)
            return null;
        else if(cat.equalsIgnoreCase("Munchkin"))
            return new Munchkin();
        else if(cat.equalsIgnoreCase("AmericanSharthaie"))
            return new AmericanSharthaie();
        else if(cat.equalsIgnoreCase("Ragdoll"))
            return new Ragdoll();
        else
            return null;
    }
}

public class factory {
    public static void main(String[] args){

        Cat cat1 = CatFactory.getCat("Munchkin");
        cat1.Meow();

        Cat cat2 = CatFactory.getCat("AmericanSharthaie");
        cat2.Meow();

        Cat cat3 = CatFactory.getCat("Ragdoll");
        cat3.Meow();
    }
}

/* Out:
 * Munchkin meows!
 * AmericanSharthaie meows!
 * Ragdoll meows!

*/
