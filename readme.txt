This project create an autonomous gneration using swing. There are 3 kinds of item: Immovable, Moveable and Autonomous
It excute 100 times of autonomous movement, and then ask if user want to see another 100 moves.


The world is represented as a 2D array. The UI displays the world by drawing a grid of buttons. 
When the items move, the array is updated and the text of buttons is also changed to reflect the change.
I design an abstract super class Item to be the parent class of all the Immovable, Movable, Autonomous objects. 
Then I create three classes Immovable, Movable, Autonomous to extend the class.Inheritance is used here.
When one item is bumped, it will call the item's bumped method and then call other item's bumped method if necessary.