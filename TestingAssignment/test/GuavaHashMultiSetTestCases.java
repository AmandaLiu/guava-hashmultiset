/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;


/**
 *
 * @author Amanda
 */

/*
 * the test suite test some of methods in hashmultiset class of Guava library.
 */
public class GuavaHashMultiSetTestCases {
    
    Multiset<String> myHashMultiSet;
    Multiset<String> emptyHashMultiSet;
    Set<String> originalSet;
    
    
    public GuavaHashMultiSetTestCases() {
      
    }
    
    @BeforeClass
    public static void setUpClass() {
      
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        myHashMultiSet = HashMultiset.create(); 
        myHashMultiSet.add("Java", 5);
        myHashMultiSet.add("Groovy");
        myHashMultiSet.add("C++", 4);
        myHashMultiSet.add("Ruby",1);
        myHashMultiSet.add("Perl",2);
        
        originalSet = new HashSet<String>();
        originalSet.add("Java");
        originalSet.add("Groovy");
        originalSet.add("C++");
        originalSet.add("Perl");
        originalSet.add("Ruby");

    }
    
    @After
    public void tearDown() {
        myHashMultiSet.clear();
    }
    
    //test count(object o) method  
     @Test
     public void testCount(){
         int result0 = 0;
         int result1 = 1;
         int result2 = 5;
         Assert.assertEquals("1. Test the element only appears once",result1, 
                 myHashMultiSet.count("Groovy"));
         Assert.assertEquals("2. Test the element only appears once",result1, 
                 myHashMultiSet.count("Ruby"));
         Assert.assertEquals("3. Test the element doesn't appear", result0, 
                 myHashMultiSet.count("PHP"));
         Assert.assertEquals("4. Test the element appears more than once",result2, 
                 myHashMultiSet.count("Java"));
     }
     
     //test elementSet() method
     @Test
     public void testElementSet(){
         Set mySet = myHashMultiSet.elementSet();
         
         int mulitSetSize = 13;
         int setSize = 5;
         
         Assert.assertEquals("5. Test the size of hashmultiset ",mulitSetSize, myHashMultiSet.size());
         Assert.assertEquals("6. Test the size of elementset ",setSize, mySet.size());
         Assert.assertTrue(mySet.contains("Java"));
         Assert.assertTrue(mySet.contains("C++"));
         Assert.assertTrue(mySet.contains("Groovy"));
         Assert.assertTrue(mySet.contains("Ruby"));
         Assert.assertTrue(mySet.contains("Perl"));
         
     }
     
     //test entrySet() method
     @Test
     public void testEntrySet(){
         Set<Multiset.Entry<String>> myEntrySet = myHashMultiSet.entrySet();
         Assert.assertEquals("7.Test the size of myEntryset",5,myEntrySet.size());
         
         //test cases for getting the count of each entry of entry set.
         for(Multiset.Entry<String> myEntry: myEntrySet){
             Assert.assertTrue(originalSet.contains(myEntry.getElement()));
             String myString = myEntry.getElement();
             if(myString.equals("Java"))
                  Assert.assertEquals(5,myEntry.getCount());
             else if(myString.equals("C++"))
                 Assert.assertEquals(4,myEntry.getCount());
             else if(myString.equals("Perl"))
                 Assert.assertEquals(2,myEntry.getCount());
             else if(myString.equals("Ruby"))
                 Assert.assertEquals(1,myEntry.getCount());
             else
                 Assert.assertEquals(1,myEntry.getCount());
         }
         
         
     }
     
     //test add(object o, int count) method
     @Test
     public void testAdd() {
         myHashMultiSet.add("PHP", 1);
         Assert.assertEquals("8. test adding new element",1, myHashMultiSet.count("PHP"));
         myHashMultiSet.add("Java",1);
         Assert.assertEquals("9. test adding more existing element",6, myHashMultiSet.count("Java"));
         myHashMultiSet.add("Javascript",0);
         Assert.assertTrue("10. test adding 0 count element",!myHashMultiSet.contains("Javascript"));
         
     }
     
     // test add(object o, int count) method under the condition throwing exception when count is negative
     @Test(expected=IllegalArgumentException.class)
     public void testAddNegativeCount() throws IllegalArgumentException{
         myHashMultiSet.add("HTML",-1);
         Assert.assertTrue(!myHashMultiSet.contains("HTML"));
     }
     
     //test remove(object o, int count) method
     @Test
     public void testRemove(){
        
         Assert.assertEquals("11. Test zero remove",5,  myHashMultiSet.remove("Java", 0));        
         Assert.assertEquals("12. Test one remove",5, myHashMultiSet.remove("Java", 4));
         Assert.assertEquals("13. Test one remove",1, myHashMultiSet.remove("Java", 1));        
         Assert.assertTrue("14.test all remove",!myHashMultiSet.contains("Java"));
         
         // should be improved. add NoSuchElementException
         myHashMultiSet.remove("Java", 4);
         Assert.assertEquals("15. test if contains the element which has been all removed",
                 0, myHashMultiSet.count("Java"));  
         
     }
     
      // test remove(object o, int count) method under the condition throwing exception when count is negative
     @Test(expected=IllegalArgumentException.class)
     public void testRemoveNegativeCount() throws IllegalArgumentException{
         myHashMultiSet.remove("C++", -3);
         Assert.assertTrue("16.test negative count ",!myHashMultiSet.contains("C++"));
     }
     
     //test setCount(object o, int count) method
     @Test
     public void testSetCount(){
         String nullString =null;
         Assert.assertEquals(4, myHashMultiSet.setCount("C++", 3));
         Assert.assertEquals(3, myHashMultiSet.count("C++"));
         Assert.assertEquals(3, myHashMultiSet.setCount("C++", 0));
         Assert.assertTrue(! myHashMultiSet.contains("C++"));
         
         Assert.assertEquals(0, myHashMultiSet.setCount("C++", 6));
         Assert.assertEquals(6, myHashMultiSet.count("C++"));
         
         Assert.assertEquals(0, myHashMultiSet.setCount(nullString, 6));
         Assert.assertEquals(6, myHashMultiSet.count(nullString));
         
     }
     
      // test setCount(object o, int count) method under the condition throwing exception when count is negative
     @Test(expected=IllegalArgumentException.class)
     public void testSetNegativeCount() throws IllegalArgumentException{
         myHashMultiSet.setCount("Ruby", -3);
         Assert.assertTrue("17.test negative count ",!myHashMultiSet.contains("Ruby"));
     }
     
     // test size() method
     @Test
     public void testSize(){
         emptyHashMultiSet = HashMultiset.create();
         Assert.assertEquals("1. test empty multiset",0, emptyHashMultiSet.size());
         Assert.assertEquals("1. test multiset",13, myHashMultiSet.size());
     }
}