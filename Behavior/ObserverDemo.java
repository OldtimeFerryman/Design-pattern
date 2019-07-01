package Behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 许多对象并不是独立存在的，其中一个对象的行为发生改变可能会导致一个或者多个其他对象的行为也发生改变.
 * 多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式。
 *
 * 主要优点如下。
 * 降低了目标与观察者之间的耦合关系，两者之间是抽象耦合关系。
 * 目标与观察者之间建立了一套触发机制。
 *
 * 它的主要缺点如下。
 * 目标与观察者之间的依赖关系并没有完全解除，而且有可能出现循环引用。
 * 当观察者对象很多时，通知的发布会花费很多时间，影响程序的效率。
 *
 * 观察者模式的主要角色如下。
 * 抽象主题（Subject）角色：也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
 * 具体主题（Concrete    Subject）角色：也叫具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
 * 抽象观察者（Observer）角色：它是一个抽象类或接口，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
 * 具体观察者（Concrete Observer）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
 */
//抽象目标：汇率
abstract class Rate
{
    protected List<Company> companys=new ArrayList<Company>();
    //增加观察者方法
    public void add(Company company)
    {
        companys.add(company);
    }
    //删除观察者方法
    public void remove(Company company)
    {
        companys.remove(company);
    }
    public abstract void change(int number);
}
//具体目标：人民币汇率
class RMBrate extends Rate
{
    public void change(int number)
    {
        for(Company company :companys)
        {
            company.response(number);
        }
    }
}
//抽象观察者：公司
interface Company
{
    void response(int number);
}
//具体观察者1：进口公司
class ImportCompany implements Company
{
    public void response(int number)
    {
        if(number>0)
        {
            System.out.println("人民币汇率升值" + number + "个基点，降低了进口产品成本，提升了进口公司利润率。");
        }
        else if(number<0)
        {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了进口产品成本，降低了进口公司利润率。");
        }
    }
}
//具体观察者2：出口公司
class ExportCompany implements Company
{
    public void response(int number)
    {
        if(number>0)
        {
            System.out.println("人民币汇率升值" + number + "个基点，降低了出口产品收入，降低了出口公司的销售利润率。");
        }
        else if(number<0)
        {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了出口产品收入，提升了出口公司的销售利润率。");
        }
    }
}


public class ObserverDemo {
    public static void main(String[] args)
    {
        Rate rate=new RMBrate();
        Company watcher1=new ImportCompany();
        Company watcher2=new ExportCompany();
        rate.add(watcher1);
        rate.add(watcher2);
        rate.change(10);
        rate.change(-9);
    }
}
