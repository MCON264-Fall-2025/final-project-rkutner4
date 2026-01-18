# Event Planner Mini Project

This project is a console-based Java application designed to demonstrate the practical use of core data structures studied throughout the semester. The application models the planning of a small event by managing guests, selecting an appropriate venue, generating a seating chart, and organizing preparation tasks. The design choices made throughout this project are rational and intentional, focusing on correctness, efficiency, and testability.

## Data Structures Used

The master guest list is implemented using a LinkedList. This linked list serves as the source of truth for all invited guests. A LinkedList was chosen because it allows efficient insertion and removal of guests and preserves the order in which guests were added.

A HashMap is used alongside the LinkedList to provide fast lookup of guests by name. This ensures that finding a guest is efficient while still maintaining a single authoritative list of guests.

Venue selection uses sorting on a List of Venue objects. After filtering venues based on budget and capacity constraints, the remaining venues are sorted by cost and then by capacity to determine the best fit.

The seating planner uses a Map<String, Queue<Guest>> to group guests by their groupTag (such as family, friends, or coworkers). Queues ensure that guests are seated fairly within each group. A TreeMap<Integer, List<Guest>> is used to store table assignments so that tables are maintained in sorted order.

Task management uses a Queue to store upcoming tasks, ensuring tasks are executed in FIFO order. A Stack is used to store completed tasks, allowing undo functionality using LIFO behavior.

## Algorithms Used

Venue selection follows a filtering and sorting algorithm. Venues are filtered by budget and guest count, then sorted by cost in ascending order, with capacity used as a tie-breaker. This approach is rational because it guarantees the most cost-effective venue that still meets event requirements.

The seating algorithm groups guests by shared attributes and assigns them to tables one table at a time. Large groups may span multiple tables, but table capacity constraints are always respected.

Task execution uses a queue-based algorithm to process tasks in the order they were added, while undo operations use a stack-based algorithm to reverse the most recent action.

## Big-O Complexity

Adding a guest runs in O(1) time for both the LinkedList insertion and HashMap update. Removing a guest requires O(1) lookup in the HashMap and O(n) time to remove from the LinkedList. Finding a guest runs in O(1) time using the HashMap.

Selecting a venue runs in O(n log n) time due to sorting. Generating seating runs in O(n) time since each guest is processed once. Executing and undoing tasks both run in O(1) time.

Overall, the project demonstrates a rational and effective application of data structures to solve a realistic problem while remaining fully testable and compliant with autograding requirements.