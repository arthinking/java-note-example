package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 15 泛型
 *   15 混型
 * 混型最基本的概念：混合多个类的能力，混型的价值之一是可以将特性和行为一致地应用于多个类之上。
 * 混型有点面向切面编程的味道。
 *     1 C++中的混型
 * 
 */
/******************************************
 *  C++代码
 ******************************************/
//: generics/Mixins.cpp
#include <string>
#include <ctime>
#include <iostream>
using namespace std;

template<class T> class TimeStamped : public T {
long timeStamp;
public:
TimeStamped() { timeStamp = time(0); }
long getStamp() { return timeStamp; }
};

template<class T> class SerialNumbered : public T {
long serialNumber;
static long counter;
public:
SerialNumbered() { serialNumber = counter++; }
long getSerialNumber() { return serialNumber; }
};

//Define and initialize the static storage:
template<class T> long SerialNumbered<T>::counter = 1;

class Basic {
string value;
public:
void set(string val) { value = val; }
string get() { return value; }
};  

int main() {
TimeStamped<SerialNumbered<Basic> > mixin1, mixin2;
mixin1.set("test string 1");
mixin2.set("test string 2");
cout << mixin1.get() << " " << mixin1.getStamp() <<
  " " << mixin1.getSerialNumber() << endl;
cout << mixin2.get() << " " << mixin2.getStamp() <<
  " " << mixin2.getSerialNumber() << endl;
} /* Output: (Sample)
test string 1 1129840250 1
test string 2 1129840250 2
*///:~



public class Chapter15_14 {
    
    public static void main(String[] args) {

    }
}






package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 15 泛型
 *   15 混型
 * 混型最基本的概念：混合多个类的能力，混型的价值之一是可以将特性和行为一致地应用于多个类之上。
 * 混型有点面向切面编程的味道。
 *     1 C++中的混型
 * 
 */
/******************************************
 *  C++代码
 ******************************************/
//: generics/Mixins.cpp
#include <string>
#include <ctime>
#include <iostream>
using namespace std;

template<class T> class TimeStamped : public T {
long timeStamp;
public:
TimeStamped() { timeStamp = time(0); }
long getStamp() { return timeStamp; }
};

template<class T> class SerialNumbered : public T {
long serialNumber;
static long counter;
public:
SerialNumbered() { serialNumber = counter++; }
long getSerialNumber() { return serialNumber; }
};

//Define and initialize the static storage:
template<class T> long SerialNumbered<T>::counter = 1;

class Basic {
string value;
public:
void set(string val) { value = val; }
string get() { return value; }
};  

int main() {
TimeStamped<SerialNumbered<Basic> > mixin1, mixin2;
mixin1.set("test string 1");
mixin2.set("test string 2");
cout << mixin1.get() << " " << mixin1.getStamp() <<
  " " << mixin1.getSerialNumber() << endl;
cout << mixin2.get() << " " << mixin2.getStamp() <<
  " " << mixin2.getSerialNumber() << endl;
} /* Output: (Sample)
test string 1 1129840250 1
test string 2 1129840250 2
*///:~



public class Chapter15_15_1 {
    
    public static void main(String[] args) {

    }
}
