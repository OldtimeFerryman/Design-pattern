package Behavior;

/**
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 *
 * 对这种有状态的对象编程，传统的解决方案是：将这些所有可能发生的情况全都考虑到，然后使用 if-else 语句来做状态判断，
 * 再进行不同情况的处理。但当对象的状态很多时，程序会变得很复杂。而且增加新的状态要添加新的 if-else 语句，
 * 这违背了“开闭原则”，不利于程序的扩展。
 *
 * 状态模式是一种对象行为型模式，其主要优点如下。
 * 状态模式将与特定状态相关的行为局部化到一个状态中，并且将不同状态的行为分割开来，满足“单一职责原则”。
 * 减少对象间的相互依赖。将不同的状态引入独立的对象中会使得状态转换变得更加明确，且减少对象间的相互依赖。
 * 有利于程序的扩展。通过定义新的子类很容易地增加新的状态和转换。
 *
 * 状态模式的主要缺点如下。
 * 状态模式的使用必然会增加系统的类与对象的个数。
 * 状态模式的结构与实现都较为复杂，如果使用不当会导致程序结构和代码的混乱。
 *
 * 状态模式包含以下主要角色。
 * 环境（Context）角色：也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
 * 抽象状态（State）角色：定义一个接口，用以封装环境对象中的特定状态所对应的行为。
 * 具体状态（Concrete State）角色：实现抽象状态所对应的行为。
 */

//环境类
class StudentScore
{
    private AbstractState state;

    public void setState(AbstractState state)
    {
        this.state = state;
    }

    public AbstractState getState()
    {
        return state;
    }

    public void CheckState(int score)
    {
        if(null == state){
            state = new LowState(this);
        }
        state.setScore(score);
    }
}
//抽象状态类
abstract class AbstractState
{
    protected StudentScore context;  //环境
    protected String stateName; //状态名

    public abstract void checkState(int score); //检查当前状态

    public void setScore(int score)
    {
        System.out.print("当前分数：" + score );
        checkState(score);
        System.out.println("分，\t当前状态："+context.getState().stateName);
    }
}
//具体状态类：不及格
class LowState extends AbstractState
{
    public LowState(StudentScore context)
    {
        this.context = context;
        stateName = "不及格";
    }
    public LowState(AbstractState state)
    {
        context = state.context;
        stateName = "不及格";
    }
    public void checkState(int score)
    {
        if(score >= 90)
        {
            context.setState(new HighState(this));
        }
        else if(score >= 60)
        {
            context.setState(new MiddleState(this));
        }
    }
}
//具体状态类：中等
class MiddleState extends AbstractState
{
    public MiddleState(AbstractState state)
    {
        context = state.context;
        stateName = "中等";
    }
    public void checkState(int score)
    {
        if(score < 60)
        {
            context.setState(new LowState(this));
        }
        else if(score >= 90)
        {
            context.setState(new HighState(this));
        }
    }
}
//具体状态类：优秀
class HighState extends AbstractState
{
    public HighState(AbstractState state)
    {
        context = state.context;
        stateName = "优秀";
    }
    public void checkState(int score)
    {
        if(score < 60)
        {
            context.setState(new LowState(this));
        }
        else if(score < 90)
        {
            context.setState(new MiddleState(this));
        }
    }
}

public class StateDemo {
    public static void main(String[] args) {
        StudentScore account = new StudentScore();
        System.out.println("学生成绩状态测试：");
        account.CheckState(50);
        account.CheckState(70);
        account.CheckState(90);
    }
}
