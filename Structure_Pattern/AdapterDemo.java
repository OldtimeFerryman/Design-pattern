/**
 * 适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。
 * 这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
 *
 * 优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
 *
 * 缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。
 *
 * Source：需要被适配的类、接口、对象，即Datas。
 * Destination：需要得到的类，Source通过适配得到的类对象，也就是我们期待得到的接口。
 * Adapter：适配器类，协调Source和Destination，使两者能够协同工作。
 */
interface USB{
    //Source
    void connect();
}

class Audio{
    //Destination
    public void play(){
        System.out.println("I am playing <<Lemon>>");
    }
}

// 类适配器模式
class adapter extends Audio implements USB {
    // Adapter
    @Override
    public void connect(){
        System.out.println("This is a USB3.0 interface");
        //use Audio.play() function.
        play();
    }
}

//对象适配器类
class ObjectAdapter implements USB
{
    private Audio audio;
    public ObjectAdapter(Audio audio)
    {
        this.audio = audio;
    }

    @Override
    public void connect()
    {
        audio.play();
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        USB usb = new adapter();
        usb.connect();

        Audio audio = new Audio();
        (new ObjectAdapter(audio)).connect();
    }
}

//This is a USB3.0 interface
//I am playing <<Lemon>>
//I am playing <<Lemon>>
