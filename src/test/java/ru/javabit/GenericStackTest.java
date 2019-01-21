package ru.javabit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenericStackTest {

    GenericStack<Integer> intStack;;
//    GenericStack<Object> objStack;
//    GenericStack<String> strStack

    @Before
    public void before(){
        intStack = new GenericStack<Integer>(Integer.class, 8);
//        strStack = new GenericStack<String>(String.class, 8);
//        objStack = new GenericStack<Object>(Object.class, 200);
    }

//    @Test
//    public void tryDifferentTypes(){ }
//
//    @Test
//    public void tryTypeProtection(){ }

    @Test
    public void push() throws Exception {
        intStack.push(5);
        intStack.push(5);
        intStack.push(5);
        intStack.push(3);
        intStack.push(9);
        intStack.push(10);
        assertTrue(intStack.getSize() == 6);
        assertTrue(intStack.pop() == 10);
        intStack.peek();
        assertTrue(intStack.pop() == 9);
    }

    @Test(expected = StackException.class)
    public void pushAboveLimit() throws StackException{
        for (int i=0; i < 20; i++){
            intStack.push(i);
        }
    }

    @Test
    public void pop() throws Exception {
        intStack.push(1);
        intStack.push(2);
        intStack.push(5);
        int i = (Integer) intStack.pop();
        Assert.assertEquals(i, 5);
    }

    @Test(expected = StackException.class)
    public void popThanWaste() throws Exception {
        intStack.push(5);
        intStack.pop();
        intStack.pop();
    }

    @Test(expected = StackException.class)
    public void peek() throws Exception {
        intStack.push(5);
        intStack.pop();
        intStack.peek();
    }

    @Test(expected = StackException.class)
    public void peekWhenWaste() throws Exception {
        intStack.push(5);
        intStack.pop();
        intStack.peek();
    }

    @Test
    public void getSize() throws Exception {
        int i;
        for (i=0; i < 6; i++) {
            intStack.push(i);
        }
        assertSame(intStack.getSize(), i);
    }

    @Test
    public void isEmpty() throws StackException {
        intStack.push(6);
        intStack.push(6);
        intStack.pop();
        intStack.pop();
        assertTrue(intStack.isEmpty());
    }

    @Test
    public void isFull() throws Exception {
        assertTrue(!intStack.isFull());
        intStack.push(5);
        intStack.push(5);
        intStack.push(5);
        intStack.push(3);
        intStack.push(10);
        intStack.push(5);
        intStack.push(5);
        assertTrue(!intStack.isFull());
        intStack.push(5);
        assertTrue(intStack.isFull());
    }

    @Test
    public void pushAll() throws Exception {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(2);
        intList.add(7);
        intList.add(14);
        int s1 = intList.size();
        intStack.pushAll(intList);
        int s2 = intStack.getSize();
        Assert.assertTrue(s1 == s2);
        int i = intStack.pop();
        Assert.assertEquals(i, 14);
    }

    @Test(expected = StackException.class)
    public void pushAllMoreThanCapacity() throws Exception {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(5);
        intList.add(2);
        intList.add(7);
        intList.add(14);
        intList.add(5);
        intList.add(2);
        intList.add(7);
        intList.add(14);
        intList.add(14);
        intStack.pushAll(intList);
    }

    @Test
    public void popAll() throws Exception {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intStack.push(5);
        intStack.push(5);
        intStack.push(5);
        int s1 = intStack.getSize();
        intStack.popAll(intList);
        int s2 = intList.size();
        Assert.assertEquals(s1, s2);
    }

    @Test(expected = StackException.class)
    public void popAllThanEmpty() throws Exception {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intStack.push(5);
        intStack.push(5);
        intStack.push(5);
        intStack.pop();
        intStack.pop();
        intStack.pop();
        intStack.popAll(intList);
    }

    @Test
    public void getEntityType() throws Exception {
        assertTrue(intStack.getEntityType() == Integer.class);
    }
}