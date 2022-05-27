package com;

//Singleton design pattern
public class Singleton {
    public static void main(String[] args)
    {
        EarlyInstantiation object1 = EarlyInstantiation.getInstance();

        LazyInstantiation object2 = LazyInstantiation.getInstance();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Synchronized object3 = Synchronized.getInstance();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking object4 = DoubleCheckedLocking.getInstance();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                DoubleCheckedLocking object5 = DoubleCheckedLocking.getInstance();
            }
        });
        t2.start();
        t3.start();

    }
}
class EarlyInstantiation{
    //static instance
    static EarlyInstantiation obj1 = new EarlyInstantiation();
    //private constructor
    private EarlyInstantiation(){
        System.out.println("In Early instantiation : Instance created");
    }
    //public method to return instance
    public static EarlyInstantiation getInstance(){
        return obj1;
    }
}//Instance created at load time

class LazyInstantiation{
    //static instance
    static LazyInstantiation obj2;
    //private constructor
    private LazyInstantiation(){
        System.out.println("In Lazy instantiation : Instance created");

    }
    //public method to return instance
    public static LazyInstantiation getInstance(){
        if(obj2 == null)
            obj2 =  new LazyInstantiation();
        return obj2;
    }
}//Creation of instance when required

class Synchronized{
    //static instance
    static Synchronized obj3;
    //private constructor
    private Synchronized(){
        System.out.println("In Synchronized : Instance created");

    }
    //public method to return instance and synchronized
    public static synchronized Synchronized getInstance(){
        if(obj3 == null)
            obj3 =  new Synchronized();
        return obj3;
    }
}

class DoubleCheckedLocking{
    //static instance
    static DoubleCheckedLocking obj4;
    //private constructor
    private DoubleCheckedLocking(){
        System.out.println("In DoubleCheckedLocking : Instance created");

    }
    //public method to return instance and synchronized
    public static synchronized DoubleCheckedLocking getInstance(){
        if(obj4 == null)
        {
            synchronized(DoubleCheckedLocking.class){
                if(obj4 == null)
                    obj4 =  new DoubleCheckedLocking();
            }
        }
        return obj4;
    }
}//Checking object is null or not twice


