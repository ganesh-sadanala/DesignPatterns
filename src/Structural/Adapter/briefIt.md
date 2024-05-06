- Allows objects with incompatible interfaces to collaborate.
- Acts as an conversion/compatibility Adapter.
- Follows Single Responsibility and Open/Closed Principles.
- **Object Adapter Structure**
- ![img.png](img.png)
- **Class Adapter Structure**
- ![img_1.png](img_1.png)
- **Example**
- ![img_2.png](img_2.png)
- Use the Adapter class when you want to use some existing class, but its interface isn’t compatible with the rest of your code.
- Use the pattern when you want to reuse several existing subclasses that lack some common functionality that can’t be added to the superclass.

In other words:\
The Adapter design pattern is a structural design pattern that allows objects with incompatible interfaces to collaborate. It acts as a bridge between two incompatible interfaces, converting the interface of one class into another interface that clients expect.

The main purpose of the Adapter pattern is to enable classes with different interfaces to work together without modifying their existing code. It provides a way to wrap an existing class with a new interface to make it compatible with the client's interface.

Here are the key components of the Adapter pattern:

- **Target Interface:** This is the interface that the client expects to work with. It defines the methods that the client will call.
- **Adaptee:** This is the existing class or interface that needs to be adapted. It has a different interface than what the client expects.
- **Adapter:** This is the class that implements the Target Interface and adapts the Adaptee to work with the Target Interface. It holds an instance of the Adaptee and translates the calls from the Target Interface to the Adaptee's interface.