The contents of this folder are a bit different from normal.  I ran into an issue with the first project 
because the book doesn't cover an imporatant step that I had to figure out in a rather backwards fashion.  
There are two versions of the RentACar app.  RentACar is done the simplest close to the book way that I 
could figure out after many hours of not knowing what the problem was.  Basically the book leaves out 
needing the list to go in a listview and the custom list line item format to go into a separate layout file 
as a text view so the ArrayAdapter can process it.  The second version, RentAFrag2 was the first way I got 
the program to work which basically adds a few steps to use a fragment to show the data.  I don't fully 
understand all of that but it seems a bit like you are going through all the trouble of re writing the code 
that the listactivity and the arrayadapter already do.  It seems that the fragment way is the more modern 
and acceptable version but it is complicated in a way I'm going to avoid until I learn more.

Also, and this is tooting my own horn a bit, but I coded the Gadget app differently than the book did it's 
example.  The book example had 3 nearly identical but entirely separate xml layouts that showed a picture.  
I found this a waste of time and with a little research figured out I can pass data to the intent activity 
that opens up one of those layouts, so i passed on the position variable that we use in the switch statement 
to decide which page to open. I have a single layout which has a textview for a title, an imageview for the 
image, and a button to navigate to the product page.  That code has three arrays, string for title, integer 
for the image id, and string for the url and the indexes all line up with the switch statement so that the 
data for the chosen item is displayed and the button goes to the right place.