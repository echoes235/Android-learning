package com.example.helloworld2

class Student(var sno: String, var grade: Int, name: String, age: Int) : Person(name, age), Study
{
    override fun readbooks()
    {
        println(name + "is reading")
    }

    //对dohomework的重写可以省去,因为kt支持对接口中的函数进行默认实现
    override fun doHomework()
    {
        println(name + "is dohomework")
    }
}