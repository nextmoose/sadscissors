# sadscissors

This is an assessment project.
I am coding to a provided interface.

## Usage
Execute `gradle clean check test pitest javadoc jar`.

Generated files will be in the build directory.

 * build/reports/checkstyle/main.html - results of static style checking
 * build/tests/index.html - results of unit testing
 * build/pitests/${TIMESTAMP}/index.html - results of mutation testing
 * build/docs/javadoc/index.html - javadoc documentation
 * build/libs/sillyscissors.jar - the generated artifact

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

#### Verbiage Regarding Empty Containers and Depleted Items  is Confusing
I am interpreting it as we should ignore items with fill factor zero.
This would be like the user consumed all 12 cans in the case and disposed of the cardboard case.

The purpose of the getItems method is obviously to prepare a shopping list.
If I am out running out of soda, then I should buy some more.
However, as implemented when the user has consumed all the soda, soda will be effectively forgotten.
There also does not seem to be a way for the user to tell the system that even though there is currently no soda in the fridge, the user wants soda on the next shopping list.

#### Fill Factor
The interface does not explicitly state what fill factor is, but it strongly suggests that it is an average.
I could add 1,000,000 mostly empty cases of soda (1 can each).
The fill factor for soda would be 0.0833.
Then `getItems(0.10)` would put soda on the grocery list even though I clearly have more than enough.

#### Failure to Use Typing
`getItems` returns an array of arrays.
The consumer of this interface must cast to use the results.
It would be much better practice to define the returned value in a class.

### Mutation Testing
This project uses mutation testing as a check on the quality of the unit tests.
This involved creating about 30 "mutants" of the source code and running the test suite
over the mutant.

A mutant is the source code with one simple change.
For example, the original source code contained a line like
```
if (fillFactor < 0) {
```
The mutant "changed the conditional boundary"
```
if (fillFactor <= 0) {
```

If the "mutant" survived the test suite then this would suggest I had not been careful enough writing tests.
Mutation testing is better than code coverage tools because code coverage only shows that the code had been exercised in test, but mutation testing exposes things like the dreaded "off by one" error.

#### Missing syncronized mutator
The code uses the syncronized keyword in an attempt to guard against inconsistent results
when multiple threads simultaneously access the fridge.
This probably works, but is absolutely untested.

Testing this would probably involve creating a fridge with an incredibly large number of items in it.
This would mean that removing items (or forgetting them) would take a non trivial amount of time.
If one thread removed items while another thread `getItems` and the operations overlapped, then we would expect inconsistencies.

I have chosen not to write such tests.
I believe that there is a good chance they would pass falsely because the JVM will for the most part schedule non-overlapping execution.

A hypothetical "synchronized mutator" would expose this.  This mutator would turn a block of code like
```
        synchronized (tuples) {
            tuples.removeIf(tuple -> tuple.getItemUUID() == itemUUID);
        }
```
into 
```
        /* not synchronized (tuples) */ {
            tuples.removeIf(tuple -> tuple.getItemUUID() == itemUUID);
        }
```