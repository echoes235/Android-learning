package com.example.helloworld2

fun methonName(param1: Int, param2: Int): Int
{
    return 0
}

fun getmx(num1: Int, num2: Int): Int
{
//    return max(num1, num2)
    //kt里面的if语句是有返回值的,返回值就是每个if里面的最后一行代码的返回值
    var value = if (num1 > num2) num1
    else num2
    return value
}

fun getscore(name: String) = when (name)
{
    "Tom" -> 65
    "Jim" -> 70
    "Bob" -> 80
    "Alice" -> 90
    else -> 0
}

fun checkNumber(num: Number)
{
    when (num)
    {
        //is相当于java的instanceof
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

fun main()
{
    println("Hello Kotlin")
    val a = 10;//编译器会自动推到val的类型
    //如果延迟赋值,就需要
    val b: Int = 10;
    println("a = " + a)
    //val关键字声明不可变的变量,而var关键字声明可变变量
    var tmp = 10
    tmp *= 10
    println(getmx(tmp, a))
    //kt中可以这么来表示一个范围(左闭右闭)
    var range = 0..10
    var range2 = 0 until 10//这样子就是左闭右开
    for (i in 1..10)
    {
        print(i)
        print(" ")
    }
    print('\n')
    //这里step2相当于i=i+2
    for (i in 0 until 10 step 2)
    {
        print(i)
        print(" ")
    }
    print('\n')
    //倒叙的方式
    for (i in 10 downTo 0 step 2)
    {
        print(i)
        print(" ")
    }
    print('\n')
    var p = Person("jack", 12)
    p.eat()
    val s = Student("1123", 96, "xiaoming", 12)
    var c1 = Cellphone("hongmi", 1299)
    var c2 = Cellphone("hongmi", 1299)
    println(c1)

    //集合类
    val list = listOf("Apple", "Banana", "Orange")
    for (fruit in list)
    {
        println(fruit)
    }

    val list2 = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit = list2.maxBy { it.length }//maxBy函数接受一个Lambda类型的参数,返回根据Lambda找到的最大值
    val newlist = list2.map { it.uppercase() }//map也接受一个函数式API,把集合里的每个元素映射成另外的值
    for (newfruit in newlist)
    {
        println(newfruit)
    }

    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    //any,all接受一个条件返回一个bool值,不用多少,至少有和是否全是
    println("anyResult is " + anyResult + ", allResult is " + allResult)
    dostudy(null)//对于空指针问题,kt把它提前到了编译前检查,如果本身就需要空指针,那么需要在调用的参数类型后加?
    //但是同时,你要处理好传入空指针可能的问题,见dostudy函数
}

fun dostudy(study: Study?)
{
    if (study != null)
    {
        study.doHomework()
        study.readbooks()
    }
    //判空处理代码
    //不为空则执行,为空则忽略
    study?.readbooks()
    //还有非空断言工具!!,意在告诉编译器我确信这里不会为空,你可以忽略
//    study!!.readbooks()

    //还有let函数,它可以把对象作为参数直接传递到lambda表达式里面,并且直接执行,所以我们可以这么写
    study?.let { stu ->
//        stu.readBooks()
        stu.doHomework()
    }
}