package Behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。
 * 对象之间存在复杂的交互关系，这种交互关系常常是“网状结构”，它要求每个对象都必须知道它需要交互的对象。
 * 把这种“网状结构”改为“星形结构”的话，将大大降低它们之间的“耦合性”，这时只要找一个“中介者”就可以了。
 *
 * 其主要优点如下:
 * 降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 *
 * 其主要缺点是：当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。
 *
 * 中介者模式包含以下主要角色。
 * 抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
 * 抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
 * 具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 */


interface OnlineRoom{
    //转发
    void relay(String msg);
    void register(Person person);
}

class ConcreteOnlineRoom implements OnlineRoom{
    protected List<Person> personlist = new ArrayList<Person>();

    @Override
    public void register(Person person) {
        if(!personlist.contains(person))
        {
            personlist.add(person);
            person.setMediator(this);
        }
    }

    @Override
    public void relay(String msg) {
        for(var person : personlist){
            person.recesive(msg);
        }
    }
}

interface Person{
    void setMediator(OnlineRoom room);
    void send(String msg);
    void recesive(String msg);
}

class ChatPerson implements Person{
    protected OnlineRoom room;
    private String name;

    public ChatPerson(String name){
        this.name = name;
    }

    @Override
    public void setMediator(OnlineRoom room) {
        this.room = room;
    }

    @Override
    public void send(String msg) {
        String str = "User " + name + " sends: " + msg;
        System.out.println(str);
        room.relay(msg);
    }

    @Override
    public void recesive(String msg) {
        System.out.println("User " + name + " received: " + msg);
    }
}

public class MediatorDemo {
    public static void main(String[] args) {
        OnlineRoom room  = new ConcreteOnlineRoom();

        Person p1 = new ChatPerson("Jhon");
        Person p2 = new ChatPerson("Alice");

        room.register(p1);
        room.register(p2);

        p1.send("Hello everyone! i'm Jhon");
        p2.send("Hi Jhon, i'm Alice");
    }
}
