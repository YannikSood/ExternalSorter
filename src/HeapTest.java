import java.io.IOException;
import student.TestCase;

public class HeapTest extends TestCase {
    private Heap heap;
    
    private Record rec1;
    private Record rec2;
    
    private byte[] key1;
    private byte[] value1;
    private byte[] key2;
    private byte[] value2;
    
    /**
     * Set Up the test case
     */
    public void setUp() {
        this.heap = new Heap(); // empty heap
        
        // some more realistic byte arrays for rec1 and rec2
        this.key1 = new byte[] {113, -49, 34, -48, 2, -20, 59, -64};
        this.value1 = new byte[] {74, -1, 102, -38, 10, 58, 66, -3};
        this.key2 = new byte[] {16, 61, 103, 74, 12, -77, 25, 65};
        this.value2 = new byte[] {58, 121, 114, 105, 108, 23, 64, -83};
        
        this.rec1 = new Record(key1, value1);
        this.rec2 = new Record(key2, value2);

    }

    /**
     * Tests the Heap constructor.
     */
    public void testHeap() {
        assertEquals(0, heap.getSize());
    }

    /**
     * Trivial test for getSize() before more thorough testing in
     * combination with other methods such as insert.
     */
    public void testCurrSize() {
        assertEquals(0, heap.getSize());
        heap.insert(rec1);
        heap.insert(rec2);
        assertEquals(2, heap.getSize());
    }
    
    /**
     * Trivial test for get root.
     */
    public void testGetRoot() {
        heap.insert(rec1);
        heap.insert(rec2);
        assertEquals(rec2, heap.getRoot());
    }

    /**
     * Test is a record is in a leaf position.
     */
    public void testIsLeaf() {
        // invalid tests
        assertFalse(heap.isLeaf(-1));
        
        // empty tests
        assertFalse(heap.isLeaf(0));
        assertFalse(heap.isLeaf(1));
        
        // insert one test if it's a leaf
        heap.insert(rec1);
        assertEquals(1, heap.getSize());
        assertTrue(heap.isLeaf(0));
        
        // insert a second record that's smaller, requiring heapify
        // this kind of tests insert as well and I actually found a bug early
        heap.insert(rec2);
        assertEquals(2, heap.getSize());
        assertFalse(heap.isLeaf(0));
        assertTrue(heap.isLeaf(1));
        assertFalse(heap.isLeaf(2)); // record not added yet to right child
        
        // going to insert key 1 that is larger so it won't require a heapify
        // to test if right child is now a leaf
        heap.insert(rec1);
        assertEquals(3, heap.getSize());
        
        assertFalse(heap.isLeaf(-1)); // invalid check again
        
        assertFalse(heap.isLeaf(0));
        assertTrue(heap.isLeaf(1));
        assertTrue(heap.isLeaf(2));
        assertFalse(heap.isLeaf(3)); // child doesn't exist yet
        assertFalse(heap.isLeaf(4)); // child doesn't exist yet
        assertFalse(heap.isLeaf(5)); // child doesn't exist yet
        assertFalse(heap.isLeaf(6)); // child doesn't exist yet
    }
    
    /**
     * Test return position of the left child of pos
     */
    public void testLeftChild() {
        // invalid tests
        assertEquals(-2, heap.leftChild(-3)); // i decided to return -2
        
        // empty tests
        assertEquals(-1, heap.leftChild(0));
        assertEquals(-1, heap.leftChild(1));
        assertEquals(-1, heap.leftChild(2));
        
        heap.insert(rec1);
        assertEquals(1, heap.getSize()); // sanity check
        
        assertEquals(-1, heap.leftChild(0));
        
        heap.insert(rec2);
        assertEquals(1, heap.leftChild(0)); // left child of root
        assertEquals(-1, heap.leftChild(1)); // left child of root's left child
    }

    /**
     * Test return position of the right child of pos
     */
    public void testRightChild() {
        // invalid tests
        assertEquals(-2, heap.rightChild(-2)); // i decided to return -2
        
        // empty tests
        assertEquals(-1, heap.rightChild(0));
        assertEquals(-1, heap.rightChild(1));
        assertEquals(-1, heap.rightChild(2));
        
        heap.insert(rec1);
        assertEquals(1, heap.getSize()); // sanity check
        
        assertEquals(-1, heap.rightChild(0));
        
        heap.insert(rec2);
        heap.insert(new Record(new byte[] {1, 2, 3, 4, 5, 6, 7, 8}, value1));
        assertEquals(2, heap.rightChild(0)); // right child of root
        assertEquals(-1, heap.rightChild(2)); // right of root's right child
    }

    /**
     * Test return of position of parent.
     * 
     * Note: this returns a value even if parent does not
     *       exist per the canvas implementation.
     */
    public void testParent() {
        // invalid test
        assertEquals(-1, heap.parent(-1));
        
        // testing parent positions
        assertEquals(-1, heap.parent(0));
        assertEquals(0, heap.parent(1));
        assertEquals(0, heap.parent(2));
        
        // more positions until satisfied
        assertEquals(1, heap.parent(3));
        assertEquals(1, heap.parent(4));
        assertEquals(2, heap.parent(5));
        assertEquals(2, heap.parent(6));
    }
    
