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
     * Test is a record is in a leaf position.
     */
    public void testIsLeaf() {
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
    public void testLeftchild() {
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
     * 
     */
    public void testRightchild() {
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


    public void testParent() {
        fail("Not yet implemented");
    }


    public void testInsert() {
        fail("Not yet implemented");
    }


    public void testBuildHeap() {
        fail("Not yet implemented");
    }


    public void testSiftdown() {
        fail("Not yet implemented");
    }


    public void testRemovemax() {
        fail("Not yet implemented");
    }


    public void testRemove() {
        fail("Not yet implemented");
    }

    public void testModify() {
        fail("Not yet implemented");
    }

    /**
     * 
     */
    public void testUpdate() {
        fail("Not yet implemented");
    }

}
