# Event Planner

# Event Planner

## Data Structures Used

Guest list: LinkedList – master guest list; keeps insertion order and allows iteration.  
Guest lookup: HashMap – allows O(1) lookup by name or ID.  
Venue selection: Sorting using List and Comparator – used to select the lowest-cost, smallest-capacity venue.  
Seating plan: Map<String, Queue<Guest>> for grouping guests by groupTag; TreeMap<Integer, List<Guest>> for ordered table numbers; Queue ensures fair seating.  
Event tasks: Queue for upcoming tasks (FIFO execution); Stack for completed tasks (LIFO undo).

## Algorithms Used

Venue selection: Filter valid venues by budget and guest count, then sort by cost ascending, then by capacity ascending.  
Seating: Group guests by groupTag, seat each group using queues, fill tables sequentially, large groups may span multiple tables.  
Task manager: Execute tasks in FIFO order using a queue, undo last task using a stack.

## Big-O Complexity

Adding a guest: O(1) amortized for map + O(1) for linked list insertion.  
Removing a guest: O(1) for map lookup + O(n) for linked list removal.  
Finding a guest: O(1) using HashMap.  
Selecting a venue: O(n log n) for sorting the valid venues.  
Generating seating: O(n) for grouping guests and assigning to tables.  
Executing a task: O(1) using queue.  
Undoing a task: O(1) using stack.