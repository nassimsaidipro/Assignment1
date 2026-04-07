ArrayLists are good for random access and LinkedLists are good for adding and removing at front or back

RecentList<T> always inserts at the front and remove at the back. LinkedList performs both of these operations in O(1) time because it maintains direct pointers to both the head and tail nodes.
ArrayList would require O(n) time for front insertion because every existing element need to shift one position to the right.
Since RecentList never needs random access, we don't need ArrayList's O(1) get(i) advantage.
Therefore, a LinkedList is more appropriate in this case, as it supports the required operations (front insertion and tail removal),
where it outperforms an ArrayList.

A2 COMPATIBILITY: YES
