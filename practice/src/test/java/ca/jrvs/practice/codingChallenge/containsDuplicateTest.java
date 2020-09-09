package ca.jrvs.practice.codingChallenge;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class containsDuplicateTest{
containsDuplicate mycontainsDuplicate = new containsDuplicate();
   @Test
   public void testContains() {
       assertEquals(true,mycontainsDuplicate.contains(new int[]{1,2,3,1}));
    }

   @Test
   public void testFindBySet() {
       assertEquals(false,mycontainsDuplicate.findBySet(new int[]{1,2,3,4}));
    }
}