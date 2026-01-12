# Event Planner Project

## Data Structures Used

| Feature | Data Structure | Reason |
|---------|----------------|--------|
| Guest list (master) | LinkedList | Maintains insertion order; allows sequential iteration |
| Guest lookup | HashMap | O(1) lookup by guest name, enabling efficient find/remove operations |
| Venue selection | List with sorting | Small number of venues; sorting by cost then capacity is a rational and simple approach |
| Seating plan | Map<String, Queue<Guest>> + TreeMap<Integer, List<Guest>> | Groups guests by groupTag (Queue for fairness); TreeMap ensures table order; sequentially fills tables |
| Event tasks | Queue + Stack | Queue ensures FIFO task execution; Stack allows rational undo behavior |

## Algorithms Used

- **Venue selection:** Linear scan of venues; sort valid venues by cost then smallest sufficient capacity.
- **Seating:** Guests grouped by `groupTag` and seated fairly using queues. Large groups spread across tables.
- **Task manager:** FIFO queue for upcoming tasks, LIFO stack for completed tasks to allow undo.

## Big-O Complexity

| Operation | Complexity |
|-----------|------------|
| Adding a guest | O(1) for map + O(1) amortized for linked list add |
| Removing a guest | O(1) map lookup + O(n) linked list removal |
| Finding a guest | O(1) via HashMap |
| Selecting a venue | O(n log n) for sorting |
| Generating seating | O(n) for grouping and seating all guests |
| Executing a task | O(1) |
| Undoing a task | O(1) |

## Rational Design Decisions

This project uses data structures rationally to meet functional requirements. The combination of a LinkedList and HashMap in `GuestListManager` allows maintaining insertion order while providing constant-time lookups. Seating groups are handled fairly using queues, and tasks are processed in a FIFO manner with undo capability provided by a stack. Venue selection uses sorting, which is sufficient given the small dataset and is easier to maintain. Each design choice balances clarity, performance, and correctness.