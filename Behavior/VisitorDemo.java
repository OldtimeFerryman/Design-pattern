package Behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * 将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，
 * 为数据结构中的每个元素提供多种访问方式。
 *
 * 访问者（Visitor）模式是一种对象行为型模式，其主要优点如下。
 * 扩展性好。能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 复用性好。可以通过访问者来定义整个对象结构通用的功能，从而提高系统的复用程度。
 * 灵活性好。访问者模式将数据结构与作用于结构上的操作解耦，使得操作集合可相对自由地演化而不影响系统的数据结构。
 * 符合单一职责原则。访问者模式把相关的行为封装在一起，构成一个访问者，使每一个访问者的功能都比较单一。
 *
 * 访问者（Visitor）模式的主要缺点如下。
 * 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类，都要在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”。
 * 破坏封装。访问者模式中具体元素对访问者公布细节，这破坏了对象的封装性。
 * 违反了依赖倒置原则。访问者模式依赖了具体类，而没有依赖抽象类。
 *
 * 访问者模式包含以下主要角色。
 * 抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素。
 * 具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
 * 抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
 * 具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，
 * 另外具体元素中可能还包含本身业务逻辑的相关操作。
 * 对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，通常由 List、Set、Map 等聚合类实现。
 *
 * 基本想法是，软件系统中拥有一个由许多对象构成的、比较稳定的对象结构，这些对象的类都拥有一个 accept 方法用来接受访问者对象的访问。
 * 访问者是一个接口，它拥有一个 visit 方法，这个方法对访问到的对象结构中不同类型的元素做出不同的处理。在对象结构的一次访问过程中，
 * 我们遍历整个对象结构，对每一个元素都实施 accept 方法，在每一个元素的 accept 方法中会调用访问者的 visit 方法，从而使访问者得以处理对象
 * 结构的每一个元素，我们可以针对对象结构设计不同的访问者类来完成不同的操作，达到区别对待的效果。
 *
 */

//抽象元素（Element）
interface Grade {
    void accept(Viewer viewer);

}

//具体元素（ConcreteElement） 必修课
class Compulsory implements Grade{
    private String Stuname;
    private String CourseName;
    private double Score;

    public Compulsory(String stu, String name, double score) {
        super();
        Stuname = stu;
        CourseName = name;
        Score = score;
    }

    public void accept(Viewer viewer) {
        viewer.view(this);
    }

    public String getName() {
        return CourseName;
    }

    public String getStuName(){
        return Stuname;
    }

    public Double getScore() {
        return Score;
    }
}

//具体元素（ConcreteElement） 限选课
class Restricted implements Grade{
    private String Stuname;
    private String CourseName;
    private double Score;

    public Restricted (String stu, String name, double score) {
        super();
        Stuname = stu;
        CourseName = name;
        Score = score;
    }

    public void accept(Viewer viewer) {
        viewer.view(this);
    }

    public String getStuName(){
        return Stuname;
    }

    public String getName() {
        return CourseName;
    }

    public Double getScore() {
        return Score;
    }
}

// 抽象访问者（Visitor）
interface Viewer {

    void view(Compulsory grade);

    void view(Restricted bill);

}

//具体访问者（ConcreteVisitor）
class Student implements Viewer{

    public void view(Compulsory grade) {
        System.out.println(grade.getStuName() + " . " + grade.getName() + " : " + grade.getScore());
    }

    public void view(Restricted grade) {
        System.out.println(grade.getStuName() + " . " + grade.getName() + " : " + grade.getScore());
    }
}

//具体访问者（ConcreteVisitor）
class Teacher implements Viewer{
    private double CompulsoryTotal = 0;
    private String CompulsoryName;
    private int CompulsoryCnt = 0;
    private double ReStrictedTotal = 0;
    private String ReStrictedName;
    private int ReStrictedCnt = 0;

    public void view(Compulsory grade) {
        CompulsoryName = grade.getName();
        CompulsoryTotal += grade.getScore();
        CompulsoryCnt += 1;
    }

    public void view(Restricted grade) {
        ReStrictedName = grade.getName();
        ReStrictedTotal += grade.getScore();
        ReStrictedCnt += 1;
    }

    public void show(){
        System.out.println(CompulsoryName + " Mean score : " + Double.toString(CompulsoryTotal/CompulsoryCnt));
        System.out.println(ReStrictedName + " Mean score : " + Double.toString(ReStrictedTotal/ReStrictedCnt));
    }
}


//成绩单类（相当于ObjectStruture）
class GradeBook {
    //成绩列表
    private List<Grade> gradeList = new ArrayList<Grade>();
    //添加成绩
    public void addGrade(Grade grade){
        gradeList.add(grade);
    }
    //供成绩的查看者查看成绩
    public void show(Viewer viewer){
        for (var grade : gradeList) {
            grade.accept(viewer);
        }
    }
}


public class VisitorDemo {
    public static void main(String[] args) {
        GradeBook book = new GradeBook();

        book.addGrade(new Compulsory("Joe", "Discrete Mathematics", 80));
        book.addGrade(new Compulsory("Jane", "Discrete Mathematics", 75));
        book.addGrade(new Compulsory("Luff", "Discrete Mathematics", 70));

        book.addGrade(new Restricted("Joe", "C ++", 80));
        book.addGrade(new Restricted("Jane", "C ++", 75));
        book.addGrade(new Restricted("Luff", "C ++", 70));

        Viewer stu = new Student();
        Viewer teacher = new Teacher();

        System.out.println("What student see:");
        book.show(stu);
        book.show(teacher);

        System.out.println("\nWhat teacher see:");
        ((Teacher) teacher).show();
    }
}
