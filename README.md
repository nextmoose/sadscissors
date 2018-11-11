# sadscissors

This is an assessment project.
I am coding to a provided interface.

## Discussion
### Criticism of the Interface
#### Containers and Items
The commentary talks about items and containers, but it not clear how the code should reference containers.
There are no add/remove container methods.
I imagine that a container is something like a case of 12 soda cans and an item is like an individual soda can.

I am guessing that the user taking one soda out of a full case (fill factory = 100%), would be represented as
1. `handleItemAdded(100, "SODA", "SODA CASE #100-A", 1.00)` - this puts the case in the fridge
2. `handleItemRemove("SODA CASE #100")` - a kludge because the user did not really remove the case
3. `handleItemAdded(100, "SODA", "SODA_CASE #100-B", 0.9167` - again a kludge, but this tells the system that one can has been removed

#### Item Name
It is not clear what the itemName in the 'handleItemAdded' method refers to.
There are two obvious candidates:
1. The name of the item type (e.g., "SODA")
2. The name of the item (e.g., "MY SODA")

If it were the name of the item type, then I should add some validation.
Once `handleItemAdded(3235, "sadfadsfdas", "SODA", 100)` is executed,
we know that the name of the item type 3235 is "SODA" and we should
reject any attempt to add item type 3235 with any other name.

If it were the name of the specific item, then that validation is not needed.

Since itemName is not use anywhere else, I decided to use the easiest candidate - name of item.

However, that suggests that either the interface is incomplete (there should be methods that do use itemName) or itemName should be removed from the handleItemAdded method.

#### Verbiage Regarding Empty Containers is Confusing
I am interpreting it as we should ignore items with fill factor zero.