    // Note: swapNodes must be functional for following insertion tests.
    //
    //

    /**
     * Test insertion to the end of heap.
     * Insertion also handles sifting upwards.
     * Should not sift if value is equal (requires remove(pos) to be functional
     * for testing.)
     */
    public void testInsertDuplicate() {
        // test duplicate key scenario
        heap.insert(rec1);
        heap.insert(new Record(new byte[] {113, -49, 34, -48, 2, -20, 59, -64},
            value2));
        assertEquals(rec1, heap.remove(0)); // this should remove rec1
    }
    
    /**
     * Test insertion to the end of heap.
     * Insertion also handles sifting upwards.
     */
    public void testInsertSiftLeftOnce() {
        heap.insert(rec1);
        heap.insert(rec2);
        assertEquals(rec2, heap.remove(0)); // this should remove rec2
    }
    
    /**
     * Test insertion to the end of heap.
     * Insertion also handles sifting upwards.
     */
    public void testInsertSiftRightOnce() {
        heap.insert(rec1);
        heap.insert(rec1);
        byte[] temp = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        heap.insert(new Record(temp, value2));
        assertEquals(temp, heap.remove(0).getKey());
    }
    
    /**
     * Test insertion to the end of heap.
     * Insertion also handles sifting upwards.
     */
    public void testInsertSiftLeftMult() {
        // build condition
        for (int i = 0; i < 7; i++) {
            heap.insert(rec1);
        }
        
        // insert
        heap.insert(rec2); // this should sift to root
        assertEquals(rec2, heap.remove(0));
    }
    
    /**
     * Test insertion to the end of heap.
     * Insertion also handles sifting upwards.
     */
    public void testInsertSiftRightMult() {
        // build condition
        for (int i = 0; i < 14; i++) {
            heap.insert(rec1);
        }
        
        // insert
        heap.insert(rec2); // this should sift to root
        assertEquals(rec2, heap.remove(0));
    }
    
    /**
     * Test insertion to a Heap that is full.
     */
    public void testInsertFull() {
        // build condition
        for (int i = 0; i < 512*8; i++) {
            heap.insert(rec1);
        }
        
        assertEquals(512*8, heap.getSize());
        heap.insert(rec2);
        assertEquals(512*8, heap.getSize());
    }
    
    /**
     * Test removeMin method.
     * Note: Requires siftDown and swapNodes to be functional.
     */
    public void testRemoveMinBaseCases() {
        // empty case
        assertNull(heap.removeMin());
        
        // last element case
        heap.insert(rec1);
        assertEquals(rec1, heap.removeMin()); // should swap with same pos
    }
    
    /**
     * Test removeMin method.
     * Note: Requires siftDown and swapNodes to be functional.
     */
    public void testRemoveMinNoSifting() {
        // going to use value1 value2 as other keys
        heap.insert(rec1); // 113
        heap.insert(rec2); // 16
        heap.insert(new Record(value2, value2)); // 58
        
        // remove 16 so that 58 is placed at root
        assertEquals(rec2, heap.removeMin());
        
        // should remove 58 because it will not have sifted down
        // after removing 16
        assertEquals(value2, heap.removeMin().getKey());
    }
    
    /**
     * Test removeMin method.
     * Note: Requires siftDown and swapNodes to be functional.
     */
    public void testRemoveMinSifting() {
        // going to use value1 value2 as other keys
        heap.insert(rec1); // 113
        heap.insert(rec2); // 16
        heap.insert(new Record(value2, value2)); // 58
        heap.insert(new Record(value1, value1)); // 74
        
        // used debugger and structure is as expected
        // now let's removeMin() and cause sifting
        heap.removeMin(); // returns 16
        
        // 113 will be root but should switch with 58
        assertEquals(value2, heap.removeMin().getKey());
    }

    /**
     * Test remove record at pos.
     * Note: Requires swapNodes and update to be functional.
     */
    public void testRemovePosBaseCases() {
        // invalid inputs
        assertNull(heap.remove(-1)); // below 0
        assertNull(heap.remove(2)); // larger than current size of heap
        
        // empty case
        assertNull(heap.remove(0)); // not larger than 0
        
        // removing the last element
        heap.insert(rec1);
        assertEquals(1, heap.getSize());
        
        // some more case testing
        assertNull(heap.remove(1)); // positive pos but equal to nHeap
        assertNull(heap.remove(2)); // positive pos but larger than nHeap
        
        // actually remove now, it's the last element in Heap, no update req.
        assertEquals(rec1, heap.remove(0));
        assertEquals(0, heap.getSize());
    }
    
