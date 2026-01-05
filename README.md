# Event Planner

## Data Structures Used

| Feature | Data Structure | Reason |
|---------|----------------|--------|
| Guest list (source of truth) | LinkedList | Efficient insertion and iteration; maintains order of guests |
| Guest lookup | HashMap | O(1) lookup by name or ID |
| Venue selection | Sorting via List + comparator | Justifies selection of lowest-cost, smallest-capacity venue |
| Seating plan | Map<String, Queue<Guest>> + TreeMap<Integer, List<Guest>> | Groups guests by tag; TreeMap ensures ordered table numbers; Queue allows fair seating |
| Event tasks | Queue + Stack | FIFO execution for tasks; LIFO for undo functionality |

## Algorithms Used

- Venue selection: Sort valid venues by cost then capacity.
- Seating: Group guests by `groupTag`, seat each group using queue, fill tables sequentially.
- Task manager: FIFO queue for upcoming tasks, stack for completed tasks to allow undo.

## Big-O Complexity

| Operation | Complexity |
|-----------|------------|
| Adding a guest | O(1) amortized for map + O(1) for linked list add |
| Removing a guest | O(1) map lookup + O(n) linked list removal |
| Finding a guest | O(1) via map |
| Selecting a venue | O(n log n) sorting for selection |
| Generating seating | O(n) for grouping and seating all guests |
| Executing a task | O(1) |
| Undoing a task | O(1) |