package ru.progwards.java2.lessons.gc;

import ru.progwards.java2.lessons.gc.InvalidPointerException;
import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.*;

public class Heap {
    private final byte[] heap;
    private final Map<Integer, Integer> filledBlocks; // <ptr, size>
    private final NavigableSet<Block> freeBlocks;
    private final Map<Integer, Integer> codePtrs; // <testPtr, myPtr>
    private boolean isBeforeCompact;
    private int freed;

    Heap(int maxHeapSize) {
        heap = new byte[maxHeapSize];
        filledBlocks = new HashMap<>();
        freeBlocks = new TreeSet<>(Comparator.comparingInt(m -> m.size));
        freeBlocks.add(new Block(0, maxHeapSize));
        codePtrs = new HashMap<>();
        isBeforeCompact = true;
        freed = 0;
    }



    public static void main(String[] args) {
        String a = "DICTID, VERSION, GLOBALDOCID,\n" +
                " DOCTYPEID, DOCTYPEVERSION, DOCSTATEID, CHECKLEVEL, CREATEDATE,\n" +
                " CREATEUSERNAME, CREATEORGID, CREATEORGNAME, CREATEORGSYSTEMNAME, \n" +
                " CREATECOMPLEXNAME, LASTMODIFYDATE, LASTMODIFYUSERNAME, LASTMODIFYCOMPLEXNAME,\n" +
                " LOCALRPLVERSION, OUTERRPLCOMPLEXID, OUTERRPLVERSION, PRIORITY, HASATTACHES, \n" +
                " LOCALRPLTIMESTAMP, ARCHIVE, HAS_ATTACHES, ATTACHES_SIZE, ATTACH_COUNT, VISIBILITYSCOPE, \n" +
                " DELETED, DELETEDASROOT, CHILDREN_COUNT, LEAF_COUNT, ACCESSLEVEL, IDEMPOTENCY_KEYS, HAS_NOTIFICATION_CONFIG \n";
String[] arr = a.split(",");
                System.out.println(arr.length);

    }

    int findToMalloc(int size) {
       Block findBlock = new Block(-1, size);
        Block currBlock = freeBlocks.ceiling(findBlock);
        if (currBlock != null) {
            int currPtr = currBlock.ptr;
            int currSize = currBlock.size;
            filledBlocks.put(currPtr, size);
            freeBlocks.remove(currBlock);
            if (currSize > size) {
                freeBlocks.add(new Block(currPtr + size, currSize - size));
            }
            if (!isBeforeCompact) {
                codePtrs.put(currPtr, null);
            }
            for (int i = currPtr; i < currPtr + size; i++) {
                heap[i] = 1;
            }
            return currPtr;
        }
        return -1;
    }

    public int malloc(int size) throws OutOfMemoryException {
        if (!freeBlocks.isEmpty()) {
            int res = findToMalloc(size);
            if (res != -1) {
                return res;
            } else {
                compact();
                res = findToMalloc(size);
                if (res != -1) {
                    return res;
                } else {
                    throw new OutOfMemoryException(size);
                }
            }
        } else {
            throw new OutOfMemoryException(size);
        }
    }

    public void free(int ptr) throws InvalidPointerException {
        if (!filledBlocks.isEmpty()) {
            Integer newPtr = ptr;
            if (!isBeforeCompact) {
                if (codePtrs.get(ptr) != null) {
                    newPtr = codePtrs.get(ptr);
                }
            }
            if (filledBlocks.containsKey(newPtr)) {
                Integer size = filledBlocks.get(newPtr);
                filledBlocks.remove(newPtr);
                if (!isBeforeCompact) {
                    codePtrs.remove(ptr);
                }
                Block block = new Block(newPtr, size);
                freeBlocks.add(block);
                defrag(block);

                for (int i = newPtr; i < newPtr+size; i++) {
                    heap[i] = 0;
                }
            } else {
                throw new InvalidPointerException(ptr);
            }
        } else {
            throw new InvalidPointerException(ptr);
        }
    }

    public void defrag(Block block) {
        NavigableSet<Block> freeblocksemp = new TreeSet<>(Comparator.comparingInt(m -> m.ptr));
        freeblocksemp.addAll(freeBlocks);
        Block left = freeblocksemp.lower(block);
        Block right = freeblocksemp.higher(block);

        if (left == null && right == null) {
            return;
        }

        int newPtr = block.ptr;
        int newSize = block.size;
        freeBlocks.remove(block);
        if (left != null && left.ptr + left.size == block.ptr) {
            newPtr = left.ptr;
            newSize += left.size;
            freeBlocks.remove(left);
        }
        if (right != null && block.ptr + block.size == right.ptr) {
            newSize += right.size;
            freeBlocks.remove(right);
        }
        freeBlocks.add(new Block(newPtr, newSize));
    }

    public void compact() {
        System.out.println("compact");
        Integer currPtr = 0;
        if (!filledBlocks.isEmpty()) {
            Map<Integer, Integer> tempBlocks = new TreeMap<>();
            for (Integer ptr : filledBlocks.keySet()) {
                Integer size = filledBlocks.get(ptr);
                tempBlocks.put(currPtr, size);
                if (isBeforeCompact) {
                    codePtrs.put(ptr, currPtr);
                }
                currPtr += size;
            }
            filledBlocks.clear();
            filledBlocks.putAll(tempBlocks);
        }
        isBeforeCompact = false;
        if (!freeBlocks.isEmpty()) {
            freeBlocks.clear();
            freeBlocks.add(new Block(currPtr, heap.length-currPtr));
        }
        int count = 0;
        for (int i=0; i < heap.length; i++) {
            if (heap[i] == 1) {
                heap[count++] = 1;
            }
        }
        for (int i=count; i < heap.length; i++) {
            heap[i] = 0;
        }
    }

    private class Block {

            public int ptr;
            public int size;

            public Block(int ptr, int size) {
                this.ptr = ptr;
                this.size = size;
            }
    }
}