    /**
     * Test remove record at pos.
     * Note: Requires swapNodes and update to be functional.
     */
    public void testRemovePos() {
        // going to use value1 value2 as other keys
        heap.insert(new Record(value2, value1)); // 58
        heap.insert(rec2); // 16
        heap.insert(rec2); // 16
        heap.insert(new Record(value2, value2)); // 58
        heap.insert(new Record(value1, value1)); // 74
        
        // remove pos 1 will cause 74 to have to switch with 58
        // should be the first 58 added with ID v1
        // covers the siftDown case for update
        assertEquals(value1, heap.remove(1).getValue());
        
        // add another 16 and 12 (turned out to not be needed tho)
        heap.insert(rec2);
        heap.insert(new Record(new byte[] {12, 1, 1, 1, 1, 1, 1, -1},
            value1));
        
        // remove pos 1 causes 16 (rec2) to switch with 58, but 58 will not move
        assertEquals(rec2, heap.remove(1));
        assertEquals(value2, heap.remove(1).getValue()); // remove that 58 v2
        
        // this function might not even be needed
        // can't test sift upwards because unable to create un-heapified heap
    }

    /**
     * Test that contents loaded to heap via pre-load constructor are
     * heapified. Unless we use constructor often, this may happen only once.
     * 
     * @throws IOException 
     */
    public void testMakeMinHeap() throws IOException {
        // empty case, does nothing
        assertEquals(0, heap.getSize());
        heap.makeMinHeap();
        
        // 10 records loaded to heap
        Heap tempHeap = new Heap(new byte[] {77, 0, 0, 0, 0, 0, 0, 0, 
                                             1, 1, 1, 1, 1, 1, 1, 1,
                                             68, 0, 0, 0, 0, 0, 0, 0,
                                             2, 2, 2, 2, 2, 2, 2, 2,
                                             96, 0, 0, 0, 0, 0, 0, 0,
                                             3, 3, 3, 3, 3, 3, 3, 3,
                                             78, 0, 0, 0, 0, 0, 0, 0,
                                             4, 4, 4, 4, 4, 4, 4, 4,
                                             22, 0, 0, 0, 0, 0, 0, 0,
                                             5, 5, 5, 5, 5, 5, 5, 5,
                                             23, 0, 0, 0, 0, 0, 0, 0,
                                             6, 6, 6, 6, 6, 6, 6, 6,
                                             28, 0, 0, 0, 0, 0, 0, 0,
                                             7, 7, 7, 7, 7, 7, 7, 7,
                                             35, 0, 0, 0, 0, 0, 0, 0,
                                             8, 8, 8, 8, 8, 8, 8, 8,
                                             20, 0, 0, 0, 0, 0, 0, 0,
                                             9, 9, 9, 9, 9, 9, 9, 9,
                                             79, 0, 0, 0, 0, 0, 0, 0,
                                             10, 10, 10, 10, 10, 10, 10, 10,},
                                                                        160);
        
        // if it was heapified correctly...
        assertEquals(10, tempHeap.getSize());
        byte[] checkResult = new byte[] {20, 22, 23, 35, 77,
            96, 28, 68, 78, 79};
        
        for (int i = 9; i >= 0; i--) {
            assertEquals(checkResult[i],
                tempHeap.remove(tempHeap.getSize() - 1).getKey()[0]);
        }
    }

    /**
     * Modify the value of a node and update, probably also not needed.
     * Note: Requires update to be working.
     */
    public void testModify() {
     // 10 records loaded to heap
        Heap tempHeap = new Heap(new byte[] {77, 0, 0, 0, 0, 0, 0, 0, 
                                             1, 1, 1, 1, 1, 1, 1, 1,
                                             68, 0, 0, 0, 0, 0, 0, 0,
                                             2, 2, 2, 2, 2, 2, 2, 2,
                                             96, 0, 0, 0, 0, 0, 0, 0,
                                             3, 3, 3, 3, 3, 3, 3, 3,
                                             78, 0, 0, 0, 0, 0, 0, 0,
                                             4, 4, 4, 4, 4, 4, 4, 4,
                                             22, 0, 0, 0, 0, 0, 0, 0,
                                             5, 5, 5, 5, 5, 5, 5, 5,
                                             23, 0, 0, 0, 0, 0, 0, 0,
                                             6, 6, 6, 6, 6, 6, 6, 6,
                                             28, 0, 0, 0, 0, 0, 0, 0,
                                             7, 7, 7, 7, 7, 7, 7, 7,
                                             35, 0, 0, 0, 0, 0, 0, 0,
                                             8, 8, 8, 8, 8, 8, 8, 8,
                                             20, 0, 0, 0, 0, 0, 0, 0,
                                             9, 9, 9, 9, 9, 9, 9, 9,
                                             79, 0, 0, 0, 0, 0, 0, 0,
                                             10, 10, 10, 10, 10, 10, 10, 10,},
                                                                        160);
        
        // tests
        // this will actually cover the siftUp case in update as well!
        assertEquals(10, tempHeap.getSize());
        byte[] temp = new byte[] {5, 0, 0, 0, 0, 0, 0, 0};
        tempHeap.modify(1, new Record(temp, value1));
        
        // root should be 5 now
        assertEquals(temp, tempHeap.removeMin().getKey());
    }

}
