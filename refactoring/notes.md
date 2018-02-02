When you find you have to add a feature to a program,
and the program's code is not structured in a convenient
way to add the feature, first refactor the program to make
it easy to add the feature, then add the feature.

Before you start refactoring, check that you have a solid suit
of **tests**. These tests must be self-checking.

Any fool can write code that a computer can understand. Good
programmers write code that humans can understand.

In most cases a method should be on the object whose data it
uses.

It is a bad idea to do a switch based on an attribute of another object.
If you must use a switch statement, it should be on your own data, not on
someone else's.

**Rafactoring** a change made to the internal structure of software to make
it easier to understand and cheaper to modify without changing its
observable behaviour.

Two **hats** - when develop software you swap hats between adding function and refactoring.
When you add function, you shouldn't be changing existing code.
When you refactor, you make a point of not adding function; you only
restructure the code.

Most refactoring introduces more **indirection**  (e.g. breaking big
objects/methods into several smaller ones) into program. If indirection
isn't paying for itself then take it out (e.g. component expected to be
shared but is used in only one place)

# Smells In Code
## Duplicate Code
* the same expression in two methods of the same class => Extract Method
* the same expression in two sibling subclasses => Extract Method on both classes
then Pull Up Method
* similar code (but not the same) in tho sibling subclasses => Extract Method,
Form Template Method
* duplicate code in two unrelated classes => Extract Class and then use the new class
from both classes
* another possibility is that method belongs to only one of the classes and
should be invoked by the other class or method belongs to third class that
should be referred by both the original classes
## Long Method
* Good method naming is key to understand small methods, without switching contexts.
* whenever need comment, write method
* find parts of method that go together and make new method
* if many temporary variables Replace Temp with Query
* long parameter list => Introduce Parameter Object, Preserve Whole Object
* if above don't work => Replace Method with Method Object
## Large Class
* when class do too much it often shows up as too many instance variables
Choose variables going together (e.g having common prefix - depositAmount and depositCurrency)
and apply Extract Class, if component makes sense as subclass => Extract Subclass
* class with too much code => Extract Class, Extract Subclass
* useful trick to determine how clients use the class => Extract Interface
## Long Parameter List
* in OO programs parameter list tend to be much smaller than traditional programs
* Replace Parameter with Method, Preserve Whole Object, Introduce Parameter Object
* exception if don't want to create dependency from the called object to the parameter
object, in this cases sending parameter list is reasonable
## Divergent Change
Classes which have more than one responsibility (more than one reason to change).
To clean this up, identify everything that changes for particular reason and use
Extract Class to put them together.
KK: Looks like another name for Single Responsibility Principle