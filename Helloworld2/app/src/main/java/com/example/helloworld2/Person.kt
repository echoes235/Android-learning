package com.example.helloworld2

open class Person(var name: String, var age: Int)//加open关键字才是能被继承
{
    fun eat()
    {
        println(name + " is eating. He is " + age + " years old.")
    }
}