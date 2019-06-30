import java.util.HashMap;
import java.util.Map;

//产品角色
class Product
{

    private Map<String, String> parts = new HashMap<>();

    public void addPart(String item, String name){
        parts.put(item, name);
    }

    public void show()
    {
        System.out.println("Compute contains:");
        for (String key : parts.keySet()) {
            System.out.println(key + " : " + parts.get(key));
        }
    }
}

// 建造者
abstract class Builder
{
    //部件的创建
    abstract void buildCpu(String cpu);
    abstract void buildSsd(String ssd);
    abstract void buildMainBoard(String MainBoard);
    //返回产品对象
    abstract Product getResult();
}

// 具体建造者
class ConcreteBuilder extends Builder
{
    private Product product = new Product();

    @Override
    public void buildCpu(String cpu)
    {
        product.addPart("CPU", cpu);
    }

    @Override
    public void buildSsd(String ssd)
    {
        product.addPart("SSD", ssd);
    }

    @Override
    public void buildMainBoard(String MainBoard)
    {
        product.addPart("MainBoard", MainBoard);
    }

    @Override
    public Product getResult(){
        return product;
    }

}

// 指挥者
class Director
{
    private Builder builder;

    public Director(Builder builder)
    {
        this.builder=builder;
    }

    //产品构建与组装方法
    public Product construct(Map<String, String> Parts)
    {
        builder.buildCpu(Parts.get("CPU"));
        builder.buildSsd(Parts.get("SSD"));
        builder.buildMainBoard(Parts.get("MainBoard"));
        return builder.getResult();
    }
}

public class BuilderDemo {

    /**
     * 在软件开发过程中有时需要创建一个复杂的对象，这个复杂对象通常由多个子部件按一定的步骤组合而成。
     * 由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。
     * 或者说是不同类的不同组合方式
     *
     * 优点： 1、建造者独立，易扩展。 2、便于控制细节风险。
     * 缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
     *
     * 建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，
     * 一般产品类和建造者类是比较稳定的，因此，将主要的业务逻辑封装在导演类中对整体而言可
     * 以取得比较好的稳定性。
     *
     * 其次，建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成，
     * 基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。
     *
     * 建造者（Builder）模式的主要角色如下。
     * 产品角色（Product）：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个滅部件。
     * 抽象建造者（Builder）：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个
     * 返回复杂产品的方法 getResult()。
     * 具体建造者(Concrete Builder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。
     * 指挥者（Director）：它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，
     * 在指挥者中不涉及具体产品的信息。
     */

    public static void main(String[] args) {
        Map<String, String> temp = new HashMap<>();
        temp.put("CPU", "Intel i7 6950X");
        temp.put("SSD", "SamSung 860PRO 500G");
        temp.put("MainBoard", " 微星B450M MORTAR");

        Builder builder=new ConcreteBuilder();
        Director director=new Director(builder);
        Product product=director.construct(temp);
        product.show();
    }

}

//Console OUT:
//Compute contains:
//SSD : SamSung 860PRO 500G
//CPU : Intel i7 6950X
//MainBoard :  微星B450M MORTAR
