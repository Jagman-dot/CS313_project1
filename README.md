# CS313_project1

## 1 Objective

The purpose of this project is to learn a new BST data structure and use it to
solve an algorithmic problem. The following data structure is used as the default
implementation of a dictionary in the Library of Efficient of Data Structures and
Algorithms (LEDA) providing implementations of a variety of algorithms and
data structures, used in Java.

## 2 Treap Data Structure

### 2.1 Definition
A **treap** is a binary-search tree with a modified way of ordering nodes. As
usual, each node x in the tree has the following field:

    1. a key value x.key.
    2. a pointer to its parent x.p.
    3. a pointer to its left child, x.lef t.
    4. a pointer to its right child, x.right.
    
Additionally, a treap maintains a random number uniformly chosen from the
interval [0, 1], denoted x.priority. This will make all priorities distinct numbers.

The nodes of the treap are ordered to maintain two properties, simultaneously: Binary-Search Tree property and Min-Heap property. That is,
for every node u in the tree:

    • If v is the left child of u, then v.key < u.key.
    • if v is the right child of u, then v.key > u.key.
    • if v is a child of u, the u.priority < v.priority.
The first two define the binary-search tree properties, the last defines the
min-heap property.


### 2.2 Operations

A treap, like every other balanced binary-search tree, supports the standard
operations:

1. Modification

        • Insert
        • Delete
   
2. Queries

        • Search
        • Max
        • Min 
        • Successor
        • Predecessor
        
All the query operations are the same as those for any standard binarysearch tree. However, insertion and deletion have new subtleties in order
to maintain the min-heap property.

**Insert**(T, x): To insert a new key x into the tree, generate a random
priority y for x. Perform a standard BST search on the tree to locate
its place in the tree and create a new leaf node at the position where the
binary search for x ended. Now, while x is not the root of T and has
smaller priority than its parent x.p, perform tree rotation that reverses
the parent-child relationship between x and x.p.

**Delete**(T, x): To delete a node x from the tree T, we follow the following
cases:

    • if x is a leaf node, remove it.
    • if x has one child, make it the child of the parent of x (or make the child the new root, if x has no parent).
    • If x has two children, swap its position in the tree with the position of its immediate successor z in the sorted 
      order, resulting in one of the previous cases. In this final case, the swap may violate the heap-ordering 
      property for z, so additional rotations may need to be performed to restore this property.

## 3 Problem

### 3.1 Implementation

Implement the above data structure in Java. For the most part, it has the same
basic structure as the standard binary-search tree we have discussed in class.
The main differences occur in the node class with the addition of a new attribute,
x.priority, in the modification algorithms, **Insert**(T,x) and **Delete**(T,x).


### 3.2 Algorithmic Problem Solving

Using the treap, design an algorithm to solve the following problem:

    1. Problem Statement: Given an array A of n integers and an integer k,
    find the number of subarrays whose average is not less than k.

    Input: An array of integers, an integer n denoting the number of elements
    in the array, and an integer k denoting a target average.

    Output: The number of subarrays whose average is note less than k.
    
### 3.3 Example

    Input: 5,4, [5,2,4,5,1] 
    Output: 5

    The subarrays that can be constructed from [5,2,4,5,1] that have average 
    greater than or equal to 4 are [5], [4], [4,5], [5], [5,2,4,5].
    
## 4 Program Specifications

### 4.1 Functionality

Your program should read in a textfile from the command line and parse the
input as described above. The file will be structured where the every odd line
will (1,3,5, ...) will have two number representing n and k, respectively, and
every even line will contain sequence of number a1, a2, ..., an, representing the
values of the array. One problem is comprised of two lines. There will be
several problems given on in a textfile. Once you have the complete input for
one problem, your program should print to the console both the input and the
output as described in example 3.3, and continue until it as processed all the
problems on the textfile.

### 4.2 Java Libraries
 
In order to assign a random priority to every node, you will need to utilize the
following library:
https://docs.oracle.com/javase/8/docs/api/java/util/Random.html.

### 4.3 Submission
All files will be submitted in a zip file to blackboard.

